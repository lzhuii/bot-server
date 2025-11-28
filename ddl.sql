drop table if exists bot.guild;
create table if not exists bot.guild
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
comment on table bot.guild is '频道表';
comment on column bot.guild.id is '频道 id';
comment on column bot.guild.name is '频道名称';
comment on column bot.guild.icon is '频道头像地址';
comment on column bot.guild.owner_id is '创建人用户 id';
comment on column bot.guild.owner is '当前人是否是创建人';
comment on column bot.guild.member_count is '成员数';
comment on column bot.guild.max_members is '最大成员数量';
comment on column bot.guild.description is '频道描述';
comment on column bot.guild.joined_at is '加入时间';
comment on column bot.guild.created_at is '创建时间';
comment on column bot.guild.updated_at is '更新时间';


drop table if exists bot.channel;
create table if not exists bot.channel
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
comment on column bot.channel.id is '子频道 id';
comment on column bot.channel.guild_id is '频道 id';
comment on column bot.channel.name is '子频道名';
comment on column bot.channel.type is '子频道类型 ChannelType';
comment on column bot.channel.sub_type is '子频道子类型 ChannelSubType';
comment on column bot.channel.position is '排序值，具体请参考 有关 position 的说明';
comment on column bot.channel.parent_id is '所属分组 id，仅对子频道有效，对 子频道分组（ChannelType=4） 无效';
comment on column bot.channel.owner_id is '创建人 id';
comment on column bot.channel.private_type is '子频道私密类型 PrivateType';
comment on column bot.channel.speak_permission is '子频道发言权限 SpeakPermission';
comment on column bot.channel.application_id is '用于标识应用子频道应用类型，仅应用子频道时会使用该字段，具体定义请参考 应用子频道的应用类型';
comment on column bot.channel.permissions is '用户拥有的子频道权限 Permissions';
comment on column bot.channel.created_at is '创建时间';
comment on column bot.channel.updated_at is '更新时间';