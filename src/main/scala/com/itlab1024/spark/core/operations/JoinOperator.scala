package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object JoinOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", "B")))
    val rdd2 = sc.makeRDD(List(("a", "A"), ("c", 3)))
    val r = rdd1.join(rdd2, 1)
    r.foreach(println) // (a,(1,A))
    // 关闭连接
    sc.stop()
  }
}