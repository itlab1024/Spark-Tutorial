package com.itlab1024.spark.core.operations.action

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object TakeOrderedOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(3, 0, 1, 5), 2)
    val l: Array[Int] = rdd.takeOrdered(2)((x: Int, y: Int) => {
      y - x
    }) // 先排序获取RDD前两个元素
    println(l.mkString(",")) // 5, 3
    // 关闭连接
    sc.stop()
  }
}
