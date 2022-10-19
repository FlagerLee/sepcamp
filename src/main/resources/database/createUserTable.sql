CREATE TABLE SEPCAMP_USER
(
    EMAIL       VARCHAR(50)     NOT NULL PRIMARY KEY COMMENT '邮箱（主键）',
    NAME        CHAR(12)        NOT NULL COMMENT '真实名字',
    STUDENT_ID  CHAR(15)        COMMENT '学号，助教老师不包含该项',
    CLASS_ID    SMALLINT        COMMENT '班级编号，助教老师不包含该项',
    TEAM_ID     INTEGER         COMMENT '所属小队ID，助教老师和尚未加入小队的学生不包含该项',
    USERNAME    VARCHAR(20)     NOT NULL COMMENT '用户名',
    PASSWORD    CHARACTER(32)   NOT NULL COMMENT '密码MD5',
    SALT        CHARACTER(20)   NOT NULL COMMENT '密码加盐',
    PRIORITY    SMALLINT        NOT NULL COMMENT '权限',
    AVATAR      CHARACTER(37)   NOT NULL COMMENT '头像文件名'
)
CHARACTER SET = utf8
COMMENT 'sepcamp用户表'