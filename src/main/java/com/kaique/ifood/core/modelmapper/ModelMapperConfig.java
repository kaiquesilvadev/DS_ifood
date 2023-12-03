package com.kaique.ifood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kaique.ifood.dto.request.EnderecoDtoRequest;
import com.kaique.ifood.dto.responce.ItemPedidoDtoResponce;
import com.kaique.ifood.entities.Endereco;
import com.kaique.ifood.entities.ItemPedido;



/*
 * Como o ModelMapper não é uma propriedade do Spring, é preciso criar essa classe
 * para que seja feita uma instância de ModelMapper como um bean do Spring assim
 * que o projeto iniciar.
 */
@Configuration
public class ModelMapperConfig {


	@Bean
	public ModelMapper modelMapper() {
		
		ModelMapper modelMapper = new ModelMapper();
		
		/*
		 * Esse método é chamado para que não de erro no cascade de pedido, quando for salvar o pedido
		 * o modelMapper vai ter um id setado no pedido.id, porém como existe o cascate no list de itens
		 * vai tentar salvar o id também em itemPedido.id e vai dar erro, por causa do nome da variável
		 * sendo assim é preciso pular com o skip o setId
		 */
		modelMapper.createTypeMap(ItemPedidoDtoResponce.class, ItemPedido.class)
			.addMappings(mapper -> mapper.skip(ItemPedido::setId));
		
		/*
		 * Método para que o nome do estado possa ser mapeado em EnderecoDTO corretamente.
		 * Como o getNome do estado não está na Entidade Endereco pois esta aninhado em endereco.cidade.estado
		 * é preciso fazer esse mapeamento para que o modelMapper busque as informações. 
		 */
		TypeMap<Endereco, EnderecoDtoRequest> mapeiaEnderecoParaCidadeResumoDTO = modelMapper.createTypeMap(Endereco.class, EnderecoDtoRequest.class);
		
		/*
		 * Aqui são passadas expressões lambda para o .addMapping(sourceGetter, destinationSetter)
		 * 
		 * sourceGetter é uma interface funcional que recebe um parâmetro, nesse caso vai buscar o valor que é o nome do estado.
		 * 
		 * destinationSetter é uma interface funcional que recebe dois parâmetros (destino, valor)
		 * no caso o destino é o enderecoDTO, o valor a ser atribuido é o nomeEstado que vai receber o valor nome retornado no sourceGetter.
		 */
		
		return modelMapper;
	}
	
}