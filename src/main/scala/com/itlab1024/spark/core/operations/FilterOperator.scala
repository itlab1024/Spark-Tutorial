package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * @author itlab
 */
object FilterOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 2, 3, 4, 5, 6))
    val r = intRDD.filter(_ > 3) // 只要大于3的的数据
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}