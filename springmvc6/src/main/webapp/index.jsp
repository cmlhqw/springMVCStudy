<%--
  Created by IntelliJ IDEA.
  User: 18192
  Date: 2018-09-13
  Time: 9:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1 style="color: #ff5329">springmvc解析json数据 ajax传递json数据</h1>

    用户名：<input type="text" name="username" id="username" ><br>
    密码：<input type="password" name="password" id="password" ><br>

    <button onclick="login()">登录</button>


    <h1>获取admin分页数据</h1>
    <h2><a href="list">点击获取</a></h2>

<script type="text/javascript" src="/js/jquery-2.1.0.js"></script>
<script>
    function login() {
        var username = $("#username").val();
        var password = $("#password").val();
        var admin = {"username": username, "password": password};
        $.ajax({
            type: "post",
            url: "login",
            data: JSON.stringify(admin),
            contentType: "application/json",
            dataType: "json",
            success: function (msg) {
                alert("登陆的用户信息：" + msg.id + ":" + msg.username + ":" + msg.password);
            },
            error: function (msg) {
                alert("你的账户密码不对呀");
            }
        });
    }
</script>
</body>
</html>
