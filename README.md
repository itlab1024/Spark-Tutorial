> 本人进入大数据领域仅仅一年，之前从未接触过大数据领域，对于Spark更是闻所未闻，目前公司主要做IOT领域。大数据技术是必须要掌握的，
> 而且项目中也使用到了Spark，所以学习下，Spark是使用Scala开发的。最好也掌握下Scala语言。
> 我学习Spark主要有通过官网、尚硅谷(不得不说尚硅谷真的是业界良心，不是打广告啊😄)，另外就是不明白的时候问问度娘。
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
Spark开发可以使用VS Code工具，或者IDEA。我使用的是IDEA。记下来简单介绍下使用IDEA创建一个项目。
因为我要使用scala语言去写spark（也可以使用Java、Python、R等语言），项目管理可以使用Maven、Gradle、SBT
SBT是scala项目的包管理工具，我这就使用SBT。
## 创建项目
![](https://itlab1024-1256529903.cos.ap-beijing.myqcloud.com/202209011344345.png)
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
