$(function(){
	all = {};
	all.account = "123";
	all.password = "123";
    all.reqcode = "4c7ac";
	$('button').click(putdata);
	console.log(JSON.stringify(all));
	function putdata(){
		alert("请求已发送");
		$.ajax({
		//	url: "http://localhost:8080/unusedgoods/goods_detail?id=19",
		//	url: "http://localhost:8080/unusedgoods/goods_ownUnderShelfList",
		//	url: "http://localhost:8080/unusedgoods/user_collection?id=1",
		//	url: "http://localhost:8080/unusedgoods/user_collection",
			url: "http://localhost:8080/unusedgoods/login_userIn",
		//	url: "http://localhost:8080/unusedgoods/user_currentUserContact",
		//	url: "http://localhost:8080/unusedgoods/admin_getAllAdmin",
		//	url: "http://localhost:8080/unusedgoods/admin_getAllVerifyLog?start=3",
		//	url: "http://localhost:8080/unusedgoods/login_adminIn",
			type: "POST",
			data: JSON.stringify(all),
			contentType: "application/json",
			success:function(data){
//				deal(JSON.parse(data));
             	  console.log(data);
            },
             error: function(XMLHttpRequest, textStatus, errorThrown) {
			//  alert(XMLHttpRequest.status + ' ' + XMLHttpRequest.readyState + ' ' + textStatus);
				alert('获取数据失败！');
			}
		});
	};
})