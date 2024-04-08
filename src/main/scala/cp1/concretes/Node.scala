package com.intellibucket.lessons
package cp1.concretes

import java.lang.Comparable
import scala.annotation.{tailrec, targetName}

class Node[T <: Comparable[T]](var value:T,var left: Node[T],var right: Node[T]) extends Comparable[Node[T]] {

  def this(value:T) = this(value,null,null)

  override def compareTo(o: Node[T]): Int = this.value.compareTo(o.value)

  def max: Node[T] = if(this.hasRight) this.right.max else this

  def min: Node[T] = if(this.hasLeft) this.left.min else this

  def isLeaf: Boolean = left == null && right == null

  def isHalf: Boolean = left != null ^ right != null

  def isNil: Boolean = this.isInstanceOf[NilNode[T]]

  def hasLeft: Boolean = left != null

  def hasRight: Boolean = right != null

  def hasBoth: Boolean = left != null && right != null

  def isGreaterThan(o: Node[T]): Boolean = this.compareTo(o) > 0

  def isLessThan(o: Node[T]): Boolean = this.compareTo(o) < 0

  def isEquals(o: Node[T]): Boolean = this.compareTo(o) == 0

  def isGreaterOrEquals(o: Node[T]): Boolean = this.compareTo(o) >= 0

  def isLessOrEquals(o: Node[T]): Boolean = this.compareTo(o) <= 0

  def isLeafOrHalf: Boolean = isLeaf || isHalf

  @targetName("insertNode")
  def +(node: Node[T]): Node[T] = {
    if(this.isNil) node
    else if(this.isGreaterThan(node)) {
      if(this.hasLeft) this.left = this.left + node
      else this.left = node
    }
    else if(this.isLessThan(node)) {
      if(this.hasRight) this.right = this.right + node
      else this.right = node
    }
    this
  }

  @targetName("deleteNode")
  def -(node: Node[T]): Node[T] = {
    if(this.isNil) this
    else if(this.isEquals(node)) {
      if(this.isLeaf) NilNode[T]
      else if(this.hasBoth) {
        val successor = this.right.min
        this.value = successor.value
        this.right = this.right - successor
      }
      else if(this.hasLeft) this.left
      else this.right
    }
    else if(this.isGreaterThan(node)) {
      this.left = this.left - node
    }
    else if(this.isLessThan(node)) {
      this.right = this.right - node
    }
    this
  }



}

class NilNode[T <: Comparable[T]] extends Node[T](null.asInstanceOf[T],null,null) {

  override def compareTo(o: Node[T]): Int = {
    if(o.isInstanceOf[NilNode[T]]) 0
    else -1
  }

  override def isNil: Boolean = true

  override def isLeaf: Boolean = true

  override def isHalf: Boolean = false

  override def hasLeft: Boolean = false

  override def hasRight: Boolean = false

  override def hasBoth: Boolean = false

  override def isGreaterThan(o: Node[T]): Boolean = false

  override def isLessThan(o: Node[T]): Boolean = if (o.isInstanceOf[NilNode[T]]) false else true

  override def isEquals(o: Node[T]): Boolean = o.isInstanceOf[NilNode[T]]

  override def isGreaterOrEquals(o: Node[T]): Boolean = o.isInstanceOf[NilNode[T]]

  override def isLessOrEquals(o: Node[T]): Boolean = true
}

object NilNode {
  def apply[T <: Comparable[T]] = new NilNode[T]
}
