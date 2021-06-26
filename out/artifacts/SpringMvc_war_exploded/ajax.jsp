<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="${pageContext.request.contextPath}/js/jquery-3.6.0.js"></script>
<script>
    var userList = new Array();
    userList.push({name:"张三",age:18});
    userList.push({name:"李四",age:19});

    $.ajax({
        type:"POST",
        url:"${pageContext.request.contextPath}/user/quick15",
        data:JSON.stringify(userList),
        contentType:"application/json;charset=utf-8",
    })
</script>
<body>

</body>
</html>
