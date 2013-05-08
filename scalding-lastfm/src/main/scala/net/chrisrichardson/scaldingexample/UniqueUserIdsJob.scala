package net.chrisrichardson.scaldingexample

import com.twitter.scalding._

import JobUtils._
import DateUtils._

class UniqueUserIdsJob(args : Args) extends Job(args) {

  var id = 0

  Tsv(args("input"))
    .filter('*) (ignoreRowsMissingFields (5) _)
    .mapTo('* -> ('user_id, 'gender, 'age, 'country, 'signup)) { fields : (String, String, String, String, String) => fields }
    .unique('user_id)
    .mapTo('user_id -> ('user_id, 'uniqueId)) { userId : String =>
        id = id + 1
        (userId, id)
     }
    .write( Tsv( args("output") ) )

}

