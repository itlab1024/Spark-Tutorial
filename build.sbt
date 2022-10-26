ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.16"

lazy val root = (project in file("."))
  .settings(
    name := "Spark-Tutorial"
  )
libraryDependencies += "org.apache.spark" % "spark-core_2.12" % "3.3.0"
libraryDependencies += "org.apache.spark" % "spark-sql_2.12" % "3.3.0"
libraryDependencies += "org.postgresql" % "postgresql" % "42.5.0"
libraryDependencies += "org.apache.spark" % "spark-hive_2.12" % "3.3.0"
libraryDependencies += "org.apache.spark" % "spark-streaming_2.12" % "3.3.0"