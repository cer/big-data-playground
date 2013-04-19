package net.chrisrichardson.scaldingexample

import com.twitter.scalding._


class CountryRankingsJob(args : Args) extends Job(args) {

  // ('user_id, 'timestamp, 'art_id, 'art_name, 'track_id, 'track_name, 'gender, 'age, 'country, 'signup)

  Tsv(args("input"))
    .mapTo('* -> ('track_name, 'country)) { fields : (String, String, String, String, String, String, String, String, String, String) => (fields._6, fields._9) }
    .groupBy(('country, 'track_name)) { _.size('plays) }
    .groupBy('country) { _.sortBy('plays).reverse.mkString('track_name -> 'rankedtracks, "|") }
    .flatMap('rankedtracks -> ('track, 'ranking)) { rankedTracks : String =>  rankedTracks.split("\\|").zipWithIndex }
    .project(('country, 'track, 'ranking))
    .write( Tsv( args("output") ) )

}

