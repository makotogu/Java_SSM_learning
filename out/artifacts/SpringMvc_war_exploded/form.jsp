<%--
  Created by IntelliJ IDEA.
  User: jianw
  Date: 2021/6/17
  Time: 11:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/quick14" method="post">
        <%--表明是第一个User对象的username--%>
        <input type="text" name="userList[0].name"><br/>
        <input type="text" name="userList[0].age"><br/>
        <input type="text" name="userList[1].name"><br/>
        <input type="text" name="userList[1].age"><br/>
        <input type="submit" value="submit">
    </form>

    <hr/>

    <form action="${pageContext.request.contextPath}/user/quick24" method="post">
        <%--表明是第一个User对象的username--%>
        <input type="text" name="username"><br/>
        <input type="submit" value="submit">
    </form>
</body>
</html>
