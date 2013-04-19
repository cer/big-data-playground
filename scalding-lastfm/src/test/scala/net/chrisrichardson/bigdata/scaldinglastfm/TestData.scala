package net.chrisrichardson.bigdata.scaldinglastfm

import java.util.Date

object TestData {

  def nowAsString = new Date().toString

  val user1_signup = nowAsString
  val user2_signup = nowAsString

  val userProfile = List(
    ("user1", "m", "34", "USA", user1_signup),
    ("user2", "m", "34", "UK", user2_signup)
  )

  val plays = List(
    ("user1", "2006-03-04T23:08:57Z", "chic-id", "Chic", "le-freak-id", "Le Freak"),
    ("user1", "2009-11-04T13:54:10Z", "chic-id", "Chic", "le-freak-id", "Le Freak"),
    ("user1", "2012-01-04T13:54:10Z", "chic-id", "Chic", "every-body-dance", "Everybody Dance"),
    ("user2", "2006-03-08T23:08:57Z", "chic-id", "Chic", "le-freak-id", "Le Freak")
  )

  val usersAndPlays = List(
    ("user1", "2006-03-04T23:08:57Z", "chic-id", "Chic", "le-freak-id", "Le Freak", "m", "34", "USA", user1_signup),
    ("user1", "2009-11-04T13:54:10Z", "chic-id", "Chic", "le-freak-id", "Le Freak", "m", "34", "USA", user1_signup),
    ("user1", "2012-01-04T13:54:10Z", "chic-id", "Chic", "every-body-dance", "Everybody Dance", "m", "34", "USA", user1_signup),
    ("user2", "2006-03-08T23:08:57Z", "chic-id", "Chic", "le-freak-id", "Le Freak", "m", "34", "UK", user2_signup)
  )

}
