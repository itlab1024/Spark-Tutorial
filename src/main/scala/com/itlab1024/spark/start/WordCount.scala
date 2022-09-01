package com.itlab1024.spark.start

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf: SparkConf = new SparkConf().setAppName("统计单词数量").setMaster("local")
    val sc: SparkContext = new SparkContext(conf)
    //1. 将文件中的数据读入到内存，结果是一行一行的。
    val rdd: RDD[String] = sc.textFile("files/wordCount.txt")
    //2. 将每行通过空格切分
    val flatRDD: RDD[String] = rdd.flatMap(_.split(" "))
    //3. 转化为元组，比如(K,V)，K代表单词，V代表单词的数量（写死1）
    val tupleRDD: RDD[(String, Int)] = flatRDD.map((_, 1))
    //4. 然后通过K聚合将所有V加起
    val result: RDD[(String, Int)] = tupleRDD.reduceByKey(_ + _)
    // 打印
    result.collect().foreach(println)
    // 关闭连接
    sc.stop()
  }
}
