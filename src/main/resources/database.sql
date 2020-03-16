create database gerenjiuyi default character set utf8mb4;
use gerenjiuyi;

create table mstorage
(
  msno int auto_increment
    primary key,
  msname varchar(20) not null,
  dosage varchar(20) null,
  msquantity int default '0' not null,
  shelflife int default '0' not null,
  exp int default '0' not null
)ENGINE=InnoDB
;

create table vinfo
(
  vno int auto_increment
    primary key,
  time int not null,
  hospital varchar(20) null,
  dname varchar(20) null,
  total double default '0' not null
)ENGINE=InnoDB
;

create table minfo
(
  mno int auto_increment
    primary key,
  vno int not null,
  mname varchar(20) not null,
  mprice double default '0' not null,
  mquantity int default '0' not null,
  mtotalprice double default '0' not null,
  constraint minfo_vinfo_vno_fk
  foreign key (vno) references vinfo (vno) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB
;

create index minfo_vinfo_vno_fk
  on minfo (vno)
;

create table pinfo
(
  pno int auto_increment
    primary key,
  vno int not null,
  pname varchar(20) not null,
  pprice double default '0' not null,
  pquantity int default '0' not null,
  ptotalprice double default '0' not null,
  constraint pinfo_vinfo_vno_fk
  foreign key (vno) references vinfo (vno) ON DELETE CASCADE ON UPDATE CASCADE
)ENGINE=InnoDB
;

create index pinfo_vinfo_vno_fk
  on pinfo (vno)
;

