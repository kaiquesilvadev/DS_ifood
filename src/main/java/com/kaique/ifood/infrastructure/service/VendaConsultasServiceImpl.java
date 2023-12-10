package com.kaique.ifood.infrastructure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.kaique.ifood.dto.request.filtros.VendaDiariaFiltro;
import com.kaique.ifood.dto.responce.VendaDiaria;
import com.kaique.ifood.entities.Pedido;
import com.kaique.ifood.enuns.StatusPedido;
import com.kaique.ifood.services.VendaConsultasService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CompoundSelection;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

/*
Anotado como Repository porque é um componente que o spring traduz algumas exceptions do JPA para
exceptions de persistencia do spring.
*/
@Repository
public class VendaConsultasServiceImpl implements VendaConsultasService {

	/*
	 * Consultas com criteria API
	 */
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFiltro vendaDiariaFiltro, String timeOffSet) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<VendaDiaria> query = builder.createQuery(VendaDiaria.class);
		Root<Pedido> root = query.from(Pedido.class);
		List<Predicate> predicates = new ArrayList<>();

		/*
		 * Cria uma expressão para criar uma função de banco de dados no Java
		 */
		var funcaoDateSQLConvertTZ = builder.function("convert_tz", Date.class, root.get("dataCriacao"),
				builder.literal("+00:00"), builder.literal(timeOffSet));

		var funcaoDateSQLDataCriacao = builder.function("date", Date.class, funcaoDateSQLConvertTZ);

		/*
		 * Criando o sql referente a esse em Criteria API select
		 * date(convert_tz(p.data_criacao, '+00:00', '-03:00')) as data_criacao,
		 * count(p.id) as total_vendas, sum(p.valor_total) as total_faturado from pedido
		 * p group by date(convert_tz(p.data_criacao, '+00:00', '-03:00'));
		 * 
		 * O builder do criteria vai fazer com que quando seja retornado um
		 * VendaDiariaDTO o construtor da classe seja chamado, por isso tem
		 * o @AllArgsConstructor do lombok na classe o construtor precisa estar nesta
		 * mesma ordem (dataCriacao, totalVendas, totalFaturado
		 */
		CompoundSelection<VendaDiaria> consulta = builder.construct(VendaDiaria.class, funcaoDateSQLDataCriacao,
				builder.count(root.get("id")), builder.sum(root.get("valorTotal")));

		// Passando apenas o restaurante o spring entende e busca um igual pelo id
		if (vendaDiariaFiltro.getRestauranteId() != null) {
			predicates.add(builder.equal(root.get("restaurante"), vendaDiariaFiltro.getRestauranteId()));
		}

		if (vendaDiariaFiltro.getDataInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), vendaDiariaFiltro.getDataInicio()));
		}

		if (vendaDiariaFiltro.getDataFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), vendaDiariaFiltro.getDataFim()));
		}

		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

		query.select(consulta);
		query.where(predicates.toArray(new Predicate[0])); // TODO: ver como melhorar essa conversão
		query.groupBy(funcaoDateSQLDataCriacao);

		return manager.createQuery(query).getResultList();

	}
}