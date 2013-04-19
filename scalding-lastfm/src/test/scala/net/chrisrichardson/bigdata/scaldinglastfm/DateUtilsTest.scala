package net.chrisrichardson.bigdata.scaldinglastfm

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import net.chrisrichardson.scaldingexample.DateUtils._

@RunWith(classOf[JUnitRunner])
class DateUtilsTest extends FunSuite with ShouldMatchers {

    test("should handle blank dates") {
        val d = dateToStartOfMonth("")
        println("blank=" + d)
    }

    test("should handle non-blank dates") {
      val d = dateToStartOfMonth("2009-05-04T13:54:10Z")
      println("blank=" + d)

    }
}
