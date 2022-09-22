package com.itlab1024.spark.core.operations.action

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object FoldOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(3, 0, 1, 5), 2)
    //    val r: Int = rdd.aggregate(2)(_ + _, _ + _) 等价于下面的fold
    val r: Int = rdd.fold(2)(_ + _)
    println(r)
    // 关闭连接
    sc.stop()
  }
}
