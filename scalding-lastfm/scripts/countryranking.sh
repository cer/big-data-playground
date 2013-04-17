#!/bin/sh

./scripts/_runhadoopjob.sh CountryRankingsJob --input hdfs:/output/usersandplays.tsv --output hdfs:/output/countryranking.tsv