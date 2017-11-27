
name := "cosmos_graph_spark"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "org.apache.tinkerpop" % "spark-gremlin" % "3.3.0"
//libraryDependencies += "org.apache.tinkerpop" % "hadoop-gremlin" % "3.3.0"
libraryDependencies += "com.microsoft.azure" % "azure-documentdb" % "1.13.0"
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0" % "provided"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}
        