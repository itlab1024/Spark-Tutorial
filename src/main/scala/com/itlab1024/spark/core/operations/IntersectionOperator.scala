package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object IntersectionOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(1 to 5, 2) // [1, 2, 3, 4, 5]
    val rdd2 = sc.makeRDD(5 to 9, 3) // [5, 6, 7, 8, 9]
    val rdd3 = rdd1.intersection(rdd2) // 交集是5
    rdd3.foreach(println)// [5]
    // 关闭连接
    sc.stop()
  }
}