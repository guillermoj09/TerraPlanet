

CREATE TABLE public.rolusuario
(
    codigo bigint NOT NULL,
    descripcion character varying(20) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT rolusuario_pk PRIMARY KEY (codigo)
)
;

CREATE TABLE public.usuario
(
    username character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default",
    correo character varying(255) COLLATE pg_catalog."default",
    nombre character varying(255) COLLATE pg_catalog."default",
    telefono character varying(255) COLLATE pg_catalog."default",
    codigo_rolusuario bigint,
    CONSTRAINT usuario_pkey PRIMARY KEY (username),
    CONSTRAINT fk_usuario_codigo_rolusuario FOREIGN KEY (codigo_rolusuario)
        REFERENCES public.rolusuario (codigo) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT rolusuario_fk FOREIGN KEY (codigo_rolusuario)
        REFERENCES public.rolusuario (codigo) MATCH FULL
        ON UPDATE CASCADE
        ON DELETE SET NULL
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.usuario
    OWNER to postgres;
	
	
CREATE SEQUENCE public.secuenciausuarioestanque
    INCREMENT 1
    START 1
    MINVALUE 1	
	CACHE 1
;

INSERT INTO public.rolusuario(
	codigo, descripcion)
	VALUES (1, 'ADMINISTRADOR');
	
INSERT INTO public.rolusuario(
	codigo, descripcion)
	VALUES (2, 'USUARIO');


INSERT INTO public.usuario(
	username, password, correo, nombre, telefono, codigo_rolusuario)
	VALUES ('admin','12345', 'admin@gmail.com', 'Christopher ', '3232232', 1);	
	

		
		

