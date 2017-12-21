<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>My JSP 'index.jsp' starting page</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
		<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	</head>

	<body>
		<form action="" enctype="multipart/form-data">
			<h2>
				多文件上传
			</h2>
			<input type="file" id="file1" name="file" />
			</br>
			<input type="file" id="file2" name="file" />
			</br>
			<input type="file" id="file3" name="file" />
			</br>
			<span>
				<table id="down">
				</table>
			</span>
			</br>
			<input type="button" onclick="fileUpload();" value="上传">
		</form>
	</body>
	<script type="text/javascript">
		function fileUpload() {
			var files = [ 'file1', 'file2', 'file3' ];  //将上传三个文件 ID 分别为file2,file2,file3
			$.ajaxFileUpload( {
				url : 'fileUploadAction',     //用于文件上传的服务器端请求地址  
				secureuri : false,            //一般设置为false  
				fileElementId : files,        //文件上传空间的id属性  <input type="file" id="file" name="file" />  
				dataType : 'json',            //返回值类型 一般设置为json  
				success : function(data, status) {
					var fileNames = data.fileFileName; //返回的文件名 
					var filePaths = data.filePath;     //返回的文件地址 
					for(var i=0;i<data.fileFileName.length;i++){
						//将上传后的文件 添加到页面中 以进行下载
						$("#down").after("<tr><td height='25'>"+fileNames[i]+
								"</td><td><a href='downloadFile?downloadFilePath="+filePaths[i]+"'>下载</a></td></tr>")
					}
				}
			})
		}
	</script>
</html>
