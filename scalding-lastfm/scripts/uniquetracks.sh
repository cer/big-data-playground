#!/bin/sh

env MR_OPTIONS="-D mapred.reduce.tasks=1" ./scripts/_runhadoopjob.sh UniqueTracksJob  --input hdfs:/lastfm/userid-timestamp-artid-artname-traid-traname.tsv --output hdfs:/output/uniquetrackids.tsv