## 不同 GC 和堆内存的总结
* 所有GC内存越大gc总时间越小，gc次数越小，但单次gc时间越长
* 针对当前的应用场景：总耗时：并行 ≈ CMS < 串行 < G1  单次耗时：串行 < 并行 ≈ CMS < G1
* SerialGC的场景: CPU利用率高，应用延时高，应用于小型应用, 堆内存很小，建议100MB以内
* ParallelGC的场景：高峰期有较好的性能，对应用停顿时间无高要求
* G1、CMS场景 应用延迟较低，内存大于6G请使用G1


| GC类型 | 堆内存 | gc 总时间 | young gc 次数 | young gc 总时间 | young gc 平均时间 | full gc 次数 | full gc 总时间 | full gc 平均时间 |
| ------ | ------ | --------- | ------------- | --------------- | ----------------- | ------------ | -------------- | ---------------- |
| 串行   | 1G     | 380 ms    | 13            | 330 ms          | 25.4 ms           | 1            | 50.0 ms        | 50.0 ms          |
| 串行   | 2G     | 370 ms    | 7             | 370 ms          | 52.9 ms           | 0            | 0              | 0                |
| 串行   | 4G     | 260 ms    | 3             | 260 ms          | 86.7 ms           | 0            | 0              | 0                |
| 并行   | 1G     | 410 ms    | 29            | 310 ms          | 10.7 ms           | 2            | 100 ms         | 50.0 ms          |
| 并行   | 2G     | 290 ms    | 14            | 290 ms          | 20.7 ms           | 0            | 0              | 0                |
| 并行   | 4G     | 190 ms    | 5             | 190 ms          | 38.0 ms           | 0            | 0              | 0                |
| CMS    | 1G     | 413 ms    | 18            | 327 ms          | 18.2 ms           | 2            | 86.4 ms        | 43.2 ms          |
| CMS    | 2G     | 357 ms    | 10            | 357 ms          | 35.7 ms           | 0            | 0              | 0                |
| CMS    | 4G     | 284 ms    | 6            | 284 ms          | 47.3 ms           | 0            | 0              | 0                |
| G1     | 1G     | 320 ms    | 72            | 320 ms          | 4.44 ms           | 0            | 0              | 0                |
| G1     | 2G     | 207 ms    | 20            | 207 ms          | 10.4 ms           | 0            | 0              | 0                |
| G1     | 4G     | 169 ms    | 9            | 169 ms          | 18.8 ms           | 0            | 0              | 0                |


https://gceasy.io/

```
java -Xmx1G -Xms1G -XX:+PrintGC -XX:+UseSerialGC GCLogAnalysis 		#serialgc
java -Xmx1G -Xms1G -XX:+PrintGC -XX:+UseParallelGC GCLogAnalysis 		#parallel
java -Xmx1G -Xms1G -XX:+PrintGC -XX:+UseConcMarkSweepGC GCLogAnalysis 	#CMS
java -Xmx1G -Xms1G -XX:+PrintGC -XX:+UseG1GC GCLogAnalysis			#G1
```