drop table if exists bot.guilds;
create table if not exists bot.guilds
(
    id           varchar(50)  not null primary key,
    name         varchar(15)  not null,
    icon         varchar(100) not null,
    owner_id     varchar(50)  not null,
    owner        boolean      not null,
    member_count int          not null,
    max_members  int          not null,
    description  varchar(300) not null,
    joined_at    timestamp    not null,
    created_at   timestamp    not null,
    updated_at   timestamp    not null
);
comment on table bot.guilds is '频道表';
comment on column bot.guilds.id is '频道ID';
comment on column bot.guilds.name is '频道名称';
comment on column bot.guilds.icon is '频道头像地址';
comment on column bot.guilds.owner_id is '创建人用户ID';
comment on column bot.guilds.owner is '当前人是否是创建人';
comment on column bot.guilds.member_count is '成员数';
comment on column bot.guilds.max_members is '最大成员数量';
comment on column bot.guilds.description is '频道描述';