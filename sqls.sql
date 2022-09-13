// DATABASES e TABELAS

create database bantads-auth;

create table tb_usuarios(
	id_usu UUID primary key,
	login_usu character varying(50),
	nome_usu character varying(100),
	perfil_usu character varying(10),
	senha_usu character varying(255)
);

insert into tb_usuarios(id_usu, login_usu, nome_usu, perfil_usu, senha_usu)
values ('d049f90b-f9e7-42db-8c33-55cbd79f6194', 'admin', 'admin', 'ADMIN', 'admin');

create database bantads-cliente;

create table tb_enderecos_clientes(
	id_endereco UUID primary key,
	cep_endereco character varying(10),
	cidade_endereco character varying(50),
	complemento_endereco character varying(50),
	estado_endereco character varying(2),
	logradouro_endereco character varying(50),
	numero_endereco integer,
	tipo_endereco character varying(10)
);

create table tb_clientes(
	id_cli UUID primary key,
	cpf_cli character varying(15),
	email_cli character varying(50),
	nome_cli character varying(100),
	salario_cli numeric(19,2),
	id_endereco_cli UUID,
	constraint fk_id_endereco
		foreign key(id_endereco_cli)
			references tb_enderecos_clientes(id_endereco)
);

create database bantads-conta;

create table tb_contas(
	numero_conta bigint primary key,
	aprovada_conta boolean,
	data_criacao_conta timestamp without time zone,
	data_hora_aprovacao_reprovacao_conta timestamp without time zone,
	id_cliente_conta uuid,
	id_gerente_conta uuid,
	limite_conta numeric(19,2),
	motivo_reprovacao_conta character varying(255),
	nome_cliente_conta character varying(100),
	saldo_conta numeric(19,2)
);

create table tb_historico_movimentacoes(
	id_his uuid primary key,
	data_hora_his timestamp without time zone,
	nome_cliente_destino_his character varying(100),
	nome_cliente_origem_his character varying(100),
	numero_conta_destino_his bigint,
	numero_conta_origem_his bigint,
	saldo_apos_movimentacao_his numeric(19,2),
	tipo_his character varying(15),
	valor_movimentacao_his numeric(19,2),
	constraint fk_numero_conta_origem
		foreign key(numero_conta_origem_his)
			references tb_contas(numero_conta)
);

create database bantads-conta-read;

create table tb_contas_read(
	numero_conta bigint primary key,
	aprovada_conta boolean,
	cep_cliente_conta character varying(10),
	cidade_cliente_conta character varying(50),
	complemento_endereco_cliente_conta character varying(50),
	cpf_cliente_conta character varying(15),
	data_criacao_conta timestamp without time zone,
	data_hora_aprovacao_reprovacao_conta timestamp without time zone,
	email_cliente_conta character varying(50),
	estado_cliente_conta character varying(2),
	id_cliente_conta uuid,
	id_gerente_conta uuid,
	limite_conta numeric(19,2),
	logradouro_cliente_conta character varying(50),
	motivo_reprovacao_conta character varying(255),
	nome_cliente_conta character varying(100),
	salario_cliente_conta numeric(19,2),
	saldo_conta numeric(19,2),
	tipo_endereco_cliente_conta character varying(10)
);

create database bantads-gerente;

create table tb_gerentes(
	id_gerente UUID primary key,
	cpf_gerente character varying(15),
	email_gerente character varying(50),
	nome_gerente character varying(100),
	quantidade_contas_gerente integer
);


create table tb_gerentes_contas(
	id_gerente_conta uuid primary key,
	id_gerente uuid,
	numero_conta bigint,
	saldo_conta numeric(19,2),
	constraint fk_id_gerente
		foreign key(id_gerente)
			references tb_gerentes(id_gerente)
);
