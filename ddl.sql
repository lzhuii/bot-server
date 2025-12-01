set search_path = bot;

drop table if exists guild;
create table if not exists guild
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
comment on table guild is '频道表';
comment on column guild.id is '频道 id';
comment on column guild.name is '频道名称';
comment on column guild.icon is '频道头像地址';
comment on column guild.owner_id is '创建人用户 id';
comment on column guild.owner is '当前人是否是创建人';
comment on column guild.member_count is '成员数';
comment on column guild.max_members is '最大成员数量';
comment on column guild.description is '频道描述';
comment on column guild.joined_at is '加入时间';
comment on column guild.created_at is '创建时间';
comment on column guild.updated_at is '更新时间';


drop table if exists channel;
create table if not exists channel
(
    id               varchar(50) not null primary key,
    guild_id         varchar(50) not null,
    name             varchar(20) not null,
    type             int         not null,
    sub_type         int         not null,
    position         int         not null,
    parent_id        varchar(50) not null,
    owner_id         varchar(50) not null,
    private_type     int,
    speak_permission int,
    application_id   varchar(50),
    permissions      varchar(50),
    created_at       timestamp   not null,
    updated_at       timestamp   not null
);
comment on table channel is '子频道表';
comment on column channel.id is '子频道 id';
comment on column channel.guild_id is '频道 id';
comment on column channel.name is '子频道名';
comment on column channel.type is '子频道类型 ChannelType';
comment on column channel.sub_type is '子频道子类型 ChannelSubType';
comment on column channel.position is '排序值，具体请参考 有关 position 的说明';
comment on column channel.parent_id is '所属分组 id，仅对子频道有效，对 子频道分组（ChannelType=4） 无效';
comment on column channel.owner_id is '创建人 id';
comment on column channel.private_type is '子频道私密类型 PrivateType';
comment on column channel.speak_permission is '子频道发言权限 SpeakPermission';
comment on column channel.application_id is '用于标识应用子频道应用类型，仅应用子频道时会使用该字段，具体定义请参考 应用子频道的应用类型';
comment on column channel.permissions is '用户拥有的子频道权限 Permissions';
comment on column channel.created_at is '创建时间';
comment on column channel.updated_at is '更新时间';