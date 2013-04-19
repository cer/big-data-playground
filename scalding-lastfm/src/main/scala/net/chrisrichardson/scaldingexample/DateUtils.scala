package net.chrisrichardson.scaldingexample

import org.apache.commons.lang.StringUtils
import org.joda.time.format.ISODateTimeFormat
import org.joda.time.{DateTimeZone, Months, DateTime}

object DateUtils {

  val beginningOfTime = new DateTime(0)

  /*
  def dateToMonth(s : String) = if (StringUtils.isBlank(s)) 0 else {
    val d = ISODateTimeFormat.dateTimeNoMillis().parseDateTime(s)
    Months.monthsBetween(beginningOfTime, d).getMonths
  }
  */

  val dateToStartOfMonth : (String) => String = {
    case s if StringUtils.isBlank(s) => ""
    case s =>
      val formatter = ISODateTimeFormat.dateTimeNoMillis().withZone(DateTimeZone.UTC)
      val d = formatter.parseDateTime(s)
      formatter.print(beginningOfTime.plusMonths(Months.monthsBetween(beginningOfTime, d).getMonths))
  }

}
