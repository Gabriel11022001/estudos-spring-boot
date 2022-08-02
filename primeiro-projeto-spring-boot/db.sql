CREATE DATABASE db_estudos_spring_boot;

USE db_estudos_spring_boot;

CREATE TABLE tbl_clientes(
	cliente_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    cliente_nome VARCHAR(255) NOT NULL,
    cliente_telefone VARCHAR(255) NOT NULL,
    cliente_email VARCHAR(255) NOT NULL,
    cliente_rg VARCHAR(13) NOT NULL,
    cliente_cpf VARCHAR(14) NOT NULL
);
CREATE TABLE tbl_produtos(
	produto_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    produto_nome VARCHAR(255) NOT NULL,
    produto_descricao TEXT NOT NULL,
    produto_quantidade_unidades_em_estoque INT NOT NULL,
    produto_preco DOUBLE NOT NULL
);
CREATE TABLE tbl_pedidos(
	pedido_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    pedido_data_cadastro DATETIME NOT NULL,
    pedido_valor_total DOUBLE NOT NULL,
    pedido_data_previsao_entrega DATE NOT NULL,
    cliente_id INT NOT NULL,
    CONSTRAINT FOREIGN KEY(cliente_id) REFERENCES tbl_clientes(cliente_id)
);
SELECT * FROM tbl_clientes;
SELECT * FROM tbl_pedidos;
SELECT * FROM tbl_produtos;
select * from tbl_itens;

INSERT INTO tbl_pedidos(pedido_data_cadastro, pedido_previsao_entrega, pedido_valor_total, cliente_cliente_id, cliente_id)
VALUES(date(now()), now(), 3200.98, 2, 2);

delete from tbl_pedidos where pedido_id = 1;
delete from tbl_pedidos where pedido_id = 2;
delete from tbl_pedidos where pedido_id = 3;
delete from tbl_pedidos where pedido_id = 4;
delete from tbl_pedidos where pedido_id = 5;
delete from tbl_pedidos where pedido_id = 6;
delete from tbl_pedidos where pedido_id = 7;
