package com.demo.spark.client

object trejak {

import org.apache.spark.sql.SparkSession
  def main(args: Array[String]) {
    val logFile = "d:\\sample.txt" // Should be some file on your system
    val spark = SparkSession.builder
                .master("local").appName("Simple Application").getOrCreate()
    val logData = spark.read.textFile(logFile).cache()
    val numAs = logData.filter(line => line.contains("a")).count()
    val numBs = logData.filter(line => line.contains("b")).count()
    println("Lines with a: $numAs, Lines with b: $numBs")
    spark.stop()
  }
}