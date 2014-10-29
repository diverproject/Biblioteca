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

CREATE TABLE cdus
(
	id INT NOT NULL AUTO_INCREMENT,
	codigo VARCHAR(9) NOT NULL,
    tema VARCHAR(200) NOT NULL,

	PRIMARY KEY (id)
);

CREATE TABLE obras
(
	id INT NOT NULL AUTO_INCREMENT,
	titulo VARCHAR(64) NOT NULL,
	subtitulo VARCHAR(64),
	cidade VARCHAR(24) NOT NULL,
	edicao INT NOT NULL,
	ano CHAR(4) NOT NULL,
	cdu INT NOT NULL,
	editora INT NOT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (cdu) REFERENCES cdus(id),
	FOREIGN KEY (editora) REFERENCES editoras(id)
);

CREATE TABLE livros
(
	id INT NOT NULL AUTO_INCREMENT,
	paginas INT NOT NULL,
	tradutor VARCHAR(48),
	isbn CHAR(13) NOT NULL,

	PRIMARY KEY (id)
) ENGINE = MyISAM;

CREATE TABLE discos
(
	id INT NOT NULL AUTO_INCREMENT,
	tipo VARCHAR (3) DEFAULT 'CD' CHECK(tipo = 'CD' OR tipo = 'DVD'),

	PRIMARY KEY (id)
) ENGINE = MyISAM;

CREATE TABLE exemplares
(
	tombo INT NOT NULL AUTO_INCREMENT,
	obra INT NOT NULL,
	livro INT NOT NULL,
	disco INT,
	emprestado CHAR(1) NOT NULL DEFAULT '0' CHECK(tipo = '0' OR tipo = '1'),

	PRIMARY KEY (tombo),
	FOREIGN KEY (obra) REFERENCES obras(id),
	FOREIGN KEY (livro) REFERENCES livros(id),
	FOREIGN KEY (disco) REFERENCES discos(id)
) ENGINE = MyISAM;

CREATE TABLE reservas
(
	id INT NOT NULL AUTO_INCREMENT,
	aluno INT NOT NULL,
	obra INT NOT NULL,
	dia DATE NOT NULL,
	estado CHAR(1) NOT NULL DEFAULT '0',

	PRIMARY KEY (id, aluno, obra),
	FOREIGN KEY (aluno) REFERENCES alunos(id),
	FOREIGN KEY (obra) REFERENCES obras(id)
) ENGINE = MyISAM;

CREATE TABLE emprestimos
(
	id INT NOT NULL AUTO_INCREMENT,
	usuario INT NOT NULL,
	obra INT NOT NULL,
	retirada DATE NOT NULL,
	devolucao DATE,

	PRIMARY KEY (id, aluno, tombo),
	FOREIGN KEY (usuario) REFERENCES usuarios(id),
	FOREIGN KEY (obra) REFERENCES obras(id)
) ENGINE = MyISAM;
