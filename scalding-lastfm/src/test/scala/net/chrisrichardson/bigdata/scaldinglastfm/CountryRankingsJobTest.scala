package net.chrisrichardson.bigdata.scaldinglastfm

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import com.twitter.scalding.{Dsl, JobTest, Tsv}
import net.chrisrichardson.scaldingexample.CountryRankingsJob
import java.util.Date


@RunWith(classOf[JUnitRunner])
class CountryRankingsJobTest extends FunSuite with ShouldMatchers {

  val userProfile = List(
    ("user1","m","34","USA", new Date().toString),
    ("user2","m","34","UK", new Date().toString)
  )

  val plays = List(
    ("user1", new Date().toString, "chic-id", "Chic", "le-freak-id", "Le Freak"),
    ("user1", new Date().toString, "chic-id", "Chic", "le-freak-id", "Le Freak"),
    ("user1", new Date().toString, "chic-id", "Chic", "every-body-dance", "Everybody Dance"),
    ("user2", new Date().toString, "chic-id", "Chic", "le-freak-id", "Le Freak")
  )


  val correctOutput = List(
    ("USA","Le Freak",0),
    ("USA","Everybody Dance",1),
    ("UK","Le Freak",0)
  )

  def runJob(profiles: List[Product],
             plays : List[Product],
             correctOutput: List[Product]) {
    import Dsl._

    JobTest(classOf[CountryRankingsJob].getName)
      .source(Tsv("input1"), profiles)
      .source(Tsv("input2"), plays)
      .arg("input1", "input1")
      .arg("input2", "input2")
      .arg("output", "output")
      .sink[(String, String, Int)](Tsv("output")) { buf =>
      buf should equal(correctOutput)
    }
      .run
      .finish
  }


  test("CountryRankingsJob should rank songs by country") {
    runJob(userProfile, plays, correctOutput)
  }


  test("CountryRankingsJob should ignore misformatted profiles") {
        runJob(List() :: userProfile, plays, correctOutput)
  }

  test("CountryRankingsJob should ignore misformatted plays") {
    runJob(userProfile, List() :: plays, correctOutput)
  }

}
