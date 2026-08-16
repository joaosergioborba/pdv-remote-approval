
SET TIME ZONE 'America/Cuiaba';
SET client_encoding = 'UTF8';
CREATE TYPE status_solicitacao AS ENUM ('em_aberto', 'aprovado', 'cancelado', 'expirado', 'negado');
CREATE TYPE nivel_usuario AS ENUM ('operador', 'fiscal', 'gerente');


CREATE TABLE IF NOT EXISTS usuarios (
  id                        INTEGER GENERATEd ALWAYS AS IDENTITY PRIMARY KEY,
  nome                      TEXT          NOT NULL,
  matricula                 TEXT          NOT NULL UNIQUE,
  senha                     TEXT          NOT NULL,
  nivel       nivel_usuario NOT NULL, 
  ativo       BOOLEAN       NOT NULL DEFAULT TRUE,
  created_at   TIMESTAMPTZ    NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at   TIMESTAMPTZ

);

CREATE TABLE IF NOT EXISTS equipamentos (
  id                       INTEGER GENERATEd ALWAYS AS IDENTITY PRIMARY KEY,
  numero_pdv               INTEGER UNIQUE,
  ip_pdv                   INTEGER NOT NULL,
  porta_comunicacao_agente INTEGER NOT NULL,
  ativo                    BOOLEAN NOT NULL DEFAULT TRUE, 
  created_at               TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at               TIMESTAMPTZ

);

CREATE TABLE IF NOT EXISTS regras_horarios_aprovacao_automatica (
  id              INTEGER   GENERATEd ALWAYS AS IDENTITY PRIMARY KEY,
  dia_unico       DATE,
  hora_inicio     TIME      NOT NULL,
  hora_fim        TIME      NOT NULL,
  segunda_active  BOOLEAN   NOT NULL DEFAULT FALSE ,
  terca_active    BOOLEAN   NOT NULL DEFAULT FALSE, 
  quarta_active   BOOLEAN   NOT NULL DEFAULT FALSE, 
  quinta_active   BOOLEAN   NOT NULL DEFAULT FALSE, 
  sexta_active    BOOLEAN   NOT NULL DEFAULT FALSE, 
  sabado_active   BOOLEAN   NOT NULL DEFAULT FALSE,
  domingo_active  BOOLEAN   NOT NULL DEFAULT FALSE

);


CREATE TABLE IF NOT EXISTS acoes_de_liberacao (
  id              integer GENERATEd ALWAYS AS IDENTITY PRIMARY KEY,
  acao            text NOT NULL,
  tecla_associada varchar(1)
);

CREATE TABLE IF NOT EXISTS liberacao_automatica (
  id                                     integer GENERATEd ALWAYS AS IDENTITY PRIMARY KEY,
  id_regra_horarios_aprovacao_automatica integer REFERENCES regras_horarios_aprovacao_automatica(id) ON DELETE RESTRICT,
  id_acoes_de_liberacao                  INTEGER REFERENCES acoes_de_liberacao(id) ON DELETE RESTRICT,
  ativo                                  boolean NOT NULL DEFAULT TRUE

);

CREATE TABLE IF NOT EXISTS solicitacoes (
  id integer GENERATEd ALWAYS AS IDENTITY PRIMARY KEY,
  id_acao_de_liberacao integer NOT NULL REFERENCES acoes_de_liberacao(id) ON DELETE RESTRICT,
  id_motivo_solicitacao text,
  id_usuario_solicitante integer NOT NULL REFERENCES usuarios(id), 
  id_usuario_aprovador integer REFERENCES usuarios(id) ON DELETE RESTRICT,
  status status_solicitacao, 
  nome_cliente text,
  codigo_cliente integer,
  created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMPTZ,
  numero_pdv integer REFERENCES equipamentos(numero_pdv) ON DELETE RESTRICT,
  item_cancelado integer,
  limite_excedido float
);


CREATE OR REPLACE FUNCTION atualizar_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER trigger_usuarios_updated_at
BEFORE UPDATE ON usuarios
FOR EACH ROW
EXECUTE FUNCTION atualizar_updated_at();

CREATE TRIGGER trigger_equipamentos_updated_at
BEFORE UPDATE ON equipamentos
FOR EACH ROW
EXECUTE FUNCTION atualizar_updated_at();

CREATE TRIGGER trigger_solicitacoes_updated_at
BEFORE UPDATE ON solicitacoes
FOR EACH ROW
EXECUTE FUNCTION atualizar_updated_at();