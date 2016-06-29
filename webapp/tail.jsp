<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@page import="java.net.InetAddress,com.baidu.hina.web.tail.util.PropertyConfigUtil"%>
<%
    String localIp = InetAddress.getLocalHost().getHostAddress();
    int port = PropertyConfigUtil.getPort();
    String hostPort = localIp+":"+port;
    System.out.println("index.jsp --- hostPort="+hostPort);
%>

<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Viewer</title>
<script type="text/javascript" charset="utf-8" src="js/jquery-1.3.2.js"></script>
<link type="text/css" rel="stylesheet" href="css/style.css" />
<script type="text/javascript">
  
function getUrlParameter(name) {
	return decodeURIComponent((RegExp(name + '=' + '([^&]*)(&|$)').exec(
			location.search) || [ , null ])[1]);
}

function escapeHtml(unsafe) {
    return unsafe
         .replace(/&/g, "&amp;")
         .replace(/</g, "&lt;")
         .replace(/>/g, "&gt;")
         .replace(/"/g, "&quot;")
         .replace(/'/g, "&#039;");
 }

$(function() {
  var text = $('#messages');
  var keepScrolling = true;
  var logFile = getUrlParameter("logFile");
  var ws = new WebSocket("ws://" + '<%=hostPort%>' + "/tail?logFile=" + logFile);
  ws.onopen = function(event) {
  };
  ws.onmessage = function(event) {
      // 接收服务端的实时日志并添加到HTML页面中
      $("#log-container div").append(escapeHtml(event.data)).append("<br>");
      // 滚动条滚动到最低部
      $("#log-container").scrollTop($("#log-container div").height() - $("#log-container").height());
  }
  ws.onclose = function(event) {
    
  }
  $(document).bind("keydown", function(event){
    if (event.keyCode == 32)
      setTimeout(function() {
          keepScrolling = !keepScrolling;
        },0);
  });
});
</script>
</head>
<body>
    <div id="log-container" style="height: 450px; overflow-y: scroll; background: #333; color: #aaa; padding: 10px;">
        <div>
        </div>
    </div>
</body>
</html>