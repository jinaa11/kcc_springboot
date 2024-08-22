<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <h1>회원가입폼</h1>
    <hr>
    <form action="/join" method="post">
        <input type="text" name="username"> <br/>
        <input type="password" name="password"> <br/>
        <input type="text" name="email"> <br/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">회원가입</button>
    </form>
</body>
</html>