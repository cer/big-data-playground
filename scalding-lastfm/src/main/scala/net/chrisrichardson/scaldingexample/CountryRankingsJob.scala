package net.chrisrichardson.scaldingexample

import com.twitter.scalding._
import cascading.tuple.TupleEntry

class CountryRankingsJob(args : Args) extends Job(args) {

  def ignoreRowsMissingFields(expectedSize : Int)(args: TupleEntry ) = args.size == expectedSize

  Tsv(args("input1"))
    .filter('*) (ignoreRowsMissingFields (5) _)
    .mapTo('* -> ('user_id, 'country)) { fields : (String, String, String, String, String) => (fields._1, fields._4) }
    .joinWithLarger('user_id -> 'user_id,
        Tsv(args("input2"))
          .filter('*) (ignoreRowsMissingFields (6) _)
          .mapTo ('* -> ('user_id, 'traname)) {  fields : (String, String, String, String, String, String) => (fields._1, fields._6) })
    .groupBy(('country, 'traname)) { _.size('plays) }
    .groupBy('country) { _.sortBy('plays).reverse.mkString('traname -> 'rankedtracks, "|") }
    .flatMap('rankedtracks -> ('track, 'ranking)) { rankedTracks : String =>  rankedTracks.split("\\|").zipWithIndex }
    .project(('country, 'track, 'ranking))
    .write( Tsv( args("output") ) )

}

