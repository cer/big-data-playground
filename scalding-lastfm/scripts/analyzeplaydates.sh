#!/bin/sh

./scripts/_runhadoopjob.sh AnalyzePlayDatesJob --input hdfs:/lastfm/userid-timestamp-artid-artname-traid-traname.tsv --output hdfs:/output/playsbymonth.tsv