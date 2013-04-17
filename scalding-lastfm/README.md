Some scalding examples that analyze the http://www.dtic.upf.edu/~ocelma/MusicRecommendationDataset/lastfm-1K.html dataset.
Inspired by https://github.com/snowplow/scalding-example-project

The shell script scripts/uploadapp.sh uploads the app to the Hadoop master node. It minimizes the upload by running "maven dependency:copy-dependencies" on the Hadoop master node to download dependencies.

You can then execute scalding jobs using the shell scripts in the scripts/ directory. These script runs "hadoop jar" with the HADOOP_CLASSPATH and -libjars set to the dependencies.
There are currently two scripts that you can use:
* scripts/joinusersandplays.sh - It joins the user profile data with the play data. It must be run first.
* scripts/countryrankingsh.sh - this script ranks songs by popularity in each country

Before running these scripts you need to set the following environment variables:
* HADOOP_SSH_OPTIONS - ssh options such as -i keypair.pem
* HADOOP_HOST - the hadoop master



