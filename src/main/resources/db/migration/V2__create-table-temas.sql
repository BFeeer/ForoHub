CREATE TABLE temas(
	id BIGINT NOT NULL AUTO_INCREMENT,
	titulo VARCHAR(100) NOT NULL UNIQUE,
	descripcion TEXT NOT NULL,
	id_usuario BIGINT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    fecha_actualizacion DATETIME NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);