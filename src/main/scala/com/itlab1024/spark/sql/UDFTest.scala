package com.itlab1024.spark.sql

import org.apache.spark.sql.SparkSession

object UDFTest {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .getOrCreate()
    // 注册一个自定义函数
    spark.udf.register("customUdf1", (name: String) => name.toUpperCase())
    val frame = spark.read.json("files/sql.json")
    frame.createOrReplaceTempView("student")
    // sql中使用自定义的函数
    spark.sql("select customUdf1(name)as upperName from student").show()
    //输出结果
    //+---------+
    //|upperName|
    //+---------+
    //|     刘备|
    //|     曹操|
    //|     孙权|
    //+---------+
  }

}
