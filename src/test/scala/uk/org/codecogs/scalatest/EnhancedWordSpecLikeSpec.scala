package uk.org.codecogs.scalatest

import java.time.LocalDate

import org.scalatest.BeforeAndAfterAll
import uk.org.codecogs.datetime.Clocks

class EnhancedWordSpecLikeSpec extends EnhancedWordSpecLike with BeforeAndAfterAll {
  Clocks.useFixedClockAt(LocalDate.parse("2016-12-14").atTime(22,21))

  override protected def afterAll  = Clocks.useSystemDefaultClock()


  "EnhancedWordSpecLike" should {
    "run ignoreUntil 2016-12-13".ignoreUntil("2016-12-13") {
      println("Ran ignoreUntil 2016-12-13!")
    }

    "run ignoreUntil 2016-12-14".ignoreUntil("2016-12-14") {
      println("Ran ignoreUntil 2016-12-14!")
    }

    "ignore ignoreUntil 2016-12-15".ignoreUntil("2016-12-15") {
      fail("ignoreUntil 2016-12-15 should not have run!")
    }

    // Check the normal in/ignore functions still work.
    //
    "run normal tests" in {println("Ran normal test!")}
    "ignore normal tests" ignore {fail("ignored test should not have run!")}
  }
}
