package com.hnumi.utils;

public interface Commands {
    String firewallStatus = "systemctl status firewalld";
    String firewallStart = "systemctl start firewalld";
    String firewallStop = "systemctl stop firewalld";

    String hdfsStart = "source /etc/profile && ${HADOOP_HOME}/sbin/start-dfs.sh";
    String hdfsStop = "source /etc/profile && ${HADOOP_HOME}/sbin/stop-dfs.sh";

    String yarnStart = "source /etc/profile && ${HADOOP_HOME}/sbin/start-yarn.sh";
    String yarnStop = "source /etc/profile && ${HADOOP_HOME}/sbin/stop-dfs.sh";

    String mysqlStatus = "systemctl status mysqld";
    String mysqlStart = "systemctl start mysqld";
    String mysqlStop = "systemctl stop mysqld";

    String hiveStart = " hiveserver2 > /dev/null 2>&1 &";

    String hbaseStart = "source /etc/profile && ${HBASE_HOME}/bin/start-hbase.sh";
    String hbaseStop = "source /etc/profile && ${HBASE_HOME}/bin/stop-hbase.sh";

    String zookeeperStart = "source /etc/profile && ${ZOOKEEPER_HOME}/bin/zkServer.sh start";
    String zookeeperStop = "source /etc/profile && ${ZOOKEEPER_HOME}/bin/zkServer.sh stop";

    String kafkaStart = "source /etc/profile && ${KAFKA_HOME}/bin/kafka-server-start.sh -daemon ${KAFKA_HOME}/config/server.properties";
    String kafkaStop = "source /etc/profile && ${KAFKA_HOME}/bin/kafka-server-stop.sh";

    String flinkStart = "source /etc/profile && ${FLINK_HOME}/bin/start-cluster.sh";
    String flinkStop = "source /etc/profile && ${FLINK_HOME}/bin/stop-cluster.sh";

    String sparkStart = "source /etc/profile && ${SPARK_HOME}/sbin/start-all.sh";
    String sparkStop = "source /etc/profile && ${SPARK_HOME}/sbin/stop-all.sh";

    String redisStart = "./usr/local/bin/redis-server";
    String redisStop = "./usr/local/bin/redis-cli shutdown";

    String jps = "source /etc/profile && ${JAVA_HOME}/bin/jps";
}
