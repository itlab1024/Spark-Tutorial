# 什么是Structured Streaming?
他是Spark基于spark sql开发的流式数据处理引擎，基于微批的概念进行的处理，所谓微批也就是将流看为一个一个小的批进行处理。
# 编程模型
核心思想就是将结构化的流式数据视为一个内容不断被追加的表，也就是微批，看如下官网图：
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202210251425741.png)
最测是数据流，中间是无边界的表，数据流每次过来就会在中间无边界的表中追加。
查询的时候，再每个触发间隔的时候，比如一秒，新行就会附加到输入表，最终更新到结果表，结果表更新时，会输出到外部数据源。
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202210251429013.png)
如图可以看到，最上层Time是数据输入时间，Input层代表是输入的数据表，Result是查询输出表，Output是输出到外部数据源层。
总共就这四层。
可以通过workcount的例子来描述下，官方图如下：
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202210251432829.png)
灰色nc是socket接入此时数据会通过socket远远不断的到来，1，2，3秒分别来了不同的数据，查询层将其拆分按照单词数量生成结果表，
最后输出。
# 单词统计
写一个wordcount来再次理解下上面的流程。
需要引入stream依赖，build.sbt
```sbt
libraryDependencies += "org.apache.spark" % "spark-streaming_2.12" % "3.3.0"
```
单词统计代码
```scala
package com.itlab1024.spark.stream

import org.apache.spark.sql.SparkSession


object WordCount {
  def main(args: Array[String]): Unit = {
    // 构建sparkSession
    val spark = SparkSession.builder().appName("单词统计").master("local[*]")
      .getOrCreate()
    // 从流中读取一行行的输出
    val lines = spark.readStream.format("socket").option("host", "localhost")
      .option("port", 9999)
      .load()
    import spark.implicits._
    // 通过空格切割并分组
    val words = lines.as[String].flatMap(_.split(" "))
    val wordCounts = words.groupBy("value").count()
    // 设置输出模式，complte，输出到控制台console
    val query = wordCounts.writeStream
      .outputMode("complete")
      .format("console")
      .start()

    query.awaitTermination()
  }
}

```
打开控制台创建socket
```shell
nc -lk 9999
```
输入后回车，多次输出查看控制台打印结果。
可以看到类似如下的日志
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202210251457209.png)
上图是我第一次输出的"hello spark"触发的计算，会被处理为如图的结果表。该结果更新后，就输出到了控制台。
# Event-Time(事件时间)和Late Data（延时数据）
event-time是嵌入到数据本身的时间，比如json:{"ts":1111,"value":1},这里的ts就是事件事件。
延时数据，实际情况中，可能12:00:00的数据，在12:00:02才到来，这是很常见的，对于这些数据spark提供了窗口、水位线等方案来解决。
# 输入源
输入源主要包含文件源、Kafka、Socket、Rate、Rate Per Micro-Batch等，接下来来学习下。
## 文件源
文件源主要包括 text, CSV, JSON, ORC, Parquet等。
使用csv来实现下。其他大同小异。
```scala
package com.itlab1024.spark.stream

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{FloatType, IntegerType, StringType, StructType}

object CsvSourceTest {
  def main(args: Array[String]): Unit = {
    // 构建sparkSession
    val spark = SparkSession.builder().appName("单词统计").master("local[1]")
      .getOrCreate()
    // 从流中读取一行行的输出
    val schema = new StructType().add("id", IntegerType).add("name", StringType).add("money", FloatType)
    val lines = spark.readStream.schema(schema).csv("files/stream")
    // 设置输出模式，complete，输出到控制台console
    lines.writeStream.format("console")
      .outputMode("append")
      .option("truncate", value = false)
      //TODO 4.启动并等待结果
      .start()
      .awaitTermination()
    spark.stop()
  }
}
```
files/stream下有一个stream.csv文件，启动后，会读取该文件，控制台输出。
```shell
-------------------------------------------
Batch: 0
-------------------------------------------
+---+----+-----+
|id |name|money|
+---+----+-----+
|1  |马超|1.3  |
|2  |关羽|100.0|
|3  |张飞|200.0|
|4  |许褚|2.0  |
|5  |曹丕|44.0 |
+---+----+-----+
```
拷贝一份为stream1.csv会再次出发计算
```shell
-------------------------------------------
Batch: 1
-------------------------------------------
+---+----+-----+
|id |name|money|
+---+----+-----+
|1  |马超|1.3  |
|2  |关羽|100.0|
|3  |张飞|200.0|
|4  |许褚|2.0  |
|5  |曹丕|44.0 |
+---+----+-----+
```
