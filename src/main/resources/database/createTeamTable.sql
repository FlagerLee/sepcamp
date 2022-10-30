CREATE DATABASE IF NOT EXISTS SEPCAMP;

USE SEPCAMP;

CREATE TABLE IF NOT EXISTS SEPCAMP_TEAM
(
    TEAM_ID     INTEGER         NOT NULL PRIMARY KEY COMMENT '小队ID',
    TEAM_NAME   CHARACTER(20)   NOT NULL COMMENT '小队名字',
    MEMBER_1    VARCHAR(50)     COMMENT '成员1（邮箱）',
    MEMBER_2    VARCHAR(50)     COMMENT '成员2（邮箱）',
    MEMBER_3    VARCHAR(50)     COMMENT '成员3（邮箱）',
    LEADER      SMALLINT        NOT NULL COMMENT '队长',
    PROJECT_ID  INTEGER         COMMENT '项目ID',
    TERM        CHARACTER(5)    NOT NULL COMMENT '学期'
)
CHARACTER SET = utf8
COMMENT 'sepcamp小队表';