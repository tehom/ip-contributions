// See README.md for license details.

package GrayCounter

import chisel3._

import chisel3.iotesters
import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}




// Call as
// testOnly GrayCounter.Tester
class Tester extends ChiselFlatSpec {

  "A counter of 2" should "increment as expected" in {
    Driver(() => new GrayCounter(2.W)) {
      c => {
        val tester = new GrayCounterChecker(c)
        tester.check(2, 8)
        tester
      }
    } should be (true)
  }
  "A counter of 2 with rollover 3" should "increment as expected" in {
    Driver(() => new GrayCounter(2.W)) {
      c => {
        val tester = new GrayCounterChecker(c)
        tester.checkWithRollover(2, 8, 3)
        tester
      }
    } should be (true)
  }
  "A counter of 2 with rollover 2" should "increment as expected" in {
    Driver(() => new GrayCounter(2.W)) {
      c => {
        val tester = new GrayCounterChecker(c)
        tester.checkWithRollover(2, 8, 2)
        tester
      }
    } should be (true)
  }

  "A counter of 3" should "increment as expected" in {
    Driver(() => new GrayCounter(3.W)) {
      c => {
        val tester = new GrayCounterChecker(c)
        tester.check(3, 16)
        tester
      }
    } should be (true)
  }
  "A counter of 3 with rollover 5" should "increment as expected" in {
    Driver(() => new GrayCounter(3.W)) {
      c => {
        val tester = new GrayCounterChecker(c)
        tester.checkWithRollover(3, 16, 5)
        tester
      }
    } should be (true)
  }
  "A counter of 4" should "increment as expected" in {
    Driver(() => new GrayCounter(4.W)) {
      c => {
        val tester = new GrayCounterChecker(c)
        tester.check(4, 20)
        tester
      }
    } should be (true)
  }

  "A longer ladder of 6 with rollover 57" should "increment as expected" in {
    Driver(() => new GrayCounter(6.W)) {
      c => {
        val tester = new GrayCounterChecker(c)
        tester.checkWithRollover(6, 129, 57)
        tester
      }
    } should be (true)
  }

}
