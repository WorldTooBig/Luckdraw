<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-form.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/zhangCong_CSS.css"></link>
<style>
	#form tr td:nth-child(5), #form tr th:nth-child(4){
		display:none;
	}
</style>
</head>
<body>
	
	<div id="form" style="width: 650px; margin:100px auto;">
		<center>
		<table id="tab" border=1 style="text-align: center;">
			<tr>
				<th><input type="checkbox" id="bigBox" onclick="checkBigBox()" />全选</th><th>姓名</th><th>部门</th><th>中奖次数</th><th>操作</th>
			</tr>
		</table>
		<input type="button" value="确认" class="btn" style="width:150px;" onclick="yesId()" />
		<input type="button" value="刷新" class="btn" style="width:150px;" onclick="$('#tab tr:not(:first)').remove();a()" />
		<input type="button" value="添加" class="btn" style="width:150px;" onclick="toAddUser()" />
		</center>
		
		<div id="uploadImgDiv" style="display:none; position: fixed; top:0; left:0; width:100%; height: 100%; background: white;">
			<center>
			<div style="margin-top: 160px;">
				<form id="imgForm" enctype="multipart/form-data">
				
					<input id="fileBtn" type="button" value="选择文件" class="btn" onclick="file()" /><br />
					<span id="fileText"></span>
					
					<input type="file" name="ufile" style="margin-top: 30px; display: none;" onchange="selectImage()" />
					
					<div id="image" style="width:200px; height:200px; margin:40px auto; border:solid 1px red; background-size: 100% 100%;"></div>
					
					<input type="button" value="上传" class="btn" style="margin: 0px; width: 200px; height: 40px; line-hight: 40px;" onclick="uploadImage()" />
				</form>
			</div>
			</center>
		</div>
	</div>
	
	
	<center style="margin-top: -50px;">
		<a href="${pageContext.request.contextPath}/index.jsp" class="btn_a" style="margin-right: 100px; width: 200px; height: 50px; line-height: 50px;">抽奖页面</a>
		<a href="${pageContext.request.contextPath}/pages/config.jsp" class="btn_a" style="width: 200px; height: 50px; line-height: 50px;">奖项设置</a>
	</center>
	
</body>
<script type="text/javascript">
	
	window.load = a();
	
	function a() {
		$.post("../userAction/selectAllUsers", null, function(data){
			$.each(data, function(i, v){
				var strs = "<tr>";
				strs += "<td style='display:none'>"+v.uid+"</td>";
				strs += "<td><input type='checkbox' name='selectBox' value='"+v.uid+"' />"+(i+1)+"</td>";
				strs += "<td>"+v.uname+"</td>";
				strs += "<td>"+v.dept.dname+"</td>";
				strs += "<td>"+i+"</td>";
				strs += "<td><input type='button' value='删除' onclick='deleteUser(event)' /></td>";
				strs += "</tr>";
				$("#tab").append(strs);
			});
		}, "json");
	}
	
	//全选反选
	function checkBigBox() {
		var $selectBox = $(":checkbox[name=selectBox]");
		if($("#bigBox").is(':checked')) {
			for(var i = 0 ; i < $selectBox.length; i++ ) {
				$selectBox[i].checked = true;
			}
		}else{
			for(var i = 0 ; i < $selectBox.length; i++ ) {
				$selectBox[i].checked = false;
			}
		}
	}
	
	//传入ID给后台
	function yesId(){
		var param = $("#form input[name=selectBox]:checked").serialize();
		alert(param);
		$.post("", param, function(data){
			
		}, "json");
	}
	
	function toAddUser(){
		$.post("../deptAction/selectAllDept", null, function(data){
			var strs = "<tr>";
			strs += "<td><input type='checkbox' name='selectBox' value='0' /></td>";
			strs += "<td><input name='uname' /></td>";
			strs += "<td><select name='dept.did'>";
			$.each(data, function(i, v){
				strs += "<option value='"+v.did+"'>"+v.dname+"</option>";
			});
			strs += "</select></td>";
			strs += "<td colspan=2><input type='button' value='上传头像' onclick='openUpoadDiv()' />";
			strs += "<input type='button' value='取消' onclick='addUserLose(event)' /><input type='button' value='确认' onclick='doAddUser()' /></td>";
			strs += "</tr>";
			$("#tab").append(strs);
		}, "json");
	}
	
	function doAddUser() {
		var param = $("input[name=uname], select[name='dept.did']").serialize();
		var $val = $("input[name=ufile]").val();
		var i = $val.lastIndexOf("\\");
		$val = $val.substring(i+1);
		if ($val == "") {
			alert("请选择头像后再进行操作");
		} else {
			param += "&uimg=image/" + $val;
			$.post("../userAction/addUsers", param, function(data){
				if(data > 0) {
					$("#tab tr:not(:first)").remove();
					a();
					alert("添加成功");
				} else {
					alert("添加失败");
				}
			});
		}
	}
	
	function addUserLose(e) {
		$(e.target).parents("tr").remove();
	}
	
	function deleteUser(e) {
		var uid = $(e.target).parents("tr").find("td").eq(0).text();
		$.post("../userAction/deleteUsers/"+uid, null, function(data){
			if(data == true) {
				$(e.target).parents("tr").remove();
			} else {
				alert("删除失败");
			}
		});
	}
	
	function openUpoadDiv() {
		$("#uploadImgDiv").css("display", "block");
	}
	
	//预览图片
	function selectImage() {
	    var file = $("input[type=file]")[0].files[0];  
	    var reader = new FileReader();  
	    //创建文件读取相关的变量  
	    var imgFile;  
	    //为文件读取成功设置事件  
	    reader.onload=function(e) {  
	        $("#image").css("background-image", "url("+reader.result+")");  
	        $("#fileText").text($("input[name=ufile]").val());
	    };  
	    //读取文件  
	    reader.readAsDataURL(file); 
	}	
	
	//上传图片
	function uploadImage() {
		$("#imgForm").ajaxSubmit({
			url: "../userAction/uploadFile",
			type: "post",
			success: function(){
				$("#uploadImgDiv").css("display", "none");
			},
			error: function(){
				alert("头像上传失败");
			}
		});
	}
	
	function file() {
		$("input[name=ufile]").click();
	}
	
</script>
</html>