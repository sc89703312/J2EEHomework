<%--
  Created by IntelliJ IDEA.
  User: echo
  Date: 16/12/29
  Time: 上午10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.lang.*" %>
<%@ taglib prefix="session" uri="/WEB-INF/tlds/session.tld" %>
<%@ page session="true" %>
<html>
<head>
    <session:checkSession/>
    <title>ResultList</title>
    <script type="text/javascript">
        var stayonthis = true;
        var a;
        function load() {
            window.onunload = function(e) {
                var xmlhttp;
                if (window.XMLHttpRequest)
                {// code for IE7+, Firefox, Chrome, Opera, Safari
                    xmlhttp=new XMLHttpRequest();
                }
                else
                {// code for IE6, IE5
                    xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
                }

                xmlhttp.open("GET",location.origin+"/onlineStockWeb03/Login?Logout=Logout",true);
                xmlhttp.send();
            };
        }
        window.onload = load;
        //]]>
    </script>
</head>
<body>

<jsp:useBean id="studentInfo" class="main.java.edu.nju.onlinestock.model.Student"
             scope="session"/>
<jsp:useBean id="list"
             type="main.java.edu.nju.onlinestock.beans.ResultListBean"
             scope="session"/>
<jsp:useBean id="item" class="main.java.edu.nju.onlinestock.model.Result"
             scope="page"/>
<jsp:useBean id="examItem" class="main.java.edu.nju.onlinestock.model.Exam"
             scope="page"/>

<table width="650" border="0" >
    <tr>
        <td width="650" height="80" background=<%= request.getContextPath() %>/image/top.jpg>&nbsp;</td>
    </tr>
</table>

<p>Welcome!
    <jsp:getProperty name="studentInfo" property="grade" />
    <jsp:getProperty name="studentInfo" property="name" />
</p>

<p>
    成绩列表:
</p>

<TABLE width="40%" border="0" cellpadding="0" cellspacing="1">
    <TBODY>
    <TR>
        <TH width="20%">Exam_id</TH>
        <TH width="20%">Exam_Name</TH>
        <TH width="20%">Result</TH>
    </TR>

    <%
        for (int i = 0; i < list.getStudentResultList().size(); i++) {
            pageContext.setAttribute("item", list.getStudentResult(i));
            pageContext.setAttribute("examItem", list.getStudentResult(i).getExam());
            pageContext.setAttribute("scoreResult", list.getStudentResult(i).getResult());
    %>
        <TR>
            <TD align="center"><jsp:getProperty name="item" property="exam_id"/></TD>
            <TD align="center"><jsp:getProperty name="examItem" property="name"/></TD>

            <% if((int) pageContext.getAttribute("scoreResult") != -1){ %>
            <TD align="center"><jsp:getProperty name="item"
                                                property="result" /></TD>
            <% }else{%>
            <TD align="center" style="color:red;">未参加!</TD>
            <% } %>

        </TR>
    <%
        }
    %>
    </TBODY>
</TABLE>

<form method="GET" action="<%= request.getContextPath() %>/Login">
    <input type="submit" name="Logout" value="Logout">
</form>
<p>Now the number of logged in is: <%= session.getAttribute("webCounter") %></p>
<p>Now the number of visitors is: <%= session.getAttribute("visitorCounter") %> </p>

</body>
</html>
