create table TB_INITIALIZER_GAME(
  ID_GAME			numeric(100) not null,
  DECK_ID  varchar(100) not null,
  REMAINING numeric(100) not null,
  DT_REGISTER  TIMESTAMP not null,
primary key (ID_GAME));
create sequence sq_tb_initializer_game;