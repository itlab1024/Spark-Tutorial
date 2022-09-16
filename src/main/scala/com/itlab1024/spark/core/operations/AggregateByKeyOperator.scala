package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object AggregateByKeyOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List[(Int, String)]((1, "a"), (1, "b"), (3, "c")), 2)
    val r =
      rdd.aggregateByKey(0)((x: Int, y: String) => {}, _)
    r.foreach(println) // [(3,CompactBuffer((3,c))) (1, CompactBuffer((1, a), (1, b)))]
    // 关闭连接
    sc.stop()
  }
}