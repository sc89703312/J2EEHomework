<%--
  Created by IntelliJ IDEA.
  User: echo
  Date: 16/12/29
  Time: 下午11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<%@ taglib prefix="counter" uri="/WEB-INF/tlds/counter.tld" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table width="650" border="0" >
    <tr>
        <td width="650" height="80" background="/onlineStockWeb03/image/top.jpg">&nbsp;</td>
    </tr>
</table>

<br>
<a href="/onlineStockWeb03/Home">主页</a>
<br>

<p>
<table width=650>
    <tr>
        <td>
            <form name="loginForm" id="loginForm" action="/onlineStockWeb03/Login" method="post">
                <table align="center" border="0">
                    <tr>
                        <td>StudentId</td>
                        <td><input type="text" name="studentId" size=25></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><input type="password" name="password" size=25></td>
                    </tr>
                    <tr>
                        <td colspan="2" align="center">
                            <input type="submit" value="Submit">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        </td>
                    </tr>
                </table>
            </form>
        </td>
    </tr>
</table>
</p>

<counter:webCounter/>

</body>
</html>
