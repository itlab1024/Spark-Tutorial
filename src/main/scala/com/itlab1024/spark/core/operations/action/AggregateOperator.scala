package com.itlab1024.spark.core.operations.action

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object AggregateOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(3, 0, 1, 5), 2)
    // 以零值=2作为初始值，对第一个分区[3, 0]做加法聚合运算，结果是5
    // 以零值=2作为初始值，对第二个分区[8, 0]做加法聚合运算，结果是8
    // 上线是分区内部的计算，接下来进行分区间的计算，使用零值分别减去各个分区的结果，2 - 5 - 8 = -11
    val r: Int = rdd.aggregate(2)(_ + _, _ - _)
    println(r) // -11
    // 关闭连接
    sc.stop()
  }
}
