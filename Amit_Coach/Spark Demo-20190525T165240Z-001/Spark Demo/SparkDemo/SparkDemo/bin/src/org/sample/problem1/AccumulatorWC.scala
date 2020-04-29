package org.sample.problem1

import org.apache.spark.sql.SparkSession


object AccumulatorWC {
  
  def main(args:Array[String])
  {
    val spark = SparkSession.builder.
                           appName("Accumulator").
                           master("local").
                           getOrCreate()
                           
     var numBlankLines = spark.sparkContext.accumulator(0)

     val myrdd = spark.sparkContext.textFile(args(0)).foreach
     {
      x => if (x.length() == 0) { numBlankLines +=1} 
     }
    
    println("total blank lines are " + numBlankLines.value)
     
     
  }
  
  
  
}