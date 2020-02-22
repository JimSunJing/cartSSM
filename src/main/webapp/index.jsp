<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>HOME</title>
    <script>
        function changeCheckCode(img) {
            img.src="${pageContext.request.contextPath}/checkCode/getCode?time="+new Date().getTime();
        }
    </script>
</head>
<body>


    <div align="center">
    <h2>用户登陆</h2>
    <form action="user/login"  method="post">
        用户名: <input type="text" name="name"><br/>
        密码: <input type="password" name="password"><br/>
        验证码：<input type="text" name="checkCode"><br/>
        <img onclick="changeCheckCode(this)" src="${pageContext.request.contextPath}/checkCode/getCode"><br/>
        <input type="submit" value="登陆"><br/>
    </form>
    </div>
</body>
</html>
