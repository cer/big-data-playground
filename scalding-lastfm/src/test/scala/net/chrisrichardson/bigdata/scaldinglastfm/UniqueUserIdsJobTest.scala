package net.chrisrichardson.bigdata.scaldinglastfm

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import com.twitter.scalding.{Dsl, JobTest, Tsv}
import net.chrisrichardson.scaldingexample.{UniqueUserIdsJob, AnalyzePlayDatesJob}
import TestData._

@RunWith(classOf[JUnitRunner])
class UniqueUserIdsJobTest extends FunSuite with ShouldMatchers {


  test("UniqueUserIdsJob bucket plays by month") {

    import Dsl._

    JobTest(classOf[UniqueUserIdsJob].getName)
      .source(Tsv("input"), userProfile)
      .arg("input", "input")
      .arg("output", "output")
      .sink[(String, Int)](Tsv("output")) { buf =>
        buf should equal(
          List(("user1", 1), ("user2", 2))
        )
    }
      .run
      .finish
  }


}
