package com.itlab1024.spark.core.operations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object CogroupOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(List(("a", 1), ("b", "B")))
    val rdd2 = sc.makeRDD(List[(String, Any)](("a", "A"), ("c", 3)))
    val r: RDD[(String, (Iterable[Any], Iterable[Any]))] = rdd1.cogroup(rdd2, 1)
    r.foreach(println)
    // 结果是
    //(a,(CompactBuffer(1),CompactBuffer(A)))
    //(b,(CompactBuffer(B),CompactBuffer()))
    //(c,(CompactBuffer(),CompactBuffer(3)))
    // 关闭连接
    sc.stop()
  }
}