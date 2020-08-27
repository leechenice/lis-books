create database listen_books charset utf8mb4;
use listen_books;
create table users(
    uid int primary key auto_increment comment '用户id',
    username varchar(64) not null unique comment '用户名',
    password char(64) not null comment '经过sha-256计算后的用户密码'
);
create table books(
    bid int primary key auto_increment comment '书籍id',
    uid int not null comment '上传用户id',
    title varchar(100) not null comment '书名'
);
create table sections (
    sid int primary key auto_increment comment '章节id',
    bid int not null comment '所属书籍id',
    name varchar(100) comment '章节名'
);
create table audios (
    aid int primary key auto_increment comment '音频id',
    uuid char(36) not null unique comment 'uuid',
    sid int not null comment '所属章节id',
    type varchar(20) comment '音频类型 audio/wmv audio/mp3',
    content longblob default null comment '音频内容'
);