package com.itlab1024.spark.core.cache

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object PersistAndCache {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(3, 0, 1, 5), 2)
    rdd.cache() // 等价于rdd.persist()
    rdd.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
