# bot-server

一个Java实现的QQ平台AI机器人

## 一、前置条件

### 1.1 注册QQ机器人

[https://q.qq.com/#/app/bot](https://q.qq.com/#/app/bot)

### 1.2 域名

> QQ官方机器人平台需要设置Webhook回调地址，要求域名备案并且通过HTTPS访问。

1. 云服务器
2. 注册域名
3. 域名备案
4. 申请SSL证书

### 1.4 运行环境

1. Java 21
2. Redis
3. Nginx

### 1.3 大模型API服务

这里我用的是阿里云百炼大模型服务平台，API兼容OpenAI格式，其他的只要兼容OpenAI的API都可以使用。

## 二、运行

1. 将代码导入IDEA
2. 添加环境变量
    - BOT_APP_ID=从QQ平台获取
    - BOT_APP_SECRET=从QQ平台获取
    - OPENAI_API_KEY=从百炼平台获取
3. 运行`BotApplication`

## 三、部署

1. 运行`mvn clean package`
2. 将`target/bot-server-1.0.0.jar`和`application.yml`复制到服务器上
3. 创建启动脚本`start.sh`
    ```shell
    export BOT_APP_ID=从QQ平台获取
    export BOT_APP_SECRET=从QQ平台获取
    export OPENAI_API_KEY=从百炼平台获取
    
    nohup java -jar ~/bot/bot-server-1.0.0.jar > /dev/null 2>&1 &
    ```
4. 启动`sh start.sh`