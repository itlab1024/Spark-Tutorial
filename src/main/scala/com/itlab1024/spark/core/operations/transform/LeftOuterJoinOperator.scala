package com.itlab1024.spark.core.operations.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object LeftOuterJoinOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", "B")))
    val rdd2 = sc.makeRDD(List(("a", "A"), ("c", 3)))
    val r = rdd1.leftOuterJoin(rdd2, 1)
    r.foreach(println)
    // 结果是
    //(a,(1,Some(A)))
    //(b,(B,None))
    // 关闭连接
    sc.stop()
  }
}
