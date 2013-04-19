package net.chrisrichardson.scaldingexample

import com.twitter.scalding._

import JobUtils._

class JoinUsersAndPlaysJob(args : Args) extends Job(args) {

  Tsv(args("input1"))
    .filter('*) (ignoreRowsMissingFields (5) _)
    .mapTo('* -> ('user_id, 'gender, 'age, 'country, 'signup)) { fields : (String, String, String, String, String) => fields }
    .joinWithLarger('user_id -> 'user_id,
        Tsv(args("input2"))
          .filter('*) (ignoreRowsMissingFields (6) _)
          .mapTo('* -> ('user_id, 'timestamp, 'art_id, 'art_name, 'track_id, 'track_name)) { fields : (String, String, String, String, String, String) => fields }
    )
    .write( Tsv( args("output") ) )

}

