package net.chrisrichardson.scaldingexample

// Hadoop
import org.apache.hadoop

// Scalding
import com.twitter.scalding.Tool

/**
 * Entrypoint for Hadoop to kick off the job.
 *
 * Borrowed from com.twitter.scalding.Tool
 *
 * Borrowed from https://github.com/snowplow/scalding-example-project
 */
object JobRunner {
  def main(args : Array[String]) {
    hadoop.util.ToolRunner.run(new hadoop.conf.Configuration, new Tool, args);
  }
}