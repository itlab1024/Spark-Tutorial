package com.itlab1024.spark.core.operations.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object MapOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 2, 3, 4, 5, 6))
    val r = intRDD.map(_ - 1) // 将RDD中每个值减1
    r.foreach(println)

    // map并行计算演示
    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))
    val rdd2 = rdd1.map(i => {
      println(s"第一次 ${i}")
      i
    })
    val rdd3 = rdd2.map(i => {
      println(s"第二次 ${i}")
      i
    })
    rdd3.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
