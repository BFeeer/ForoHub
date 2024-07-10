CREATE TABLE respuestas (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_tema BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    contenido TEXT NOT NULL,
    fecha_creacion DATETIME NOT NULL,
    fecha_actualizacion DATETIME NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY (id_tema) REFERENCES temas(id),
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);