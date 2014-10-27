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
