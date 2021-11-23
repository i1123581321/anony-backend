# 数据设计

## 数据实体

### User

每个用户由一个 sha3-256字符串唯一标识，该字符串由用户提供的 secret 生成
用户表

* \_id
* sha256：标识字符串
* createAt：创建时间

### Thread

一条匿名串的元信息，包含标题，创建时间，更新时间，创建者

* \_id
* title：标题
* createAt：创建时间
* updateAt：更新时间
* user\_id

### Post

一个匿名串的回复，包含内容，创建时间，创建者，所属的串

* \_id
* content
* createAt
* anonymous\_id
* thread\_id
* user\_id

## 数据库使用

初步设计使用 mongodb，按照实体分为三个 collection

## rest endpoint

* `/user`：支持 GET/POST
* `/user/threads`：支持 GET
* `/user/posts`：支持 GET
* `/threads`：支持 GET/POST
* `/threads/{id}`：支持 GET
* `/threads/{id}/posts`：支持 GET/POST
* `/posts`：支持 GET
* `/posts/{id}`：支持 GET
