CREATE TABLE usuarios(
	id BIGINT NOT NULL AUTO_INCREMENT,
	nombre_usuario VARCHAR(100) NOT NULL UNIQUE,
	contrasenia VARCHAR(255),
	correo_electronico VARCHAR(100) NOT NULL UNIQUE,
    fecha_creacion DATETIME NOT NULL,
    fecha_actualizacion DATETIME NOT NULL,
	PRIMARY KEY(id)
);