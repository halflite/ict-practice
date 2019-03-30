# Database/Source Migration

* Flyway/Doma-Genによるマイグレーションを行う

## 初期化

> mvn flyway:clean

## DBマイグレーション

> mvn flyway:migrate

## DBからEntity/Daoのマイグレーション

> mvn clean antrun:run@doma-gen
 