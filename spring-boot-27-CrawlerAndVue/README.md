#项目实战

##背景
    
   爬取  最强战队游戏的图片视频，保存到文本；并且使用vue进行展示
  
使用数据库记录对应队伍的爬取状态（每个图像、视频）

前端页面提供列表查询、指定队伍视频图片展示




   
   
   
##使用的技术：
   
   
   ###webmagic
   
爬虫库

   ###vue 
      
展示页面实现

Vue生命周期可以总共分为8个阶段：

beforeCreate（创建前）,
created（创建后）,
beforeMount(载入前),
mounted（载入后）,
beforeUpdate（更新前）,
updated（更新后）,
beforeDestroy（销毁前）,
destroyed（销毁后）

    
### SpringData
   
实现后台持久化（@OneToMany自动管理外键）

### Mybatis
   
实现列表分页查询   

### HttpClient

实现文件下载   
需优化http线程池 创建、分配、回收逻辑


   
      
      
      
   
##测试地址
爬虫接口

      localhost:8080/crawler/start
      
前端页面

      localhost:9526      


   
