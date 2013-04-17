package net.chrisrichardson.bigdata.scaldinglastfm

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import com.twitter.scalding.{Dsl, JobTest, Tsv}
import net.chrisrichardson.scaldingexample.JoinUsersAndPlaysJob
import java.util.Date
import TestData._

@RunWith(classOf[JUnitRunner])
class JoinUsersAndPlaysJobTest extends FunSuite with ShouldMatchers {


  def runJob(profiles: List[Product],
             plays : List[Product],
             correctOutput: List[Product]) {
    import Dsl._

    JobTest(classOf[JoinUsersAndPlaysJob].getName)
      .source(Tsv("input1"), profiles)
      .source(Tsv("input2"), plays)
      .arg("input1", "input1")
      .arg("input2", "input2")
      .arg("output", "output")
      .sink[(String, String, String, String, String, String, String, String, String, String)](Tsv("output")) { buf =>
      buf should equal(correctOutput)
    }
      .run
      .finish
  }


  test("JoinUsersAndPlaysJob should rank songs by country") {
    runJob(userProfile, plays, usersAndPlays)
  }


  test("JoinUsersAndPlaysJob should ignore misformatted profiles") {
        runJob(List() :: userProfile, plays, usersAndPlays)
  }

  test("JoinUsersAndPlaysJob should ignore misformatted plays") {
    runJob(userProfile, List() :: plays, usersAndPlays)
  }

}
