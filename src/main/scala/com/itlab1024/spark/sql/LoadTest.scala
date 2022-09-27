package com.itlab1024.spark.sql

import org.apache.spark.sql.{SaveMode, SparkSession}

import java.util.Properties

object LoadTest {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .getOrCreate()
    val frame = spark.read.csv("files/user.csv")
    frame.createOrReplaceTempView("user")
    spark.sql("select * from user").show()
    //运行结果
    //+----+-----+
    //| _c0|  _c1|
    //+----+-----+
    //|姓名| 年龄|
    //|刘备|  100|
    //|赵云| "40"|
    //|张飞| "60"|
    //+----+-----+
    // 上面的方式你会发现表头使用的是自动生成的有序的，但是实际情况可能是csv的第一行是表头，那么就可以通过option设置，这个options的取值可以去CSVOptions类查看，看如下示例代码
    spark.read.format("csv").option("header", value = true).load("files/user.csv").show()
    //运行结果
    //+----+-----+
    //|姓名| 年龄|
    //+----+-----+
    //|刘备|  100|
    //|赵云| "40"|
    //|张飞| "60"|
    //+----+-----+
    // 当前也可以直接使用sql查询
    spark.sql("select * from csv.`files/user.csv`").show()


    // JDBC
    val dataFrame = spark.read.format(
      "jdbc")
      // url结构如下，也可以将一些参数放到url中
      .option("url", "jdbc:postgresql://localhost:5432/spark-tutorial?user=postgres&password=postgres")
      // 驱动器，不同数据库Driver不同
      .option("driver", "org.postgresql.Driver")
      // 表名字，也可以使用query，query支持使用select语句。两者只能有一个
      .option("dbtable", "BOOK")
      // 用户名
      .option("user", "postgres")
      // 密码
      .option("password", "postgres")
      .load()
    dataFrame.show()
    // 运行结果
    //+---+--------+------------+
    //| id|    name|     authors|
    //+---+--------+------------+
    //|  1|    语文|[张飞, 庞德]|
    //|  2|    数学|[许褚, 郭嘉]|
    //|  3|思想品德|[杨修, 法正]|
    //+---+--------+------------+
    // 将数据保存到文件夹files/write下（注意write是个文件夹，最终写入的是一个分区文件，也有校验文件等）,SaveMode.Append代表追加模式
    dataFrame.write.mode(SaveMode.Append).json("files/write")
  }

}
