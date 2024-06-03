INSERT INTO tb_estado (id, nome) VALUES (1, 'São Paulo');
INSERT INTO tb_estado (id, nome) VALUES (2, 'Rio de Janeiro');

INSERT INTO tb_cidade (id, nome, estado_id) VALUES (1, 'São Paulo', 1);
INSERT INTO tb_Cidade (id, nome, estado_id) VALUES (1, 'São Paulo', 1);
INSERT INTO tb_cidade (id, nome, estado_id) VALUES (2, 'Rio de Janeiro', 2);

INSERT INTO tb_cozinha (id, nome) VALUES (1, 'Italiana');
INSERT INTO tb_cozinha (id, nome) VALUES (2, 'Japonesa');

INSERT INTO tb_restaurante (id, nome, taxa_frete, data_cadastro, data_atualizacao, ativo, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id)
VALUES (1, 'Restaurante Italiano', 5.0, '2023-01-01 12:00:00', '2023-01-01 12:00:00', TRUE, 1, '12345-678', 'Rua A', '100', 'Apto 101', 'Centro', 1);
INSERT INTO tb_restaurante (id, nome, taxa_frete, data_cadastro, data_atualizacao, ativo, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id)
VALUES (2, 'Restaurante Japonês', 7.0, '2023-02-01 12:00:00', '2023-02-01 12:00:00', TRUE, 2, '87654-321', 'Avenida B', '200', 'Bloco B', 'Jardim', 2);


INSERT INTO tb_endereco (endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id)
VALUES ('12345-678', 'Rua A', '100', 'Apto 101', 'Centro', 1);
INSERT INTO tb_endereco (endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id)
VALUES ('87654-321', 'Avenida B', '200', 'Bloco B', 'Jardim', 2);

INSERT INTO tb_forma_pagamento (id, descricao) VALUES (1, 'Cartão de Crédito');
INSERT INTO tb_forma_pagamento (id, descricao) VALUES (2, 'Dinheiro');

INSERT INTO tb_grupo (id, nome) VALUES (1, 'Administradores');
INSERT INTO tb_grupo (id, nome) VALUES (2, 'Usuários');

INSERT INTO tb_permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_USUARIO', 'Permissão para consultar usuários');
INSERT INTO tb_permissao (id, nome, descricao) VALUES (2, 'EDITAR_USUARIO', 'Permissão para editar usuários');

INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (1, 1);
INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (1, 2);
INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (2, 1);

INSERT INTO tb_produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (1, 'Pizza Margherita', 'Pizza tradicional com molho de tomate e manjericão', 29.90, TRUE, 1);
INSERT INTO tb_produto (id, nome, descricao, preco, ativo, restaurante_id) VALUES (2, 'Sushi de Salmão', 'Sushi tradicional japonês com salmão fresco', 15.90, TRUE, 2);


INSERT INTO tb_Restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1);
INSERT INTO tb_Restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 2);
INSERT INTO tb_Restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (2, 1);

INSERT INTO tb_usuario (id, nome, email, senha, data_cadastro) VALUES (1, 'João Silva', 'joao.silva@example.com', 'senha123', '2023-01-01 12:00:00');
INSERT INTO tb_usuario (id, nome, email, senha, data_cadastro) VALUES (2, 'Maria Souza', 'maria.souza@example.com', 'senha456', '2023-02-01 12:00:00');

INSERT INTO tb_usuario_grupo (usuario_id, grupo_id) VALUES (1, 1);
INSERT INTO tb_usuario_grupo (usuario_id, grupo_id) VALUES (2, 2);

INSERT INTO tb_pedido (id, codigo, subTotal, taxaFrete, valorTotal, statusPedido, dataCriacao, dataConfirmacao, dataEntrega, dataCancelamento, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, forma_pagamento_id, usuario_id, restaurante_id)
VALUES (1, 'PED12345', 50.0, 5.0, 55.0, 'CRIADO', '2023-03-01 12:00:00', NULL, NULL, NULL, '12345-678', 'Rua A', '100', 'Apto 101', 'Centro', 1, 1, 1, 1);
INSERT INTO tb_pedido (id, codigo, subTotal, taxaFrete, valorTotal, statusPedido, dataCriacao, dataConfirmacao, dataEntrega, dataCancelamento, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, forma_pagamento_id, usuario_id, restaurante_id)
VALUES (2, 'PED67890', 30.0, 7.0, 37.0, 'CRIADO', '2023-03-02 12:00:00', NULL, NULL, NULL, '87654-321', 'Avenida B', '200', 'Bloco B', 'Jardim', 2, 2, 2, 2);

INSERT INTO tb_itens_pedidos (id, quantidade, precoUnitario, precoTotal, observacao, pedido_id)
VALUES (1, 2, 25.0, 50.0, 'Sem cebola', 1);
INSERT INTO tb_itens_pedidos (id, quantidade, precoUnitario, precoTotal, observacao, pedido_id)
VALUES (2, 1, 30.0, 30.0, 'Extra wasabi', 2);
