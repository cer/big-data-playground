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
    ("user1", nowAsString, "chic-id", "Chic", "le-freak-id", "Le Freak"),
    ("user1", nowAsString, "chic-id", "Chic", "le-freak-id", "Le Freak"),
    ("user1", nowAsString, "chic-id", "Chic", "every-body-dance", "Everybody Dance"),
    ("user2", nowAsString, "chic-id", "Chic", "le-freak-id", "Le Freak")
  )

  val usersAndPlays = List(
    ("user1", nowAsString, "chic-id", "Chic", "le-freak-id", "Le Freak", "m", "34", "USA", user1_signup),
    ("user1", nowAsString, "chic-id", "Chic", "le-freak-id", "Le Freak", "m", "34", "USA", user1_signup),
    ("user1", nowAsString, "chic-id", "Chic", "every-body-dance", "Everybody Dance", "m", "34", "USA", user1_signup),
    ("user2", nowAsString, "chic-id", "Chic", "le-freak-id", "Le Freak", "m", "34", "UK", user2_signup)
  )

}
