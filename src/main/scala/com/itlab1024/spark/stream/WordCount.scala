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
