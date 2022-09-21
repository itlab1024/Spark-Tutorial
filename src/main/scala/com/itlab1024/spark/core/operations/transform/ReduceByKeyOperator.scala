package com.itlab1024.spark.core.operations.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object ReduceByKeyOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List((1, "a"), (1, "b"), (3, "c")))
    val r = rdd.reduceByKey(_ + _)
    r.foreach(println) // [(1,ab),(3,c)]
    // 关闭连接
    sc.stop()
  }
}
