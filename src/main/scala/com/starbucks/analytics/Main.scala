package com.starbucks.analytics


/**
  * Created by depatel on 11/21/17.
  */

import org.apache.commons.configuration.BaseConfiguration
import org.apache.tinkerpop.gremlin.spark.process.computer.SparkGraphComputer
import org.apache.tinkerpop.gremlin.structure.util.GraphFactory


object Main {

  def main(args: Array[String]) {

    def getSparkConfig: BaseConfiguration = {
      val conf = new BaseConfiguration()
      // Configuration for the Spark Titan Graph Computer.
      //    conf.setProperty("gremlin.graph", "org.apache.tinkerpop.gremlin.hadoop.structure.HadoopGraph")
      //    conf.setProperty("gremlin.hadoop.graphInputFormat", "com.thinkaurelius.titan.hadoop.formats.cassandra.CassandraInputFormat")
      //    conf.setProperty("gremlin.hadoop.graphOutputFormat", "org.apache.tinkerpop.gremlin.hadoop.structure.io.gryo.GryoOutputFormat")
      //    conf.setProperty("gremlin.hadoop.inputLocation", "none")
      //    conf.setProperty("gremlin.hadoop.outputLocation", "output")
      //    //    # Cassandra Cluster Config         #
      //    //    ####################################
      //    conf.setProperty("titanmr.ioformat.conf.storage.backend", "cassandra")
      //    conf.setProperty("titanmr.ioformat.conf.storage.hostname", "localhost")
      //    conf.setProperty("titanmr.ioformat.conf.storage.port", "9160")
      //    conf.setProperty("titanmr.ioformat.conf.storage.cassandra.keyspace", "titan")
      //    conf.setProperty("storage.backend", "cassandra")
      //    conf.setProperty("storage.hostname", "127.0.0.1")
      //    conf.setProperty("cassandra.input.partitioner.class", "org.apache.cassandra.dht.Murmur3Partitioner")
      //    //    ####################################
      //    //    # Spark Configuration              #
      //    //    ####################################
      //    conf.setProperty("spark.master", "local[*]")
      ////    conf.setProperty("gremlin.spark.persistContext", "true")
      //    conf.setProperty("spark.serializer", "org.apache.spark.serializer.KryoSerializer")

      conf.setProperty("gremlin.graph", "org.apache.tinkerpop.gremlin.hadoop.structure.HadoopGraph")
      conf.setProperty("gremlin.hadoop.jarsInDistributedCache" , "true")
      conf.setProperty("gremlin.hadoop.defaultGraphComputer", "org.apache.tinkerpop.gremlin.spark.process.computer.SparkGraphComputer")
      conf.setProperty("gremlin.hadoop.graphReader", "com.microsoft.azure.cosmosdb.spark.gremlin.CosmosDBInputRDD")
      conf.setProperty("gremlin.hadoop.graphWriter", "com.microsoft.azure.cosmosdb.spark.gremlin.CosmosDBOutputRDD")

      //####################################
      //# SparkGraphComputer Configuration #
      //####################################
      conf.setProperty("spark.master", "local")
      conf.setProperty("spark.executor.memory", "1g")
      conf.setProperty("spark.executor.instances", "6")
      conf.setProperty("spark.serializer" , "org.apache.spark.serializer.KryoSerializer")
      conf.setProperty("spark.kryo.registrator", "org.apache.tinkerpop.gremlin.spark.structure.io.gryo.GryoRegistrator")
      conf.setProperty("gremlin.spark.persistContext", "true")

      //######################################
      //# DocumentDB Spark connector         #
      //######################################
//      conf.setProperty("spark.documentdb.connectionMode", "Gateway")
//      conf.setProperty("spark.documentdb.schema_samplingratio", "1.0")
      conf.setProperty("spark.cosmosdb.Endpoint", "https://s00072realtimegraphdb.documents.azure.com:443/")
      conf.setProperty("spark.cosmosdb.Masterkey", "ygYPc5ifUUexVhNAGcePClFOkrLisO6WmWOT37IbzFIcL68gAZi0Ho32rcuGg3E315EW6TFBTGEMZokn6IjLiw==")
      conf.setProperty("spark.cosmosdb.Database", "realtimedb")
      conf.setProperty("spark.cosmosdb.Collection", "posdaily")
      conf.setProperty("spark.cosmosdb.preferredRegions", "West US")

      conf
    }

//    val gremlinSpark = Spark.create(new SparkContext(new SparkConf().setAppName("Spark_Graph").setMaster("local[*]")))
    val sparkComputerConnection = GraphFactory.open(getSparkConfig)
    val g = sparkComputerConnection.traversal().withComputer(Predef.classOf[SparkGraphComputer])

    Predef.println("Counting using the spark Graph Computer: "+g.V().count().next())

    sparkComputerConnection.close()


  }
}
