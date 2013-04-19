package net.chrisrichardson.scaldingexample

import com.twitter.scalding._

import JobUtils._
import DateUtils._

class UserPlayCountsJob(args : Args) extends Job(args) {


  Tsv(args("input"))
    .filter('*) (ignoreRowsMissingFields (6) _)
    .mapTo('* -> ('user_id, 'timestamp, 'art_id, 'art_name, 'track_id, 'track_name)) { fields : (String, String, String, String, String, String) => fields }
    .mapTo('* -> ('user_id, 'track_name)) { fields : (String, String, String, String, String, String) => (fields._1, fields._6) }
    .groupBy(('user_id, 'track_name)) { _.size('plays) }
    .write( Tsv( args("output") ) )

}

