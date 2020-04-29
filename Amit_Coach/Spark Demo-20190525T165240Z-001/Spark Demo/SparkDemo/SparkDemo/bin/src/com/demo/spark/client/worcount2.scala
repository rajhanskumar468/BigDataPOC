package com.demo.spark.client

import org.apache.spark.sql.SparkSession

object worcount2 {

  def main(args: Array[String]) {
    val sparkSession = SparkSession.builder
      .master("local")
      .appName("example")
      .getOrCreate()

    import sparkSession.implicits._
    val data = sparkSession.read.text("D:\\sample.txt").as[String]

    val words = data.flatMap(value => value.split("\\s+"))
    val groupedWords = words.groupByKey(_.toLowerCase)

    val counts = groupedWords.count()
    counts.show()
  }
}