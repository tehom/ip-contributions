/* 
 * Gray code value in Chisel
 *
 * If you need to access the value directly, use _.asGrayUInt
 */

package GrayCounter.GrayCode

import chisel3._
import chisel3.internal.firrtl._


class GrayCode(val asGrayUInt:UInt) extends Bundle {

  def this(w: Width) {
    this(UInt(w))
  }
  override def cloneType =
    (new GrayCode(asGrayUInt)).asInstanceOf[this.type]
  def :=(x : GrayCode) = { asGrayUInt := x.asGrayUInt }
  def ===(x : GrayCode) = { asGrayUInt === x.asGrayUInt }
  def =/=(x : GrayCode) = { asGrayUInt =/= x.asGrayUInt }
}

object GrayCode {
  def binToGray(x : Int) : Int = { x ^ x >> 1 }
  def binToGray(x : UInt) : UInt = { x ^ x >> 1 }
}
