
#模块划分

###biz     
  - 服务器业务层

###service  
  - ares serveice相关组件：包括服务管理、服务实现
        
  - 服务注册、服务发现功能，使用spring注解实现。
  
    - 优点：降低实现复杂度；
    - 缺点：必须依赖spring框架，某些操作不够灵活，比如动态新增服务会比较复杂。
    
  - 服务隔离机制 熔断机制  快速失败 
          
        
        
        
        
        
###CLI
  - 客户端接口
  
###SPI
  - 标准服务接口定义
  
  接口设计：组合模式、继承模式
  
  
##其他

    http://localhost:7955/swagger-ui.html  
    
    http://localhost:7955/hystrix
  
  
        

                  
        
