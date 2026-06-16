          CREATE table if not exists users (

    id uuid not null,
    name varchar(100) not null,
    email varchar(100) not null,
    telefone varchar(15) not null,
    cpf varchar(11) not null,
    tema_escuro boolean not null,
    created_at timestamp not null,
    updated_at timestamp not null,


    constraint users_pk primary key (id)



)