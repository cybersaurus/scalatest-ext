package uk.org.codecogs.scalatest

import java.time.LocalDate

import org.scalactic.source
import org.scalatest.WordSpecLike
import uk.org.codecogs.datetime.Clocks

class EnhancedWordSpecLike extends WordSpecLike {
  implicit protected def convertToIgnoreUntilWrapper(wrapped: String) = new IgnoreUntilWordSpecStringWrapper(wrapped)

  protected final class IgnoreUntilWordSpecStringWrapper(wrapped: String) {
    // Can only reference WordSpecStringWrapper from subclass of WordSpecLike.
    private val wordSpecStringWrapper = new WordSpecStringWrapper(wrapped)

    def ignoreUntil(expiryDate: String)(f: => Any)(implicit pos: source.Position): Unit = runIfExpiredElseIgnore(expiryDate)(f)

    private def runIfExpiredElseIgnore(expiryDate: String): ( => Any) => Unit = {
      if (!LocalDate.parse(expiryDate).isAfter(LocalDate.now(Clocks.getClock))) wordSpecStringWrapper.in else wordSpecStringWrapper.ignore
    }
  }
}