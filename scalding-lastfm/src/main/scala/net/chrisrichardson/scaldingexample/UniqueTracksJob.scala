package net.chrisrichardson.scaldingexample

import com.twitter.scalding._

import JobUtils._
import org.apache.commons.lang.StringUtils

class UniqueTracksJob(args : Args) extends Job(args) {

  var id = 0

  Tsv(args("input"))
    .filter('*) (ignoreRowsMissingFields (6) _)
    .mapTo('* -> ('user_id, 'timestamp, 'art_id, 'art_name, 'track_id, 'track_name)) { fields : (String, String, String, String, String, String) => fields }
    .filter('track_id) { StringUtils.isNotBlank }
    .unique('track_id)
    .mapTo('track_id -> ('track_id, 'uniqueId)) { trackId : String =>
        id = id + 1
        (trackId, id)
     }
    .write( Tsv( args("output") ) )

}

