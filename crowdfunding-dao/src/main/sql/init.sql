
GRANT ALL PRIVILEGES ON crowdfunding.* TO 'crowdfunding'@'%' identified by "crowdfunding" WITH GRANT OPTION;

flush privileges;

drop table if exists user;
create table user(
    id bigint not null auto_increment,
    name varchar(100) not null,
    age int not null,
    gender varchar(5) not null,
    backup TEXT,
    create_time datetime not null,
    update_time timestamp not null,
    primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';



