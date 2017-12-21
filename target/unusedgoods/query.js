$(function(){
	all = {};
	all.id = 3;
	all.content = "书籍 2";
	$('button').click(putdata);
	console.log(JSON.stringify(all));
	function putdata(){
		alert("请求已发送");
		$.ajax({
			url: "http://localhost:8080/unusedgoods/user_listDialog?start=1",
//			url: "http://localhost:8080/unusedgoods/admin_noPass",
//			url: "http://localhost:8080/unusedgoods/goods_ownCollect?start=1",
//			url: "http://localhost:8080/unusedgoods/home_recommend",?start=1
//			url: "http://localhost:8080/unusedgoods/goods_search",
//			url: "http://localhost:8080/unusedgoods/goods_detail?id=2",
//			url: "http://localhost:8080/unusedgoods/user_retrievePassword",
//			url: "http://localhost:8080/unusedgoods/user_add",
//			url: "http://localhost:8080/unusedgoods/user_registered",
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