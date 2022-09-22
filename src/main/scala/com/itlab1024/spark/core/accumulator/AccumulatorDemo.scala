package com.itlab1024.spark.core.accumulator

import org.apache.spark.{SparkConf, SparkContext}

object AccumulatorDemo {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5))
    // 声明累加器
    val sum = sc.longAccumulator("sum");
    rdd.foreach(
      num => {
        // 使用累加器
        sum.add(num)
      })
    // 获取累加器的值
    println(sum.value) // 15
  }
}
