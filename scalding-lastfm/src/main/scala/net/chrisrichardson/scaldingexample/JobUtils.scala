package net.chrisrichardson.scaldingexample

import cascading.tuple.TupleEntry

object JobUtils {

  def ignoreRowsMissingFields(expectedSize : Int)(args: TupleEntry ) = args.size == expectedSize

}
