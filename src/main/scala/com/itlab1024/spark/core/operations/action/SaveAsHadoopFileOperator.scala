package com.itlab1024.spark.core.operations.action

import org.apache.hadoop.io.{IntWritable, Text}
import org.apache.hadoop.mapred.TextOutputFormat
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object SaveAsHadoopFileOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List((1, 2), (1, 3), (2, 1)), 2)
    rdd.saveAsHadoopFile("partitions", classOf[Text], classOf[IntWritable], classOf[TextOutputFormat[Text,IntWritable]])
    // 关闭连接
    sc.stop()
  }
}
