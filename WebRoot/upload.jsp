<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>文件上传</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<script src="js/jquery-1.8.3.js"></script>
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
  <form id="uploadfrm" method="post" action="javascript:void(0)" enctype="multipart/form-data" >
						<input class="import-file" name="file" type="file" id="importExcel">
    					<input id="importBtn" type="submit" value = "上传"></input>
					</form>
  </body>
  
  <script>
  $("#importBtn").click(function(){
	  alert(1);
  })
	  /*上传功能--BEGIN*/
		 /* var importurl="http://localhost:8080/hjb-test02/upload/test";
		$("#importExcel").fileupload({
			autoUpload: false,//是否自动上传  
            dataType: 'json', 
            maxNumberOfFiles:1,
		    url:importurl,//文件上传地址，当然也可以直接写在input的data-url属性内
		    done:function(e,result){
		    	alert("导入成功"); */
		    	/* if(result.result.remark=="成功"){
		    		alert("导入成功");
		    		loadPage(adEffectData);
		    	}else{
		    		alert(result.result.remark);
		    	} */
		  /*  },
  		add: function (e, data) { 
              $("#importBtn").unbind('click').click(function (){
                  data.submit();
              });
          }
		})
		
		$('#importForm').attr('action', importurl); */
		/*上传功能--END*/
  </script>
</html>
