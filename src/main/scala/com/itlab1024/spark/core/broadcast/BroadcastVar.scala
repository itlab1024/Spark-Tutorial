package com.itlab1024.spark.core.broadcast

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 广播变量
 */
object BroadcastVar {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List((1, 2), (1, 3), (2, 1)), 2)
    val min = 1;
    val v = sc.broadcast(min)
    // v.value是获取广播变量的值
    val r = rdd.filter(f => {f._1 > v.value})
    r.foreach(println) // (2,1)
    // 关闭连接
    sc.stop()
  }
}
