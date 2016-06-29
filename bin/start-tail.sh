#! /bin/bash

bin=`dirname "$0"`
bin=`cd "$bin"; pwd`
BASEDIR=${bin}/..

log_dir=${BASEDIR}/logs
cd $BASEDIR

CLASSPATH=$BASEDIR
CLASSPATH=$CLASSPATH:./conf


for f in `find $BASEDIR/lib -type f -name "*.jar"`
do
  CLASSPATH=$CLASSPATH:$f
done

OPT="-cp $CLASSPATH -Djava.library.path=$BASEDIR/lib"
OPT=${OPT}" -server -Xms500m -Xmx500m -XX:+UseConcMarkSweepGC -XX:NewRatio=2 -XX:+UseCMSCompactAtFullCollection -XX:CMSFullGCsBeforeCompaction=10"
OPT=${OPT}" -XX:+PrintGCDetails  -XX:+PrintHeapAtGC  -XX:+PrintGCDateStamps"
OPT=${OPT}" -XX:+PrintTenuringDistribution  -verbose:gc  -Xloggc:${log_dir}/gc.log"

JAVA_BIN=`which java`
if [ "$JAVA_BIN" == "" ]; then
        JAVA_BIN=/usr/jdk64/jdk1.7.0_71/bin/java
fi

PARAM="$*"

# ***************
# ** Run...    **
# ***************
nohup $JAVA_BIN $OPT com.baidu.hina.web.tail.JettyServerMain $PARAM 1>${log_dir}/hina-tail-web.log 2>${log_dir}/hina-tail-web.err &
echo $! > ${BASEDIR}/hina-tail-web.pid
