# SparkSQL是什么？
![image-20220924095541476](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209240955854.png)

Spark SQL 是 Spark 用于结构化数据(structured data)处理的 Spark 模块。他是基于Spark Core开发的，主要用于处理结构化数据，SQL标准查询语言使用更方便，Spark会将spark sql解析为对应的算子，从而来处理底层的RDD。

SparkSQL运行后，结果将作为DataFrame、Dataset返回。

# DataFrame

DataFrame 是一种以 RDD 为基础的分布式数据集，类似于**传统数据库中的二维表格**。

DataFrame 内部的有明确 Scheme 结构，即列名、列字段类型都是已知的，这带来的好处是可以减少数据读取以及更好地优化执行计划，从而保证查询效率。

通过下图看下RDD和DataFrame的区别

![img](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209241006681.jpeg)

可以看到DataFrame有schema信息（灰色部分），确实类似关系型数据库的结构。RDD可以通过.toDF转化为DataFrame。

# DataSet

Dataset 也是分布式的数据集合，在 Spark 1.6 版本被引入，它集成了 RDD 和 DataFrame 的优点，具备强类型的特点，同时支持 Lambda 函数，但只能在 Scala 和 Java 语言中使用。在 Spark 2.0 后，为了方便开发者，Spark 将 DataFrame 和 Dataset 的 API 融合到一起，提供了结构化的 API(Structured API)，即用户可以通过一套标准的 API 就能完成对两者的操作。



# Spark Session

之前学习RDD的时候，连接spark使用的是SparkContenxt，SparkSQL是一个新的模块，他也有新的链接类，叫做SparkSession，顾名思义就是spark 会话。接下来看使用如下代码来构建sparkSession。SparkSession底层也是SparkContext。

前提需要引入依赖，在build.sbt中增加spark-sql的依赖

```groovy
libraryDependencies += "org.apache.spark" % "spark-sql_2.12" % "3.3.0"
```

构建SparkSession

```scala
package com.itlab1024.spark.sql
import org.apache.spark.sql.SparkSession
object SparkSessionCreate {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .getOrCreate()
  }
}
```

# DataFrame实操

## 基本使用

在实操之前，需要先准备数据，我这里使用json文件。内容如下

```
{"name": "刘备", "age": 1000}
{"name": "曹操", "age": 2000}
{"name": "孙权", "age": 2}
```

接下来创建DataFrame，并展示数据

```scala
package com.itlab1024.spark.sql

import org.apache.spark.sql.{DataFrame, SparkSession}

object SQLDataFrame {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .getOrCreate()
    // 创建DataFrame
    val frame: DataFrame = spark.read.json("files/sql.json")
    frame.show()
    //+----+----+
    //| age|name|
    //+----+----+
    //|1000|刘备|
    //|2000|曹操|
    //|   2|孙权|
    //+----+----+
  }
}
```

使用spark.read.json("files/sql.json")读取JSON文件，show()方法用于打印DataFrame信息。

创建临时view，然后通过view进行查询

```scala
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
```

也可以使用createOrReplaceTempView,如果view存在着替换。

上面使用的view只有在当前session下有效，但是如果还有另一个session，也想使用这个view，就要使用全局view

```scala
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
```

## DSL语言

DataFrame 提供一个特定领域语言(domain-specific language, DSL)去管理结构化的数据。

可以在 Scala, Java, Python 和 R 中使用 DSL，使用 DSL 语法风格不必去创建临时视图了，加下来尝试下

查看Schema信息

```scala
// 查看schema信息
frame.printSchema()
//运行结果
//root
// |-- age: long (nullable = true)
// |-- name: string (nullable = true)
```

值查看name数据

```scala
frame.select("name").show
//运行结果
//+----+
//|name|
//+----+
//|刘备|
//|曹操|
//|孙权|
//+----+
```

查询多列数据

```scala
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
```

计算查询

```scala
// 计算，类似于sql中select age + 1
import spark.implicits._ // 使用$标记，需要引入这个
frame.select($"age"+  1).show()
```

过滤数据

```scala
// 过滤条件，类似where,查询年龄大于10的数据，只查询name
frame.filter($"age" > 10).select("name").show
//运行结果
//+----+
//|name|
//+----+
//|刘备|
//|曹操|
//+----+
```

分组统计

```scala
// 分组，按照名字分区，统计每个名字的人数
frame.groupBy("name").count().show()
//运行结果
//+----+-----+
//|曹操|    1|
//|刘备|    1|
//|孙权|    1|
//+----+-----+
```

# Dataset实操

DataSet 是具有强类型的数据集合，需要提供对应的类型信息。

可以使用样例类创建Dataset

```scala
// 创建Dataset
val ds: Dataset[Student] = List(Student(name = "诸葛亮", age = 1005)).toDS()
ds.printSchema()
// 运行结果
//root
// |-- name: string (nullable = true)
// |-- age: integer (nullable = false)

//创建样例类
case class Student(name: String, age: Int) {}
```

也可以使用基本类型创建Dataset

```scala
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
```

## RDD、DataFrame、Dataset三者转换关系

![image-20220924181750195](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209241817411.png)

# UDF

UDF意思是用户定义函数，用户可以在sql中使用自定义函数。

```scala
package com.itlab1024.spark.sql

import org.apache.spark.sql.SparkSession

object UDFTest {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .getOrCreate()
    // 注册一个自定义函数
    spark.udf.register("customUdf1", (name: String) => name.toUpperCase())
    val frame = spark.read.json("files/sql.json")
    frame.createOrReplaceTempView("student")
    // sql中使用自定义的函数
    spark.sql("select customUdf1(name)as upperName from student").show()
    //输出结果
    //+---------+
    //|upperName|
    //+---------+
    //|     刘备|
    //|     曹操|
    //|     孙权|
    //+---------+
  }

}
```

# 数据加载和保存

## 数据加载

Spark提供了spark.read.load 加载数据的通用方法，他支持多种类型文件的加载，read里有很多的方法，很多都是load的特殊形式，比如spark.read.json，就等价于spark.read.format("json").load()

![image-20220925123101705](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209251231931.png)

方法签名如下：

```scala
spark.read.format("…")[.option("…")].load("…")
```



*  format("…")：指定加载的数据类型，包括"csv"、"jdbc"、"json"、"orc"、"parquet"和 "textFile"。 

* load("…")：在"csv"、"jdbc"、"json"、"orc"、"parquet"和"textFile"格式下需要传入加载数据的路径。

* option("…")：在"jdbc"格式下需要传入 JDBC 相应参数，url、user、password 和 dbtable

我们前面都是使用 read API 先把文件加载到 DataFrame 然后再查询。

接下来我尝试下csv和jdbc示例如下：

CSV:

```scala
package com.itlab1024.spark.sql

import org.apache.spark.sql.SparkSession

object LoadTest {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .getOrCreate()
    val frame = spark.read.csv("files/user.csv")
    frame.createOrReplaceTempView("user")
    spark.sql("select * from user").show()
    //运行结果
    //+----+-----+
    //| _c0|  _c1|
    //+----+-----+
    //|姓名| 年龄|
    //|刘备|  100|
    //|赵云| "40"|
    //|张飞| "60"|
    //+----+-----+
    // 上面的方式你会发现表头使用的是自动生成的有序的，但是实际情况可能是csv的第一行是表头，那么就可以通过option设置，这个options的取值可以去CSVOptions类查看，看如下示例代码
    spark.read.format("csv").option("header", value = true).load("files/user.csv").show()
    //运行结果
    //+----+-----+
    //|姓名| 年龄|
    //+----+-----+
    //|刘备|  100|
    //|赵云| "40"|
    //|张飞| "60"|
    //+----+-----+
    // 当前也可以直接使用sql查询
    spark.sql("select * from csv.`files/user.csv`").show()
  }
}
```

如果你csv分隔符等信息不是默认的，比如分隔符不是默认的",则可以通过shema()方法设置

其他方法比如csv(), json()等底层都是调用format然后load数据的。比如csv（）方法

```
def csv(paths: String*): DataFrame = format("csv").load(paths : _*)
```

**jdbc**

jdbc方法需要的参数说明可以查看官网文档https://spark.apache.org/docs/latest/sql-data-sources-jdbc.html#data-source-option，我这里是读取Postgresql数据库的数据。

项目引入postgres驱动依赖

```groovy
libraryDependencies += "org.postgresql" % "postgresql" % "42.5.0"
```

首先我创建个数据库，新建个BOOK表

```sql
create table book
(
    id      integer not null
        constraint "BOOK_pk"
            primary key,
    name    varchar(100),
    authors character varying[]
);

comment on column book.id is '主键ID';

comment on column book.name is '书籍名称';

alter table book
    owner to postgres;

```

表数据如下：

| id   | name     | authors     |
| :--- | :------- | :---------- |
| 1    | 语文     | {张飞,庞德} |
| 2    | 数学     | {许褚,郭嘉} |
| 3    | 思想品德 | {杨修,法正} |

接下来load下数据。

```scala
// JDBC
val dataFrame = spark.read.format(
  "jdbc")
// url结构如下，也可以将一些参数放到url中
.option("url", "jdbc:postgresql://localhost:5432/spark-tutorial?user=postgres&password=postgres")
// 驱动器，不同数据库Driver不同
.option("driver", "org.postgresql.Driver")
// 表名字，也可以使用query，query支持使用select语句。两者只能有一个
.option("dbtable", "BOOK")
// 用户名
.option("user", "postgres")
// 密码
.option("password", "postgres")
.load()
dataFrame.show()
// 运行结果
//+---+--------+------------+
//| id|    name|     authors|
//+---+--------+------------+
//|  1|    语文|[张飞, 庞德]|
//|  2|    数学|[许褚, 郭嘉]|
//|  3|思想品德|[杨修, 法正]|
//+---+--------+------------+
```

## 数据保存

数据保存使用write方法，一下我将刚才jdbc的数据以json结构保存到本地文件中（不是普通文件，而是分区文件）

```scala
// 将数据保存到文件夹files/write下（注意write是个文件夹，最终写入的是一个分区文件，也有校验文件等）,SaveMode.Append代表追加模式
dataFrame.write.mode(SaveMode.Append).json("files/write")
```

## Hive

### 内部Hive

spark内置了hive，可以直接使用

```scala
package com.itlab1024.spark.sql

import org.apache.spark.sql.SparkSession

object HiveTest {
  def main(args: Array[String]): Unit = {
    // 使用SparkSession.builder创建一个SparkSession实例，appName是应用名称，master是spark环境
    // 可以使用.config（k, v）增加配置
    val spark = SparkSession.builder.appName("应用").master("local[*]")
      .enableHiveSupport()
      .getOrCreate()
    spark.sql("show tables").show
    // 运行结果如下，此时还没有表
    //+---------+---------+-----------+
    //|namespace|tableName|isTemporary|
    //+---------+---------+-----------+
    //+---------+---------+-----------+
    // 创建一个表
    spark.sql("create table t1(id int)")
    spark.sql("show tables").show
    //运行结果
    //+---------+---------+-----------+
    //|namespace|tableName|isTemporary|
    //+---------+---------+-----------+
    //|  default|       t1|      false|
    //+---------+---------+-----------+
  }
}
```

Spark SQL 会在当前的工作目录中创建出

自己的 Hive 元数据仓库，叫作 metastore_db

![image-20220927102449806](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209271024106.png)

表创建完了，可以填充数据。

```scala
// 填充数据并查询
spark.sql("load data local inpath 'files/t1.txt' into table t1")
spark.sql("select * from t1").show
//运行结果如下
//+---+
//| id|
//+---+
//|  1|
//|  2|
//|  3|
//|  4|
//|  5|
//+---+
```

在实际使用中, 几乎没有任何人会使用内置的 Hive，我们公司就是用的单独的外部的hive。

### 外部Hive

