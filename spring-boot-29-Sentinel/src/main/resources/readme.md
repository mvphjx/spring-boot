阿里断路器测试程序

DegradeRule  为熔断配置



  - 超时处理   由于缺省连续5次、平均访问时间达到阈值，进行服务降级。
            配置窗口期X 秒，时间过后恢复。
  
        http://localhost:7951/timeOut
  
  - 异常处理
   
        http://localhost:7951/exception



###问题


        https://github.com/alibaba/Sentinel/issues/631
