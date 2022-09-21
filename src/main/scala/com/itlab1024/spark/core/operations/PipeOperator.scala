package com.itlab1024.spark.core.operations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object PipeOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))
    val r: RDD[String] = rdd1.pipe("command/pipe.sh")
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}