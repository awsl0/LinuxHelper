package com.hnumi.control;

import ch.ethz.ssh2.Connection;
import com.alibaba.fastjson.JSON;
import com.hnumi.pojo.Login;
import com.hnumi.pojo.Res;
import com.hnumi.service.ActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;


@RestController
public class HomeController {
    @Autowired
    ActiveService activeService;

    @CrossOrigin
    @GetMapping("/login")
    public String loginPage(){
        return JSON.toJSONString(new Res(401,"请先创建连接！"));
    }

    @CrossOrigin
    @PostMapping("/login")
    public String login(@RequestBody Login login, HttpSession session) {
        if (login.getIpAddr().isEmpty()||login.getPort().isEmpty()||login.getUserName().isEmpty()||login.getPassword().isEmpty())
            return JSON.toJSONString(new Res(401,"请填写完整信息！"));
        Connection conn = new Connection(login.getIpAddr(), Integer.valueOf(login.getPort()));
        try {
            conn.connect();
            if(conn.authenticateWithPassword(login.getUserName(), login.getPassword())) {
                session.setAttribute("conn",conn);
                return JSON.toJSONString(new Res(200, "登陆成功！"));
            }else
                return JSON.toJSONString(new Res(500,"用户名或密码错误！"));
        }catch (IOException e) {
            e.printStackTrace();
            return JSON.toJSONString(new Res(501,"IP地址或端口无法连接！"));
        }
    }

    @CrossOrigin
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("conn");
        return JSON.toJSONString(new Res(200));
    }
}
