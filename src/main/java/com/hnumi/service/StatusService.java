package com.hnumi.service;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.hnumi.pojo.Status;
import com.hnumi.utils.Commands;
import com.hnumi.utils.LinuxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatusService implements Commands {
    @Autowired
    LinuxUtil util;

    private static final String charset = Charset.defaultCharset().toString();

    public Status getFireWallStatus(Session session){
        Map map = util.exec(firewallStatus,session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("running"))
                return new Status("FireWall",true);
            else
                return new Status("FireWall",false);
        }else
            System.out.println("getFireWallStatus :"+map.get("error"));
            return new Status("FireWall",false);
    }

    public Status getHDFSStatus(Session session){
        Map map = util.exec(jps,session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("DataNode"))
                return new Status("HDFS",true);
            else
                return new Status("HDFS",false);
        }else
            System.out.println("getHDFSStatus :"+map.get("error"));
        return new Status("HDFS",false);
    }

    public Status getYarnStatus(Session session){
        Map map = util.exec(jps,session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("NodeManager"))
                return new Status("Yarn",true);
            else
                return new Status("Yarn",false);
        }else
            System.out.println("getYarnStatus :"+map.get("error"));
        return new Status("Yarn",false);
    }

    public Status getMysqlStatus(Session session){
        Map map = util.exec(mysqlStatus,session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("running"))
                return new Status("Mysql",true);
            else
                return new Status("Mysql",false);
        }else
            System.out.println("getMysqlStatus :"+map.get("error"));
        return new Status("Mysql",false);
    }

    public Status getHiveStatus(Session session){
        Map map = util.exec("ps -ef |grep hive",session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("hiveserver2"))
                return new Status("Hive",true);
            else
                return new Status("Hive",false);
        }else
            System.out.println("getHiveStatus :"+map.get("error"));
        return new Status("Hive",false);
    }

    public Status getHBaseStatus(Session session){
        Map map = util.exec(jps,session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("HMaster")||result.contains("HRegionServer"))
                return new Status("HBase",true);
            else
                return new Status("HBase",false);
        }else
            System.out.println("getHBaseStatus :"+map.get("error"));
        return new Status("HBase",false);
    }

    public Status getZookeeperStatus(Session session){
        Map map = util.exec(jps,session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("QuorumPeerMain"))
                return new Status("Zookeeper",true);
            else
                return new Status("Zookeeper",false);
        }else
            System.out.println("getZookeeperStatus :"+map.get("error"));
        return new Status("Zookeeper",false);
    }

    public Status getKafkaStatus(Session session){
        Map map = util.exec(jps,session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("Kafka"))
                return new Status("Kafka",true);
            else
                return new Status("Kafka",false);
        }else
            System.out.println("getKafkaStatus :"+map.get("error"));
        return new Status("Kafka",false);
    }

    public Status getSparkStatus(Session session){
        Map map = util.exec(jps,session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("Master")||result.contains("Worker"))
                return new Status("Spark",true);
            else
                return new Status("Spark",false);
        }else
            System.out.println("getSparkStatus :"+map.get("error"));
        return new Status("Spark",false);
    }

    public Status getRedisStatus(Session session){
        Map map = util.exec("ps -ef |grep redis",session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("redis-server"))
                return new Status("Redis",true);
            else
                return new Status("Redis",false);
        }else
            System.out.println("getRedisStatus :"+map.get("error"));
        return new Status("Redis",false);
    }

    public Status getFlinkStatus(Session session){
        Map map = util.exec(jps,session,charset);
        if (map.get("error")==null){
            String result = map.get("result").toString();
            if(result.contains("StandaloneSessionClusterEntrypoint")||result.contains("TaskManagerRunner"))
                return new Status("Flink",true);
            else
                return new Status("Flink",false);
        }else
            System.out.println("getFlinkStatus :"+map.get("error"));
        return new Status("Flink",false);
    }

    public Status[] getAllStatus(Connection conn){
        List<Status> statuses = new ArrayList<>();
        try {
            Session session = conn.openSession();
            statuses.add(getFireWallStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getHDFSStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getYarnStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getMysqlStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getHiveStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getHBaseStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getZookeeperStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getKafkaStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getSparkStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getRedisStatus(session));
            session.close();
            session = conn.openSession();
            statuses.add(getFlinkStatus(session));
            session.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        Status[] arr= new Status[statuses.size()];
        return statuses.toArray(arr);
    }
}
