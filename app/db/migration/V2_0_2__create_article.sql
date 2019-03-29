CREATE TABLE `article` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `account_id` bigint(20) NOT NULL COMMENT 'アカウントID',
  `name` varchar(64) NOT NULL COMMENT '名前',
  `description` text NOT NULL COMMENT '詳細',
  `status` varchar(16) NOT NULL COMMENT '状態',
  `modified` timestamp NOT NULL COMMENT '更新日時',
  `created` timestamp NOT NULL COMMENT '登録日時',
  PRIMARY KEY (`id`),
  KEY `status` (`status`),
  KEY `created` (`created`),
  KEY `article_account_id_fk` (`account_id`),
  CONSTRAINT `article_account_id_fk` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='記事'
