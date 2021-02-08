package com.hnumi.control;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import com.alibaba.fastjson.JSON;
import com.hnumi.pojo.Res;
import com.hnumi.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/active")
public class ActiveController {
    @Autowired
    ActiveService service;

    @CrossOrigin
    @PostMapping("/FireWall")
    public String FireWall(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.FireWall(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/HDFS")
    public String HDFS(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.HDFS(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/Yarn")
    public String Yarn(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.Yarn(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/Mysql")
    public String Mysql(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.Mysql(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/Hive")
    public String Hive(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.Hive(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/HBase")
    public String HBase(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.HBase(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/Zookeeper")
    public String Zookeeper(HttpSession httpSession,@RequestParam Boolean active){
        System.out.println(active);
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.Zookeeper(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/Kafka")
    public String Kafka(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.Kafka(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/Spark")
    public String Spark(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.Spark(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/Redis")
    public String Redis(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.Redis(session,active));
        session.close();
        return res;
    }

    @CrossOrigin
    @PostMapping("/Flink")
    public String Flink(HttpSession httpSession,Boolean active){
        Session session = getSession(httpSession);
        if (session==null)
            return JSON.toJSONString(new Res(401,"请先登录！"));
        String res = JSON.toJSONString(service.Flink(session,active));
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
