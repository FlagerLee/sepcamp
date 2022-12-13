CREATE TABLE IF NOT EXISTS SEPCAMP_HOMEWORK
(
    Homework_Id     TIMESTAMP           NOT NULL COMMENT '作业序号,用作业创建时间标识',
    User_Id         INTEGER             NOT NULL COMMENT '用户ID',
    Is_Submitted    BOOLEAN             NOT NULL DEFAULT False COMMENT '是否已经提交',
    Describe_Text   VARCHAR(1000)       NOT NULL COMMENT '作业描述',
    Last_UpdateTime TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '作业最后更新时间',
    Start_Time      TIMESTAMP           NOT NULL COMMENT '作业开始时间',
    End_Time        TIMESTAMP           NOT NULL COMMENT '作业结束时间',
    Text_Answer     VARCHAR(1000)       COMMENT '作业文本',
    File_Answer     VARCHAR(200)        COMMENT '作业文件',
    TERM            CHARACTER(5)        NOT NULL COMMENT '学期',
    Homework_Type   INTEGER             NOT NULL COMMENT '作业类型：随堂作业/课后作业',
    Score           INTEGER             COMMENT '分数',
    PRIMARY KEY(Homework_Id,User_Id)
)
    CHARACTER SET = utf8
    COMMENT 'sepcamp作业表';