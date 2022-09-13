package com.itlab1024.spark.core

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 创建RDD
 *
 * @author itlab1024
 */
object RDDCreate02 {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local")
    val sc = new SparkContext(conf)

    val value: RDD[(String, String)] = sc.wholeTextFiles("files")
    value.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
