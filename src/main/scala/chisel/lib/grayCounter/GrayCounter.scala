package GrayCounter

import GrayCounter.GrayCode._
import chisel3._
import chisel3.util._
import chisel3.experimental.ChiselEnum

object opcode extends ChiselEnum {
  val opZero, opSetRollover, opCount, opNone
    = Value
}

import opcode._


class GrayCounter(width : chisel3.internal.firrtl.Width) extends Module
{
  val io = IO(new Bundle {
    val operation = Input(opcode())
    val rollover = Input(new GrayCode(width))
    val out = Output(new GrayCode(width))
  })
  val counter = RegInit(0.U(width))
  val rollover = Reg(UInt(width))
  
  def toGray(x : UInt) = GrayCode.GrayCode.binToGray(x)

  switch (io.operation) {
    is (opNone) {}
    is (opZero) {
      counter := 0.U
    }
    is (opSetRollover) {
      rollover := io.rollover.asGrayUInt
    }
    is (opCount) {
      val nextCounter = counter + 1.U
      val nextGray = toGray(nextCounter)

      when (nextGray === rollover)
      { counter := 0.U }
        .otherwise
      { counter := nextCounter }
    }
  }
  io.out.asGrayUInt := toGray(counter)
}

