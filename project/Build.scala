import sbt._
import sbt.Classpaths.publishTask
import Keys._

import com.typesafe.sbt.packager.archetypes.JavaAppPackaging
import com.typesafe.sbt.packager.Keys._


object Build extends Build {

  def sharedSettings = Seq(
    version := "0.1.0",
    scalaVersion := "2.11.8",
    crossScalaVersions := Seq("2.11.8", "2.10.6"),
    retrieveManaged := true,

    fork := true,
    javaOptions += "-Xmx2500M",

    resolvers ++= Seq(
      "Akka Repository"        at "http://repo.akka.io/releases",
      "Typesafe Repository"    at "http://repo.typesafe.com/typesafe/releases/"
    ),

    publishMavenStyle := true
  )


  lazy val service = Project(
    id = "example-service",
    base = file("service"),
    settings = Project.defaultSettings
      ++ sharedSettings
  )
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "example-service",
    mainClass in Compile := Some("dockerize.service.ExampleService"),
    dockerExposedPorts := Seq(8080),
    dockerEntrypoint := Seq("sh", "-c", "bin/example-service"),
    dockerRepository := Some("example"),
    dockerBaseImage := "openjdk",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.0.0"
    )
  )

}