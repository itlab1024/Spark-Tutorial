package com.itlab1024.spark.core.operations.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object SortByOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 6, 3, 2, 5, 4, 100, 43), 3)
    intRDD.saveAsTextFile("output1")
    val r = intRDD.sortBy(_ - 1, true, 4)
    r.saveAsTextFile("output2")
    // 关闭连接
    sc.stop()
  }
}
