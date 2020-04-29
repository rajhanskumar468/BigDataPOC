package org.sample.problem1

import org.apache.spark.sql.SparkSession

object Exercise2 {
  
  def main(args:Array[String])
  {
  
  val spark = SparkSession.builder
              .config("spark-warehouse","D:\\")
              .master("local")
              .appName("Rdd")
              .getOrCreate()
              
              
   val arr = Array(2,6,8,19,17,56,99)
   
   val rdd = spark.sparkContext.parallelize(arr)
   
   val sumAll = rdd.sum.toInt
   
   rdd.persist(org.apache.spark.storage.StorageLevel.MEMORY_ONLY)
   
   println("sum of all elements is " + sumAll)
   
   //actions
   
   rdd.collect.foreach(println)
   
   rdd.take(2).foreach(println)
   
   rdd.count()
   
   
  
  
}
  
}