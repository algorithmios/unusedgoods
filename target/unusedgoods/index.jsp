<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<form action="user_update" method="post" enctype="multipart/form-data">
	昵称:<input type="text" name="nickname" /> <br />
	QQ:<input type="number" name="qq" /> <br />
	微信:<input type="text" name="weixin" /> <br />
	电话:<input type="text" name="phone" /> <br />
	头像:<input type="file" name="headImg" /> <br />
<!-- 	private String nickname;
	private String qq;
	private String weixin;
	private String phone;
	private File headImg; -->
	<input type="submit" value="提交">
</form>

</body>
</html>
