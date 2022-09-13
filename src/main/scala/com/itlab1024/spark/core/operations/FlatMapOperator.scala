package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * @author itlab
 */
object FlatMapOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(Array(List(1, 2), List(3, 4)))
    val r = intRDD.flatMap(data => data) // 将RDD展开，数值原样输出
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}