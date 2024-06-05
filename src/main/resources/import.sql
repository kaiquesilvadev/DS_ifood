INSERT INTO tb_estado (nome) VALUES ('São Paulo');
INSERT INTO tb_estado (nome) VALUES ('Rio de Janeiro');

INSERT INTO tb_cidade (nome, estado_id) VALUES ('São Paulo', 1);
INSERT INTO tb_cidade (nome, estado_id) VALUES ('Rio de Janeiro', 2);

INSERT INTO tb_cozinha (nome) VALUES ('Italiana');
INSERT INTO tb_cozinha (nome) VALUES ('Japonesa');

INSERT INTO tb_restaurante (nome, taxa_frete, data_cadastro, data_atualizacao, ativo, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) VALUES ('Restaurante Italiano', 5.0, '2023-01-01 12:00:00', '2023-01-01 12:00:00', TRUE, 1, '12345-678', 'Rua A', '100', 'Apto 101', 'Centro', 1);
INSERT INTO tb_restaurante (nome, taxa_frete, data_cadastro, data_atualizacao, ativo, cozinha_id, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id) VALUES ('Restaurante Japonês', 7.0, '2023-02-01 12:00:00', '2023-02-01 12:00:00', TRUE, 2, '87654-321', 'Avenida B', '200', 'Bloco B', 'Jardim', 2);

INSERT INTO tb_forma_pagamento (descricao) VALUES ('Cartão de Crédito');
INSERT INTO tb_forma_pagamento (descricao) VALUES ('Dinheiro');

INSERT INTO tb_grupo (nome) VALUES ('Administradores');
INSERT INTO tb_grupo (nome) VALUES ('Usuários');

INSERT INTO tb_permissao (nome, descricao) VALUES ('CONSULTAR_USUARIO', 'Permissão para consultar usuários');
INSERT INTO tb_permissao (nome, descricao) VALUES ('EDITAR_USUARIO', 'Permissão para editar usuários');

INSERT INTO tb_usuario (nome, email, senha, data_cadastro) VALUES ('João Silva', 'joao.silva@example.com', 'senha123', '2023-01-01 12:00:00');
INSERT INTO tb_usuario (nome, email, senha, data_cadastro) VALUES ('Maria Souza', 'maria.souza@example.com', 'senha456', '2023-02-01 12:00:00');

INSERT INTO tb_usuario_grupo (usuario_id, grupo_id) VALUES (1, 1);
INSERT INTO tb_usuario_grupo (usuario_id, grupo_id) VALUES (2, 2);

INSERT INTO tb_pedido (codigo, sub_total, taxa_frete, valor_total, status_pedido, data_criacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, forma_pagamento_id, usuario_id, restaurante_id)VALUES ('ITL_PEDIDO_1', 50.00, 5.00, 55.00, 'CONFIRMADO', NOW(), '12345-678', 'Rua das Flores', '123', 'Apartamento 101', 'Centro', 1, 1, 1);
INSERT INTO tb_pedido (codigo, sub_total, taxa_frete, valor_total, status_pedido, data_criacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, forma_pagamento_id, usuario_id, restaurante_id)VALUES ('ITL_PEDIDO_2', 40.00, 3.00, 43.00, 'ENTREGUE', NOW(), '54321-876', 'Avenida dos Anjos', '456', 'Casa', 'Bela Vista', 2, 2, 1);
INSERT INTO tb_pedido (codigo, sub_total, taxa_frete, valor_total, status_pedido, data_criacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, forma_pagamento_id, usuario_id, restaurante_id)VALUES ('ITL_PEDIDO_3', 30.00, 4.00, 34.00, 'CANCELADO', NOW(), '98765-432', 'Praça da Liberdade', '789', 'Sala 201', 'Liberdade', 1, 2, 1);
INSERT INTO tb_pedido (codigo, sub_total, taxa_frete, valor_total, status_pedido, data_criacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, forma_pagamento_id, usuario_id, restaurante_id)VALUES ('JPN_PEDIDO_1', 60.00, 6.00, 66.00, 'CONFIRMADO', NOW(), '13579-246', 'Rua Sakura', '987', 'Bloco B', 'Harajuku', 2, 1, 2);
INSERT INTO tb_pedido (codigo, sub_total, taxa_frete, valor_total, status_pedido, data_criacao, endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, forma_pagamento_id, usuario_id, restaurante_id)VALUES ('JPN_PEDIDO_2', 70.00, 7.00, 77.00, 'ENTREGUE', NOW(), '64237-810', 'Avenida Fuji', '345', 'Casa 2', 'Shibuya', 1, 2, 2);

INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Pizza Margherita', 'Traditional Neapolitan pizza', 25.00, TRUE, 1);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Pasta Carbonara', 'Italian pasta with egg, cheese, and pancetta', 20.00, TRUE, 1);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Tiramisu', 'Italian dessert made of ladyfingers, coffee, and mascarpone', 10.00, TRUE, 1);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Lasagna Bolognese', 'Layered pasta dish with meat sauce', 22.00, TRUE, 1);
INSERT INTO tb_produto (nome, descricao, preco, ativo, restaurante_id) VALUES ('Caprese Salad', 'Italian salad with tomatoes, mozzarella, basil, and olive oil', 15.00, TRUE, 1);


INSERT INTO tb_itens_pedido (quantidade, preco_unitario, observacao, pedido_id, produto_id) VALUES (2, 25.0, 'Sem cebola', 1, 1);
INSERT INTO tb_itens_pedido (quantidade, preco_unitario, observacao, pedido_id, produto_id) VALUES (1, 30.0, 'Extra wasabi', 2, 2);
