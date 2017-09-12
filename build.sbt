name := "Chess"

version := "1.0"

scalaVersion := "2.12.2"

lazy val root = (project in file(".")).enablePlugins(PlayJava, SbtWeb).disablePlugins(PlayFilters)

libraryDependencies ++= Seq(
  "org.mindrot" % "jbcrypt" % "0.3m",
  "org.webjars" % "angularjs" % "1.1.5",
  "org.webjars" % "requirejs" % "2.1.1",
  "org.webjars" % "webjars-play" % "2.1.0-1",
  "com.typesafe.play" %% "play-json" % "2.6.0"
)

libraryDependencies += guice

pipelineStages := Seq(rjs)
