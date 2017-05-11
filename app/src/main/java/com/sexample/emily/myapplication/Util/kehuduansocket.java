package com.sexample.emily.myapplication.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Emily on 2017/5/5.
 */

public class kehuduansocket {
    public static void main(String[] args) {
        try {
            // 创建客户端Socket,指定主机和端口
            Socket mSocket = new Socket("localhost", 5120);
            // 通过输入流向服务器发数据
            OutputStream os = mSocket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            pw.write("来自客户端Socket的数据，你收到了吗？");
            pw.flush();
            mSocket.shutdownOutput();
            // 收取服务端的回复信息
            InputStream is = mSocket.getInputStream();
            // 通过获取缓冲数据方式来读取文本信息
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // 获取文本信息
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            System.out.println(sb.toString());
            // 关闭流通常放在finally中操作
            br.close();
            is.close();
            pw.close();
            os.close();
            mSocket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
