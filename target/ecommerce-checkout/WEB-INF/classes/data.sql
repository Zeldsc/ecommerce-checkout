INSERT IGNORE produto (descricao, preco, quantidade) VALUES ('TV 32', '1199', '50');
INSERT IGNORE produto (descricao, preco, quantidade) VALUES ('Maquina de Lavar 12Kg', '1250', '50');
INSERT IGNORE produto (descricao, preco, quantidade) VALUES ('Smartfone Otmos', '1120', '40');

INSERT IGNORE INTO promocao (codigo, disponivel, fim, inicio, tipo, valor) VALUES ('TESTE', '1000', '2020-08-30', '2020-07-11', '0', '10');
INSERT IGNORE INTO promocao (codigo, disponivel, fim, inicio, tipo, valor) VALUES ('TESTE1', '1000', '2020-08-30', '2020-07-11', '1', '300');

INSERT IGNORE INTO transportadora ( nome) VALUES ('Super Entrega');

INSERT IGNORE INTO frete (descricao, cep_destino, cep_origem, prazo_entrega, preco, transportadora) VALUES ('Normal', '65074115', '65074115', '20', '45', '1');
INSERT IGNORE INTO frete (descricao, cep_destino, cep_origem, prazo_entrega, preco, transportadora) VALUES ('Expressa', '65074115', '65074115', '7', '80', '1');

