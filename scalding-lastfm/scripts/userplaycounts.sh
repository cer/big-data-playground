#!/bin/sh

./scripts/_runhadoopjob.sh UserPlayCountsJob --input hdfs:/lastfm/userid-timestamp-artid-artname-traid-traname.tsv --output hdfs:/output/userplaycounts.tsv