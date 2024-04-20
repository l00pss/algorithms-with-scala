package com.intellibucket.lessons
package cp0

object FunctionalProgramming_Playground extends App {

  var exFunction: Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
    override def apply(v1: Int): Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  
  val supFunction: Function1[Int, Function1[Int, Function1[Int,Int]]] = new Function1[Int, Function1[Int, Function1[Int, Int]]] {
    override def apply(v1: Int): Function1[Int, Function1[Int, Int]] = new Function1[Int, Function1[Int, Int]] {
      override def apply(v2: Int): Function1[Int, Int] = new Function1[Int, Int] {
        override def apply(v3: Int): Int = v1 + v2 + v3
      }
    }
  }

  println(supFunction(5)(6)(4))

}
