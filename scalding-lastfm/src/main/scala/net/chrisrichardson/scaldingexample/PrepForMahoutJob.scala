package net.chrisrichardson.scaldingexample

import com.twitter.scalding._

import JobUtils._

class PrepForMahoutJob(args : Args) extends Job(args) {

  Tsv(args("plays"))
    .mapTo('* -> ('user_id, 'track_id, 'plays)) { fields : (String, String, Int) => fields }
    .joinWithSmaller('user_id -> 'user_id,
        Tsv(args("userIds"))
          .mapTo('* -> ('user_id, 'numeric_user_id)) { fields : (String, Int) => fields }
    )
    .joinWithSmaller('track_id -> 'track_id,
        Tsv(args("trackIds"))
          .mapTo('* -> ('track_id, 'numeric_track_id)) { fields : (String, Int) => fields }
    )
    .project(('numeric_user_id, 'numeric_track_id, 'plays))
    .write( Tsv( args("output") ) )

}

