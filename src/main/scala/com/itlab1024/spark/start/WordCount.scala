package com.itlab1024.spark.start

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("统计单词数量").setMaster("local")
    val sc = new SparkContext(conf)
    //1. 将文件中的数据读入到内存，结果是一行一行的。
    val rdd = sc.textFile("files/wordCount.txt")
    //2. 将每行通过空格切分
    val flatRDD = rdd.flatMap(_.split(" "))
    //3. 转化为元组，比如(K,V)，K代表单词，V代表单词的数量（写死1）
    val tupleRDD = flatRDD.map((_, 1))
    //4. 然后通过K聚合将所有V加起
    val result = tupleRDD.reduceByKey(_ + _)
    // 打印
    result.collect().foreach(println)
    // 关闭连接
    sc.stop()
  }
}
