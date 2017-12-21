$(function(){
	var file1, file2, file3, btn, data ={};
	var ulr = '';
	var form;
	var formData;
	function init(){
		file1 = $("input[name='file1']");
		file2 = $("input[name='file2']");
		file3 = $("input[name='file3']");
		form = $('form');
		btn = $('button');
		btn.click(putdata);
		console.log("1");
	};
	function putdata(){
		
//		data.file1 = file1.val();
//		data.file2 = file2.val();
//		data.file3 = file3.val();
//		formData.append("file",file1);
//		formData.append("name",data.fiel1);
//		formData.append("file",file2);
//		formData.append("name",data.fiel2);
//		formData.append("file",file2);
//		formData.append("name",data.fiel2);
		formData = new FormData(form);

		console.log(formData);
		
		$.ajax({ 
			url: 'http://localhost:8080/unusedgoods/file_fileUpload',
            type: 'POST',
            data: formData,
            async: true,
            cache: false,
            contentType: false,
            processData: false,
			beforeSend:function(){
			console.log("正在进行，请稍候");
			},
			success : function(responseStr) { 
			if(responseStr.status===0){
			console.log("成功"+responseStr);
			}else{
			console.log("失败" +responseStr);
			}
			}, 
			error : function(responseStr) { 
			console.log("error");
			} 
		});
		console.log(data);
	};
	init();
})