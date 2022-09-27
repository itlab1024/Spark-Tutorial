package com.itlab1024.spark.sql
import org.apache.spark.sql.SparkSession
object SparkSessionCreate {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .getOrCreate()
  }
}
