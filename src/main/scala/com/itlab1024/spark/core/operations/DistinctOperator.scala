package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 * Map算子: Spark中的map和scala、java中的map基本相同，通过传入一个函数，将值转化为另外一种结果，形成一种新的RDD
 *
 * @author itlab
 */
object DistinctOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 1, 3, 3, 5, 6), 2)
    //    val r = intRDD.distinct()
    intRDD.saveAsTextFile("outputh1")
    val r = intRDD.distinct(3)
    r.saveAsTextFile("output2")
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}