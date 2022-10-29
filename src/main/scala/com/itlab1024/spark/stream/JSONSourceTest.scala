package com.itlab1024.spark.stream

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.streaming.Trigger
import org.apache.spark.sql.types.{FloatType, IntegerType, StringType, StructType}

object JSONSourceTest {
  def main(args: Array[String]): Unit = {
    // 构建sparkSession
    val spark = SparkSession.builder().appName("单词统计").master("local[1]")
      .getOrCreate()
    // 从流中读取一行行的输出
    val schema = new StructType().add("id", IntegerType).add("name", StringType).add("money", FloatType)
    val lines = spark.readStream.schema(schema).json("files/stream/json")
    // 设置输出模式，complete，输出到控制台console
    lines.writeStream.format("console")
      .outputMode("append")
      .option("truncate", value = false)
      .trigger(Trigger.ProcessingTime(0))
      //TODO 4.启动并等待结果
      .start()
      .awaitTermination()
    spark.stop()
  }
}
