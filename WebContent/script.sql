drop table if exists PUBLIC.ITEM cascade;
drop table if exists PUBLIC.LANCAMENTO cascade;
drop sequence PUBLIC.SEQ_ITEM;
drop sequence PUBLIC.SEQ_LANCAMENTO;
alter table lancamentoitem 
    drop constraint FKlancamentoitem_lancamento;
alter table lancamentoitem 
    drop constraint FKlancamentoitem_item;
drop table if exists lancamentoitem cascade;
create sequence PUBLIC.SEQ_ITEM start 1 increment 1;
create sequence PUBLIC.SEQ_LANCAMENTO start 1 increment 1;
create table PUBLIC.ITEM (
    OID int8 not null,
    DESCRICAO varchar(255) not null,
    VALOR float8 not null,
    primary key (OID)
);
create table PUBLIC.LANCAMENTO (
    OID int8 not null,
    DT_FINAL timestamp not null,
    DT_INICIAL timestamp not null,
    OBSERVACAO varchar(1000) not null,
    primary key (OID)
);
create table lancamentoitem (
    oid_lancamento int8 not null,
    oid_item int8 not null
);
alter table lancamentoitem 
    add constraint FKlancamentoitem_item
    foreign key (oid_item) 
    references PUBLIC.ITEM;
alter table lancamentoitem 
    add constraint FKlancamentoitem_lancamento
    foreign key (oid_lancamento) 
    references PUBLIC.LANCAMENTO;
