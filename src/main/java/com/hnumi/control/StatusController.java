package com.hnumi.control;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.alibaba.fastjson.JSON;
import com.hnumi.pojo.Res;
import com.hnumi.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/status")
public class StatusController {
    @Autowired
    StatusService service;

    @CrossOrigin
    @GetMapping("/all")
    public String getAllStatus(HttpSession httpSession){
        Connection conn= (Connection) httpSession.getAttribute("conn");
        Map map = new HashMap<String, Object>();
        map.put("data",service.getAllStatus(conn));
        String res = JSON.toJSONString(new Res(200,map));
        return res;
    }

    @CrossOrigin
    @GetMapping("/firewall")
    public String getFireWallStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getFireWallStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/hdfs")
    public String getHDFSStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getHDFSStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/yarn")
    public String getYarnStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getYarnStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/mysql")
    public String getMysqlStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getMysqlStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/hive")
    public String getHiveStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getHiveStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/hbase")
    public String getHBaseStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getHBaseStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/zookeeper")
    public String getZookeeperStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getZookeeperStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/kafka")
    public String getKafkaStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getKafkaStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/spark")
    public String getSparkStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getSparkStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/redis")
    public String getRedisStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getRedisStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    @CrossOrigin
    @GetMapping("/flink")
    public String getFlinkStatus(HttpSession httpSession){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        Map map = new HashMap<String, Object>();
        map.put("data",service.getFlinkStatus(session));
        String res = JSON.toJSONString(new Res(200,map));
        session.close();
        return res;
    }

    private Session getSession(HttpSession session) {
        Connection conn= (Connection) session.getAttribute("conn");
        Session s = null;
        try {
            if (conn!=null)
                s = conn.openSession();
        }catch (IOException e){
            e.printStackTrace();
        }
        return s;
    }
}
