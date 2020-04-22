package GrayCounter

import chisel3._
import chisel3.util._
import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
import GrayCounter.GrayCode._
import GrayCounter.opcode._


class GrayCounterChecker(c : GrayCounter)
    extends PeekPokeTester(c) {

  def binToGray(x : Int) = GrayCode.GrayCode.binToGray(x)

  def check(width : Int, endCount : Int) = {
    val topBitMask = 1 << width // eg 0x8
    val highBitMask = (topBitMask - 1) // eg 0x7

    poke(c.io.operation, opZero)
    step(1)
    poke(c.io.operation, opSetRollover)
    /* A 0 rollover means use the native width, since there isn't enough
     * space to write topBitMask into the rollover register */
    poke(c.io.rollover.asGrayUInt, 0.U) 
    step(1)
    expect(c.io.out.asGrayUInt, 0x0)

    // Check each value
    for (j <- 1 until endCount) {
      poke(c.io.operation, opCount)
      step(1)
      val asBinary = j & highBitMask
      expect(c.io.out.asGrayUInt, binToGray(asBinary))
    }
  }

  def checkWithRollover(width : Int, endCount : Int, rollover : Int) = {
    // Reset it
    poke(c.io.operation, opZero)
    step(1)
    poke(c.io.operation, opSetRollover)
    poke(c.io.rollover.asGrayUInt, binToGray(rollover).U)
    step(1)
    expect(c.io.out.asGrayUInt, 0x0)

    // Check each value
    for (j <- 1 until endCount) {
      poke(c.io.operation, opCount)
      step(1)
      val asBinary = j % rollover
      expect(c.io.out.asGrayUInt, binToGray(asBinary))
    }
  }
}
