#!/bin/sh

./scripts/_runhadoopjob.sh JoinUsersAndPlaysJob --input1 hdfs:/lastfm/user-profile.tsv --input2 hdfs:/lastfm/userid-timestamp-artid-artname-traid-traname.tsv --output hdfs:/output/usersandplays.tsv