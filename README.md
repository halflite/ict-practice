# Spring Boot based ToDo App.

## ToDoアプリ仕様

* 未完了のTodoをリストで表示できる
* 「タスク名」と「概要」を一つのTodoとして登録できる
* リストに表示された「タスク名」及び「概要」を更新できる
* Todoを完了させてリストから削除できる

## 環境

* Java 8
* Spring Boot
* Thymeleaf
* Maven
* MySQL

## 実行

1. 以下を最初にインストール

* JDK (Java SE 8 以上)
* Maven (3.6 以上)
* Vagrant (2.2.x)
* Virtualbox 5.2.22

Virtualbox以外はパスを通しておく

2. このプロジェクトをGitHubからクローンする

```
git clone https://github.com/halflite/todoboot.git
cd todoboot
```

以下、 `todoboot` ディレクトリをホームディレクトリと呼ぶ

3. MySQL/Redis環境を起動する

ホームディレクトリから以下を実行、Virtualbox上にMySQL/Redis環境が作成される

```
cd local/vagrant
vagrant up --provision
```

4. DBのマイグレーションを実行する

ホームディレクトリから以下を実行。DBにテーブルが作られる

```
cd app
mvn flyway:migrate
```

5. アプリケーションのコンパイル/実行

ホームディレクトリから以下を実行。

```
cd app
mvn clean package -Dmaven.antrun.skip=true
java -jar target/app-0.0.1-SNAPSHOT.jar
```

6. ブラザで確認

http://localhost:8080/ にアクセスする
画面上右上の鉛筆アイコンをクリックして、ユーザー登録/ログインの後、ToDoが作れるようになります。