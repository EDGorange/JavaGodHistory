### JVM 内存分析及其堆栈分析工具及其方法
> jvm生成dump文件及其堆栈文件操作
* 先找到PID
    - ps -ef | grep java
    - jps 
* 生成java heap dump文件和java 线程调用堆栈  
    - jmap -dump:format=b,file=/app/test.dump PID
    - jstack -l PID >> thread.log
* 常用dump分析工具和线程堆栈分析工具
    - [IBM jca](https://www.ibm.com/support/pages/ibm-thread-and-monitor-dump-analyzer-java-tmda)
    - VisualVM || [Eclipse mat](https://www.eclipse.org/downloads/download.php?file=/mat/1.14.0/rcp/MemoryAnalyzer-1.14.0.20230315-win32.win32.x86_64.zip)
* 常用分析jvm的方法工具教程文章摘录
    - [文章1](https://zhuanlan.zhihu.com/p/642932325?utm_id=0)
    - [文章2](https://zhuanlan.zhihu.com/p/491115741)
    - [文章3](https://www.yii666.com/article/171486.html)
