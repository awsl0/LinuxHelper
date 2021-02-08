package com.hnumi.utils;

import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class LinuxUtil {
    public Map<String, Object> exec(String cmds, Session session,String charset){
        InputStream inputStream = null;
        String result = "";
        Map<String, Object> map = new HashMap<>();
        try {
            session.execCommand(cmds);
            inputStream = session.getStdout();
            //获取控制台标准输出
            result = this.processStdout(inputStream, charset);
            //如果输出为空，则说明执行失败，获取失败信息
            if (StringUtils.isEmpty(result)) {
                result = processStdout(session.getStderr(), charset);
                map.put("error",result);
            }else
                map.put("result",result);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("error",e.getMessage());
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return map;
        }
    }

    /**
     * 获取控制台输出信息
     */
    public String processStdout(InputStream inputStream, String charset) {
        InputStream in = new StreamGobbler(inputStream);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in, charset));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuffer.toString();
    }
}
