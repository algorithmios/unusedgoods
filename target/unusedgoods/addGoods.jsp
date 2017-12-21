<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
    <form action="goods_addGoods" method="post" enctype="multipart/form-data">  
        名字：<input type="text" name="goods.name" /><br/>
        现价：<input type="number" name="goods.price"/><br/>
        原价：<input type="number" name="goods.originalPrice"/><br/>
        介绍：<input type="text" name="goods.introduction"/><br/>
        类型：<input type="text" name="goods.classification"/><br/>
  交易地址：<input type="text" name="goods.address"/><br/>
        图片：<input type="file" name="images"/><br/>
        图片：<input type="file" name="images"/><br/>
        图片：<input type="file" name="images"/><br/>
        图片：<input type="file" name="images"/><br/>
		<input type="submit" value="提交" >
	</form>

</body>
</html>