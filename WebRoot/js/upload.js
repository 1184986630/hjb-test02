$(function() {
	alert(1);
	$("#uploadbtn").click(function() {
		console.log(1);
		var ajax_option = {
			url : "projectimgupload.shtml",
			type : "post",
			dataType : "json",
			success : function(data) {
				alert("�ϴ��ɹ�!");
				location.reload();
				// console.log(data);
			}
		};
		$("#uploadfrm").ajaxSubmit(ajax_option);
	});
});
