-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    email character varying(254) COLLATE pg_catalog."default",
    age integer,
    created_at timestamp without time zone,
    CONSTRAINT "User_pkey" PRIMARY KEY (id)
)

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;

GRANT ALL ON TABLE public.users TO postgres WITH GRANT OPTION;