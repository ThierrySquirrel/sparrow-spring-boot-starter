# sparrow-spring-boot-starter

Sparrow Spring Book Edition

[中文](./README_zh_CN.md)

Support Function:
- [x] CreateTopic
- [x] DeleteTopic
- [x] GetTopic
- [x] GetAllTopic
- [x] SendAsyncMessage
- [x] ConsumerMessage

# CreateTopic:
 The Topic Used For Sending And Receiving Messages Is Indispensable    

# DeleteTopic:
 When You Delete A Topic, All Messages Under The Topic Will Be Deleted  
 
# GetTopic:
 According To The Subject Name, Get The Subject, Which Is Usually Used To Detect The Subject  
     
# GetAllTopic:
 Get All Created Topic  

# SendAsyncMessage:
 Send Message Asynchronously, Method Runs Successfully,Ensure The Normal Operation Of [SparrowServer](https://github.com/ThierrySquirrel/sparrow-server-spring-boot-starter) And The Message Must Be Sent Successfully                                                                                                                                                  
 Method Failed To Run, Message Not Sent.  

# ConsumerMessage:
 For Consuming Messages
  
## Quick Start

```xml
<!--Adding dependencies to pom. XML-->
        <dependency>
            <artifactId>sparrow-spring-boot-starter</artifactId>
            <groupId>com.github.thierrysquirrel</groupId>
            <version>2.0.1-RELEASE</version>
        </dependency>
``` 

 ```properties
 ## application.properties
 sparrow.sparrow-server-url=127.0.0.1:6060 #If You Need A Cluster 127.0.0.1:6060,127.0.0.1:6061,127.0.0.1:6062
 ```  

 # Start Sparrow
 ```java
 @SpringBootApplication
 public class SparrowApplication{
     public static void main(String[] args){
         SpringApplication.run(DemoApplication.class, args);
     }
    
 }
 ```  

# CreateTopic,DeleteTopic,GetTopic,GetAllTopic

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

# SendAsyncMessage

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

# ConsumerMessage

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