package net.chrisrichardson.scaldingexample

import com.twitter.scalding._

import JobUtils._
import DateUtils._

class AnalyzePlayDatesJob(args : Args) extends Job(args) {


  Tsv(args("input"))
    .filter('*) (ignoreRowsMissingFields (6) _)
    .mapTo('* -> ('user_id, 'timestamp, 'art_id, 'art_name, 'track_id, 'track_name)) { fields : (String, String, String, String, String, String) => fields }
    .mapTo('* -> 'timestamp) { fields : (String, String, String, String, String, String) => fields._2 }
    .mapTo('timestamp -> 'month) { timestamp : String => dateToStartOfMonth(timestamp) }
    .groupBy('month) { _.size('timestamp) }
    .write( Tsv( args("output") ) )

}

