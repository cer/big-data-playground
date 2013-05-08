#!/bin/sh

env MR_OPTIONS="-D mapred.reduce.tasks=1" ./scripts/_runhadoopjob.sh UniqueUserIdsJob  --input hdfs:/lastfm/user-profile.tsv --output hdfs:/output/uniqueuserids.tsv