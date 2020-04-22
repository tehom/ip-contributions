/* 
 * Gray code value in Scala
 *
 */

package GrayCounter.GrayCode

import chisel3._

class GrayCodeScala(val asGrayInt:Int) extends Bundle {

  override def cloneType =
    (new GrayCodeScala(asGrayInt)).asInstanceOf[this.type]
  def ==(x : GrayCodeScala) = { asGrayInt == x.asGrayInt }
  def !=(x : GrayCodeScala) = { asGrayInt != x.asGrayInt }
  def asGrayUInt() = { new GrayCode(asGrayInt.U) }
}

