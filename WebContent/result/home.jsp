<%--
  Created by IntelliJ IDEA.
  User: echo
  Date: 16/12/29
  Time: 下午10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Welcome!</title>
    <script type="text/javascript">
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

                xmlhttp.open("GET",location.origin+"/onlineStockWeb03/Home?Exit=Exit",true);
                xmlhttp.send();
            };
        }
        window.onload = load;
        //]]>
    </script>
</head>
<body>
<table width="650" border="0" >
    <tr>
        <td width="650" height="80" background=<%= request.getContextPath() %>/image/top.jpg ></td>
    </tr>
</table>

<p>This is the Home Page for all visitors without auth</p>
<form action="/onlineStockWeb03/Home" method='GET'>
    <input type='submit' name='Login' value='Login'>
</form>

<p>Now the number of logged in is: <%= request.getAttribute("webCounter") %></p>
<p>Now the number of visitors is: <%= request.getAttribute("visitorCounter") %> </p>

</body>
</html>
