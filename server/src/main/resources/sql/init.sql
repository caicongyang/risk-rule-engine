CREATE TABLE `risk_record`
(
    `id`               bigint  NOT NULL,
    `scene_code`       varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin        DEFAULT NULL,
    `request_code`     varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin        DEFAULT NULL,
    `request_json`     varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      DEFAULT NULL,
    `fire_rule_json`   varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      DEFAULT NULL,
    `result_json`      varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      DEFAULT NULL,
    `deleted`          tinyint NOT NULL                                             DEFAULT '0' COMMENT '是否删除 0-正常 1-已删除',
    `available`        tinyint NOT NULL                                             DEFAULT '1' COMMENT '是否可用，0-不可用，1-可用',
    `update_time`      datetime                                                     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time`      datetime                                                     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新用户编码',
    `create_user_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建用户编码',
    `update_username`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新用户名',
    `create_username`  varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建用户名',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;



CREATE TABLE `risk_rule_config`
(
    `id`                bigint  NOT NULL,
    `code`              varchar(20) COLLATE utf8mb4_bin                              DEFAULT NULL,
    `factor_code`       varchar(20) COLLATE utf8mb4_bin                              DEFAULT NULL,
    `operator`          varchar(20) COLLATE utf8mb4_bin                              DEFAULT NULL,
    `factor_code_value` varchar(128) COLLATE utf8mb4_bin                             DEFAULT NULL,
    `action_code`       varchar(20) COLLATE utf8mb4_bin                              DEFAULT NULL,
    `deleted`           tinyint NOT NULL                                             DEFAULT '0' COMMENT '是否删除 0-正常 1-已删除',
    `available`         tinyint NOT NULL                                             DEFAULT '1' COMMENT '是否可用，0-不可用，1-可用',
    `update_time`       datetime                                                     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_time`       datetime                                                     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_user_code`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新用户编码',
    `create_user_code`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建用户编码',
    `update_username`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新用户名',
    `create_username`   varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建用户名',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;