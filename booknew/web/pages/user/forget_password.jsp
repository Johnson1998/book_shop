<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>智慧书城会员登录页面</title>
    <%--	静态包含base标签、css样式 jQuer文件--%>
    <%@include file="/pages/common/head.jsp"%>
    <style type="text/css">
        .login_form{
            height:380px;
            margin-top: 25px;
        }
        #sendCode{
            font-size: 15px;
        }
    </style>
    <script>
        var InterValObj; //timer变量做时间控制
        var count = 60; // 间隔函数
        var curCount; //当前剩余时间
        $(function () {
            $("#sendCode").click(function () {
                if ($("#email").val()){
                    curCount = count;
                    $("#sendCode").attr("disabled", "true");
                    $("#sendCode").val(curCount+"秒重新发送");
                    InterValObj = window.setInterval(setRemainTime, 1000);
                    $.post("${basePath}userServlet", "action=ajaxSendEmailCode&email="+$("#email").val(),
                        function (data) {
                            alert("发送成功");
                        } )
                }else {
                    alert("邮箱号不可为空");
                }
            });
            function setRemainTime(){
                if (curCount == 0){
                    window.clearInterval(InterValObj);
                    $("#sendCode").removeAttr("disabled")
                    $("#sendCode").val("重新发送验证码");
                }else{
                    curCount--;
                    $("#sendCode").val(curCount+"秒重新发送");
                }
            }
        });
        $(function () {
            $("#sub_btn").click(function () {
                var usernameText = $("#username").val();

                var usernamePatt = /^\w{5,12}$/;
                if (!usernamePatt.test(usernameText)){
                    $("span.errorText").text("用户名不合法！")
                    return false;
                }
                var passwordText =$("#password").val();
                var passwordPatt = /^\w{5,12}$/;

                if (!passwordPatt.test(passwordText)){
                    $("span.errorText").text("密码不合法");
                    return false;
                }

                var repwdText =$("#repwd").val();

                if (repwdText != passwordText){
                    $("span.errorText").text("确认密码与密码不一致");
                    return false;
                }

                var emailText = $("#email").val();
                var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
                if (!emailPatt.test(emailText)){
                    $("span.errorText").text("邮箱不合法");
                    return false;
                }
                $("span.errorText").text("");

            });
        });
    </script>
</head>
<body>
<div id="login_header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="forget_box">
                <div class="tit">
                    <h1>密码修改</h1>
                    <span class="errorText">
                        ${ requestScope.msg }
                    </span>
                    <a href="pages/user/login.jsp">返回登录</a>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="updatePassword">
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1"
                               name="username" value="${requestScope.username}"/>
                        <div>
                            <label>电子邮件：</label>
                            <input class="itxt" type="text" placeholder="请输入邮箱地址"
                                   autocomplete="off" tabindex="1" name="email" id="email"
                                   value="${ requestScope.email }"
                            />

                        </div>
                        <div>
                            <label>用户密码：</label>
                            <input class="itxt" type="password" placeholder="请输入密码"
                                   autocomplete="off" tabindex="1" name="newPassword" id="password" />
                        </div>

                        <div>
                            <label>确认密码：</label>
                            <input class="itxt" type="password" placeholder="确认密码"
                                   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
                        </div>
                        <div>
                            <label>邮箱验证：</label>
                            <input class="itxt" type="text" style="width: 100px;" name="emailCode" id="emailCode"
                            />
                            <input type="button" id="sendCode" value="发送验证码" style="
										margin-left: 10px; margin-top: 10px">
                        </div>

                        <input type="submit" value="确认修改" id="sub_btn" />

                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="/pages/common/footer.jsp"%>
</body>
</html>