<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="js/jquery-3.3.1.min.js"></script>
</head>
<body style=" background-image: url('image/bigBackground1.jpg');background-size:100% 100%;">

	<%-- <jsp:forward page="pages/config.jsp"/> --%>
 	<a href="pages/config.jsp">奖项设置</a>
 	
 	<div style="width:94%; height:900px; margin:0 auto; background: RGBA(0,0,0,0.2)">
 		<div style="width:80%; float: left;">
 			<div id="headImg" style="width:200px; height:170px; border: solid 1px red; margin:0 auto; background-size:100% 100%;">
				<div id="uname"
					style="width: 100%; height: 30px; position: relative; bottom: -140px; background-color: gray; opacity: 0.7; color: white; font-size:20px; font-weight:20px; text-align: center; line-height: 30px;"></div>
				<div id="uid" style="display:none"></div>
 				<div id="dname" style="display:none"></div>
 			</div>
 			<div style="text-align: center;">
	 			现在抽取
	 			<select id="selectDraw" onchange="selLuck()">
	 				<option>请选择要抽取的奖项</option>
	 			</select><br>
	 			<span id="selSpan"></span>
	 			<br />
	 			<input type="button" id="start" value="立即抽取" disabled onclick="start()" />
	 			<input type="button" id="end" value="停止" disabled onclick="end()" /><br />
	 			<span id="span"></span>
 			</div>
 		</div>
		<div id="drawList" style="float:right; width:300px; height:100%; border: solid 1px black;">
			中奖名单
			<hr />
			<ol></ol>
			
		</div>
 	</div>
</body>
<script type="text/javascript">

	var myTime;//计时器
	var userList;
	var rules;//抽奖规则
	var maxCount;//记录当次抽奖

	window.load = a();
		
	function a(){
		//获取抽奖的用户
		$.post("userAction/selectAllUsers", null, function(data) {
			userList = data;
		}, "json");
		//获取奖项设置
		$.post("rulesAction/findRules", null, function(data) {
			rules = data;
			$.each(data[0].rule.split(","), function(i, v) {
				var val = v.split("-");
				$("#selectDraw").append("<option value='"+val[0]+",需要抽取"+val[1]+"人,剩余"+val[1]+"人"+"'>"+val[0]+"</option>");
			});
			$("#selectDraw option").eq(0).prop("disabled", true);
		}, "json");
		//记录当次抽奖
		$.post("situationAction/selectCount", null, function(data) {
			maxCount = parseInt(data) + 1;
		});
	}
	
	//随机一个人
	function randomUser() {
		var i = parseInt(userList.length*Math.random());
		$("#span").text(i);
		//设置头像
		$("#headImg").css("background-image","url("+userList[i].uimg+")");
		//名字
		$("#uname").text(userList[i].uname);
		//id
		$("#uid").text(userList[i].uid);
		//部门
		$("#dname").text(userList[i].dept.dname);
	}
	//开始随机
	function start() {
		myTime = setInterval(function(){randomUser()},100);
		$("#selectDraw").prop("disabled", true);
		$("#start").prop("disabled", true);
		$("#end").prop("disabled", false);
	}
	//结束随机
	function end() {
		
		//获取当前选择的option
		var $option = $("#selectDraw").children("option[value='"+$("#selectDraw").val()+"']");
		//获得该option的value
		var val = $option.val();
		//获得当前选择奖项的的剩余抽奖人数
		var remain = val.substring(val.indexOf("余")+1,val.length-1);
		//将当前奖项的剩余抽奖人数-1
		val = val.substring(0, val.indexOf("余")+1) + (remain<=1?0:remain-1) + val.substring(val.length-1);
		//将-1后的新字符串赋予给该option
		$option.val(val);
		//将-1后的信息显示
		$("#selSpan").text(val.substring(val.indexOf(",")+1));
		//禁用停止按钮
		$("#end").prop("disabled", true);
		//点击停止后延迟300毫秒操作
		setTimeout(function(){
			//停止随机的计时器
			clearInterval(myTime);
			//当该奖项的剩余抽奖人数为0时，使该option不能被选中
			if (remain <= 1)
				$option.prop("disabled", true);

			/**
			 * 需要获得的数据：
			 * maxCount--当次抽奖序号---scout
			 * 中奖人id---------------uid
			 * 中奖名称----------------stype
			 */
			//将该中奖人及其中奖信息保存到数据库中
			var stype = val.substring(0, val.indexOf(","));
			var uid = $("#uid").text();
			var param = "scount=" + maxCount + "&stype=" + stype + "&user.uid=" + uid;
			$.post("situationAction/addSituation", param, function(data) {
				var dname = $("#dname").text();
				var uname = $("#uname").text();
				alert("恭喜 "+ dname +" 的 "+ uname +" 获得 " + stype);
				var src = $("#headImg").css("background-image");
				var src = src.substring(src.indexOf("(")+1, src.indexOf(")"));
				$.each(userList, function(i, v) {
					if (v.uid == uid) {
						src = v.uimg;
					}
				});
				$("#drawList ol").append("<li><img width='30px' height='30px' src='"+src+"'/>  "+ stype +"  "+uname+"  "+dname+"</li>");			
			});
			
			//放开选择框
			$("#selectDraw").prop("disabled", false);
			//调用该方法，根据剩余人数决定开始按钮是否禁用
			selLuck();
		}, 300)
	}
	
	//选择了奖项,显示人数
	function selLuck() {
		var val = $("#selectDraw").val();
		var i = val.indexOf(",");
		val = val.substring(i+1);
		$("#selSpan").text(val);
		//该奖项剩余抽取人数
		var remain = val.substring(val.indexOf("余")+1,val.length-1);
		if (remain > 0) 
			$("#start").prop("disabled", false);	
		else
			$("#start").prop("disabled", true);
	}

</script>
</html>

