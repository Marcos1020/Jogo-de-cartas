create table TB_VENCEDORES(
  ID_RODADA			numeric(100) not null,
  VENCEDOR_DA_RODADA varchar(100) not null,
  PONTUAÇÃO_VENCEDOR numeric(100) not null,
  DT_REGISTER  TIMESTAMP not null
primary key (ID_RODADA));
create sequence sq_tb_vencedores;