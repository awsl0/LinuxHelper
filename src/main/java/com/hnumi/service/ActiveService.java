package com.hnumi.service;

import com.hnumi.pojo.Res;
import com.hnumi.utils.Commands;
import com.hnumi.utils.LinuxUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ch.ethz.ssh2.Session;
import java.nio.charset.Charset;
import java.util.Map;

@Service
public class ActiveService implements Commands {
    @Autowired
    LinuxUtil util;

    private String charset = Charset.defaultCharset().toString();

    //如果active是true开启服务，如果active是false关闭服务
    public Res FireWall(Session session,boolean active){
        if (active){
            Map map = util.exec(firewallStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启防火墙！");
            else
                return new Res(500,"开启防火墙失败",map);
        }else {
            Map map = util.exec(firewallStop,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已关闭防火墙！");
            else
                return new Res(500,"关闭防火墙失败",map);
        }
    }

    public Res HDFS(Session session,boolean active){
        if (active){
            Map map = util.exec(hdfsStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启HDFS！");
            else
                return new Res(500,"开启HDFS失败",map);
        }else {
            Map map = util.exec(hdfsStop,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已关闭HDFS！");
            else
                return new Res(500,"关闭HDFS失败",map);
        }
    }

    public Res Yarn(Session session,boolean active){
        if (active){
            Map map = util.exec(yarnStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启Yarn！");
            else
                return new Res(500,"开启Yarn失败",map);
        }else {
            Map map = util.exec(yarnStop,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已关闭Yarn！");
            else
                return new Res(500,"关闭Yarn失败",map);
        }
    }

    public Res Mysql(Session session,boolean active){
        if (active){
            Map map = util.exec(mysqlStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启Mysql！");
            else
                return new Res(500,"开启Mysql失败",map);
        }else {
            Map map = util.exec(mysqlStop,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已关闭Mysql！");
            else
                return new Res(500,"关闭Mysql失败",map);
        }
    }

    public Res Hive(Session session,boolean active){
        if (active){
            Map map = util.exec(hiveStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启Hive！");
            else
                return new Res(500,"开启Hive失败",map);
        }else {
            return new Res(200,"此功能未开启！");
        }
    }

    public Res HBase(Session session,boolean active){
        if (active){
            Map map = util.exec(hbaseStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启HBase！");
            else
                return new Res(500,"开启HBase失败",map);
        }else {
            Map map = util.exec(hbaseStop,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已关闭HBase！");
            else
                return new Res(500,"关闭HBase失败",map);
        }
    }

    public Res Zookeeper(Session session,boolean active){
        if (active){
            Map map = util.exec(zookeeperStart,session,charset);
            if (!map.containsKey("error"))
                return new Res(200,"已开启Zookeeper！");
            else
                return new Res(500,"开启Zookeeper失败",map);
        }else {
            Map map = util.exec(zookeeperStop,session,charset);
            if (!map.containsKey("error"))
                return new Res(200,"已关闭Zookeeper！");
            else
                return new Res(500,"关闭Zookeeper失败",map);
        }
    }

    public Res Kafka(Session session,boolean active){
        if (active){
            Map map = util.exec(kafkaStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启Kafka！");
            else
                return new Res(500,"开启Kafka失败",map);
        }else {
            Map map = util.exec(kafkaStop,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已关闭Kafka！");
            else
                return new Res(500,"关闭Kafka失败",map);
        }
    }

    public Res Spark(Session session,boolean active){
        if (active){
            Map map = util.exec(sparkStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启Spark！");
            else
                return new Res(500,"开启Spark失败",map);
        }else {
            Map map = util.exec(sparkStop,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已关闭Spark！");
            else
                return new Res(500,"关闭Spark失败",map);
        }
    }

    public Res Redis(Session session,boolean active){
        if (active){
            Map map = util.exec(redisStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启Redis！");
            else
                return new Res(500,"开启Redis失败",map);
        }else {
            Map map = util.exec(redisStop,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已关闭Redis！");
            else
                return new Res(500,"关闭Redis失败",map);
        }
    }

    public Res Flink(Session session,boolean active){
        if (active){
            Map map = util.exec(flinkStart,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已开启Flink！");
            else
                return new Res(500,"开启Flink失败",map);
        }else {
            Map map = util.exec(flinkStop,session,charset);
            if (map.get("error").toString().isEmpty())
                return new Res(200,"已关闭Flink！");
            else
                return new Res(500,"关闭Flink失败",map);
        }
    }
}
