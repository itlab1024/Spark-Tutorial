package com.itlab1024.spark.sql

import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

object SQLDataFrame {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .getOrCreate()
    // 创建DataFrame
    val frame: DataFrame = spark.read.json("files/sql.json")
    frame.show()
    //结果如下
    //+----+----+
    //| age|name|
    //+----+----+
    //|1000|刘备|
    //|2000|曹操|
    //|   2|孙权|
    //+----+----+
    // 创建一个view
    frame.createTempView("view1")
    spark.sql("select * from view1").show()
    //结果如下
    //+----+----+
    //| age|name|
    //+----+----+
    //|1000|刘备|
    //|2000|曹操|
    //|   2|孙权|
    //+----+----+
    // 跟数据库SQL使用一样，可以查询指定列
    spark.sql("select age from view1").show()
    //结果如下
    //+----+
    //| age|
    //+----+
    //|1000|
    //|2000|
    //|   2|
    //+----+
    // 全局view，需要注意全局view需要使用global_temp.前缀
    frame.createOrReplaceGlobalTempView("view2")
    spark.sql("select * from global_temp.view2").show
    //运行结果
    //+----+----+
    //| age|name|
    //+----+----+
    //|1000|刘备|
    //|2000|曹操|
    //|   2|孙权|
    //+----+----+
    //使用newSession打开新的会话
    spark.newSession().sql("select * from global_temp.view2").show
    //运行结果
    //+----+----+
    //| age|name|
    //+----+----+
    //|1000|刘备|
    //|2000|曹操|
    //|   2|孙权|
    //+----+----+
    // DSL语言
    // 查看schema信息
    frame.printSchema()
    //运行结果
    //root
    // |-- age: long (nullable = true)
    // |-- name: string (nullable = true)
    frame.select("name").show
    //运行结果
    //+----+
    //|name|
    //+----+
    //|刘备|
    //|曹操|
    //|孙权|
    //+----+
    // 查询多个列
    frame.select("name", "age").show
    //运行结果
    //+----+----+
    //|name| age|
    //+----+----+
    //|刘备|1000|
    //|曹操|2000|
    //|孙权|   2|
    //+----+----+
    // 计算，类似于sql中select age + 1
    import spark.implicits._ // 使用$标记，需要引入这个
    frame.select($"age"+  1).show()
    // 运行结果
    //+---------+
    //|(age + 1)|
    //+---------+
    //|     1001|
    //|     2001|
    //|        3|
    //+---------+
    // 过滤条件，类似where,查询年龄大于10的数据，只查询name
    frame.filter($"age" > 10).select("name").show
    //运行结果
    //+----+
    //|name|
    //+----+
    //|刘备|
    //|曹操|
    //+----+
    // 分组，按照名字分区，统计每个名字的人数
    frame.groupBy("name").count().show()
    //运行结果
    //+----+-----+
    //|曹操|    1|
    //|刘备|    1|
    //|孙权|    1|
    //+----+-----+

    //Dataset实操
    // 创建Dataset
    val ds: Dataset[Student] = List(Student(name = "诸葛亮", age = 1005)).toDS()
    ds.printSchema()
    // 运行结果
    //root
    // |-- name: string (nullable = true)
    // |-- age: integer (nullable = false)

    // 使用基本类型序列创建Ds
    val ds2 = List(1, 2, 3).toDS()
    ds2.printSchema()
    //运行结果
    //root
    // |-- value: integer (nullable = false)
    ds2.show()
    //运行结果
    //+-----+
    //|value|
    //+-----+
    //|    1|
    //|    2|
    //|    3|
    //+-----+

  }
  //创建样例类
  case class Student(name: String, age: Int) {}
}
