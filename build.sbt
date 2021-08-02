val scala3Version = "3.0.0"
val zioVersion = "2.0.0-M1" 


lazy val root = project
  .in(file("."))
  .settings(
    name := "clickhouse-demo",
    version := "0.1.0",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
    //   "com.typesafe.akka" % "akka-stream_2.13"  % AkkaVersion,
      "io.findify" % "clickhouse-akka-stream_2.13" % "0.4.6",
      "dev.zio" %% "zio" % zioVersion,
      "dev.zio" % "zio-kafka_2.13" % "0.15.0",
      "com.crobox.clickhouse" % "client_2.13" % "0.10.0"
    )
  )