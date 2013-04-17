package net.chrisrichardson.bigdata.scaldinglastfm

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import com.twitter.scalding.{Dsl, JobTest, Tsv}
import net.chrisrichardson.scaldingexample.CountryRankingsJob

import TestData.usersAndPlays

@RunWith(classOf[JUnitRunner])
class CountryRankingsJobTest extends FunSuite with ShouldMatchers {

   val correctOutput = List(
    ("USA","Le Freak",0),
    ("USA","Everybody Dance",1),
    ("UK","Le Freak",0)
  )

  test("CountryRankingsJob should rank songs by country") {
    import Dsl._

    JobTest(classOf[CountryRankingsJob].getName)
      .source(Tsv("input"), usersAndPlays)
      .arg("input", "input")
      .arg("output", "output")
      .sink[(String, String, Int)](Tsv("output")) { buf =>
      buf should equal(correctOutput)
    }
      .run
      .finish
  }

}
