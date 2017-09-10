name := "Chess"

version := "1.0"

scalaVersion := "2.12.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtWeb)

libraryDependencies ++= Seq(
  "org.mindrot" % "jbcrypt" % "0.3m"
)
libraryDependencies += guice

pipelineStages := Seq(rjs)
