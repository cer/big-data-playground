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


  test("UserPlayCountsJob counts plays by user") {

    import Dsl._

    JobTest(classOf[UserPlayCountsJob].getName)
      .source(Tsv("input"), plays)
      .arg("input", "input")
      .arg("output", "output")
      .sink[(String, String, Int)](Tsv("output")) { buf =>
        buf should equal(
          List(
            ("user1", "Everybody Dance", 1),
            ("user1", "Le Freak", 2),
            ("user2", "Le Freak", 1)
          ))}
      .run
      .finish
  }


}
