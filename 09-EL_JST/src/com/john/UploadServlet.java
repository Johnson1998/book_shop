package com.john;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author John
 * @create 2021-09-2021:39
 */
public class UploadServlet extends HttpServlet {

    /**
     * 用来处理上传的数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("文件上传过来啦！");
//        1.先判断上传的数据是否多段数据（只有是多段的数据，才是文件上传）
        req.setCharacterEncoding("utf-8");
        if (ServletFileUpload.isMultipartContent(req)) {
//            创建FileItemFactor工厂实现类
            FileItemFactory fileItemFactory = new DiskFileItemFactory();
//            创建用于解析上传数据的工具类ServletFileUpload类
            ServletFileUpload servletFileUpload = new ServletFileUpload(fileItemFactory );
//            解析上传的数据得到每一个表单项FileITem
            try {
                List<FileItem> list = servletFileUpload.parseRequest(req);
                for (FileItem fileItem : list){
                    if (fileItem.isFormField()){
                        // 普通表单项
                        System.out.println("表单项的name属性值" + fileItem.getFieldName());
                        // 参数UTF-8解决乱码问题
                        System.out.println("表单项的value属性值" + fileItem.getString("UTF-8"));
                    }else{
                        // 上传文件
                        System.out.println("表单项的name属性值" + fileItem.getFieldName());
                        System.out.println("上传的文件名" + fileItem.getName());

                        fileItem.write(new File("e:\\" + fileItem.getName()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
