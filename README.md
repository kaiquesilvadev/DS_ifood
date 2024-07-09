# DS_ifood

Seja bem-vindo ao meu projeto DS_iFood! Sinta-se à vontade para explorar o repositório e mergulhar no incrível mundo do Spring Boot. Meu nome é Kaique e sou um entusiasta apaixonado por programação. Fico muito feliz em te ver por aqui.

## 1 Introdução sobre o projeto

Este projeto é um mini sistema baseado no iFood e tem como objetivo me ajudar  a aplicar todo o conhecimento que adquiri. Nele, utilizo as técnicas e tecnologias mais modernas e amplamente empregadas no mercado atual.

### técnicas e tecnologias

- 1.1  Spring e Injeção de Dependências
- 1.2  JPA e Hibernate
- 1.3  REST com Spring
- 1.4  Tratamento e modelagem de erros da API
- 1.5  Validações com Bean Validation
- 1.6  Explorando mais do JPA e Hibernate
- 1.8  Testes unitario
- 1.9  Boas práticas e técnicas para APIs
- 1.10 Modelagem avançada e implementação da API
- 1.11 Modelagem de projeções, pesquisas e relatórios
- 1.12 Upload e download de arquivos
- 1.13 E-mails transacionais e Domain Events
- 1.14 Documentação da API com OpenAPI, Swagger UI e SpringFox
- 1.15 Discoverability e HATEOAS A Glória do REST
- 1.16 Evoluindo e versionando a API
- 1.17 Logging
- 1.18 Segurança com Spring Security e OAuth2
- 1.19 OAuth2 avançado com JWT e controle de acesso

#### 1.1. Spring e Injeção de Dependências

Spring é um framework popular para desenvolvimento de aplicações Java. Uma de suas principais funcionalidades é a Injeção de Dependências (DI - Dependency Injection).

A DI é um padrão de design que promove a inversão de controle (IoC). Em vez de criar dependências diretamente, os objetos recebem essas dependências de uma entidade externa. No Spring, isso é gerenciado pelo contêiner de IoC.

##### 1.1.0 Os benefícios da DI incluem:

- Facilidade de Teste: Permite substituir dependências por mocks.
- Flexibilidade: Facilita a troca de implementações.
- Manutenção: Reduz o acoplamento, tornando o código mais modular.

#### 1.9 Boas práticas e técnicas para APIs**

@Transactional: Essa anotação do sprint (org.springframework.transaction.annotation.Transactional) faz com que seja aberta uma transação na base de dados sempre que um método que manipula dados for chamado, a implementação do Spring Data JPA que é a classe SimpleJpaRepository já tem as operações como save, delete, update marcadas com @Transactional, porém como boa prática é interessante marcar os métodos dos nossos services que manipulam dados na base também, assim garantimos que não haja inconstência nos dados caso dê algúm problema e uma das operações e precise ser feito um rollback.
Com a anotação, é aberta uma transação no momento da chamada do método no service por exemplo, e não apenas quando for para o repository, o spring data JPA gerencia essas transações e executa na base de dados de acordo com a fila de gerenciamento de transações que ele cria.
Ao final do método é feito um commit na base de dados.
Caso queira forçar a execução pelo jpa é preciso usar um flush no método, não é um commit, é apenas a forma de fazer com que as operações sejam executadas na base.

Desserialização é de JSON para Objeto, serialização é de Objeto para JSON.

Mixim é uma classe que possui propriedades de uma outra classe original onde podem ser colocadas anotações que queremos deixar separadas da classe original para que fique mais coeso, como por exemplo anotações Jackson que são referêntes à API em classes de domínio, o ideal é colocar as anotações Jackson em uma classe mixim. (RestauranteMixim)  

TimeZone é o fuso horário utilizado em cada região do globo terrestre de acordo com o UTC, a diferença desses horários é chamada de offset.
UTC é a referência de horário principal de onde todas as demais regiões do mundo se baseiam, coincide com o GMT.
GMT é a TimeZone da linha principal do mundo (Prime Meridian ou Meridiano de Greenwich), é onde se inicia o TimeZone com 0, países que ficam à esqueda (Oeste) tem seus TimeZone's diminuídos em horas e países à direita (Leste) tem seus TimeZone's acrescidos em horas.
![](/img/WorldTimes.png)
