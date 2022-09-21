package com.itlab1024.spark.core.operations.action

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object CountOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(1, 2, 3), 2)
    val l: Long = rdd.count()
    println(l) // 3
    // 关闭连接
    sc.stop()
  }
}
