package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object FoldByKeyOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // 第一个分区数据是("a", 1), ("a", 2), ("c", 3)
    // 第二个分区数据是("b", 4), ("c", 5), ("c", 6)
    val rdd = sc.makeRDD(List(
      ("a", 1), ("a", 2), ("c", 3),
      ("b", 4), ("c", 5), ("c", 6)
    ), 2)
    // 初始零值是=0
    val r = rdd.foldByKey(0)( _ + _)
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}