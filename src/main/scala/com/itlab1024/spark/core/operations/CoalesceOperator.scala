package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object CoalesceOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 1, 3, 3, 5, 6), 3)
    intRDD.saveAsTextFile("output1")
    val r = intRDD.coalesce(3, false) // 缩减分区数，但是不洗牌
    r.saveAsTextFile("output2")
    // 关闭连接
    sc.stop()
  }
}