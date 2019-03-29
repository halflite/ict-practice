CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `provider_id` varchar(128) NOT NULL COMMENT '外部認証プロバイダーのアカウントID',
  `provider_type` varchar(16) NOT NULL COMMENT '外部認証プロバイダー種別',
  `name` varchar(32) NOT NULL COMMENT '表示名',
  `status` varchar(16) NOT NULL COMMENT '状態',
  `modified` timestamp NOT NULL COMMENT '更新日時',
  `created` timestamp NOT NULL COMMENT '登録日時',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_provider_id_provider_type` (`provider_id`,`provider_type`),
  KEY `status` (`status`),
  KEY `created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='アカウント'
