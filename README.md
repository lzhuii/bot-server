# bot
一个 Java 实现的 QQ AI 对话机器人
## 一、前置条件
### 1.1 注册 QQ 机器人
[https://q.qq.com/#/app/bot](https://q.qq.com/#/app/bot)
### 1.2 云服务器
准备一台有公网IP的服务器
### 1.3 域名
注册域名、备案、申请 SSL 证书。

QQ 官方机器人平台需要设置 Webhook 回调地址，要求域名备案并且通过 HTTPS 访问。
### 1.4 运行环境
1. Java 21
2. Redis
3. Nginx
### 1.5 大模型 API 服务
这里我用的是阿里云百炼大模型服务平台，API 兼容 OpenAI 格式，其他的只要兼容 OpenAI 的 API 都可以使用
## 二、安装步骤
1. 拉取代码
2. 执行 `mvn clean package` 打包
3. 将 **target/0.1.0/** 目录下所有的文件上传到服务器
```shell
lib/
application.yml
bot-0.1.0.jar
```
## 三、启动机器人
启动前将 `application.yml` 里相关的令牌修改为自己的配置
- 启动（bot-0.1.0.jar所在路径下执行）
```shell
java -Dloader.path=lib -jar bot-0.1.0.jar > /dev/null 2>&1 &
```
- 停止（任意路径执行）
```shell
ps -ef | grep "bot-0.1.0" | grep -v grep | awk '{print $2}' | xargs kill
```
