package com.itlab1024.spark.core

import org.apache.logging.slf4j.Log4jLoggerFactory
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 创建RDD
 *
 * @author itlab1024
 */
object RDDCreate01 {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local")
    val sc = new SparkContext(conf)

    // 准备内存Seq数据
    val list = List(1, 2, 3, 4, 5)
    // 通过parallelize方法
    val intRDD = sc.parallelize(list)
    intRDD.foreach(println)
    // 通过makeRDD方法
    val intRDD2 = sc.makeRDD(list)
    intRDD2.foreach(println)

    //使用saveAsTextFile将数据以文件的形式保存到不同分区，放到项目下的partitions文件夹下
    val intRDD3 = sc.makeRDD(list, 2)
    intRDD3.saveAsTextFile("partitions")

    // 关闭连接
    sc.stop()
  }
}
