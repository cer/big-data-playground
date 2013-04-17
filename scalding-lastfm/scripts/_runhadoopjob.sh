#!/bin/sh
set -e
CLASS_NAME=$1
shift
ssh ${HADOOP_SSH_OPTIONS} hadoop@${HADOOP_HOST} "env HADOOP_CLASSPATH=\`find  app -type f -name '*.jar' | tr '\\n' ':'\` hadoop jar app/scalding-lastfm-1.0-SNAPSHOT.jar  net.chrisrichardson.scaldingexample.JobRunner -libjars \`find app -type f -name '*.jar' | tr '\\n' ','\` net.chrisrichardson.scaldingexample.${CLASS_NAME} --hdfs  $*"