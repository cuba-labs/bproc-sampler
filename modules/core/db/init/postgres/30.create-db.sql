-- Users groups

insert into BPROC_USER_GROUP
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAME, CODE, DESCRIPTION, TYPE_)
values ('51382ce2-2d3b-7c1e-adc0-8d51c0d3e959', 1, '2019-07-03 19:10:16', 'admin', '2019-07-03 19:10:16', null, null, null, 'All Users', 'all-users', null, 'a');

insert into BPROC_USER_GROUP
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAME, CODE, DESCRIPTION, TYPE_)
values ('0d260bbc-e7d0-5286-439a-11da7b6e71ef', 1, '2019-07-04 14:17:17', 'admin', '2019-07-04 14:17:17', null, null, null, 'Reviewers', 'reviewers', null, 'u');

-- Scheduled tasks

insert into SYS_SCHEDULED_TASK
(ID, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, DEFINED_BY, BEAN_NAME, METHOD_NAME, CLASS_NAME, SCRIPT_NAME, USER_NAME, IS_SINGLETON, IS_ACTIVE, PERIOD_, TIMEOUT, START_DATE, CRON, SCHEDULING_TYPE, TIME_FRAME, START_DELAY, PERMITTED_SERVERS, LOG_START, LOG_FINISH, LAST_START_TIME, LAST_START_SERVER, METHOD_PARAMS, DESCRIPTION)
values ('b8f589c8-74a7-43fd-9aaa-2660ae33c660', '2019-07-03 19:25:16', 'admin', '2019-07-03 19:25:18', 'admin', null, null, 'B', 'cuba_Emailer', 'processQueuedEmails', null, null, null, true, true, 5, null, null, null, 'P', null, null, null, null, null, null, null, '<?xml version="1.0" encoding="UTF-8"?>

<params/>
', null);

-- Users

insert into SEC_USER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, PASSWORD_ENCRYPTION, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, IP_MASK)
values ('d3e84021-7744-022a-550b-88aa36c088eb', 1, '2019-07-04 14:14:25', 'admin', '2019-07-04 14:14:25', null, null, null, 'alice', 'alice', '$2a$10$28Mf8I8IbmEbjvntpZI0R.GXRm5i0YMxW0tygiEvUDHYZEOC21jKi', 'bcrypt', 'Alice', null, null, null, null, 'alice@alice.alice', 'en', null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null);

insert into SEC_USER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, PASSWORD_ENCRYPTION, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, IP_MASK)
values ('b2dac644-0cfd-afaf-a194-02d54f199cbd', 1, '2019-07-04 14:14:32', 'admin', '2019-07-04 14:14:32', null, null, null, 'bob', 'bob', '$2a$10$SDC19jlD6lZgFvhM6FtOnOumzUEmenwCXwVxRYbiOX8xZHnxgRDvS', 'bcrypt', 'Bob', null, null, null, null, 'bob@bob.bob', 'en', null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null);

insert into SEC_USER
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, LOGIN, LOGIN_LC, PASSWORD, PASSWORD_ENCRYPTION, NAME, FIRST_NAME, LAST_NAME, MIDDLE_NAME, POSITION_, EMAIL, LANGUAGE_, TIME_ZONE, TIME_ZONE_AUTO, ACTIVE, CHANGE_PASSWORD_AT_LOGON, GROUP_ID, IP_MASK)
values ('51df9508-fbec-0099-0097-9665ba0a3fc4', 1, '2019-07-04 14:14:42', 'admin', '2019-07-04 14:14:42', null, null, null, 'john', 'john', '$2a$10$Iww.09wj8myDqZsDCyfJeO6y/Q62AHnJrO1YKkgzS3SlfazGu4l0G', 'bcrypt', 'John', null, null, null, null, 'john@john.john', 'en', null, null, true, false, '0fa2b1a5-1d68-4d69-9fbd-dff348347f93', null);

-- Roles

insert into SEC_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, NAME, LOC_NAME, DESCRIPTION, ROLE_TYPE, IS_DEFAULT_ROLE)
values ('ea2e955d-d1ec-7e5b-41d6-0ac305b7a1d9', 2, '2019-08-19 14:52:10', 'admin', '2019-08-19 14:52:37', 'admin', null, null, 'Manager', null, null, 0, null);

-- UserRole

-- Alice - Manager
insert into SEC_USER_ROLE
(ID, VERSION, CREATE_TS, CREATED_BY, UPDATE_TS, UPDATED_BY, DELETE_TS, DELETED_BY, USER_ID, ROLE_ID)
values ('f56d9c6c-7b07-3f65-a4db-dc9abd541b80', 1, '2019-08-19 14:52:28', 'admin', '2019-08-19 14:52:28', null, null, null, 'd3e84021-7744-022a-550b-88aa36c088eb', 'ea2e955d-d1ec-7e5b-41d6-0ac305b7a1d9');


