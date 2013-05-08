package net.chrisrichardson.bigdata.scaldinglastfm

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import com.twitter.scalding.{Dsl, JobTest, Tsv}
import net.chrisrichardson.scaldingexample.UserPlayCountsJob
import TestData._

@RunWith(classOf[JUnitRunner])
class UserPlayCountsJobTest extends FunSuite with ShouldMatchers {


  val expectedPlayCounts =  List(
    ("user1", "everybody-dance-id", 1),
    ("user1", "le-freak-id", 2),
    ("user2", "le-freak-id", 1)
  )

  test("UserPlayCountsJob counts plays by user") {

    import Dsl._

    JobTest(classOf[UserPlayCountsJob].getName)
      .source(Tsv("input"), plays)
      .arg("input", "input")
      .arg("output", "output")
      .sink[(String, String, Int)](Tsv("output")) { buf =>
        buf should equal(expectedPlayCounts)}
      .run
      .finish
  }

  test("UserPlayCountsJob counts plays by user ignoring blank trackids") {

    import Dsl._

    JobTest(classOf[UserPlayCountsJob].getName)
      .source(Tsv("input"), playsWithNoTrackIds)
      .arg("input", "input")
      .arg("output", "output")
      .sink[(String, String, Int)](Tsv("output")) { buf =>
        buf should equal(expectedPlayCounts)}
      .run
      .finish
  }


}
