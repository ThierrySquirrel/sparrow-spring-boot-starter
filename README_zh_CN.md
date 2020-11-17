# sparrow-server-spring-boot-starter

Sparrow Spring Book Edition

[English](./README.md)

支持功能:
- [x] 创建主题
- [x] 删除主题
- [x] 得到主题
- [x] 得到所有主题
- [x] 发送异步消息
- [x] 消费消息

# 创建主题:
 用于收发消息的主题,是不可缺的  

# 删除主题:
 删除主题时,主题下的所有消息,都将删除  
 
# 得到主题:
 根据主题名称,得到主题,通常用于检测主题  
 
# 得到所有主题:
 得到所有创建过的主题

# 发送异步消息:
 异步发送消息,方法运行成功,保障[SparrowServer](https://github.com/ThierrySquirrel/sparrow-server-spring-boot-starter) 运行正常,消息一定发送成功  
 方法执行失败,消息不发送  

# 消费消息:
 用于消费消息  

## Quick Start

```xml
<!--在pom.xml中添加依赖-->
        <dependency>
            <artifactId>sparrow-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>2.0.3-RELEASE</version>
        </dependency>
``` 

 ```properties
 ## application.properties
 sparrow.sparrow-server-url=127.0.0.1:6060 #如果您需要集群 127.0.0.1:6060,127.0.0.1:6061,127.0.0.1:6062
 ```  

 # 启动Sparrow
 ```java
 @SpringBootApplication
 public class SparrowApplication{
     public static void main(String[] args){
         SpringApplication.run(DemoApplication.class, args);
     }
    
 }
 ```  

# 创建主题,删除主题,得到主题,得到所有主题

 ```java
@RestController
public class CreateTopic {
    @Resource
    private AdministrationTemplate administrationTemplate;
    
    @GetMapping("createBroadcastTopic")
    public ResponseDomain createBroadcastTopic()throws Exception{
        return administrationTemplate.createBroadcastTopic ("topic");
    }

    @GetMapping("createClusterTopic")
    public ResponseDomain createClusterTopic()throws Exception{
        return administrationTemplate.createClusterTopic ("topic");
    }

    @GetMapping("deleteTopic")
    public ResponseDomain deleteTopic()throws Exception{
        return administrationTemplate.deleteTopic ("topic");
    }

    @GetMapping("getTopic")
    public SparrowTopic getTopic()throws Exception{
        return administrationTemplate.getTopic ("topic");
    }
    
    @GetMapping("getAllTopic")
    public List<SparrowTopic> getAllTopic()throws Exception{
        return administrationTemplate.getAllTopic ();
    }

}
 ``` 

# 发送异步消息

 ```java
@RestController
public class SendMessage {

    @GetMapping("/sendAsyncMessageHello")
    @SparrowAsyncProducer("hello")
    public User sendMessage() {
        User user = new User ();
        user.setId (123);
        user.setName ("hello");
        return user;
    }

    @GetMapping("/sendAsyncMessageWorld")
    @SparrowAsyncProducer("world")
    public User sendAsyncMessage() {
        User user = new User ();
        user.setId (456);
        user.setName ("world");
        return user;
    }
}
    
 ```

# 消费消息

 ```java
@SparrowListener
public class ConsumerMessage {

    @MessageListener("hello")
    public void hello(User user) {
        System.out.println (user);
    }

    @MessageListener("world")
    public void world(User user) {
        System.out.println (user);
    }

}
 ```