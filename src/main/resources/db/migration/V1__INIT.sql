CREATE TABLE IF NOT EXISTS `tb_account`
(
    `id`        INTEGER PRIMARY KEY auto_increment,
    `user_name` VARCHAR(100),
    `age`       INTEGER,
    `birthday`  DATETIME
);

INSERT INTO tb_account(id, user_name, age, birthday)
VALUES (1, '张三', 18, now()),
       (2, '李四', 19, now());