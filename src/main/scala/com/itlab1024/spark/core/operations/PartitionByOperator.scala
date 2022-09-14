package com.itlab1024.spark.core.operations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object PartitionByOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd: RDD[(Int, String)] =
      sc.makeRDD(List((1, "a"), (2, "b"), (3, "c")), 3)
    rdd.saveAsTextFile("output1")
    import org.apache.spark.HashPartitioner
    val r: RDD[(Int, String)] =
      rdd.partitionBy(new HashPartitioner(2))
    r.saveAsTextFile("output2")
    // 关闭连接
    sc.stop()
  }
}