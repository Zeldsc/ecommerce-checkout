CREATE DATABASE IF NOT EXISTS ecommerce_db;
use ecommerce_db;

CREATE TABLE IF NOT EXISTS `cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `logado` bit(1) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(12) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r1u8010d60num5vc8fp0q1j2a` (`cpf`),
  UNIQUE KEY `UK_cmxo70m08n43599l3h0h07cc6` (`email`)
);

LOCK TABLES `cliente` WRITE;
UNLOCK TABLES;

CREATE TABLE IF NOT EXISTS `endereco` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bairro` varchar(255) DEFAULT NULL,
  `cep` bigint(20) DEFAULT NULL,
  `cidade` varchar(255) DEFAULT NULL,
  `complemento` varchar(255) DEFAULT NULL,
  `logradouro` varchar(255) DEFAULT NULL,
  `numero` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `uf` varchar(255) DEFAULT NULL,
  `endereco` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnc7lrfa695jmph4gwlekee1t3` (`endereco`),
  CONSTRAINT `FKnc7lrfa695jmph4gwlekee1t3` FOREIGN KEY (`endereco`) REFERENCES `cliente` (`id`)
);

LOCK TABLES `endereco` WRITE;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS `produto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `banner` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) NOT NULL,
  `preco` double NOT NULL,
  `quantidade` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ;

LOCK TABLES `produto` WRITE;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS `promocao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(8) NOT NULL,
  `disponivel` bigint(20) NOT NULL,
  `fim` date NOT NULL,
  `inicio` date NOT NULL,
  `tipo` int(11) NOT NULL,
  `valor` double NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_s4fto4neaxmrx2oi635t74wr4` (`codigo`)
) ;
LOCK TABLES `promocao` WRITE;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS `transportadora` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

LOCK TABLES `transportadora` WRITE;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS `frete` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `cep_destino` bigint(20) DEFAULT NULL,
  `cep_origem` bigint(20) DEFAULT NULL,
  `prazo_entrega` bigint(20) NOT NULL,
  `preco` double NOT NULL,
  `transportadora` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKgw7qmtbitmumr5pdjcjk60u3x` (`cep_origem`,`cep_destino`,`transportadora`),
  KEY `FK3bdcqxveke24cfx3sm38fcwjr` (`transportadora`),
  CONSTRAINT `FK3bdcqxveke24cfx3sm38fcwjr` FOREIGN KEY (`transportadora`) REFERENCES `transportadora` (`id`)
) ;

LOCK TABLES `frete` WRITE;
UNLOCK TABLES;

CREATE TABLE IF NOT EXISTS `compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_inicio` date NOT NULL,
  `data_ultima_atualizacao` date DEFAULT NULL,
  `status` int(11) NOT NULL,
  `cliente` bigint(20) NOT NULL,
  `entrega` bigint(20) DEFAULT NULL,
  `frete` bigint(20) DEFAULT NULL,
  `promocao` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKehuvce9n1qjt8r5jd35kmy0o6` (`cliente`),
  KEY `FK3nhemocm7faq7yl0doao5et8m` (`entrega`),
  KEY `FK1y6nlh41lcl8sxvyjgtwk4qyo` (`frete`),
  KEY `FK1ga465my5q51ra5dy7vug1wyo` (`promocao`),
  CONSTRAINT `FK1ga465my5q51ra5dy7vug1wyo` FOREIGN KEY (`promocao`) REFERENCES `promocao` (`id`),
  CONSTRAINT `FK1y6nlh41lcl8sxvyjgtwk4qyo` FOREIGN KEY (`frete`) REFERENCES `frete` (`id`),
  CONSTRAINT `FK3nhemocm7faq7yl0doao5et8m` FOREIGN KEY (`entrega`) REFERENCES `endereco` (`id`),
  CONSTRAINT `FKehuvce9n1qjt8r5jd35kmy0o6` FOREIGN KEY (`cliente`) REFERENCES `cliente` (`id`)
) ;

LOCK TABLES `compra` WRITE;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS `item_compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `quantidade` bigint(20) NOT NULL,
  `produto` bigint(20) NOT NULL,
  `compra` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt8lm2eku5mrx0kq3uulukg0ca` (`produto`),
  KEY `FKa82ujqg741ehe5b8wmflraw1k` (`compra`),
  CONSTRAINT `FKa82ujqg741ehe5b8wmflraw1k` FOREIGN KEY (`compra`) REFERENCES `compra` (`id`),
  CONSTRAINT `FKt8lm2eku5mrx0kq3uulukg0ca` FOREIGN KEY (`produto`) REFERENCES `produto` (`id`)
) ;

LOCK TABLES `item_compra` WRITE;
UNLOCK TABLES;

CREATE TABLE IF NOT EXISTS `pagamento` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` date DEFAULT NULL,
  `hora` datetime DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `compra` bigint(20) DEFAULT NULL,
  `pagador` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq59hhscivbei37w2648cbff3t` (`compra`),
  KEY `FKavrc0yf1406ronfjr8c7ov4mc` (`pagador`),
  CONSTRAINT `FKavrc0yf1406ronfjr8c7ov4mc` FOREIGN KEY (`pagador`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FKq59hhscivbei37w2648cbff3t` FOREIGN KEY (`compra`) REFERENCES `compra` (`id`)
) ;

LOCK TABLES `pagamento` WRITE;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS `pagamento_boleto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo_barras` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKp06bx0oovurnldy3f43fm2g6q` FOREIGN KEY (`id`) REFERENCES `pagamento` (`id`)
) ;
LOCK TABLES `pagamento_boleto` WRITE;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS `pagamento_cartao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo_transacao` varchar(255) DEFAULT NULL,
  `cpf_proprietario` varchar(255) NOT NULL,
  `csv` bigint(20) NOT NULL,
  `data_confirmacao` date DEFAULT NULL,
  `hora_confirmacao` datetime DEFAULT NULL,
  `juros` double DEFAULT NULL,
  `nome_proprietario` varchar(255) NOT NULL,
  `numero` bigint(20) NOT NULL,
  `parcela` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKp3fstcdbe5eyyihf2wwkwl7np` FOREIGN KEY (`id`) REFERENCES `pagamento` (`id`)
) ;
LOCK TABLES `pagamento_cartao` WRITE;
UNLOCK TABLES;


CREATE TABLE IF NOT EXISTS `entrega` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `codigo_rastreio` varchar(255) DEFAULT NULL,
  `pedido` bigint(20) DEFAULT NULL,
  `compra` bigint(20) DEFAULT NULL,
  `destino` bigint(20) DEFAULT NULL,
  `frete` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK492cmd2e0rpxb14fo620j51ka` (`compra`),
  KEY `FKor1e9nsymoopd8rmhpuxco25y` (`destino`),
  KEY `FKaxgvhj6p599m8lktvrkucxwt3` (`frete`),
  CONSTRAINT `FK492cmd2e0rpxb14fo620j51ka` FOREIGN KEY (`compra`) REFERENCES `compra` (`id`),
  CONSTRAINT `FKaxgvhj6p599m8lktvrkucxwt3` FOREIGN KEY (`frete`) REFERENCES `frete` (`id`),
  CONSTRAINT `FKor1e9nsymoopd8rmhpuxco25y` FOREIGN KEY (`destino`) REFERENCES `endereco` (`id`)
) ;
LOCK TABLES `entrega` WRITE;
UNLOCK TABLES;


