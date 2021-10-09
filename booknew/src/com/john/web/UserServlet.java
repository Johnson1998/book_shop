package com.john.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.google.gson.Gson;
import com.john.pojo.User;
import com.john.service.UserService;
import com.john.service.impl.UserServiceImpl;
import com.john.util.CodeRandomUtil;
import com.john.util.HTMLText;
import com.john.util.SendMailUtil;
import com.john.util.WebUtil;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @author John
 * @create 2021-09-2610:01
 */
public class UserServlet extends BaseServlet {
    UserService userService = new UserServiceImpl();

    public UserServlet() {
    }

    /**
     * 注销功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1.销毁session
        req.getSession().invalidate();
//         2.重定向回首页
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 登录功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUSer = userService.login(new User(null, username, password, null));
        if (this.userService.login(new User((Integer)null, username, password, (String)null)) == null) {
            req.setAttribute("msg", "账号或密码错误!");
            System.out.println(req.getAttribute("msg"));
            req.setAttribute("username", username);
            req.getRequestDispatcher("pages/user/login.jsp").forward(req, resp);
        } else {
            req.getSession().setAttribute("user", loginUSer);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }

    }


    protected void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        System.out.println(email);
        String newPassword = req.getParameter("newPassword");
        String emailCode = req.getParameter("emailCode");
        String tokenEmail = (String) req.getSession().getAttribute("emailCode");
        req.getSession().removeAttribute("emailCode");
        if (tokenEmail != null && tokenEmail.equalsIgnoreCase(emailCode)) {
            if (this.userService.existsUsername(username)) {
                if (this.userService.isUsernameEqualEmail(username, email)){
                    this.userService.updatePassword(username, newPassword);
                } else {
                    req.setAttribute("msg", "账号与邮箱不匹配");
                    req.getRequestDispatcher("/pages/user/forget_password.jsp").forward(req, resp);
                    System.out.println("账号与邮箱不匹配");
                }
            }else {
                req.setAttribute("msg", "账号不存在");
                req.getRequestDispatcher("/pages/user/forget_password.jsp").forward(req, resp);
            }
        }else {
            req.setAttribute("msg", "邮箱验证码错误");
            req.getRequestDispatcher("/pages/user/forget_password.jsp").forward(req, resp);
        }
    }
    /**
     * 注册功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        String emailCode = req.getParameter("emailCode");
        User user = (User) WebUtil.copyParamToBean(req.getParameterMap(), new User());
        System.out.println(user);
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        String tokenEmail = (String) req.getSession().getAttribute("emailCode");
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute("emailCode");
        if (token != null && token.equalsIgnoreCase(code)) {
            if (tokenEmail != null && tokenEmail.equalsIgnoreCase(emailCode)) {
                if (this.userService.existsUsername(username)) {
                    req.setAttribute("msg", "用户名【 " + username + " 】已存在");
                    req.setAttribute("username", username);
                    req.setAttribute("email", email);
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

                } else {

                    this.userService.registerUser(new User((Integer) null, username, password, email));
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
                }
            }
            else {
                req.setAttribute("msg", "邮箱验证码错误");
                req.setAttribute("username", username);
                req.setAttribute("email", email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", username);
            req.setAttribute("email", email);
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }

    /**
     * 注册时检测用户是否存在
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求参数uesrname
        String username = req.getParameter("username");
        // 调用service层判断是否存在username
        boolean existsUsername = userService.existsUsername(username);
        // 将结果放入map里
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);

        Gson gson = new Gson();
        String json = gson.toJson(resultMap);
        resp.getWriter().write(json);
    }

    /**
     * 发送邮箱验证码
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws MessagingException
     */
    protected void ajaxSendEmailCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, MessagingException {
        try {
            String email = req.getParameter("email");
            SendMailUtil.receiveMailAccount = email;
            // 1.创建参数配置，用于连接邮箱服务器的参数配置
            Properties props = new Properties();
            props.setProperty("mail.debug","true");
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.host", "smtp.qq.com");
            props.setProperty("mail.transport.protocol", "smtp");

//        2.根据配置创造会话对象，用于和邮件服务器交互
            Session session = Session.getInstance(props);

            session.setDebug(true);

            //3.创建一封邮件 导入activation后解决报错
            String code = CodeRandomUtil.getRandom(6);
            String html = HTMLText.html(code);
            MimeMessage message = SendMailUtil.createMimeMessage(session, SendMailUtil.emailAccount,
                    SendMailUtil.receiveMailAccount, html);

//        4.根据session获取邮件传输对象
            Transport transport = session.getTransport();
//        5.使用邮箱账号和密码连接邮件服务器
            transport.connect(SendMailUtil.emailAccount,SendMailUtil.emailPassword);
//        6.发送邮件，发送所有收件人地址
            transport.sendMessage(message, message.getAllRecipients());
            //7.关闭连接
            transport.close();
            req.getSession().setAttribute("emailCode", code);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败"+e);
        }
    }
}

