create table TB_WINNERS(
  ID_ROUND			numeric(100) not null,
  ID_GAME			numeric(100) not null,
  ROUND_WINNER varchar(100) not null,
  SCORE_WINNER numeric(100) not null,
  DT_REGISTER  TIMESTAMP not null,
primary key (ID_ROUND));
create sequence sq_tb_winners;