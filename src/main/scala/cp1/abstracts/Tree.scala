package com.intellibucket.lessons
package cp1.abstracts

trait Tree[-T] {
  def isEmpty: Boolean
  def insert(x: T): Void
  def contains(x: T): Boolean
  def remove(x: T): Void
}