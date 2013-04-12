Some scalding examples that analyze the http://www.dtic.upf.edu/~ocelma/MusicRecommendationDataset/lastfm-1K.html dataset.
Inspired by https://github.com/snowplow/scalding-example-project

The scripts/uploadapp.sh script uploads the app to the Hadoop master node. It minimizes the upload by running "maven dependency:copy-dependencies" on the Hadoop master node to download dependencies.

You can then execute the scalding job using the scripts/runhadoopjob.sh script. This script runs "hadoop jar" with the HADOOP_CLASSPATH and -libjars set to the dependencies.

Before running these scripts you need to set the following environment variables:
* HADOOP_SSH_OPTIONS - ssh options such as -i keypair.pem
* HADOOP_HOST - the hadoop master



