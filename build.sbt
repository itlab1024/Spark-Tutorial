ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.16"

lazy val root = (project in file("."))
  .settings(
    name := "Spark-Tutorial"
  )
libraryDependencies += "org.apache.spark" % "spark-core_2.12" % "3.3.0"