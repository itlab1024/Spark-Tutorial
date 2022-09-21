package com.itlab1024.spark.core.operations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object CartesianOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(List(1, 2, 3, 4))
    val rdd2 = sc.makeRDD(List(5, 6, 7, 8))
    val r: RDD[(Int, Int)] = rdd1.cartesian(rdd2)
    r.foreach(println) // 结果是两个RDD的笛卡尔积,两个RDD中的任意两个组合结果
    // 关闭连接
    sc.stop()
  }
}