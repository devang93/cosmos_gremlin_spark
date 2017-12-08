
name := "cosmos_gremlin_spark"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.tinkerpop" % "spark-gremlin" % "3.3.0" % "provided"
libraryDependencies += "org.apache.tinkerpop" % "gremlin-core" % "3.3.0" % "provided"
libraryDependencies += "org.apache.tinkerpop" % "gremlin-shaded" % "3.3.0" % "provided"
libraryDependencies += "org.apache.tinkerpop" % "hadoop-gremlin" % "3.3.0" % "provided"
libraryDependencies += "org.apache.tinkerpop" % "tinkergraph-gremlin" % "3.3.0" % "provided"
libraryDependencies += "com.microsoft.azure" % "azure-documentdb" % "1.14.0" % "provided"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0" % "provided"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
        