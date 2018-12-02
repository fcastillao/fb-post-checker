name := "akka-quickstart-scala"

version := "1.0"

scalaVersion := "2.12.6"

lazy val akkaVersion = "2.5.18"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "io.spray" %% "spray-json" % "1.3.5",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.github.vooolll" %% "facebook4s" % "0.3.2",
  "org.scalatest" %% "scalatest" % "3.0.5" % "test"
)

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.18"
libraryDependencies += "com.typesafe" % "config" % "1.3.2"

resolvers += Resolver.sonatypeRepo("releases")