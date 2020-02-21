<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 2020/2/21
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SSM 框架整合</title>
</head>
<body>
    <div>
        <table width="200px" cellpadding="5px" border="1px" >
            <tr>
                <td>编号</td>
                <td>姓名</td>
                <td>密码</td>
            </tr>
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
            </tr>
        </table>
    </div>
</body>
</html>
