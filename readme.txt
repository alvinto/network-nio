java nio使用demo
Channel
Buffer
Selecter

使用NIO读取文件的三个步骤
1、从FileInputStream获取Channel 通道
2、创建Buffer
3、将数据从Channel读到Buffer中

使用NIO写入文件的三个步骤
1、从FileOutputStream中获取通道
2、创建缓冲区并在其中放入一些数据
3、写入缓冲区