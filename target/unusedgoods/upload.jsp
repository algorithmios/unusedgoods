<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="user_update" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value = "1"/>    
        昵称：<input type="text" name="nickname" /><br/>
	QQ：<input type="text" name="qq"/><br/>
        微信：<input type="text" name="weixin"/><br/>
        电话：<input type="text" name="phone"/><br/>
        头像：<input type="file" name="headImg"/><br/>
		<input type="submit" value="提交" >
	</form>
   	
</body>
</html>