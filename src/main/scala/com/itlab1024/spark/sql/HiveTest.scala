package com.itlab1024.spark.sql

import org.apache.spark.sql.SparkSession

object HiveTest {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .enableHiveSupport()
      .getOrCreate()
    spark.sql("show tables").show
    // 运行结果如下，此时还没有表
    //+---------+---------+-----------+
    //|namespace|tableName|isTemporary|
    //+---------+---------+-----------+
    //+---------+---------+-----------+
    // 创建一个表
    spark.sql("drop table t1")
    spark.sql("create table t1(id int)")
    spark.sql("show tables").show
    //运行结果
    //+---------+---------+-----------+
    //|namespace|tableName|isTemporary|
    //+---------+---------+-----------+
    //|  default|       t1|      false|
    //+---------+---------+-----------+

    // 填充数据并查询
    spark.sql("load data local inpath 'files/t1.txt' into table t1")
    spark.sql("select * from t1").show
    //运行结果如下
    //+---+
    //| id|
    //+---+
    //|  1|
    //|  2|
    //|  3|
    //|  4|
    //|  5|
    //+---+
  }
}
