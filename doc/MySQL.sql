USE biblioteca;

CREATE TABLE usuarios
(
	id INT AUTO_INCREMENT,
    acesso INT,
    usuario VARCHAR(24),
    senha VARCHAR(24),
    nome VARCHAR(40),
    sexo ENUM('M', 'F'),

	PRIMARY KEY(id)
);

CREATE TABLE editoras
(
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(32) NOT NULL,
	logradouro VARCHAR(38) NOT NULL,
	numero VARCHAR(5) NOT NULL,
	complemento VARCHAR(16),
	bairro VARCHAR(24) NOT NULL,
	cidade VARCHAR(24) NOT NULL,
	uf CHAR(2) NOT NULL,
	cep CHAR(8) NOT NULL,
	telefone VARCHAR(12),
	email VARCHAR(48),

	PRIMARY KEY (id)
);

CREATE TABLE autores
(
	id INT NOT NULL AUTO_INCREMENT,
	nome VARCHAR(16) NOT NULL,
	sobrenome VARCHAR(32) NOT NULL,
	nascionalidade VARCHAR(24) NOT NULL,

	PRIMARY KEY (id)
);
