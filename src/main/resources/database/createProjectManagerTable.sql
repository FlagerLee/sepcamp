CREATE TABLE IF NOT EXISTS SEPCAMP_PROJECTMANAGER
(
    Manager_Id      INTEGER             NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '项目阶段管理ID',
    Project_Id      INTEGER             NOT NULL COMMENT '项目ID',
    Phase_Type      INTEGER             NOT NULL COMMENT '项目对应阶段',
    Is_Submitted    BOOLEAN             NOT NULL DEFAULT False COMMENT '是否已经提交',
    Describe_Text   VARCHAR(1000)       NOT NULL COMMENT '阶段提交描述',
    Last_UpdateTime TIMESTAMP           NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '阶段提交最后更新时间',
    Text_Answer     VARCHAR(1000)       COMMENT '阶段提交文本',
    File_Answer     VARCHAR(200)        COMMENT '阶段提交文件'
    )
    CHARACTER SET = utf8
    COMMENT 'sepcamp项目阶段管理表';