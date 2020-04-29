package com.demo.spark.client;

import org.apache.spark.ml.clustering.KMeans
// $example off$
import org.apache.spark.sql.SparkSession

object KMeansExample {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession
      .builder
      .master("local")
      .config("spark.sql.warehouse.dir", "file:///C:\\Scala\\ScalaClient\\spark-warehouse")
      .appName(s"${this.getClass.getSimpleName}")
      .getOrCreate()

    // Loads data.
    val dataset = spark.read.format("libsvm").load("C:/Cassandra/Simplilearn/kmeans_data.txt")

    // Trains a k-means model.
    val kmeans = new KMeans().setK(2)
    val model = kmeans.fit(dataset)

    // Evaluate clustering by computing Within Set Sum of Squared Errors.
    val WSSSE = model.computeCost(dataset)
    println(s"Within Set Sum of Squared Errors = $WSSSE")

    // Shows the result.
    println("Cluster Centers: ")
    model.clusterCenters.foreach(println)

    spark.stop()
  }
}
