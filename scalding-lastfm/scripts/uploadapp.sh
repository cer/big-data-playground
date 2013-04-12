#!/bin/sh
set -e

if [ ! -f target/scalding-lastfm-1.0-SNAPSHOT.jar  ] ; then
    echo run mvn package first!
    exit 99
fi

ssh ${HADOOP_SSH_OPTIONS} hadoop@${HADOOP_HOST} mkdir -p app
scp ${HADOOP_SSH_OPTIONS} pom.xml target/scalding-lastfm-1.0-SNAPSHOT.jar hadoop@${HADOOP_HOST}:app
ssh ${HADOOP_SSH_OPTIONS} hadoop@${HADOOP_HOST} "cd app ; mvn clean dependency:copy-dependencies"

echo Done