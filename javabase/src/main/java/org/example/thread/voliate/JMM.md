#Java内存模型
## 现代计算机模型
   由于cpu执行速度和内存IO速度存在几个数量级的差距，为了更高效的利用cpu时间，现代计算机模型大多使用了cpu内存缓存来减少内存IO的等待。
   我们现将内存中的数据读取到cpu缓冲中，再对缓存中的数据做操作。但是这样会引入新的问题，当多个CPU同时对同一数据进行操作时，写回主内存时，到底以谁的缓存数据为准呢？
   这时我们就引入了缓存一致性协议解决缓存不一致的问题。
## JMM
  不同的平台有对缓存一致性协议自己的解决方案。Java引入JMM（Java内存模型）的封装来屏蔽各操作系统和硬件之间差异，以实现跨平台一致性的内存访问结果。