CREATE TABLE `risk_factor_db` (
  `id` bigint NOT NULL,
  `name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `factor_code` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '因子code',
  `type` tinyint(1) DEFAULT NULL COMMENT '1: 风控因子code\n2. action code',
  `script` text COLLATE utf8mb4_bin COMMENT '脚本',
  `script_type` tinyint(1) DEFAULT NULL COMMENT '1: grovvy\n2. js',
  `descript` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '因子说明',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_code` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新用户编码',
  `create_user_code` varchar(50) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建用户编码',
  `update_username` varchar(30) COLLATE utf8mb4_bin DEFAULT '' COMMENT '更新用户名',
  `create_username` varchar(30) COLLATE utf8mb4_bin DEFAULT '' COMMENT '创建用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;


CREATE TABLE `risk_record` (
  `id` bigint NOT NULL,
  `scene_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `request_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `request_json` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `fire_rule_json` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `result_json` varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-正常 1-已删除',
  `available` tinyint NOT NULL DEFAULT '1' COMMENT '是否可用，0-不可用，1-可用',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新用户编码',
  `create_user_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建用户编码',
  `update_username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新用户名',
  `create_username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;



CREATE TABLE `risk_rule_config` (
  `id` bigint NOT NULL,
  `scene_code` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '场景code',
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '规则code',
  `factor_code` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `operator` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `factor_code_value` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `action_code` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除 0-正常 1-已删除',
  `available` tinyint NOT NULL DEFAULT '1' COMMENT '是否可用，0-不可用，1-可用',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新用户编码',
  `create_user_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建用户编码',
  `update_username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '更新用户名',
  `create_username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '创建用户名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;