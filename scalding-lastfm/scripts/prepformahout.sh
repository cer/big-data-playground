#!/bin/sh

./scripts/_runhadoopjob.sh PrepForMahoutJob --plays hdfs:/output/userplaycounts.tsv --userIds /output/uniqueuserids.tsv --trackIds /output/uniquetrackids.tsv --output /output/inputtorecommender.tsv
