> 本人进入大数据领域仅仅一年，之前从未接触过大数据领域，对于Spark更是闻所未闻，目前公司主要做IOT领域。大数据技术是必须要掌握的，
> 而且项目中也使用到了Spark，所以学习下，Spark是使用Scala开发的。最好也掌握下Scala语言。
> 我学习Spark主要有通过官网、尚硅谷(不得不说尚硅谷真的是业界良心，不是打广告啊😄)，另外就是不明白的时候问问度娘。
> 本篇文章是我在学习了一遍之后重新来写的，一是为了加深记忆，二是想分享出来，跟网友互相学习，取长补短，共同进步。

# 什么是Spark?
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202208312008157.png)
Spark 是Apache基金会开发的用于大数据处理的统一分析引擎。它提供 Java、Scala、Python 和 R 语言中的高级 API，
还有机器学习、图形处理等（这两个我目前用不到，先不学了。）

# 下载安装
目前最新版本是3.3.0。我就是用这个版本进行学习了。
打开[Spark下载地址](https://spark.apache.org/downloads.html)
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202208312006177.png)
下载完成后，解压，如下图：
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202208312037740.png)
# 交互Shell和运行示例
spark的bin目录下提供了一个交互shell，并且他提供了很多例子，接下来我就运行spark-shell脚本，打开交互命令行
```shell
➜  spark-3.3.0-bin-hadoop3 bin/spark-shell 
22/08/31 20:12:00 WARN Utils: Your hostname, ITshiyanshideMacBook-Pro.local resolves to a loopback address: 127.0.0.1; using 10.112.82.59 instead (on interface en0)
22/08/31 20:12:00 WARN Utils: Set SPARK_LOCAL_IP if you need to bind to another address
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
22/08/31 20:12:06 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Spark context Web UI available at http://10.112.82.59:4040
Spark context available as 'sc' (master = local[*], app id = local-1661947928082).
Spark session available as 'spark'.
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 3.3.0
      /_/
         
Using Scala version 2.12.15 (Java HotSpot(TM) 64-Bit Server VM, Java 17.0.3.1)
Type in expressions to have them evaluated.
Type :help for more information.

scala> 
```
可以看到上面就打开了一个shell。在这里就可以输出scala代码，比如：
```scala
scala> val i = 1
i: Int = 1

scala> val j = 1
j: Int = 1
scala> println(i + j)
2
```
没有问题。spark中已经为我们初始化了sparkContext和sparkSession，变量分别对应的是sc和spark。
也可以指定参数启动shell，比如
```shell
➜  spark-3.3.0-bin-hadoop3 bin/spark-shell --master 'local[2]' 
22/08/31 20:21:06 WARN Utils: Your hostname, ITshiyanshideMacBook-Pro.local resolves to a loopback address: 127.0.0.1; using 10.112.82.59 instead (on interface en0)
22/08/31 20:21:06 WARN Utils: Set SPARK_LOCAL_IP if you need to bind to another address
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
22/08/31 20:21:12 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Spark context Web UI available at http://10.112.82.59:4040
Spark context available as 'sc' (master = local[2], app id = local-1661948473413).
Spark session available as 'spark'.
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 3.3.0
      /_/
         
Using Scala version 2.12.15 (Java HotSpot(TM) 64-Bit Server VM, Java 17.0.3.1)
Type in expressions to have them evaluated.
Type :help for more information.

scala> 
```
local代表本地模式，spark还有standalone模式，yarn模式等等，慢慢学。[2]这个2代表线程数，具体含义以后慢慢学习。

接下来我使用run-example来运行一个内置的PI计算的任务。
```shell
➜  spark-3.3.0-bin-hadoop3 bin/run-example SparkPi 10
22/08/31 20:18:25 WARN Utils: Your hostname, ITshiyanshideMacBook-Pro.local resolves to a loopback address: 127.0.0.1; using 10.112.82.59 instead (on interface en0)
22/08/31 20:18:25 WARN Utils: Set SPARK_LOCAL_IP if you need to bind to another address
22/08/31 20:18:25 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
22/08/31 20:18:25 INFO SparkContext: Running Spark version 3.3.0
22/08/31 20:18:25 INFO ResourceUtils: ==============================================================
22/08/31 20:18:25 INFO ResourceUtils: No custom resources configured for spark.driver.
22/08/31 20:18:25 INFO ResourceUtils: ==============================================================
22/08/31 20:18:25 INFO SparkContext: Submitted application: Spark Pi
22/08/31 20:18:25 INFO ResourceProfile: Default ResourceProfile created, executor resources: Map(cores -> name: cores, amount: 1, script: , vendor: , memory -> name: memory, amount: 1024, script: , vendor: , offHeap -> name: offHeap, amount: 0, script: , vendor: ), task resources: Map(cpus -> name: cpus, amount: 1.0)
22/08/31 20:18:26 INFO ResourceProfile: Limiting resource is cpu
22/08/31 20:18:26 INFO ResourceProfileManager: Added ResourceProfile id: 0
22/08/31 20:18:26 INFO SecurityManager: Changing view acls to: itlab
22/08/31 20:18:26 INFO SecurityManager: Changing modify acls to: itlab
22/08/31 20:18:26 INFO SecurityManager: Changing view acls groups to: 
22/08/31 20:18:26 INFO SecurityManager: Changing modify acls groups to: 
22/08/31 20:18:26 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users  with view permissions: Set(itlab); groups with view permissions: Set(); users  with modify permissions: Set(itlab); groups with modify permissions: Set()
22/08/31 20:18:26 INFO Utils: Successfully started service 'sparkDriver' on port 65391.
22/08/31 20:18:26 INFO SparkEnv: Registering MapOutputTracker
22/08/31 20:18:26 INFO SparkEnv: Registering BlockManagerMaster
22/08/31 20:18:26 INFO BlockManagerMasterEndpoint: Using org.apache.spark.storage.DefaultTopologyMapper for getting topology information
22/08/31 20:18:26 INFO BlockManagerMasterEndpoint: BlockManagerMasterEndpoint up
22/08/31 20:18:26 INFO SparkEnv: Registering BlockManagerMasterHeartbeat
22/08/31 20:18:26 INFO DiskBlockManager: Created local directory at /private/var/folders/b7/yhnw9hws0ng2w1_khl8nr2t40000gn/T/blockmgr-006f1115-02e9-4883-96c4-7deb4c9c0f2c
22/08/31 20:18:26 INFO MemoryStore: MemoryStore started with capacity 434.4 MiB
22/08/31 20:18:26 INFO SparkEnv: Registering OutputCommitCoordinator
22/08/31 20:18:27 INFO Utils: Successfully started service 'SparkUI' on port 4040.
22/08/31 20:18:27 INFO SparkContext: Added JAR file:///Users/itlab/dev-tools/spark-3.3.0-bin-hadoop3/examples/jars/scopt_2.12-3.7.1.jar at spark://10.112.82.59:65391/jars/scopt_2.12-3.7.1.jar with timestamp 1661948305908
22/08/31 20:18:27 INFO SparkContext: Added JAR file:///Users/itlab/dev-tools/spark-3.3.0-bin-hadoop3/examples/jars/spark-examples_2.12-3.3.0.jar at spark://10.112.82.59:65391/jars/spark-examples_2.12-3.3.0.jar with timestamp 1661948305908
22/08/31 20:18:27 INFO SparkContext: The JAR file:/Users/itlab/dev-tools/spark-3.3.0-bin-hadoop3/examples/jars/spark-examples_2.12-3.3.0.jar at spark://10.112.82.59:65391/jars/spark-examples_2.12-3.3.0.jar has been added already. Overwriting of added jar is not supported in the current version.
22/08/31 20:18:27 INFO Executor: Starting executor ID driver on host 10.112.82.59
22/08/31 20:18:27 INFO Executor: Starting executor with user classpath (userClassPathFirst = false): ''
22/08/31 20:18:27 INFO Executor: Fetching spark://10.112.82.59:65391/jars/scopt_2.12-3.7.1.jar with timestamp 1661948305908
22/08/31 20:18:27 INFO TransportClientFactory: Successfully created connection to /10.112.82.59:65391 after 39 ms (0 ms spent in bootstraps)
22/08/31 20:18:27 INFO Utils: Fetching spark://10.112.82.59:65391/jars/scopt_2.12-3.7.1.jar to /private/var/folders/b7/yhnw9hws0ng2w1_khl8nr2t40000gn/T/spark-12cd6bb6-84c2-4145-90bc-0aa083c2b6c4/userFiles-ce4cfeb0-70e4-42b7-9f37-e4143760ff2a/fetchFileTemp5878280159627562337.tmp
22/08/31 20:18:27 INFO Executor: Adding file:/private/var/folders/b7/yhnw9hws0ng2w1_khl8nr2t40000gn/T/spark-12cd6bb6-84c2-4145-90bc-0aa083c2b6c4/userFiles-ce4cfeb0-70e4-42b7-9f37-e4143760ff2a/scopt_2.12-3.7.1.jar to class loader
22/08/31 20:18:27 INFO Executor: Fetching spark://10.112.82.59:65391/jars/spark-examples_2.12-3.3.0.jar with timestamp 1661948305908
22/08/31 20:18:27 INFO Utils: Fetching spark://10.112.82.59:65391/jars/spark-examples_2.12-3.3.0.jar to /private/var/folders/b7/yhnw9hws0ng2w1_khl8nr2t40000gn/T/spark-12cd6bb6-84c2-4145-90bc-0aa083c2b6c4/userFiles-ce4cfeb0-70e4-42b7-9f37-e4143760ff2a/fetchFileTemp2553748991312385625.tmp
22/08/31 20:18:27 INFO Executor: Adding file:/private/var/folders/b7/yhnw9hws0ng2w1_khl8nr2t40000gn/T/spark-12cd6bb6-84c2-4145-90bc-0aa083c2b6c4/userFiles-ce4cfeb0-70e4-42b7-9f37-e4143760ff2a/spark-examples_2.12-3.3.0.jar to class loader
22/08/31 20:18:27 INFO Utils: Successfully started service 'org.apache.spark.network.netty.NettyBlockTransferService' on port 65394.
22/08/31 20:18:27 INFO NettyBlockTransferService: Server created on 10.112.82.59:65394
22/08/31 20:18:27 INFO BlockManager: Using org.apache.spark.storage.RandomBlockReplicationPolicy for block replication policy
22/08/31 20:18:27 INFO BlockManagerMaster: Registering BlockManager BlockManagerId(driver, 10.112.82.59, 65394, None)
22/08/31 20:18:27 INFO BlockManagerMasterEndpoint: Registering block manager 10.112.82.59:65394 with 434.4 MiB RAM, BlockManagerId(driver, 10.112.82.59, 65394, None)
22/08/31 20:18:27 INFO BlockManagerMaster: Registered BlockManager BlockManagerId(driver, 10.112.82.59, 65394, None)
22/08/31 20:18:27 INFO BlockManager: Initialized BlockManager: BlockManagerId(driver, 10.112.82.59, 65394, None)
22/08/31 20:18:28 INFO SparkContext: Starting job: reduce at SparkPi.scala:38
22/08/31 20:18:28 INFO DAGScheduler: Got job 0 (reduce at SparkPi.scala:38) with 10 output partitions
22/08/31 20:18:28 INFO DAGScheduler: Final stage: ResultStage 0 (reduce at SparkPi.scala:38)
22/08/31 20:18:28 INFO DAGScheduler: Parents of final stage: List()
22/08/31 20:18:28 INFO DAGScheduler: Missing parents: List()
22/08/31 20:18:28 INFO DAGScheduler: Submitting ResultStage 0 (MapPartitionsRDD[1] at map at SparkPi.scala:34), which has no missing parents
22/08/31 20:18:28 INFO MemoryStore: Block broadcast_0 stored as values in memory (estimated size 4.0 KiB, free 434.4 MiB)
22/08/31 20:18:28 INFO MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 2.3 KiB, free 434.4 MiB)
22/08/31 20:18:28 INFO BlockManagerInfo: Added broadcast_0_piece0 in memory on 10.112.82.59:65394 (size: 2.3 KiB, free: 434.4 MiB)
22/08/31 20:18:28 INFO SparkContext: Created broadcast 0 from broadcast at DAGScheduler.scala:1513
22/08/31 20:18:28 INFO DAGScheduler: Submitting 10 missing tasks from ResultStage 0 (MapPartitionsRDD[1] at map at SparkPi.scala:34) (first 15 tasks are for partitions Vector(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
22/08/31 20:18:28 INFO TaskSchedulerImpl: Adding task set 0.0 with 10 tasks resource profile 0
22/08/31 20:18:28 INFO TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0) (10.112.82.59, executor driver, partition 0, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:28 INFO TaskSetManager: Starting task 1.0 in stage 0.0 (TID 1) (10.112.82.59, executor driver, partition 1, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:28 INFO TaskSetManager: Starting task 2.0 in stage 0.0 (TID 2) (10.112.82.59, executor driver, partition 2, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:28 INFO TaskSetManager: Starting task 3.0 in stage 0.0 (TID 3) (10.112.82.59, executor driver, partition 3, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:28 INFO Executor: Running task 1.0 in stage 0.0 (TID 1)
22/08/31 20:18:28 INFO Executor: Running task 2.0 in stage 0.0 (TID 2)
22/08/31 20:18:28 INFO Executor: Running task 0.0 in stage 0.0 (TID 0)
22/08/31 20:18:28 INFO Executor: Running task 3.0 in stage 0.0 (TID 3)
22/08/31 20:18:28 INFO Executor: Finished task 2.0 in stage 0.0 (TID 2). 1008 bytes result sent to driver
22/08/31 20:18:28 INFO Executor: Finished task 3.0 in stage 0.0 (TID 3). 1008 bytes result sent to driver
22/08/31 20:18:28 INFO Executor: Finished task 1.0 in stage 0.0 (TID 1). 1008 bytes result sent to driver
22/08/31 20:18:28 INFO Executor: Finished task 0.0 in stage 0.0 (TID 0). 1008 bytes result sent to driver
22/08/31 20:18:28 INFO TaskSetManager: Starting task 4.0 in stage 0.0 (TID 4) (10.112.82.59, executor driver, partition 4, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:28 INFO Executor: Running task 4.0 in stage 0.0 (TID 4)
22/08/31 20:18:28 INFO TaskSetManager: Starting task 5.0 in stage 0.0 (TID 5) (10.112.82.59, executor driver, partition 5, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:28 INFO Executor: Running task 5.0 in stage 0.0 (TID 5)
22/08/31 20:18:28 INFO TaskSetManager: Starting task 6.0 in stage 0.0 (TID 6) (10.112.82.59, executor driver, partition 6, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:28 INFO Executor: Running task 6.0 in stage 0.0 (TID 6)
22/08/31 20:18:28 INFO TaskSetManager: Starting task 7.0 in stage 0.0 (TID 7) (10.112.82.59, executor driver, partition 7, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:28 INFO Executor: Running task 7.0 in stage 0.0 (TID 7)
22/08/31 20:18:28 INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 334 ms on 10.112.82.59 (executor driver) (1/10)
22/08/31 20:18:28 INFO TaskSetManager: Finished task 3.0 in stage 0.0 (TID 3) in 323 ms on 10.112.82.59 (executor driver) (2/10)
22/08/31 20:18:28 INFO TaskSetManager: Finished task 1.0 in stage 0.0 (TID 1) in 332 ms on 10.112.82.59 (executor driver) (3/10)
22/08/31 20:18:28 INFO TaskSetManager: Finished task 2.0 in stage 0.0 (TID 2) in 338 ms on 10.112.82.59 (executor driver) (4/10)
22/08/31 20:18:28 INFO Executor: Finished task 5.0 in stage 0.0 (TID 5). 965 bytes result sent to driver
22/08/31 20:18:28 INFO TaskSetManager: Starting task 8.0 in stage 0.0 (TID 8) (10.112.82.59, executor driver, partition 8, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:28 INFO TaskSetManager: Finished task 5.0 in stage 0.0 (TID 5) in 139 ms on 10.112.82.59 (executor driver) (5/10)
22/08/31 20:18:28 INFO Executor: Running task 8.0 in stage 0.0 (TID 8)
22/08/31 20:18:29 INFO Executor: Finished task 4.0 in stage 0.0 (TID 4). 965 bytes result sent to driver
22/08/31 20:18:29 INFO TaskSetManager: Starting task 9.0 in stage 0.0 (TID 9) (10.112.82.59, executor driver, partition 9, PROCESS_LOCAL, 4578 bytes) taskResourceAssignments Map()
22/08/31 20:18:29 INFO TaskSetManager: Finished task 4.0 in stage 0.0 (TID 4) in 184 ms on 10.112.82.59 (executor driver) (6/10)
22/08/31 20:18:29 INFO Executor: Finished task 7.0 in stage 0.0 (TID 7). 965 bytes result sent to driver
22/08/31 20:18:29 INFO Executor: Running task 9.0 in stage 0.0 (TID 9)
22/08/31 20:18:29 INFO TaskSetManager: Finished task 7.0 in stage 0.0 (TID 7) in 180 ms on 10.112.82.59 (executor driver) (7/10)
22/08/31 20:18:29 INFO Executor: Finished task 6.0 in stage 0.0 (TID 6). 965 bytes result sent to driver
22/08/31 20:18:29 INFO TaskSetManager: Finished task 6.0 in stage 0.0 (TID 6) in 190 ms on 10.112.82.59 (executor driver) (8/10)
22/08/31 20:18:29 INFO Executor: Finished task 8.0 in stage 0.0 (TID 8). 965 bytes result sent to driver
22/08/31 20:18:29 INFO TaskSetManager: Finished task 8.0 in stage 0.0 (TID 8) in 77 ms on 10.112.82.59 (executor driver) (9/10)
22/08/31 20:18:29 INFO Executor: Finished task 9.0 in stage 0.0 (TID 9). 965 bytes result sent to driver
22/08/31 20:18:29 INFO TaskSetManager: Finished task 9.0 in stage 0.0 (TID 9) in 69 ms on 10.112.82.59 (executor driver) (10/10)
22/08/31 20:18:29 INFO TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool 
22/08/31 20:18:29 INFO DAGScheduler: ResultStage 0 (reduce at SparkPi.scala:38) finished in 0.808 s
22/08/31 20:18:29 INFO DAGScheduler: Job 0 is finished. Cancelling potential speculative or zombie tasks for this job
22/08/31 20:18:29 INFO TaskSchedulerImpl: Killing all running tasks in stage 0: Stage finished
22/08/31 20:18:29 INFO DAGScheduler: Job 0 finished: reduce at SparkPi.scala:38, took 0.882169 s
Pi is roughly 3.14032714032714
22/08/31 20:18:29 INFO SparkUI: Stopped Spark web UI at http://10.112.82.59:4040
22/08/31 20:18:29 INFO MapOutputTrackerMasterEndpoint: MapOutputTrackerMasterEndpoint stopped!
22/08/31 20:18:29 INFO MemoryStore: MemoryStore cleared
22/08/31 20:18:29 INFO BlockManager: BlockManager stopped
22/08/31 20:18:29 INFO BlockManagerMaster: BlockManagerMaster stopped
22/08/31 20:18:29 INFO OutputCommitCoordinator$OutputCommitCoordinatorEndpoint: OutputCommitCoordinator stopped!
22/08/31 20:18:29 INFO SparkContext: Successfully stopped SparkContext
22/08/31 20:18:29 INFO ShutdownHookManager: Shutdown hook called
22/08/31 20:18:29 INFO ShutdownHookManager: Deleting directory /private/var/folders/b7/yhnw9hws0ng2w1_khl8nr2t40000gn/T/spark-12cd6bb6-84c2-4145-90bc-0aa083c2b6c4
22/08/31 20:18:29 INFO ShutdownHookManager: Deleting directory /private/var/folders/b7/yhnw9hws0ng2w1_khl8nr2t40000gn/T/spark-250b1c99-4798-4faf-b534-6ecdac448570
```
可以看到日志中：Pi is roughly 3.14032714032714，这就是Pi的运算结果。
# 快速上手
Spark开发可以使用VS Code工具或者IDEA。我使用的是IDEA。记下来简单介绍下使用IDEA创建一个项目。
因为我要使用scala语言去写spark（也可以使用Java、Python、R等语言），项目管理可以使用Maven、Gradle、SBT
SBT是scala项目的包管理工具，我这就使用SBT。
## 安装插件
IDEA开发scala需要安装scala插件
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209011344345.png)
使用使用maven，需要点击File菜单对项目进行scala框架支持。
## Hello World
搭建完项目后，实现个Hello world示例
```scala
package com.itlab1024.spark.start

object HelloWorld {
  def main(args: Array[String]): Unit = {
    println("hello world!") // hello world!
  }
}
```

## 创建项目
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209011456148.png)
## 引入spark依赖
这里我先映入spring-core包。其他的包以后用到再引入，build.sbt是sbt的依赖配置，类似maven的pom。
```sbt
ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.16"

lazy val root = (project in file("."))
  .settings(
    name := "Spark-Tutorial"
  )
libraryDependencies += "org.apache.spark" % "spark-core_2.12" % "3.3.0"
```
上面的例子还没有使用到spark，接下来写一个Spark的经典例子，WordCount。统计文本中单词数目。
如何统计呢？ 
a.将文件中的数据读入到内存，结果是一行一行的。 
b.将每行通过空格切分 
c.转化为元组，比如(K,V)，K代表单词，V代表单词的数量（写死1）
d.然后通过K聚合将所有V加起
新建一个wordCount.txt
```text
I am learning spark
I am learning go
I am learning scala
I am learning java
```
接下来通过代码来实现该功能
```scala
package com.itlab1024.spark.start

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf: SparkConf = new SparkConf().setAppName("统计单词数量").setMaster("local[*]")
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
```
运行结果：
```text
-------省略一部分日志------
22/09/01 15:13:46 INFO DAGScheduler: Job 0 finished: collect at WordCount.scala:20, took 3.147017 s
(scala,1)
(learning,4)
(spark,1)
(am,4)
(I,4)
(java,1)
(go,1)
22/09/01 15:13:46 INFO SparkUI: Stopped Spark web UI at http://10.112.82.59:4040
-------省略一部分日志------
```
可以看到日志中打印出来了统计的结果：
```text
(scala,1)
(learning,4)
(spark,1)
(am,4)
(I,4)
(java,1)
(go,1)
```

> 看到上面的代码有很多不理解的地方，比如setMaster里的local是什么意思？RDD是什么？
> 别着急接下来慢慢学习。

# 运行环境
Spark的运行环境有开发环境、本地环境、独立环境（Standalone）、Hadoop Yarn模式、Kubernetes环境。 ##开发模式：上面我们执行WordCount代码的环境就是开发环境，严格来说他并不是一种环境，仅仅用于开发。
* **本地模式**：使用spark-shell开启的环境就是本地环境，用于开发、测试、调试、演示等基本使用。
* **独立模式Stanalone**：独立模式是最简单的模式，他是主从架构，生产可用。
* **Hadoop Yarn模式**：据说国内主流，咱也不清楚，生产可用。
* **Kubernetes模式**：这个我觉得肯定是流行的，因为容器化现在非常流行，生产可用。
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209011528643.png)

## 开发环境
没啥好说的
## 本地模式(单机)
其实之前讲解shell的时候已经使用了本地模式，这里主要说下之前没有介绍的
WebUI,本地环境会启动一个WebUI界面,启动日志中我们可以看到如下日志：
```text
22/09/01 15:39:20 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Spark context Web UI available at http://10.112.82.59:4040
Spark context available as 'sc' (master = local[*], app id = local-1662017962631).
```
http://10.112.82.59:4040就是web界面
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209011540886.png)
我们将上面WordCount的代码放到shell中执行，首先要在spark安装目录下创建wordCount.txt文件。
```shell
➜  files pwd
/Users/itlab/dev-tools/spark-3.3.0-bin-hadoop3/files
➜  files cat wordCount.txt 
I am learning spark
I am learning go
I am learning scala
I am learning java
```
接下来在shell中执行WordCount的代码
```shell
scala> sc.textFile("files/wordCount.txt")
res2: org.apache.spark.rdd.RDD[String] = files/wordCount.txt MapPartitionsRDD[5] at textFile at <console>:24

scala> val rdd = sc.textFile("files/wordCount.txt")
rdd: org.apache.spark.rdd.RDD[String] = files/wordCount.txt MapPartitionsRDD[7] at textFile at <console>:23

scala> val flatRDD = rdd.flatMap(_.split(" "))
flatRDD: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[8] at flatMap at <console>:23

scala> val tupleRDD = flatRDD.map((_, 1))
tupleRDD: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[9] at map at <console>:23

scala> val result = tupleRDD.reduceByKey(_ + _)
result: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[10] at reduceByKey at <console>:23

scala> result.collect().foreach(println)
(scala,1)
(learning,4)
(am,4)
(java,1)
(go,1)
(spark,1)
(I,4)
```
如果不降文件放入spark目录下会提示如下
```text
org.apache.hadoop.mapred.InvalidInputException: Input path does not exist: file:/Users/itlab/dev-tools/spark-3.3.0-bin-hadoop3/files/wordCount.txt
  at org.apache.hadoop.mapred.FileInputFormat.singleThreadedListStatus(FileInputFormat.java:304)
  at org.apache.hadoop.mapred.FileInputFormat.listStatus(FileInputFormat.java:244)
  at org.apache.hadoop.mapred.FileInputFormat.getSplits(FileInputFormat.java:332)
  at org.apache.spark.rdd.HadoopRDD.getPartitions(HadoopRDD.scala:208)
  at org.apache.spark.rdd.RDD.$anonfun$partitions$2(RDD.scala:292)
  at scala.Option.getOrElse(Option.scala:189)
  at org.apache.spark.rdd.RDD.partitions(RDD.scala:288)
  at org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:49)
  at org.apache.spark.rdd.RDD.$anonfun$partitions$2(RDD.scala:292)
  at scala.Option.getOrElse(Option.scala:189)
  at org.apache.spark.rdd.RDD.partitions(RDD.scala:288)
  at org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:49)
  at org.apache.spark.rdd.RDD.$anonfun$partitions$2(RDD.scala:292)
  at scala.Option.getOrElse(Option.scala:189)
  at org.apache.spark.rdd.RDD.partitions(RDD.scala:288)
  at org.apache.spark.rdd.MapPartitionsRDD.getPartitions(MapPartitionsRDD.scala:49)
  at org.apache.spark.rdd.RDD.$anonfun$partitions$2(RDD.scala:292)
  at scala.Option.getOrElse(Option.scala:189)
  at org.apache.spark.rdd.RDD.partitions(RDD.scala:288)
  at org.apache.spark.Partitioner$.$anonfun$defaultPartitioner$4(Partitioner.scala:78)
  at org.apache.spark.Partitioner$.$anonfun$defaultPartitioner$4$adapted(Partitioner.scala:78)
  at scala.collection.immutable.List.map(List.scala:293)
  at org.apache.spark.Partitioner$.defaultPartitioner(Partitioner.scala:78)
  at org.apache.spark.rdd.PairRDDFunctions.$anonfun$reduceByKey$4(PairRDDFunctions.scala:323)
  at org.apache.spark.rdd.RDDOperationScope$.withScope(RDDOperationScope.scala:151)
  at org.apache.spark.rdd.RDDOperationScope$.withScope(RDDOperationScope.scala:112)
  at org.apache.spark.rdd.RDD.withScope(RDD.scala:406)
  at org.apache.spark.rdd.PairRDDFunctions.reduceByKey(PairRDDFunctions.scala:323)
  ... 47 elided
Caused by: java.io.IOException: Input path does not exist: file:/Users/itlab/dev-tools/spark-3.3.0-bin-hadoop3/files/wordCount.txt
  at org.apache.hadoop.mapred.FileInputFormat.singleThreadedListStatus(FileInputFormat.java:278)
  ... 74 more
```
重新打开WebUI，会看到多了一个Job。
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209011956399.png)
这个Job就是刚才提交的WordCount。
## 独立模式Standalone（集群）
spark独立模式官网有详细的说明，接下来我简单介绍下该模式，并创建一个独立模式的集群环境。
Spark独立模式采用的是Master-Slave主从模式。集群由自身管理，高可用的集群采用主备Master实现，通过Zookeeper协调。
### 准备
首先要准备四台机器（可以是虚拟机），我使用[vagrant](https://www.vagrantup.com/)搭配[virtualbox](https://www.virtualbox.org/)来创建虚拟机.
```shell
➜  spark-stanalone cat Vagrantfile 
Vagrant.configure("2") do |config|
   (1..4).each do |i|
        config.vm.define "spark-standalone#{i}" do |node|
            # 设置虚拟机的Box。指定本地的box文件
            node.vm.box = "centos/7"

            # 设置虚拟机的主机名
            node.vm.hostname="spark-standalone#{i}"

            # 设置虚拟机的IP
            node.vm.network "private_network", ip: "192.168.56.#{i}"

            # VirtaulBox相关配置
            node.vm.provider "virtualbox" do |v|
                # 设置虚拟机的名称
                v.name = "spark-standalone#{i}"
                # 设置虚拟机的内存大小
                v.memory = 1024
                # 设置虚拟机的CPU个数
                v.cpus = 1
            end
        end
   end
end
```
然后执行vagrant up，可以看到开始创建虚拟机了，第一次使用可能没有centos/7包，会自动下载，但是因为服务器在国外很慢，可以先下载下来再使用vagrant box add。
额~出现了如下问题
```shell
➜  spark-stanalone vagrant up
Bringing machine 'spark-standalone1' up with 'virtualbox' provider...
Bringing machine 'spark-standalone2' up with 'virtualbox' provider...
Bringing machine 'spark-standalone3' up with 'virtualbox' provider...
Bringing machine 'spark-standalone4' up with 'virtualbox' provider...
==> spark-standalone1: Box 'centos/7' could not be found. Attempting to find and install...
    spark-standalone1: Box Provider: virtualbox
    spark-standalone1: Box Version: >= 0
==> spark-standalone1: Loading metadata for box 'centos/7'
    spark-standalone1: URL: https://vagrantcloud.com/centos/7
==> spark-standalone1: Adding box 'centos/7' (v2004.01) for provider: virtualbox
    spark-standalone1: Downloading: https://vagrantcloud.com/centos/boxes/7/versions/2004.01/providers/virtualbox.box
==> spark-standalone1: Box download is resuming from prior download progress
Download redirected to host: cloud.centos.org
    spark-standalone1: Calculating and comparing box checksum...
==> spark-standalone1: Successfully added box 'centos/7' (v2004.01) for 'virtualbox'!
==> spark-standalone1: You assigned a static IP ending in ".1" to this machine.
==> spark-standalone1: This is very often used by the router and can cause the
==> spark-standalone1: network to not work properly. If the network doesn't work
==> spark-standalone1: properly, try changing this IP.
==> spark-standalone1: Importing base box 'centos/7'...
==> spark-standalone1: Matching MAC address for NAT networking...
==> spark-standalone1: You assigned a static IP ending in ".1" to this machine.
==> spark-standalone1: This is very often used by the router and can cause the
==> spark-standalone1: network to not work properly. If the network doesn't work
==> spark-standalone1: properly, try changing this IP.
==> spark-standalone1: Checking if box 'centos/7' version '2004.01' is up to date...
==> spark-standalone1: Setting the name of the VM: spark-standalone1
==> spark-standalone1: Clearing any previously set network interfaces...
There was an error while executing `VBoxManage`, a CLI used by Vagrant
for controlling VirtualBox. The command and stderr is shown below.

Command: ["hostonlyif", "create"]

Stderr: 0%...
Progress state: NS_ERROR_FAILURE
VBoxManage: error: Failed to create the host-only adapter
VBoxManage: error: VBoxNetAdpCtl: Error while adding new interface: failed to open /dev/vboxnetctl: No such file or directory
VBoxManage: error: Details: code NS_ERROR_FAILURE (0x80004005), component HostNetworkInterfaceWrap, interface IHostNetworkInterface
VBoxManage: error: Context: "RTEXITCODE handleCreate(HandlerArg *)" at line 95 of file VBoxManageHostonly.cpp
```
解决方案https://stackoverflow.com/questions/21069908/vboxmanage-error-failed-to-create-the-host-only-adapter
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209021116771.png)
重新执行vagrant up
```shell
➜  spark-stanalone vagrant up
Bringing machine 'spark-standalone1' up with 'virtualbox' provider...
Bringing machine 'spark-standalone2' up with 'virtualbox' provider...
Bringing machine 'spark-standalone3' up with 'virtualbox' provider...
Bringing machine 'spark-standalone4' up with 'virtualbox' provider...
==> spark-standalone1: You assigned a static IP ending in ".1" to this machine.
==> spark-standalone1: This is very often used by the router and can cause the
==> spark-standalone1: network to not work properly. If the network doesn't work
==> spark-standalone1: properly, try changing this IP.
==> spark-standalone1: Importing base box 'centos/7'...
==> spark-standalone1: Matching MAC address for NAT networking...
==> spark-standalone1: You assigned a static IP ending in ".1" to this machine.
==> spark-standalone1: This is very often used by the router and can cause the
==> spark-standalone1: network to not work properly. If the network doesn't work
==> spark-standalone1: properly, try changing this IP.
==> spark-standalone1: Checking if box 'centos/7' version '2004.01' is up to date...
==> spark-standalone1: Setting the name of the VM: spark-standalone1
==> spark-standalone1: Clearing any previously set network interfaces...
==> spark-standalone1: Preparing network interfaces based on configuration...
    spark-standalone1: Adapter 1: nat
    spark-standalone1: Adapter 2: hostonly
==> spark-standalone1: Forwarding ports...
    spark-standalone1: 22 (guest) => 2222 (host) (adapter 1)
==> spark-standalone1: Running 'pre-boot' VM customizations...
==> spark-standalone1: Booting VM...
==> spark-standalone1: Waiting for machine to boot. This may take a few minutes...
    spark-standalone1: SSH address: 127.0.0.1:2222
    spark-standalone1: SSH username: vagrant
    spark-standalone1: SSH auth method: private key
    spark-standalone1: 
    spark-standalone1: Vagrant insecure key detected. Vagrant will automatically replace
    spark-standalone1: this with a newly generated keypair for better security.
    spark-standalone1: 
    spark-standalone1: Inserting generated public key within guest...
    spark-standalone1: Removing insecure key from the guest if it's present...
    spark-standalone1: Key inserted! Disconnecting and reconnecting using new SSH key...
==> spark-standalone1: Machine booted and ready!
==> spark-standalone1: Checking for guest additions in VM...
    spark-standalone1: No guest additions were detected on the base box for this VM! Guest
    spark-standalone1: additions are required for forwarded ports, shared folders, host only
    spark-standalone1: networking, and more. If SSH fails on this machine, please install
    spark-standalone1: the guest additions and repackage the box to continue.
    spark-standalone1: 
    spark-standalone1: This is not an error message; everything may continue to work properly,
    spark-standalone1: in which case you may ignore this message.
==> spark-standalone1: Setting hostname...
==> spark-standalone1: Configuring and enabling network interfaces...
==> spark-standalone1: Rsyncing folder: /Users/itlab/vms/spark-stanalone/ => /vagrant
.........省略很多日志........
```
打开VirtualBox可以看到四个虚拟机已经创建完毕。
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209021129927.png)
使用vagrant ssh 主机名来连接虚拟机，例如

```shell
➜  spark-stanalone vagrant ssh spark-standalone1
[vagrant@spark-standalone1 ~]$ 
```



四台虚拟机使用角色说明如下：



| 主机名            | 角色         | IP地址         |
| ----------------- | ------------ | -------------- |
| spark-standalone1 | Master       | 192.168.56.101 |
| spark-standalone2 | Master(备用) | 192.168.56.102 |
| spark-standalone3 | Slave        | 192.168.56.103 |
| spark-standalone4 | Slave        | 192.168.56.104 |

修改四个主机的hosts，使其能够通过主机名互通

```shell
[root@spark-standalone1 vagrant]# sudo su 
[root@spark-standalone1 vagrant]# cat >> /etc/hosts << EOF
192.168.56.101 spark-standalone1
192.168.56.102 spark-standalone2
192.168.56.103 spark-standalone3
192.168.56.104 spark-standalone4
EOF
```

四个机器都安装JDK安装，spark运行依赖JDK。我安装的是JDK1.8

```shell
sudo yum install java-1.8.0-openjdk* -y
```

### 搭建(手动)

手动搭建使用sbin目录下的start-master.sh、stop-master.sh、start-worker.sh、stop-worker.sh这几个文件。

不用修改任何配置文件，通过命令行参数就可以启动集群。

步骤就是先启动master，然后启动worker，此时指定master地址。

首先将本地下载的spark包上传到四个虚拟机中，我使用的vagrant scp插件。

```shell
# 安装vagrant scp插件
➜  spark-stanalone vagrant plugin install vagrant-scp 
Installing the 'vagrant-scp' plugin. This can take a few minutes...
Fetching vagrant-scp-0.5.9.gem
Installed the plugin 'vagrant-scp (0.5.9)'!
# ➜  spark-stanalone vagrant scp ~/Downloads/spark-3.3.0-bin-hadoop3.tgz spark-standalone1:/home/vagrant
Warning: Permanently added '[127.0.0.1]:2222' (ED25519) to the list of known hosts.
spark-3.3.0-bin-hadoop3.tgz                   100%  285MB  42.5MB/s   00:06
# 其他三个的日志省略
```

分别在四个服务器上解压并重命名文件夹

```shell
➜  tar zxvf spark-3.3.0-bin-hadoop3.tgz && mv spark-3.3.0-bin-hadoop3 spark-standalone
```

然后执行如下命令启动Master（spark-standalone1节点）

```shell
[vagrant@spark-standalone1 spark-standalone]$ sbin/start-master.sh -h spark-standalone1 -p 7077 --webui-port 8080
starting org.apache.spark.deploy.master.Master, logging to /home/vagrant/spark-standalone/logs/spark-vagrant-org.apache.spark.deploy.master.Master-1-spark-standalone1.out
```

启动完毕后在宿主机上访问mater的webUI，http://spark-standalone1:8080

![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209021427746.png)

可以看到此时并没有workder。

接下来启动工作节点（spark-standalone3和spark-standalone4）

```shell
[vagrant@spark-standalone3 spark-standalone]$ sbin/start-worker.sh spark://spark-standalone1:7077 -h spark-standalone3 --webui-port 8081
starting org.apache.spark.deploy.worker.Worker, logging to /home/vagrant/spark-standalone/logs/spark-vagrant-org.apache.spark.deploy.worker.Worker-1-spark-standalone3.out
```

spark://spark-standalone1:7077是集群地址，在webui上能获取到。

此时spark-standalone3节点的worker已经启动，看看webUI上有何变化。

![image-20220903172258429](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209031722623.png)

可以看到增加了一个worker。

再把spark-standalone4加入进来

```shell
[vagrant@spark-standalone4 spark-standalone]$ sbin/start-worker.sh spark://spark-standalone1:7077 -h spark-standalone4 --webui-port 8081
starting org.apache.spark.deploy.worker.Worker, logging to /home/vagrant/spark-standalone/logs/spark-vagrant-org.apache.spark.deploy.worker.Worker-1-spark-standalone4.out
```

查看WebUI

![image-20220903172506133](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209031725226.png)

也成功加入了进来。

### 搭建（脚本启动）

上面的启动方式是先启动master，然后一个一个workder的启动，这在节点少的情况下还可以，但是节点多的情况下就麻烦了。

使用脚本启动主要是是如下步骤：

A. Master通过SSH控制Worker节点，所以要保证各个节点能够通过ssh访问，并且无密码或者通过私钥访问。

B. 配置conf下的spark-env.sh，在这里配置Master节点的信息（包括主机名、端口等），默认没有这个文件，只需要将其下的spark-env.sh.template重命名为spark-env.sh即可。

C. conf下的workers.template重命名为workers，在这里配置所有workder的主机名。

接下来来操作下。

配置免密：

四个节点需要配置允许密码登录

```shell
sudo vi /etc/ssh/sshd_config
```

将里面的PasswordAuthentication no修改为PasswordAuthentication yes。

![image-20220903214717899](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209032147241.png)

然后重启ssd服务

```shell
[vagrant@spark-standalone4 ~]$ systemctl restart sshd
==== AUTHENTICATING FOR org.freedesktop.systemd1.manage-units ===
Authentication is required to manage system services or units.
Authenticating as: root
Password: 
==== AUTHENTICATION COMPLETE ===
```

在主节点（spark-standalone1）使用如下命令生成公私钥

```shell
[vagrant@spark-standalone1 ~]$ ssh-keygen -t rsa
Generating public/private rsa key pair.
Enter file in which to save the key (/home/vagrant/.ssh/id_rsa): 
Enter passphrase (empty for no passphrase): 
Enter same passphrase again: 
Your identification has been saved in /home/vagrant/.ssh/id_rsa.
Your public key has been saved in /home/vagrant/.ssh/id_rsa.pub.
The key fingerprint is:
SHA256:3xWV7mKV24HBpVNzPIwrmvFde7nZe3gEy+k/GKdUk4A vagrant@spark-standalone4
The key's randomart image is:
+---[RSA 2048]----+
|            o +=+|
|           E =o=+|
|             oB +|
|         . . oo@ |
|        S = o.*+B|
|         + o B+*+|
|          . +.*o=|
|             oo++|
|               +=|
+----[SHA256]-----+
```

一路回车即可

然后将其拷贝到其他服务器

```
[vagrant@spark-standalone4 ~]$ ssh-copy-id vagrant@spark-standalone1
[vagrant@spark-standalone4 ~]$ ssh-copy-id vagrant@spark-standalone2
[vagrant@spark-standalone4 ~]$ ssh-copy-id vagrant@spark-standalone3
[vagrant@spark-standalone4 ~]$ ssh-copy-id vagrant@spark-standalone4
```

会让输入密码，我使用的是vagrant工具，默认密码是vagrant。

**特别提醒** ：如果使用的是root用户，/etc/ssh/sshd_config需要修改允许Root登录，遇到问题问度娘。



首先修改spark-env.sh，这里有很多配置项，我就简单配置下主要的，使其能够正常启动集群即可。

```shell
[vagrant@spark-standalone1 conf]$ cat spark-env.sh 
#!/usr/bin/env bash

#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# This file is sourced when running various Spark programs.
# Copy it as spark-env.sh and edit that to configure Spark for your site.

# Options read when launching programs locally with
# ./bin/run-example or ./bin/spark-submit
# - HADOOP_CONF_DIR, to point Spark towards Hadoop configuration files
# - SPARK_LOCAL_IP, to set the IP address Spark binds to on this node
# - SPARK_PUBLIC_DNS, to set the public dns name of the driver program

# Options read by executors and drivers running inside the cluster
# - SPARK_LOCAL_IP, to set the IP address Spark binds to on this node
# - SPARK_PUBLIC_DNS, to set the public DNS name of the driver program
# - SPARK_LOCAL_DIRS, storage directories to use on this node for shuffle and RDD data
# - MESOS_NATIVE_JAVA_LIBRARY, to point to your libmesos.so if you use Mesos

# Options read in any mode
# - SPARK_CONF_DIR, Alternate conf dir. (Default: ${SPARK_HOME}/conf)
# - SPARK_EXECUTOR_CORES, Number of cores for the executors (Default: 1).
# - SPARK_EXECUTOR_MEMORY, Memory per Executor (e.g. 1000M, 2G) (Default: 1G)
# - SPARK_DRIVER_MEMORY, Memory for Driver (e.g. 1000M, 2G) (Default: 1G)

# Options read in any cluster manager using HDFS
# - HADOOP_CONF_DIR, to point Spark towards Hadoop configuration files

# Options read in YARN client/cluster mode
# - YARN_CONF_DIR, to point Spark towards YARN configuration files when you use YARN

# Options for the daemons used in the standalone deploy mode
# - SPARK_MASTER_HOST, to bind the master to a different IP address or hostname
# - SPARK_MASTER_PORT / SPARK_MASTER_WEBUI_PORT, to use non-default ports for the master
# - SPARK_MASTER_OPTS, to set config properties only for the master (e.g. "-Dx=y")
# - SPARK_WORKER_CORES, to set the number of cores to use on this machine
# - SPARK_WORKER_MEMORY, to set how much total memory workers have to give executors (e.g. 1000m, 2g)
# - SPARK_WORKER_PORT / SPARK_WORKER_WEBUI_PORT, to use non-default ports for the worker
# - SPARK_WORKER_DIR, to set the working directory of worker processes
# - SPARK_WORKER_OPTS, to set config properties only for the worker (e.g. "-Dx=y")
# - SPARK_DAEMON_MEMORY, to allocate to the master, worker and history server themselves (default: 1g).
# - SPARK_HISTORY_OPTS, to set config properties only for the history server (e.g. "-Dx=y")
# - SPARK_SHUFFLE_OPTS, to set config properties only for the external shuffle service (e.g. "-Dx=y")
# - SPARK_DAEMON_JAVA_OPTS, to set config properties for all daemons (e.g. "-Dx=y")
# - SPARK_DAEMON_CLASSPATH, to set the classpath for all daemons
# - SPARK_PUBLIC_DNS, to set the public dns name of the master or workers

# Options for launcher
# - SPARK_LAUNCHER_OPTS, to set config properties and Java options for the launcher (e.g. "-Dx=y")

# Generic options for the daemons used in the standalone deploy mode
# - SPARK_CONF_DIR      Alternate conf dir. (Default: ${SPARK_HOME}/conf)
# - SPARK_LOG_DIR       Where log files are stored.  (Default: ${SPARK_HOME}/logs)
# - SPARK_LOG_MAX_FILES Max log files of Spark daemons can rotate to. Default is 5.
# - SPARK_PID_DIR       Where the pid file is stored. (Default: /tmp)
# - SPARK_IDENT_STRING  A string representing this instance of spark. (Default: $USER)
# - SPARK_NICENESS      The scheduling priority for daemons. (Default: 0)
# - SPARK_NO_DAEMONIZE  Run the proposed command in the foreground. It will not output a PID file.
# Options for native BLAS, like Intel MKL, OpenBLAS, and so on.
# You might get better performance to enable these options if using native BLAS (see SPARK-21305).
# - MKL_NUM_THREADS=1        Disable multi-threading of Intel MKL
# - OPENBLAS_NUM_THREADS=1   Disable multi-threading of OpenBLAS
# 这里我就配置了master的信息，worker使用默认的
SPARK_MASTER_HOST=spark-standalone1
SPARK_MASTER_PORT=7077
SPARK_MASTER_WEBUI_PORT=8080
```

conf/workers文件

```shell
[vagrant@spark-standalone1 conf]$ cat workers 
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# A Spark Worker will be started on each of the machines listed below.
spark-standalone3
spark-standalone4
```

**特别注意**，这两个文件要同步到所有节点。

**启动集群**，执行sbin/start-all.sh

```shell
[vagrant@spark-standalone1 spark-standalone]$ sbin/start-all.sh 
starting org.apache.spark.deploy.master.Master, logging to /home/vagrant/spark-standalone/logs/spark-vagrant-org.apache.spark.deploy.master.Master-1-spark-standalone1.out
spark-standalone3: starting org.apache.spark.deploy.worker.Worker, logging to /home/vagrant/spark-standalone/logs/spark-vagrant-org.apache.spark.deploy.worker.Worker-1-spark-standalone3.out
spark-standalone4: starting org.apache.spark.deploy.worker.Worker, logging to /home/vagrant/spark-standalone/logs/spark-vagrant-org.apache.spark.deploy.worker.Worker-1-spark-standalone4.out
```

从日志上看一个主（spark-standalone1）,两个从（spark-standalone3和spark-standalone4）都已经启动，查看下UI看看是否启动成功。

![image-20220903220549261](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209032205543.png)

没有问题！

### 提交应用

使用spark-submit脚本提交应用

```shell
[vagrant@spark-standalone1 spark-standalone]$ bin/spark-submit --class org.apache.spark.examples.SparkPi --master spark://spark-standalone1:7077 ./examples/jars/spark-examples_2.12-3.3.0.jar 10
22/09/03 14:09:47 INFO SparkContext: Running Spark version 3.3.0
22/09/03 14:09:47 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
22/09/03 14:09:47 INFO ResourceUtils: ==============================================================
22/09/03 14:09:47 INFO ResourceUtils: No custom resources configured for spark.driver.
22/09/03 14:09:47 INFO ResourceUtils: ==============================================================
22/09/03 14:09:47 INFO SparkContext: Submitted application: Spark Pi
22/09/03 14:09:47 INFO ResourceProfile: Default ResourceProfile created, executor resources: Map(cores -> name: cores, amount: 1, script: , vendor: , memory -> name: memory, amount: 1024, script: , vendor: , offHeap -> name: offHeap, amount: 0, script: , vendor: ), task resources: Map(cpus -> name: cpus, amount: 1.0)
22/09/03 14:09:48 INFO ResourceProfile: Limiting resource is cpu
-----------省略一部分日志-------------
```

再次查看WebUI

![image-20220903221049928](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209032210162.png)

Good！！！

### 历史服务

Spark有WebUI监控，但是一旦重启服务器，历史就会丢失，Spark提供历史服务，需要将数据保存到本地文件或者HDFS（Hadoop 分布式文件系统）中。

HDFS搭建这里我就不演示了，自行查看[Hadoop官网]( https://hadoop.apache.org/)文档搭建。

配置conf/spark-defaults.conf文件

```text
[vagrant@spark-standalone1 conf]$ cat spark-defaults.conf
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# Default system properties included when running spark-submit.
# This is useful for setting default environmental settings.

# Example:
spark.master                     spark://spark-standalone1:7077
spark.eventLog.enabled           true
spark.eventLog.dir               hdfs://spark-standalone1:9000/spark-events
# spark.serializer                 org.apache.spark.serializer.KryoSerializer
# spark.driver.memory              5g
# spark.executor.extraJavaOptions  -XX:+PrintGCDetails -Dkey=value -Dnumbers="one two three
```

主要修改了一下三处

```text
spark.master                     spark://spark-standalone1:7077
spark.eventLog.enabled           true
spark.eventLog.dir               hdfs://spark-standalone1:9000/spark-events
```

然后修改conf/spark-env.sh

```
[vagrant@spark-standalone1 conf]$ cat spark-env.sh 
#!/usr/bin/env bash

#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# This file is sourced when running various Spark programs.
# Copy it as spark-env.sh and edit that to configure Spark for your site.

# Options read when launching programs locally with
# ./bin/run-example or ./bin/spark-submit
# - HADOOP_CONF_DIR, to point Spark towards Hadoop configuration files
# - SPARK_LOCAL_IP, to set the IP address Spark binds to on this node
# - SPARK_PUBLIC_DNS, to set the public dns name of the driver program

# Options read by executors and drivers running inside the cluster
# - SPARK_LOCAL_IP, to set the IP address Spark binds to on this node
# - SPARK_PUBLIC_DNS, to set the public DNS name of the driver program
# - SPARK_LOCAL_DIRS, storage directories to use on this node for shuffle and RDD data
# - MESOS_NATIVE_JAVA_LIBRARY, to point to your libmesos.so if you use Mesos

# Options read in any mode
# - SPARK_CONF_DIR, Alternate conf dir. (Default: ${SPARK_HOME}/conf)
# - SPARK_EXECUTOR_CORES, Number of cores for the executors (Default: 1).
# - SPARK_EXECUTOR_MEMORY, Memory per Executor (e.g. 1000M, 2G) (Default: 1G)
# - SPARK_DRIVER_MEMORY, Memory for Driver (e.g. 1000M, 2G) (Default: 1G)

# Options read in any cluster manager using HDFS
# - HADOOP_CONF_DIR, to point Spark towards Hadoop configuration files

# Options read in YARN client/cluster mode
# - YARN_CONF_DIR, to point Spark towards YARN configuration files when you use YARN

# Options for the daemons used in the standalone deploy mode
# - SPARK_MASTER_HOST, to bind the master to a different IP address or hostname
# - SPARK_MASTER_PORT / SPARK_MASTER_WEBUI_PORT, to use non-default ports for the master
# - SPARK_MASTER_OPTS, to set config properties only for the master (e.g. "-Dx=y")
# - SPARK_WORKER_CORES, to set the number of cores to use on this machine
# - SPARK_WORKER_MEMORY, to set how much total memory workers have to give executors (e.g. 1000m, 2g)
# - SPARK_WORKER_PORT / SPARK_WORKER_WEBUI_PORT, to use non-default ports for the worker
# - SPARK_WORKER_DIR, to set the working directory of worker processes
# - SPARK_WORKER_OPTS, to set config properties only for the worker (e.g. "-Dx=y")
# - SPARK_DAEMON_MEMORY, to allocate to the master, worker and history server themselves (default: 1g).
# - SPARK_HISTORY_OPTS, to set config properties only for the history server (e.g. "-Dx=y")
# - SPARK_SHUFFLE_OPTS, to set config properties only for the external shuffle service (e.g. "-Dx=y")
# - SPARK_DAEMON_JAVA_OPTS, to set config properties for all daemons (e.g. "-Dx=y")
# - SPARK_DAEMON_CLASSPATH, to set the classpath for all daemons
# - SPARK_PUBLIC_DNS, to set the public dns name of the master or workers

# Options for launcher
# - SPARK_LAUNCHER_OPTS, to set config properties and Java options for the launcher (e.g. "-Dx=y")

# Generic options for the daemons used in the standalone deploy mode
# - SPARK_CONF_DIR      Alternate conf dir. (Default: ${SPARK_HOME}/conf)
# - SPARK_LOG_DIR       Where log files are stored.  (Default: ${SPARK_HOME}/logs)
# - SPARK_LOG_MAX_FILES Max log files of Spark daemons can rotate to. Default is 5.
# - SPARK_PID_DIR       Where the pid file is stored. (Default: /tmp)
# - SPARK_IDENT_STRING  A string representing this instance of spark. (Default: $USER)
# - SPARK_NICENESS      The scheduling priority for daemons. (Default: 0)
# - SPARK_NO_DAEMONIZE  Run the proposed command in the foreground. It will not output a PID file.
# Options for native BLAS, like Intel MKL, OpenBLAS, and so on.
# You might get better performance to enable these options if using native BLAS (see SPARK-21305).
# - MKL_NUM_THREADS=1        Disable multi-threading of Intel MKL
# - OPENBLAS_NUM_THREADS=1   Disable multi-threading of OpenBLAS
SPARK_MASTER_HOST=spark-standalone1
SPARK_MASTER_PORT=7077
SPARK_MASTER_WEBUI_PORT=8080
SPARK_HISTORY_OPTS="-Dspark.history.retainedApplications=3 
-Dspark.history.fs.logDirectory=hdfs://spark-standalone1:9000/spark-events"
```

增加了如下配置（历史服务）

```text
SPARK_HISTORY_OPTS="-Dspark.history.retainedApplications=3
-Dspark.history.fs.logDirectory=hdfs://spark-standalone1:9000/spark-events"
```

历史服务默认请求地址是：http://spark-standalone1:18080

![image-20220905103140306](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209051031707.png)

此时历史服务里没有记录，我来提交一个应用

```shell
[vagrant@spark-standalone1 spark-standalone]$ bin/spark-submit --class org.apache.spark.examples.SparkPi --master spark://spark-standalone1:7077 ./examples/jars/spark-examples_2.12-3.3.0.jar 10
```

重新查看历史服务

![image-20220905105303338](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209051053652.png)

可以查看到刚刚提交的spark记录。

查看下Hadoop hdfs的页面

![image-20220905105613132](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209051056262.png)

### 高可用

高可用模式之后单独出一篇文章。

## YARN模式

之后学习再更新一个单独章节

## Kubernetes模式

之后学习再更新一个单独章节。



# Spark运行架构

本章介绍下Spark的组件以及运行方式等信息，该篇特别重要，无论学习什么技术，了解其底层原理都是非常重要的。

![Spark 集群组件](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209041819018.png)

上图就是Spark运行时的基本结构。

上面图中都代表什么意思？我先放一张官网的集群词汇表

| 术语            | 含义                                                         |
| :-------------- | :----------------------------------------------------------- |
| Application     | 基于 Spark 构建的用户程序。包括集群上的Driver和Executors。   |
| Application jar | 包含用户程序的Jar包，在某些情况下，用户会希望创建一个超级Jar，其中包含应用程序和依赖项，用户的程序不应该包含Hadoop或者Spark的依赖，这些Jar应该在Spark运行时内，放到Spark的安装目录下的jars文件夹下，默认就有很多Jar。 |
| Driver program  | 运行应用程序的 main() 函数并创建 SparkContext 的进程         |
| Cluster manager | 用于获取集群上资源的外部服务（例如standalone manager、Mesos、YARN、Kubernetes）。 |
| Deploy mode     | 区分Driver运行在哪里的标志，如果模式是“cluster”，框架在集群内部启动Driver，如果是“client”提交者在集群外部启动Driver。 |
| Worker node     | 可以在集群中运行应用程序代码的任何节点，Executor就在工作节点上。 |
| Executor        | 为工作节点上的Application启动的进程，它运行Task并将数据保存在内存或磁盘存储中。每个Application都有自己的Executors。 |
| Task            | 发送个Executor的工作单元                                     |
| Job             | 由多个Task组成的过个并行计算，这些Task响应Spark的Action（一种算子，Spark中有两种算子，另一种是Transform），Driver的日志能看到Job的相关日志信息 |
| Stage           | 每一个Job都会被分割为更小的Task集合，Each job gets divided into smaller sets of tasks called *stages* that depend on each other (similar to the map and reduce stages in MapReduce); you'll see this term used in the driver's logs. |



## 核心组件

Spark有两个核心组件，Driver和Executor

### Driver

Driver用于执行应用程序的main方法，他在作业执行的时候主要负责如下工作：

* 将用户程序转化为Job

* 在Executor之间调度任务

* 跟踪Executor的执行情况

* 通过WebUI查询运行情况

Driver默认运行在提交任务的机器上（因为提交任务默认方式（deploy-mode）使用的是客户端模式(client)），如果是集群模式（cluster），则集群管理器会随机选择一个worker启动Driver，之前我提交应用的方式因为没有指定deploy-mode参数，所以使用默认client模式。

### Executor

Executor也是一个进程，他运行在Worker节点，负责执行Spark的任务，将结果返回给Driver，同时他也提供为需要缓存的RDD提供内存存储。



## 提交任务流程

任务提交使用Spark目录下的bin/spark-submit执行，他有很多参数可以执行，如下表

| 参数                     | 解释                                                         | 可选值举例                                                |
| ------------------------ | ------------------------------------------------------------ | --------------------------------------------------------- |
| --class                  | Spark程序中包含主函数的类完全名                              | --class org.apache.spark.examples.SparkPi                 |
| --master                 | Spark程序运行的模式                                          | 本地模式：local[*]、spark://spark-standalone1:7077 、Yarn |
| --deploy-mode            | 提交应用模式，client和cluster，默认是client，client模式主要用于开发和测试，生产环境必须使用cluster |                                                           |
| --executor-memory 1G     | 指定每个executor可用内存为1G                                 | 符合集群内存配置即可，具体情况具体分析。                  |
| --total-executor-cores 2 | 指定所有executor使用的cpu核数为2个                           |                                                           |
| application-jar          | 打包好的应用jar，包含依赖。这个URL在集群中全局可见。 比如hdfs:// 共享存储系统，如果是file:// path，那么所有的节点的path都包含同样的jar |                                                           |
| application-arguments    | 传给main()方法的参数                                         |                                                           |

接下来我主要介绍下客户端模式和集群模式下任务提交的流程。

### 客户端模式

这是默认的模式，借用一张网络图来说明提交流程。

![img](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209081603802.png)

用户在本地机器上执行bin/submit脚本后，会在本机上启动一个JVM进程，就是Driver，Driver解析（转化为Job等）应用后将其注册到master，Master根据资源的需求获取worker资源（启动Executor进程），然后Executor会反向注册给Driver，之后Driver会将Task分配给具体的Executor执行，执行完毕后后Executor会将结果反馈给Driver。

### 集群模式

![img](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209081600166.jpeg)

集群模式提交流程跟客户端模式是类似的，不同的是Driver的执行地点，客户端是在提交应用的那个机器上启动Driver，集群模式下，是Master随机找一个Worker运行Driver。其他没有什么区别。



这样做的目的无非就是在提交任务多的时候，通过多worker的特点将压力减小。试想客户端模式下，如果任务过多，就会启动很多进程，这无疑会增加计算机的负担。



# 核心编程

RDD（Resilient Distributed Datasets）：弹性分布式数据集，他永远是一个集合，他是Spark的核心部分，Spark-SQL等上层架构都是基于RDD来实现的。

RDD数据是分布式存储的，也就是按照不同的分区存储。可以根据内置的方法自由扩展分区或者缩小分区（看实际业务情况）。

## 初识算子

RDD有很多方法，这些方法叫做算子，算子主要分为两种，一种是转换（Transformations），一种是动作(Action)。

转换算子主要用于定义RDD处理流程，比如map，flatMap等等。

动作算子用于触发执行，因为Spark中的任务执行是惰性的，只有触发动作算子的时候才会真正的计算，比如

reduceByKey等等。

## DAG（Directed Acyclic Graph）有向无环图

顾名思义就是一个有方向的但是不能形成闭环的图，这就是说RDD所有的算子都遵循这样的规范。

我打算使用之前的wordcount例子来具体讲解下DAG

为了方便，我先试用spark执行wordCount代码。

```shell
itlab@itlab1024com ~/dev-tools/spark-3.3.0-bin-hadoop3$ bin/spark-shell        
22/09/08 16:32:08 WARN Utils: Your hostname, itlab1024com.local resolves to a loopback address: 127.0.0.1; using 10.112.82.59 instead (on interface en0)
22/09/08 16:32:08 WARN Utils: Set SPARK_LOCAL_IP if you need to bind to another address
Setting default log level to "WARN".
To adjust logging level use sc.setLogLevel(newLevel). For SparkR, use setLogLevel(newLevel).
22/09/08 16:32:16 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
Spark context Web UI available at http://10.112.82.59:4040
Spark context available as 'sc' (master = local[*], app id = local-1662625937869).
Spark session available as 'spark'.
Welcome to
      ____              __
     / __/__  ___ _____/ /__
    _\ \/ _ \/ _ `/ __/  '_/
   /___/ .__/\_,_/_/ /_/\_\   version 3.3.0
      /_/
         
Using Scala version 2.12.15 (Java HotSpot(TM) 64-Bit Server VM, Java 17.0.3.1)
Type in expressions to have them evaluated.
Type :help for more information.

scala>     //1. 将文件中的数据读入到内存，结果是一行一行的。

scala>     val rdd = sc.textFile("files/wordCount.txt")
rdd: org.apache.spark.rdd.RDD[String] = files/wordCount.txt MapPartitionsRDD[1] at textFile at <console>:23

scala>     //2. 将每行通过空格切分

scala>     val flatRDD = rdd.flatMap(_.split(" "))
flatRDD: org.apache.spark.rdd.RDD[String] = MapPartitionsRDD[2] at flatMap at <console>:23

scala>     //3. 转化为元组，比如(K,V)，K代表单词，V代表单词的数量（写死1）

scala>     val tupleRDD = flatRDD.map((_, 1))
tupleRDD: org.apache.spark.rdd.RDD[(String, Int)] = MapPartitionsRDD[3] at map at <console>:23

scala>     //4. 然后通过K聚合将所有V加起

scala>     val result = tupleRDD.reduceByKey(_ + _)
result: org.apache.spark.rdd.RDD[(String, Int)] = ShuffledRDD[4] at reduceByKey at <console>:23

scala>     // 打印

scala>     result.collect().foreach(println)
(scala,1)
(learning,4)
(am,4)
(java,1)
(go,1)
(spark,1)
(I,4)
```

然后打开WebUI。

![image-20220908163421293](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209081634639.png)

进入后再点击

![image-20220908163509088](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209081635258.png)

这个图就是DAG。

看到这就会有很多疑问，图1中的job是什么意思？有job0，会有job1吗？图2为什么是这样的？stage是什么？如何划分的？Task是如何划分的。

Spark对应用进行处理，可以分解为多个Job，依据主要是根据Action算子，遇到Action算子就会拆解为job，而每一个Job中如果遇到Shuffle算子（洗牌算子，数据会重新分区），就会拆解为stage，spark会将某个或者某些算子放到一起组装为一个Task（Spark自身有优化，会将某些算子放到一起，拆分规则我暂时不清楚），最终的task会发送到Executer执行，Task也是Spark的最小执行单元。

## 创建RDD

RDD的创建方式有很多

* 通过集合创建
* 通过外部数据源创建

### 通过集合创建

Spark支持在内存中通过集合创建RDD可以通过parallelize和makeRDD方法，这两个方法是完全一样的，因为makeRDD底层调用的就是parallelize。推荐使用makeRDD，这个名字容易理解。

```scala
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
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // 准备内存Seq数据
    val list = List(1, 2, 3, 4)
    // 通过parallelize方法
    val intRDD = sc.parallelize(list)
    intRDD.foreach(println)
    // 通过makeRDD方法
    val intRDD2 = sc.makeRDD(list)
    intRDD2.foreach(println)

    // 关闭连接
    sc.stop()
  }
}
```

这两个方法除了第一个Seq类型的参数外，还有第二个参数numSlices，并行度的概念，意思是讲集合分配到几个分区，来测试下。

测试分区需要使用一个输出方法，将其保存到分区文件中，更能直观的展示。

```scala
//使用saveAsTextFile将数据以文件的形式保存到不同分区，放到项目下的partitions文件夹下
val intRDD3 = sc.makeRDD(list, 2)
intRDD3.saveAsTextFile("partitions")
```

查看partitions文件夹，可以看到两个分区文件，并且数据已经放入文件中。

![image-20220908201132328](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209082011590.png)

两个分区，[1 2]被放到了第一个分区，[3 4]被放到了第二个分区。

### 通过外部数据源创建RDD

Spark支持本地文件系统、Hadoop支持的数据集（比如HDFS、Hbase等）来创建RDD，这里有基础特别需要注意的地方

* 使用本地文件系统路径，必须能够在每一个worker上都能访问到文件，否则就找不到文件无法完成任务
* 支持目录，压缩文件和通配符方式，比如 textFile("/my/directory")， textFile("/my/directory/*.txt")和textFile("/my/directory/*.gz")等，多个文件时分区顺序无法保证，取决于文件系统返回的顺序。
* 特别注意文件系统方式，文件要求必须是UTF-8编码，否则读取出来的会有乱码。

**读取目录**

```scala
val value: RDD[String] = sc.textFile("files")
value.foreach(println)
```

上面代码会读取files文件下的所有文件。

**读取压缩文件**

spark支持读取使用tar指令打包的压缩包，zip的压缩包无法读取。

```scala
val value: RDD[String] = sc.textFile("files/tarfile.tar.gz")
value.foreach(println)
```

如果是zip的压缩包，读取不出来，会出现乱码，是否有解决方法，暂不知晓。

![image-20220913144501263](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209131445546.png)

**读取HDFS文件**

```scala
val value: RDD[String] = sc.textFile("hdfs://spark-standalone1:9000/wordCount.txt")
value.foreach(println)
```

hdfs端口，根据自己设置的配置，我配置的是9000

```xml
<property>
  <name>fs.defaultFS</name>
  <value>hdfs://spark-standalone1:9000</value>
</property>
```

**wholeTextFiles方法**

跟上面类似也是读取本地文件系统或者HDFS，不同的是他会生成一个kv元组的RDD

```scala
val value: RDD[(String, String)] = sc.wholeTextFiles("files")
value.foreach(println)
```

![image-20220913161907868](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209131619130.png)

## 算子

前面已经简单介绍过算子，这里我要一个一个具体学习每一个算子的含义以及如何使用。

### map

Spark中的map和scala、java中的map基本相同，通过传入一个函数，将值转化为另外一种结果，形成一种新的RDD，不同于scala基本map之处在于，spark中的map是并行计算的。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 * 
 *
 * @author itlab
 */
object MapOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 2, 3, 4, 5, 6))
    val r = intRDD.map(_ - 1) // 将RDD中每个值减1
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
```

![image-20220913163720751](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209131637901.png)

map的并行计算，请注意下图中setMaster处的修改。

![image-20220913173723422](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209131737513.png)

可以看到并非按照数据顺序一步一步的执行，执行顺序是不确定的。

### filter

过滤器，通过条件（返回boolean类型）返回结果是true的元素组成的新的RDD

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 
 *
 * @author itlab
 */
object FilterOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 2, 3, 4, 5, 6))
    val r = intRDD.filter(_ > 3) // 只要大于3的的数据
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
```

![image-20220913164103076](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209131641202.png)

### flatMap

flatMap功能跟map类似，不同之处在于flat，平铺开，他会将集合中的数据全部展开。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * @author itlab
 */
object FlatMapOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(Array(List(1, 2), List(3, 4)))
    val r = intRDD.flatMap(data => data) // 将RDD展开，数值原样输出
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
```

![image-20220913164930176](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209131649454.png)

### mapPartition

功能类似于map，但两者是有区别的，请看如下说明：

* map主要是用于数据的转换，数据数量不会变化，而mapPartition的参数是一个迭代器，可以实现数据量的减少或者增加。

* map每次处理一条数据，而mapPartition：每次处理一个分区的数据，这个分区的数据处理完后，原RDD中分区的数据才能释放，可能导致OOM。

* 当内存空间较大的时候建议使用mapPartition，以提高处理效率。

示例：

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

object MapPartitionOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 2, 3, 4, 5, 6))
    val r = intRDD.mapPartitions(datas => datas)
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
```

![image-20220913182156452](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209131821550.png)

### mapPartitionsWithIndex

功能类似mapPartitions，不同之处是可以获得分区的索引。

![image-20220913184350438](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209131843562.png)

### glom

glom算子是将每个分区的数据组装为一个数组形成的RDD，分区数目保持不变。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object GlomOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)
    val rdd1: RDD[Array[Int]] = rdd.glom()
    rdd1.foreach(x=>x.foreach(println)) // 这里为什么是两次循环，就是因为glom会将每个分区的数据组装为一个Array，再形成一个RDD
    // 关闭连接
    sc.stop()
  }
}
```

上面代码中，初始RDD是Int类型的，使用glom返回的是RDD[Array[Int]]，这就是将每个分区的数据组装为Array，然后形成一个RDD，1,2,3在第一个分区，组成Array[1,2,3]，同理，第二个分区的组装为Array[4,5,6]数组，这两个数组再形成一个RDD。

![image-20220913201747245](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209132017427.png)

上图中要用两个循环，第一个循环式循环RDD，第二个是循环Array。

### groupBy

分组，通过条件进行分组，func的返回值就是key，分组操作不会导致分区数的变化，但是会导致数据被打乱重新组合，这也叫做洗牌（shuffle）。这可能会导致某个分区数据量激增，压力增大，这也叫做数据倾斜。数据清洗是优化计算的一个关注点，以后再慢慢学习优化。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 
 *
 * @author itlab
 */
object GroupByOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)
    val r = intRDD.groupBy(_ % 2 == 0)
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
```

请看上面代码中，功能时间元素能够被2整除的放到一起，不能被整除的放到一起，分组的key只有true和false。

假设RDD中的元素都是偶数，那么数据groupBy结果都是true的值，导致所有数据重新组合到了一起，就产生了数据倾斜。

运行结果如下：

![image-20220914101402949](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209141014125.png)

### sample

顾名思义，样本，用于数据取样，该算子主要分为抽取数据是否放回，不放回的情况使用的是伯努利算法，放回使用的是泊松分布算法，实际开发中主要用于发现倾斜数据解决数据倾斜、预估内存等。

该算子有三个参数，

* withReplacement：代表元素是否可以多次采样，true代表已经抽取的数据放回，false代表不放回
* fraction：代表每条数据抽取的概率，这里根据是否放回情况不同，如果是放回，则表示重复数据的几率，范围大于等于 0.表示每一个元素被期望抽取到的次数，如果是不放回抽取，该参数代表抽取的几率，范围在[0,1]之间,0：全不取；1：全取；
* seed 抽取数据时，随机算法的种子，如果不传递，默认使用的是当前系统时间。

示例：

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 
 *
 * @author itlab
 */
object SampleOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 2, 3, 4, 5, 6), 2)
    val r = intRDD.sample(withReplacement = false, 0.5)
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
```

上面的代码中，使用的是不放回抽取方式，那么第二个参数（上面代码是0.5）就代表每个元素被期望抽取的次数。

![image-20220914103834179](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209141038347.png)

![image-20220914103909263](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209141039407.png)

上面两个图是我执行两次的结果，结果并不同，抽样本身就是一个不确定返回值的东西。

### distinct

该算子用于对数据去重，该算子支持一个可选参数分区数量numPartitions，需要注意的是，该算子会导致数据会重新分区，也就是shuffle（底层会使用reduceBykey算子）。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 * 
 *
 * @author itlab
 */
object DistinctOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 1, 3, 3, 5, 6), 2)
    val r = intRDD.distinct()
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
```

上面代码中我使用的是没有传递分区数参数，运行结果如下：

![image-20220914104155259](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209141041435.png)

### coalesce

coaleasce是合并的意思，该算子是合并分区，根据数据量缩减分区，用于大数据集过滤后，提高小数据集的执行效率

当 spark 程序中，存在过多的小任务的时候，可以通过 coalesce 方法，收缩合并分区，减少

分区的个数，减小任务调度成本。

具体说明：假设之前RDD是x个分区，使用coalesce要分为y个分区

1. x >= y：也就是说分区数目较少了，这时候会得到目标分区数y，是否洗牌取决于第二个参数shuffle。
2. x < y：目标是想要实现分区数目增大，这种情况下可能会出现无法达到目标的情况，比如此时设置了第二个参数shuffle=false，则最终得到的分区数还是x。啥也没错。但是如果是shuffle=true则会打乱数据重新组合，得到y分区数。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 * @author itlab
 */
object CoalesceOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 1, 3, 3, 5, 6), 4)
    //    val r = intRDD.distinct()
    val r = intRDD.coalesce(2, shuffle = false) // 缩减分区数，但是不洗牌
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
```

通常缩减分区数的时候使用该算子。

### repartition

该算子是coalesce的缩小版功能，因为他其实就是调用了coalesce(numPartitions, shuffle = true)，并限定了shuffle=true。这个算子一定有洗牌操作。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object RepartitionOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 1, 3, 3, 5, 6), 3)
    intRDD.saveAsTextFile("output1")
    val r = intRDD.repartition(4) // 增加分区数，洗牌
    r.saveAsTextFile("output2")
    // 关闭连接
    sc.stop()
  }
}
```

### sortBy

排序功能， 有三个参数，第一个参数是一个函数，用于处理数据，第二个是排序方式，默认是true，升序，第三个参数是目标分区数，

排序后新产生的 RDD 的分区数与原 RDD 的分区数一

致。而且存在shuffle。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object SortByOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val intRDD = sc.makeRDD(List(1, 6, 3, 2, 5, 4, 100, 43), 3)
    intRDD.saveAsTextFile("output1")
    val r = intRDD.sortBy( _ - 1,true, 4)
    r.saveAsTextFile("output2")
    // 关闭连接
    sc.stop()
  }
}
```

运行结果说明：

sortBy之前，有三个分区，数据分别是第1个分区数据是[1, 6]，第二个是[3, 2, 5], 第三个是[4, 100, 43]

sourtBy时候，有四阿哥分区，第一个[1, 2]， 第二个[3, 4]， 第三个[5, 6]， 第四个[43, 100]

上面的运行结果也说明了洗牌的存在。

### intersection

交集，要求两个集合类型必须一致。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object IntersectionOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(1 to 5, 2) // [1, 2, 3, 4, 5]
    val rdd2 = sc.makeRDD(5 to 9, 3) // [5, 6, 7, 8, 9]
    val rdd3 = rdd1.intersection(rdd2) // 交集是5
    rdd3.foreach(println)// [5]
    // 关闭连接
    sc.stop()
  }
}
```

### union

并集，会返回两个集合的组合，不会去重。也要求两个RDD类型一致。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object UnionOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(1 to 5, 2) // [1, 2, 3, 4, 5]
    val rdd2 = sc.makeRDD(5 to 9, 3) // [5, 6, 7, 8, 9]
    val rdd3 = rdd1.union(rdd2) // 并集是[1, 2, 3, 4, 5, 5, 6, 7, 8, 9]
    rdd3.foreach(println)// [5]
    // 关闭连接
    sc.stop()
  }
}
```

### subtract

求两个RDD的差集，获取RDD1中元素不存在于RDD2中的所有元素组成新的RDD

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object SubtractOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(1 to 5, 2) // [1, 2, 3, 4, 5]
    val rdd2 = sc.makeRDD(5 to 9, 3) // [5, 6, 7, 8, 9]
    val rdd3 = rdd1.subtract(rdd2)
    rdd3.foreach(println)// [1, 2, 3, 4]
    // 关闭连接
    sc.stop()
  }
}
```

### zip

压缩功能，将两个RDD压缩为一个，该算子不要求两个RDD的类型一致，但是要求两个RDD的分区数一样，并且要求相同数目的分区元素。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object ZipOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd1 = sc.makeRDD(1 to 5, 3) // [1, 2, 3, 4, 5]
    val rdd2 = sc.makeRDD(List("a", "b", "c", "d", "e"), 3) // [5, 6, 7, 8, 9]
    val rdd3 = rdd1.zip(rdd2)
    rdd3.foreach(println)// [1, 2, 3, 4]
    // 关闭连接
    sc.stop()
  }
}
```

![image-20220914144508321](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209141445426.png)

### partitionBy

通过分区key重新分区， 该算子是PairRDDFunctions下的方法，也就是说他是PairRDD的特有方法。需要一个HashPartitioner分区器

```scala
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
```

请注意RDD的类型。

### reduceByKey

对RDD相同的key实现value的聚合，也是要求RDD类型是键值对。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object ReduceByKeyOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List((1, "a"), (1, "b"), (3, "c")))
    val r = rdd.reduceByKey(_ + _)
    r.foreach(println) // [(1,ab),(3,c)]
    // 关闭连接
    sc.stop()
  }
}
```

### groupByKey

只分组不聚合，这不同于上面说的reduceByKey，reduceByKey默认有分组操作，然后执行聚合，groupByKey没有聚合的含义，如果想实现reduceByKey一样的功能，还需要搭配聚合算子。相同的是两者都有shuffle。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object GroupByKeyOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    val rdd = sc.makeRDD(List((1, "a"), (1, "b"), (3, "c")))
    val r = rdd.groupBy(_._1) // 通过元组的第一个元组分组
    r.foreach(println) // [(3,CompactBuffer((3,c))) (1, CompactBuffer((1, a), (1, b)))]
    // 关闭连接
    sc.stop()
  }
}
```

两者具体区别（摘自尚硅谷的一段话）：

**从** **shuffle** **的角度**：reduceByKey 和 groupByKey 都存在 shuffle 的操作，但是 reduceByKey

可以在 shuffle 前对分区内相同 key 的数据进行预聚合（combine）功能，这样会减少落盘的

数据量，而 groupByKey 只是进行分组，不存在数据量减少的问题，reduceByKey 性能比较

高。

**从功能的角度**：reduceByKey 其实包含分组和聚合的功能。GroupByKey 只能分组，不能聚

合，所以在分组聚合的场合下，推荐使用 reduceByKey，如果仅仅是分组而不需要聚合。那

么还是只能使用 groupByKey

### aggregateByKey

通过key聚合，不同于reduceByKey的是，该算子将数据根据不同的规则进行分区内计算和分区间计算，可以分别指定分区内和分区间聚合的方法（可以不同）。

该算子使用了函数的柯里话，有两个参数列表，第一个参数列表有一个参数，代表初始值，第二个参数列表有两个参数，参数一代表减区间的计算函数，参数2代表分区内的函数。

比如我可以选择将分区内的计算使用获取最大值，分区间的聚合使用相加，看如下示例。

```scala
package com.itlab1024.spark.core.operations

import org.apache.spark.{SparkConf, SparkContext}

/**
 *
 *
 * @author itlab
 */
object AggregateByKeyOperator {
  def main(args: Array[String]): Unit = {
    // 定义配置，通过配置建立连接
    val conf = new SparkConf().setAppName("应用").setMaster("local[*]")
    val sc = new SparkContext(conf)

    // 第一个分区数据是("a", 1), ("a", 2), ("c", 3)
    // 第二个分区数据是("b", 4), ("c", 5), ("c", 6)
    val rdd = sc.makeRDD(List(
      ("a", 1), ("a", 2), ("c", 3),
      ("b", 4), ("c", 5), ("c", 6)
    ), 2)
    // 初始零值是=0
    val r = rdd.aggregateByKey(0)((x, y) => {
      math.max(x, y)
    }, _ + _)
    r.foreach(println)
    // 关闭连接
    sc.stop()
  }
}
```

分析下，整数代码中注释所写的那样，初始数据被分为两个分区，// 第一个分区数据是("a", 1), ("a", 2), ("c", 3)，// 第二个分区数据是("b", 4), ("c", 5), ("c", 6)，零值=0，分区内的算法是相同的key获取最大的，所以第一个分区就变为了("a", 2), ("c", 3)，第二个分区就是("b", 4), ("c", 6)，那么最终的结果就应该是 (a,2) (b,4) (c,9)。

看下代码运行截图

![image-20220916174337942](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209161743473.png)

确实是这样。
