package com.john;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author John
 * @create 2021-09-2023:05
 */
public class DownloadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.获取要下载的文件名
        String downloadFileName = "b.jpg";
//        2.读取要下载的文件内容（通过servletContext对象可以读取）
        ServletContext servletContext = getServletContext();
        String mimeType = servletContext.getMimeType("/file/" + downloadFileName);
        System.out.println("下载的文件类型" + mimeType);
//        4.回传前，通过响应头告诉客户端返回的数据类型
        resp.setContentType(mimeType);
//        5.还要告诉客户端收到的数据是不是用来下载使用的（还是用来相应头）

//        3。吧下载的文件内容回传给客户端
//        读取输入流中的数据（resourceAsstream）复制给输出流（outputStream客户端）
        InputStream resourceAsStream = servletContext.getResourceAsStream("/file/" + downloadFileName);
        OutputStream outputStream = resp.getOutputStream();
        IOUtils.copy(resourceAsStream, outputStream);



    }
}
