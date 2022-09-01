> 本人进入大数据领域仅仅一年，之前从未接触过大数据领域，对于Spark更是闻所未闻，目前公司主要做IOT领域。大数据技术是必须要掌握的，
> 而且项目中也使用到了Spark，所以学习下，Spark是使用Scala开发的。最好也掌握下Scala语言。
> 我学习Spark主要有通过官网、尚硅谷(不得不说尚硅谷真的是业界良心，不是打广告啊😄)，另外就是不明白的时候问问度娘。
> 本篇文章是我在学习了一遍之后重新来写的，一是为了加深记忆，而是想分享出来，跟网友互相学习，取长补短，共同进步。
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
1. 将文件中的数据读入到内存，结果是一行一行的。
2. 将每行通过空格切分
3. 转化为元组，比如(K,V)，K代表单词，V代表单词的数量（写死1）
4. 然后通过K聚合将所有V加起
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
```
运行结果：
```text
/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/bin/java -agentlib:jdwp=transport=dt_socket,address=127.0.0.1:52978,suspend=y,server=n -javaagent:/Users/itlab/Library/Caches/JetBrains/IntelliJIdea2022.2/captureAgent/debugger-agent.jar -Dfile.encoding=UTF-8 -classpath /Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/charsets.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/deploy.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/cldrdata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/dnsns.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/jaccess.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/jfxrt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/localedata.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/nashorn.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/sunec.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/sunjce_provider.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/sunpkcs11.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/ext/zipfs.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/javaws.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/jce.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/jfr.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/jfxswt.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/jsse.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/management-agent.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/plugin.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/resources.jar:/Library/Java/JavaVirtualMachines/jdk1.8.0_333.jdk/Contents/Home/jre/lib/rt.jar:/Users/itlab/workspace/github/Spark-Tutorial/target/scala-2.12/classes:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/clearspring/analytics/stream/2.9.6/stream-2.9.6.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/esotericsoftware/kryo-shaded/4.0.2/kryo-shaded-4.0.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/esotericsoftware/minlog/1.3.0/minlog-1.3.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.13.3/jackson-annotations-2.13.3.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.13.3/jackson-core-2.13.3.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.13.3/jackson-databind-2.13.3.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/module/jackson-module-scala_2.12/2.13.3/jackson-module-scala_2.12-2.13.3.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/luben/zstd-jni/1.5.2-1/zstd-jni-1.5.2-1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/code/findbugs/jsr305/3.0.2/jsr305-3.0.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/code/gson/gson/2.8.6/gson-2.8.6.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/crypto/tink/tink/1.6.1/tink-1.6.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/guava/guava/16.0.1/guava-16.0.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/google/protobuf/protobuf-java/3.14.0/protobuf-java-3.14.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/ning/compress-lzf/1.1/compress-lzf-1.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/thoughtworks/paranamer/paranamer/2.8/paranamer-2.8.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/twitter/chill-java/0.10.0/chill-java-0.10.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/twitter/chill_2.12/0.10.0/chill_2.12-0.10.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-codec/commons-codec/1.15/commons-codec-1.15.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-collections/commons-collections/3.2.2/commons-collections-3.2.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-io/commons-io/2.11.0/commons-io-2.11.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-lang/commons-lang/2.6/commons-lang-2.6.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-core/4.2.7/metrics-core-4.2.7.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-graphite/4.2.7/metrics-graphite-4.2.7.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-jmx/4.2.7/metrics-jmx-4.2.7.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-json/4.2.7/metrics-json-4.2.7.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/dropwizard/metrics/metrics-jvm/4.2.7/metrics-jvm-4.2.7.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-all/4.1.74.Final/netty-all-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-buffer/4.1.74.Final/netty-buffer-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-codec/4.1.74.Final/netty-codec-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-common/4.1.74.Final/netty-common-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-handler/4.1.74.Final/netty-handler-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-resolver/4.1.74.Final/netty-resolver-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-tcnative-classes/2.0.48.Final/netty-tcnative-classes-2.0.48.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-classes-epoll/4.1.74.Final/netty-transport-classes-epoll-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-classes-kqueue/4.1.74.Final/netty-transport-classes-kqueue-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-epoll/4.1.74.Final/netty-transport-native-epoll-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-epoll/4.1.74.Final/netty-transport-native-epoll-4.1.74.Final-linux-aarch_64.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-epoll/4.1.74.Final/netty-transport-native-epoll-4.1.74.Final-linux-x86_64.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-kqueue/4.1.74.Final/netty-transport-native-kqueue-4.1.74.Final-osx-aarch_64.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-kqueue/4.1.74.Final/netty-transport-native-kqueue-4.1.74.Final-osx-x86_64.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport-native-unix-common/4.1.74.Final/netty-transport-native-unix-common-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/netty/netty-transport/4.1.74.Final/netty-transport-4.1.74.Final.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/servlet/jakarta.servlet-api/4.0.3/jakarta.servlet-api-4.0.3.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/validation/jakarta.validation-api/2.0.2/jakarta.validation-api-2.0.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/jakarta/ws/rs/jakarta.ws.rs-api/2.1.6/jakarta.ws.rs-api-2.1.6.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/javax/activation/activation/1.1.1/activation-1.1.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/razorvine/pickle/1.2/pickle-1.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/sf/py4j/py4j/0.10.9.5/py4j-0.10.9.5.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/avro/avro-ipc/1.11.0/avro-ipc-1.11.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/avro/avro-mapred/1.11.0/avro-mapred-1.11.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/avro/avro/1.11.0/avro-1.11.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-collections4/4.4/commons-collections4-4.4.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-compress/1.21/commons-compress-1.21.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-crypto/1.1.0/commons-crypto-1.1.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-lang3/3.12.0/commons-lang3-3.12.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-math3/3.6.1/commons-math3-3.6.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-text/1.9/commons-text-1.9.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/curator/curator-client/2.13.0/curator-client-2.13.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/curator/curator-framework/2.13.0/curator-framework-2.13.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/curator/curator-recipes/2.13.0/curator-recipes-2.13.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/hadoop/hadoop-client-api/3.3.2/hadoop-client-api-3.3.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/hadoop/hadoop-client-runtime/3.3.2/hadoop-client-runtime-3.3.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/ivy/ivy/2.5.0/ivy-2.5.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-1.2-api/2.17.2/log4j-1.2-api-2.17.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-api/2.17.2/log4j-api-2.17.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-core/2.17.2/log4j-core-2.17.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/logging/log4j/log4j-slf4j-impl/2.17.2/log4j-slf4j-impl-2.17.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-core_2.12/3.3.0/spark-core_2.12-3.3.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-kvstore_2.12/3.3.0/spark-kvstore_2.12-3.3.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-launcher_2.12/3.3.0/spark-launcher_2.12-3.3.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-network-common_2.12/3.3.0/spark-network-common_2.12-3.3.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-network-shuffle_2.12/3.3.0/spark-network-shuffle_2.12-3.3.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-tags_2.12/3.3.0/spark-tags_2.12-3.3.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/spark/spark-unsafe_2.12/3.3.0/spark-unsafe_2.12-3.3.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/xbean/xbean-asm9-shaded/4.20/xbean-asm9-shaded-4.20.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/yetus/audience-annotations/0.5.0/audience-annotations-0.5.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/zookeeper/zookeeper-jute/3.6.2/zookeeper-jute-3.6.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/zookeeper/zookeeper/3.6.2/zookeeper-3.6.2.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/fusesource/leveldbjni/leveldbjni-all/1.8/leveldbjni-all-1.8.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/external/aopalliance-repackaged/2.6.1/aopalliance-repackaged-2.6.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/external/jakarta.inject/2.6.1/jakarta.inject-2.6.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/hk2-api/2.6.1/hk2-api-2.6.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/hk2-locator/2.6.1/hk2-locator-2.6.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/hk2-utils/2.6.1/hk2-utils-2.6.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/hk2/osgi-resource-locator/1.0.3/osgi-resource-locator-1.0.3.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/containers/jersey-container-servlet-core/2.34/jersey-container-servlet-core-2.34.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/containers/jersey-container-servlet/2.34/jersey-container-servlet-2.34.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/core/jersey-client/2.34/jersey-client-2.34.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/core/jersey-common/2.34/jersey-common-2.34.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/core/jersey-server/2.34/jersey-server-2.34.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/glassfish/jersey/inject/jersey-hk2/2.34/jersey-hk2-2.34.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/javassist/javassist/3.25.0-GA/javassist-3.25.0-GA.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-ast_2.12/3.7.0-M11/json4s-ast_2.12-3.7.0-M11.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-core_2.12/3.7.0-M11/json4s-core_2.12-3.7.0-M11.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-jackson_2.12/3.7.0-M11/json4s-jackson_2.12-3.7.0-M11.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/json4s/json4s-scalap_2.12/3.7.0-M11/json4s-scalap_2.12-3.7.0-M11.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/lz4/lz4-java/1.8.0/lz4-java-1.8.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/objenesis/objenesis/2.5.1/objenesis-2.5.1.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/roaringbitmap/RoaringBitmap/0.9.25/RoaringBitmap-0.9.25.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/roaringbitmap/shims/0.9.25/shims-0.9.25.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/rocksdb/rocksdbjni/6.20.3/rocksdbjni-6.20.3.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-xml_2.12/1.2.0/scala-xml_2.12-1.2.0.jar:/Users/itlab/.sbt/boot/scala-2.12.16/lib/scala-library.jar:/Users/itlab/.sbt/boot/scala-2.12.16/lib/scala-reflect.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jcl-over-slf4j/1.7.32/jcl-over-slf4j-1.7.32.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/jul-to-slf4j/1.7.32/jul-to-slf4j-1.7.32.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.35/slf4j-api-1.7.35.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/spark-project/spark/unused/1.0.0/unused-1.0.0.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/tukaani/xz/1.9/xz-1.9.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/xerial/snappy/snappy-java/1.1.8.4/snappy-java-1.1.8.4.jar:/Users/itlab/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/oro/oro/2.0.8/oro-2.0.8.jar:/Users/itlab/Library/Application Support/JetBrains/Toolbox/apps/IDEA-U/ch-0/222.3739.54/IntelliJ IDEA.app/Contents/lib/idea_rt.jar com.itlab1024.spark.start.WordCount
Connected to the target VM, address: '127.0.0.1:52978', transport: 'socket'
Using Spark's default log4j profile: org/apache/spark/log4j2-defaults.properties
22/09/01 15:13:36 WARN Utils: Your hostname, ITshiyanshideMacBook-Pro.local resolves to a loopback address: 127.0.0.1; using 10.112.82.59 instead (on interface en0)
22/09/01 15:13:36 WARN Utils: Set SPARK_LOCAL_IP if you need to bind to another address
22/09/01 15:13:37 INFO SparkContext: Running Spark version 3.3.0
22/09/01 15:13:37 WARN NativeCodeLoader: Unable to load native-hadoop library for your platform... using builtin-java classes where applicable
22/09/01 15:13:38 INFO ResourceUtils: ==============================================================
22/09/01 15:13:38 INFO ResourceUtils: No custom resources configured for spark.driver.
22/09/01 15:13:38 INFO ResourceUtils: ==============================================================
22/09/01 15:13:38 INFO SparkContext: Submitted application: 统计单词数量
22/09/01 15:13:38 INFO ResourceProfile: Default ResourceProfile created, executor resources: Map(cores -> name: cores, amount: 1, script: , vendor: , memory -> name: memory, amount: 1024, script: , vendor: , offHeap -> name: offHeap, amount: 0, script: , vendor: ), task resources: Map(cpus -> name: cpus, amount: 1.0)
22/09/01 15:13:38 INFO ResourceProfile: Limiting resource is cpu
22/09/01 15:13:38 INFO ResourceProfileManager: Added ResourceProfile id: 0
22/09/01 15:13:38 INFO SecurityManager: Changing view acls to: itlab
22/09/01 15:13:38 INFO SecurityManager: Changing modify acls to: itlab
22/09/01 15:13:38 INFO SecurityManager: Changing view acls groups to: 
22/09/01 15:13:38 INFO SecurityManager: Changing modify acls groups to: 
22/09/01 15:13:38 INFO SecurityManager: SecurityManager: authentication disabled; ui acls disabled; users  with view permissions: Set(itlab); groups with view permissions: Set(); users  with modify permissions: Set(itlab); groups with modify permissions: Set()
22/09/01 15:13:39 INFO Utils: Successfully started service 'sparkDriver' on port 52982.
22/09/01 15:13:39 INFO SparkEnv: Registering MapOutputTracker
22/09/01 15:13:39 INFO SparkEnv: Registering BlockManagerMaster
22/09/01 15:13:39 INFO BlockManagerMasterEndpoint: Using org.apache.spark.storage.DefaultTopologyMapper for getting topology information
22/09/01 15:13:39 INFO BlockManagerMasterEndpoint: BlockManagerMasterEndpoint up
22/09/01 15:13:39 INFO SparkEnv: Registering BlockManagerMasterHeartbeat
22/09/01 15:13:39 INFO DiskBlockManager: Created local directory at /private/var/folders/b7/yhnw9hws0ng2w1_khl8nr2t40000gn/T/blockmgr-49e73bfc-fa98-41e3-9b9e-48a26d04917d
22/09/01 15:13:39 INFO MemoryStore: MemoryStore started with capacity 912.3 MiB
22/09/01 15:13:39 INFO SparkEnv: Registering OutputCommitCoordinator
22/09/01 15:13:40 INFO Utils: Successfully started service 'SparkUI' on port 4040.
22/09/01 15:13:40 INFO Executor: Starting executor ID driver on host 10.112.82.59
22/09/01 15:13:40 INFO Executor: Starting executor with user classpath (userClassPathFirst = false): ''
22/09/01 15:13:40 INFO Utils: Successfully started service 'org.apache.spark.network.netty.NettyBlockTransferService' on port 52983.
22/09/01 15:13:40 INFO NettyBlockTransferService: Server created on 10.112.82.59:52983
22/09/01 15:13:40 INFO BlockManager: Using org.apache.spark.storage.RandomBlockReplicationPolicy for block replication policy
22/09/01 15:13:40 INFO BlockManagerMaster: Registering BlockManager BlockManagerId(driver, 10.112.82.59, 52983, None)
22/09/01 15:13:40 INFO BlockManagerMasterEndpoint: Registering block manager 10.112.82.59:52983 with 912.3 MiB RAM, BlockManagerId(driver, 10.112.82.59, 52983, None)
22/09/01 15:13:40 INFO BlockManagerMaster: Registered BlockManager BlockManagerId(driver, 10.112.82.59, 52983, None)
22/09/01 15:13:40 INFO BlockManager: Initialized BlockManager: BlockManagerId(driver, 10.112.82.59, 52983, None)
22/09/01 15:13:42 INFO MemoryStore: Block broadcast_0 stored as values in memory (estimated size 358.0 KiB, free 912.0 MiB)
22/09/01 15:13:43 INFO MemoryStore: Block broadcast_0_piece0 stored as bytes in memory (estimated size 32.3 KiB, free 911.9 MiB)
22/09/01 15:13:43 INFO BlockManagerInfo: Added broadcast_0_piece0 in memory on 10.112.82.59:52983 (size: 32.3 KiB, free: 912.3 MiB)
22/09/01 15:13:43 INFO SparkContext: Created broadcast 0 from textFile at WordCount.scala:12
22/09/01 15:13:43 INFO FileInputFormat: Total input files to process : 1
22/09/01 15:13:43 INFO SparkContext: Starting job: collect at WordCount.scala:20
22/09/01 15:13:44 INFO DAGScheduler: Registering RDD 3 (map at WordCount.scala:16) as input to shuffle 0
22/09/01 15:13:44 INFO DAGScheduler: Got job 0 (collect at WordCount.scala:20) with 1 output partitions
22/09/01 15:13:44 INFO DAGScheduler: Final stage: ResultStage 1 (collect at WordCount.scala:20)
22/09/01 15:13:44 INFO DAGScheduler: Parents of final stage: List(ShuffleMapStage 0)
22/09/01 15:13:44 INFO DAGScheduler: Missing parents: List(ShuffleMapStage 0)
22/09/01 15:13:44 INFO DAGScheduler: Submitting ShuffleMapStage 0 (MapPartitionsRDD[3] at map at WordCount.scala:16), which has no missing parents
22/09/01 15:13:44 INFO MemoryStore: Block broadcast_1 stored as values in memory (estimated size 6.9 KiB, free 911.9 MiB)
22/09/01 15:13:44 INFO MemoryStore: Block broadcast_1_piece0 stored as bytes in memory (estimated size 4.0 KiB, free 911.9 MiB)
22/09/01 15:13:44 INFO BlockManagerInfo: Added broadcast_1_piece0 in memory on 10.112.82.59:52983 (size: 4.0 KiB, free: 912.3 MiB)
22/09/01 15:13:44 INFO SparkContext: Created broadcast 1 from broadcast at DAGScheduler.scala:1513
22/09/01 15:13:44 INFO DAGScheduler: Submitting 1 missing tasks from ShuffleMapStage 0 (MapPartitionsRDD[3] at map at WordCount.scala:16) (first 15 tasks are for partitions Vector(0))
22/09/01 15:13:44 INFO TaskSchedulerImpl: Adding task set 0.0 with 1 tasks resource profile 0
22/09/01 15:13:44 INFO TaskSetManager: Starting task 0.0 in stage 0.0 (TID 0) (10.112.82.59, executor driver, partition 0, PROCESS_LOCAL, 4527 bytes) taskResourceAssignments Map()
22/09/01 15:13:44 INFO Executor: Running task 0.0 in stage 0.0 (TID 0)
22/09/01 15:13:46 INFO HadoopRDD: Input split: file:/Users/itlab/workspace/github/Spark-Tutorial/files/wordCount.txt:0+75
22/09/01 15:13:46 INFO Executor: Finished task 0.0 in stage 0.0 (TID 0). 1341 bytes result sent to driver
22/09/01 15:13:46 INFO TaskSetManager: Finished task 0.0 in stage 0.0 (TID 0) in 1786 ms on 10.112.82.59 (executor driver) (1/1)
22/09/01 15:13:46 INFO TaskSchedulerImpl: Removed TaskSet 0.0, whose tasks have all completed, from pool 
22/09/01 15:13:46 INFO DAGScheduler: ShuffleMapStage 0 (map at WordCount.scala:16) finished in 2.219 s
22/09/01 15:13:46 INFO DAGScheduler: looking for newly runnable stages
22/09/01 15:13:46 INFO DAGScheduler: running: Set()
22/09/01 15:13:46 INFO DAGScheduler: waiting: Set(ResultStage 1)
22/09/01 15:13:46 INFO DAGScheduler: failed: Set()
22/09/01 15:13:46 INFO DAGScheduler: Submitting ResultStage 1 (ShuffledRDD[4] at reduceByKey at WordCount.scala:18), which has no missing parents
22/09/01 15:13:46 INFO MemoryStore: Block broadcast_2 stored as values in memory (estimated size 5.2 KiB, free 911.9 MiB)
22/09/01 15:13:46 INFO MemoryStore: Block broadcast_2_piece0 stored as bytes in memory (estimated size 3.1 KiB, free 911.9 MiB)
22/09/01 15:13:46 INFO BlockManagerInfo: Added broadcast_2_piece0 in memory on 10.112.82.59:52983 (size: 3.1 KiB, free: 912.3 MiB)
22/09/01 15:13:46 INFO SparkContext: Created broadcast 2 from broadcast at DAGScheduler.scala:1513
22/09/01 15:13:46 INFO DAGScheduler: Submitting 1 missing tasks from ResultStage 1 (ShuffledRDD[4] at reduceByKey at WordCount.scala:18) (first 15 tasks are for partitions Vector(0))
22/09/01 15:13:46 INFO TaskSchedulerImpl: Adding task set 1.0 with 1 tasks resource profile 0
22/09/01 15:13:46 INFO TaskSetManager: Starting task 0.0 in stage 1.0 (TID 1) (10.112.82.59, executor driver, partition 0, NODE_LOCAL, 4271 bytes) taskResourceAssignments Map()
22/09/01 15:13:46 INFO Executor: Running task 0.0 in stage 1.0 (TID 1)
22/09/01 15:13:46 INFO ShuffleBlockFetcherIterator: Getting 1 (97.0 B) non-empty blocks including 1 (97.0 B) local and 0 (0.0 B) host-local and 0 (0.0 B) push-merged-local and 0 (0.0 B) remote blocks
22/09/01 15:13:46 INFO ShuffleBlockFetcherIterator: Started 0 remote fetches in 34 ms
22/09/01 15:13:46 INFO Executor: Finished task 0.0 in stage 1.0 (TID 1). 1535 bytes result sent to driver
22/09/01 15:13:46 INFO TaskSetManager: Finished task 0.0 in stage 1.0 (TID 1) in 245 ms on 10.112.82.59 (executor driver) (1/1)
22/09/01 15:13:46 INFO TaskSchedulerImpl: Removed TaskSet 1.0, whose tasks have all completed, from pool 
22/09/01 15:13:46 INFO DAGScheduler: ResultStage 1 (collect at WordCount.scala:20) finished in 0.280 s
22/09/01 15:13:46 INFO DAGScheduler: Job 0 is finished. Cancelling potential speculative or zombie tasks for this job
22/09/01 15:13:46 INFO TaskSchedulerImpl: Killing all running tasks in stage 1: Stage finished
22/09/01 15:13:46 INFO DAGScheduler: Job 0 finished: collect at WordCount.scala:20, took 3.147017 s
(scala,1)
(learning,4)
(spark,1)
(am,4)
(I,4)
(java,1)
(go,1)
22/09/01 15:13:46 INFO SparkUI: Stopped Spark web UI at http://10.112.82.59:4040
22/09/01 15:13:46 INFO BlockManagerInfo: Removed broadcast_2_piece0 on 10.112.82.59:52983 in memory (size: 3.1 KiB, free: 912.3 MiB)
22/09/01 15:13:47 INFO MapOutputTrackerMasterEndpoint: MapOutputTrackerMasterEndpoint stopped!
22/09/01 15:13:47 INFO MemoryStore: MemoryStore cleared
22/09/01 15:13:47 INFO BlockManager: BlockManager stopped
22/09/01 15:13:47 INFO BlockManagerMaster: BlockManagerMaster stopped
22/09/01 15:13:47 INFO OutputCommitCoordinator$OutputCommitCoordinatorEndpoint: OutputCommitCoordinator stopped!
22/09/01 15:13:47 INFO SparkContext: Successfully stopped SparkContext
22/09/01 15:13:47 INFO ShutdownHookManager: Shutdown hook called
22/09/01 15:13:47 INFO ShutdownHookManager: Deleting directory /private/var/folders/b7/yhnw9hws0ng2w1_khl8nr2t40000gn/T/spark-4fab4f8b-374b-4fba-87fd-d06176d5e405
Disconnected from the target VM, address: '127.0.0.1:52978', transport: 'socket'

Process finished with exit code 0

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
Spark的运行环境有开发环境、本地环境、独立环境（Standalone）、Hadoop Yarn模式、Kubernetes环境。
* 开发环境：上面我们执行WordCount代码的环境就是开发环境，严格来说他并不是一种环境，仅仅用于开发。
* 本地环境：使用spark-shell开启的环境就是本地环境，用于开发、测试、调试、演示等基本使用。
* 独立环境：独立环境是最简单的模式，他是主从架构，生产可用。
* Hadoop Yarn环境：据说国内主流，咱也不清楚，生产可用。
* Kubernetes环境：这个我觉得肯定是流行的，因为容器化现在非常流行，生产可用。
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209011528643.png)
## 开发环境
没啥好说的
## 本地模式
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



