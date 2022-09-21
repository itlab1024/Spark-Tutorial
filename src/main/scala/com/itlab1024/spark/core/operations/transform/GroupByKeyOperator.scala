package com.itlab1024.spark.core.operations.transform

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object GroupByKeyOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List((1, "a"), (1, "b"), (3, "c")))
    val r = rdd.groupByKey(1) // 通过元组的第一个元组分组
    r.foreach(println) // [(3,CompactBuffer((3,c))) (1, CompactBuffer((1, a), (1, b)))]
    // 关闭连接
    sc.stop()
  }
}
