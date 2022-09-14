package com.itlab1024.spark.core.operations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object GlomOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)
    val rdd1: RDD[Array[Int]] = rdd.glom()
    rdd1.foreach(x=>x.foreach(println)) // 这里为什么是两次循环，就是因为glom会将每个分区的数据组装为一个Array，再形成一个RDD
    // 关闭连接
    sc.stop()
  }
}
