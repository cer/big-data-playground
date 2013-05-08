package net.chrisrichardson.bigdata.scaldinglastfm

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import com.twitter.scalding.{Dsl, JobTest, Tsv}
import net.chrisrichardson.scaldingexample.{UniqueTracksJob, UniqueUserIdsJob}
import TestData._

@RunWith(classOf[JUnitRunner])
class UniqueTracksJobTest extends FunSuite with ShouldMatchers {


  test("UniqueTracksJob bucket plays by month") {

    import Dsl._

    JobTest(classOf[UniqueTracksJob].getName)
      .source(Tsv("input"), plays)
      .arg("input", "input")
      .arg("output", "output")
      .sink[(String, Int)](Tsv("output")) { buf =>
        buf should equal(
          List(("everybody-dance-id",1), ("le-freak-id",2))
        )
    }
      .run
      .finish
  }


}
