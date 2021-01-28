# [Redis 订阅发布功能]

## 功能：简单聊天室


### 技术栈
- redis订阅分布功能
- spring-data-redis（<2 jedis实现，新版使用LettuceConnection实现）
- websocket

### 功能场景
- 创建聊天室（订阅主题）
- 浏览器，通过websocket加入聊天室
  
      localhost:8080/websocket.html
  

- 发布消息 
  
      //通过redis指令实现
      publish  TopicChat   "this is test message"
- 浏览器，接收、展示消息

