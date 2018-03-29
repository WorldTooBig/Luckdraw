<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
    pageEncoding="UTF-8"%>
<%@ page%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.3.1.min.js"></script>
</head>
<body>

	<h2>设置奖项</h2>
	<ul id="draw">
		<li>一等奖<input name="drawCount" oninput="changeText()"/>人</li>
		<li>二等奖<input name="drawCount" oninput="changeText()"/>人</li>
		<li>三等奖<input name="drawCount" oninput="changeText()"/>人</li>
		<li>
			<input type="button" value="+" onclick="increase()" />
			<input type="button" value="-" onclick="reduce()" />
		</li>
	</ul>
	
	<input id="rdone" type="checkbox" />同一部门是否只能有一个人中奖<br />
	<input id="rfor" type="checkbox" />是否可以重复中奖<br />
	共<span id="span_sum">0</span>个奖项
	<br />
	<input type="button" value="选择该方案" onclick="yesDraw()" />
	<p><br></p>
	往期抽奖方案：
	<select id="select">
		<option>· · · · · ·</option>
	</select>
	<br />
	<input type="button" value="使用" onclick="application()" />
	<br />
	<br /><br /><br />
	<a href="${pageContext.request.contextPath}/index.jsp">抽奖页面</a><br /><br />
	<a href="${pageContext.request.contextPath}/pages/userList.jsp">用户设置</a>
</body>
<script type="text/javascript">

	var rulesList;//保存往期抽奖方案

	window.load = oldPlan();
	
	//往期抽奖方案
	function oldPlan(){
		$.post("../rulesAction/findRulesList", null, function(data){
			rulesList = data;
			var strs = "";
			$("#select option:not(:first)").remove();
			$.each(data, function(i, v) {
				strs += "<option value="+v.rid+">"+v.rule+"==="+v.rdone+","+v.rfor+"</option>";
			});
			$("#select").append(strs);
			$("#select option").eq(0).prop("disabled", true);
		}, "json");
	}
	
	//添加奖项
	function increase() {
		var $li = $("#draw li");
		var defaultValue;//弹出对话框的默认值，即设置奖项的默认名字
		var name;//奖项的名字
		switch ($li.length) {
		case 1:
			defaultValue = "一等奖";
			break;
		case 2:
			defaultValue = "二等奖";
			break;
		case 3:
			defaultValue = "三等奖";
			break;
		case 4:
			defaultValue = "幸运奖";
			break;

		default:
			defaultValue = "安慰奖";
			break;
		}
		name = prompt("请输入奖项名字：", defaultValue);
		if($li.length > 1)
			$li.eq($li.length-2).after("<li>"+name+"<input value='1' name='drawCount' oninput='changeText()' />人</li>");
		else
			$("#draw").prepend("<li>"+name+"<input value='1' name='drawCount' oninput='changeText()' />人</li>");
		changeText();
	}
	
	//减少奖项
	function reduce() {
		var $li = $("#draw li");
		if($li.length > 1)
			$li.eq($li.length-2).remove();
		changeText();
	}
	
	//计算奖项总数
	function changeText() {
		var $inputs = $("input[name=drawCount]");
		var sum = 0;
		for (var i = 0; i < $inputs.length; i++) {
			sum += Number($inputs.eq(i).val()); 
		$("#span_sum").text(sum);
		}
	}
	
	//保存抽奖方案
	function yesDraw() {
		var $li = $("#draw li:not(:last)");
		var rule = "";
		for (var i = 0 ; i < $li.length; i++) {
			var text = $li.eq(i).text();
			var val = $li.children("input").eq(i).val();
			rule += text.substring(0,text.length-1) + "-" + (val==""?0:val) + ",";
		}
		rule = rule.substring(0, rule.length-1);
		var rdone;
		var rfor;
		$("#rdone").is(":checked")?rdone=1:rdone=0;
		$("#rfor").is(":checked")?rfor=1:rfor=0;
		var param = "rule=" + rule + "&rdone=" + rdone + "&rfor=" + rfor;
		$.post("../rulesAction/addRules", param, function(data) {
			if (data == 1) {
				$("#select option").eq(0).prop("disabled", false);
				oldPlan();
			}
		});
	}
	
	//应用选择的抽奖方案
	function application(){
		var rule;
		$.each(rulesList, function(i, v){
			if (v.rid == $("#select").val()) {
				rule = v;
			}
		});
		//奖励名称和人数
		var rules = rule.rule.split(",");
		$("#draw li:not(:last)").remove();
		$.each(rules, function(i, v) {
			var val = v.split("-");
			$("#draw li:last").before("<li>"+val[0]+"<input value='"+val[1]+"' name='drawCount' oninput='changeText()' />人</li>");
		});
		//是否同一部门只能中奖一次
		rule.rdone==1?$("#rdone").prop("checked",true):$("#rdone").prop("checked", false);
		//是否可以重复中奖
		rule.rfor==1?$("#rfor").prop("checked",true):$("#rfor").prop("checked", false);

	}
	
</script>
</html>


<!-- 
 	

 -->







