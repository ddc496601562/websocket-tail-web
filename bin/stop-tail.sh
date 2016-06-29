#/bin/bash

bin=`dirname "$0"`
bin=`cd "$bin"; pwd`
base_home_dir=${bin}/..

last_pid=`cat ${base_home_dir}/hina-tail-web.pid`

kill -15 $last_pid