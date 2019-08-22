alter table BPS_ORDER add constraint FK_BPS_ORDER_ON_MANAGER foreign key (MANAGER_ID) references SEC_USER(ID);
create index IDX_BPS_ORDER_ON_MANAGER on BPS_ORDER (MANAGER_ID);
