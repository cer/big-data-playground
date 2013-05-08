package net.chrisrichardson.bigdata.scaldinglastfm

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import com.twitter.scalding.{Dsl, JobTest, Tsv}
import net.chrisrichardson.scaldingexample.{PrepForMahoutJob, UserPlayCountsJob}
import TestData._

@RunWith(classOf[JUnitRunner])
class PrepForMahoutJobTest extends FunSuite with ShouldMatchers {


  val expectedPlayCounts =  List(
    ("user1", "everybody-dance-id", 1),
    ("user1", "le-freak-id", 2),
    ("user2", "le-freak-id", 1)
  )

  val userIds = List(
    ("user1", 101),
    ("user2", 102))

  val trackIds = List(
    ("everybody-dance-id", 901),
    ("le-freak-id", 902)
  )

  test("PrepForMahoutJob counts plays by user") {

    import Dsl._

    JobTest(classOf[PrepForMahoutJob].getName)
      .source(Tsv("plays"), expectedPlayCounts)
      .source(Tsv("userIds"), userIds)
      .source(Tsv("trackIds"), trackIds)
      .arg("plays", "plays")
      .arg("userIds", "userIds")
      .arg("trackIds", "trackIds")
      .arg("output", "output")
      .sink[(Int, Int, Int)](Tsv("output")) { buf =>
        buf should equal(List((101,901,1), (101,902,2), (102,902,1)))}
      .run
      .finish
  }



}
