CREATE TABLE `account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(64) NOT NULL UNIQUE COMMENT 'ログイン用ユーザーネーム',
  `hashed_password` varchar(64) NOT NULL COMMENT 'ハッシュ化されたパスワード',
  `display_name` varchar(32) NOT NULL COMMENT '表示名',
  `status` varchar(16) NOT NULL COMMENT '状態',
  `modified` timestamp NOT NULL COMMENT '更新日時',
  `created` timestamp NOT NULL COMMENT '登録日時',
  PRIMARY KEY (`id`),
  KEY `username_hashed_password` (`username`, `hashed_password`),
  KEY `status` (`status`),
  KEY `created` (`created`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='アカウント'
