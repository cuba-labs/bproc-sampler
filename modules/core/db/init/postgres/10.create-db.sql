-- begin BPS_ORDER
create table BPS_ORDER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NUMBER_ varchar(255),
    DATE_ date,
    MANAGER_ID uuid,
    STATE varchar(255),
    AMOUNT decimal(19, 2),
    --
    primary key (ID)
)^
-- end BPS_ORDER
