package net.chrisrichardson.bigdata.scaldinglastfm

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import com.twitter.scalding.{Dsl, JobTest, Tsv}
import net.chrisrichardson.scaldingexample.{AnalyzePlayDatesJob, JoinUsersAndPlaysJob}
import TestData._

@RunWith(classOf[JUnitRunner])
class AnalyzePlayDatesJobTest extends FunSuite with ShouldMatchers {


  test("AnalyzePlayDatesJob bucket plays by month") {

    import Dsl._

    JobTest(classOf[AnalyzePlayDatesJob].getName)
      .source(Tsv("input"), plays)
      .arg("input", "input")
      .arg("output", "output")
      .sink[(String, Int)](Tsv("output")) { buf =>
        buf should equal(List(("2006-03-01T00:00:00Z",2), ("2009-10-31T23:00:00Z",1), ("2012-01-01T00:00:00Z",1)))
    }
      .run
      .finish
  }


}
