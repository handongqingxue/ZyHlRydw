<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.cen_div{
	margin-top:65px;
	margin-left: 220px;
	background-color: #EFF3F6;
	padding: 1px;
}
.cen_div .toolbar{
	height:40px;
	margin-top:10px;
	margin-left: 10px;
	margin-bottom:10px;
	background-color: #fff;
}
.cen_div .toolbar .row_div{
	height:40px;
}
.cen_div .toolbar .row_div .firstDept_span,
.cen_div .toolbar .row_div .secondDept_span,
.cen_div .toolbar .row_div .date_span,
.cen_div .toolbar .row_div .patrolTeam_span,
.cen_div .toolbar .row_div .patrolStaff_span{
	margin-top:10px;
	margin-left: 13px;
	position: absolute;
}
.cen_div .toolbar .row_div .to_span{
	margin-top:10px;
	margin-left: 5px;
	position: absolute;
}
.cen_div .toolbar .row_div .search_but{
	margin-top:8px;
	margin-left: 13px;
}
.data_div{
	height: 500px;
	margin-top: 20px;
	margin-left: 10px;
	background-color: #fff;
	padding: 1px;
}
.reachPercent_div{
	width: 800px;
	height: 80px;
	margin-top: 5px;
	margin-left: 5px;
}
.reachPercent_div .item_div{
	height: 80px;
}
.reachPercent_div .rdc_item_div{
	background-color: #62BF82;
}
.reachPercent_div .plrp_item_div{
	margin-top: -80px;
	background-color: #6F78B9;
}
.reachPercent_div .parp_item_div{
	margin-top: -80px;
	background-color: #5E87C6;
}
.reachPercent_div .item_div .icon_img{
	width: 45px;
	height:45px;
	margin-top: 10px;
	margin-left: 10px;
}
.reachPercent_div .item_div .rdc_span,
.reachPercent_div .item_div .plrp_span,
.reachPercent_div .item_div .parp_span{
	margin-top: 8px;
	margin-left: 10px;
	color: #fff;
	font-size: 25px;
	position: absolute;
}
.reachPercent_div .item_div .fgx_span{
	margin-top: 15px;
	margin-left: 30px;
	color: #fff;
	font-size: 15px;
	position: absolute;
}
.reachPercent_div .item_div .sdc_span{
	margin-top: 16px;
	margin-left: 40px;
	color: #fff;
	font-size: 15px;
	position: absolute;
}
.reachPercent_div .item_div .text_span{
	margin-top: 40px;
	margin-left: 10px;
	color: #fff;
	font-size: 13px;
	position: absolute;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var mainPath=path+'main/';
var patrolMgmtPath=path+'patrolMgmt/';
var nav='${param.nav}';
$(function(){
	initFirstDeptCBB();
	initSecondDeptCBB();
	initPatrolTeamCBB();
	initPatrolStaffCBB();
	initStartDateDB();
	initEndDateDB();
	initSearchLB();
	setTimeout(function(){
		var comboLength=$(".cen_div .toolbar .combo").length;
		for(var i=0;i<comboLength;i++){
			setComboLocation(i);
		}
	},"1000");
	resizeDiv();
	getCenAnaData();
});

function resizeDiv(){
	var cenDiv=$("#cen_div");
	var leftNavDiv=$("#left_nav_div");
	var bodyWidth=$("body").width();
	var leftNavWidth=leftNavDiv.width();
	cenDiv.css("width",bodyWidth-leftNavWidth-40+"px");

	var cenDivWidth=cenDiv.width();
	var toolbarDiv=$("#cen_div #toolbar");
	toolbarDiv.css("width",cenDivWidth-20+"px");
	
	var toolbarDivWidth=toolbarDiv.width();
	var dataDiv=$("#data_div");
	dataDiv.css("width",toolbarDivWidth+"px");
	
	var dataDivWidth=dataDiv.width();
	var reachPercentDiv=$("#reachPercent_div");
	reachPercentDiv.css("width",dataDivWidth-10+"px");
	
	var reachPercentDivWidth=reachPercentDiv.width();
	var itemDiv=reachPercentDiv.find(".item_div");
	itemDiv.css("width",(reachPercentDivWidth-40)/3+"px");
	
	var itemDivWidth=itemDiv.width();
	$(".plrp_item_div").css("margin-left",itemDivWidth+20+"px");
	$(".parp_item_div").css("margin-left",(itemDivWidth+20)*2+"px");
}

function initFirstDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择一级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:0},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			firstDeptCBB=$("#cen_div #firstDept_cbb").combobox({
				valueField:"value",
				textField:"text",
				data:data,
				onSelect:function(){
					loadSecondDeptCBBData();
				}
			});
		}
	,"json");
}

function initSecondDeptCBB(){
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	secondDeptCBB=$("#cen_div #secondDept_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadPatrolTeamCBBData();
		}
	});
}

function loadSecondDeptCBBData(){
	var deptId=firstDeptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择二级部门"});
	$.post(mainPath+"queryDeptCBBList",
		{parentId:deptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].deptId,"text":rows[i].deptName});
			}
			secondDeptCBB.combobox("loadData",data);
		}
	,"json");
}

function initPatrolTeamCBB(){
	var data=[];
	data.push({"value":"","text":"请选择巡检班组"});
	patrolTeamCBB=$("#cen_div #patrolTeam_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data,
		onSelect:function(){
			loadPssCBBData();
		}
	});
}

function loadPatrolTeamCBBData(){
	var deptId=secondDeptCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择巡检班组"});
	$.post(patrolMgmtPath+"queryTeamCBBListByDeptId",
		{deptId:deptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
		}
	,"json");
}

function initPatrolStaffCBB(){
	var data=[];
	data.push({"value":"","text":"请选择巡检人员"});
	patrolStaffCBB=$("#cen_div #patrolStaff_cbb").combobox({
		valueField:"value",
		textField:"text",
		data:data
	});
}

function loadPatrolStaffCBBData(){
	var ptId=patrolTeamCBB.combobox("getValue");
	var data=[];
	data.push({"value":"","text":"请选择巡检人员"});
	$.post(patrolMgmtPath+"queryTeamStaffCBBList",
		{ptId:ptId},
		function(result){
			var rows=result.rows;
			for(var i=0;i<rows.length;i++){
				data.push({"value":rows[i].id,"text":rows[i].name});
			}
			patrolStaffCBB.combobox("loadData",data);
		}
	,"json");
}

function initStartDateDB(){
	startDateDB=$("#startDate_db").datebox({
		required:false
	});
}

function initEndDateDB(){
	endDateDB=$("#endDate_db").datebox({
		required:false
	});
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var plName=$("#toolbar #plName").val();
			var paName=$("#toolbar #paName").val();
			var pdName=$("#toolbar #pdName").val();
			var pdaNo=$("#toolbar #pdaNo").val();
			var pdpName=$("#toolbar #pdpName").val();
			var pdpUnit=$("#toolbar #pdpUnit").val();
			var startTime=startTimeDTB.datetimebox("getValue");
			var endTime=endTimeDTB.datetimebox("getValue");
			tab1.datagrid("load",{plName:plName,paName:paName,pdName:pdName,pdaNo:pdaNo,
				pdpName:pdpName,pdpUnit:pdpUnit,startTime:startTime,endTime:endTime});
		}
	});
}

function setComboLocation(num){
	var marginTop;
	var marginLeft=0;
	switch (num) {
	case 0:
	case 1:
		marginTop=10;
		marginLeft=100;
		break;
	case 2:
	case 3:
	case 4:
		marginTop=10;
		marginLeft=70;
		break;
	case 5:
		marginTop=10;
		marginLeft=35;
		break;
	}
	$(".cen_div .toolbar .combo").eq(num).css("margin-top",marginTop+"px");
	$(".cen_div .toolbar .combo").eq(num).css("margin-left",marginLeft+"px");
}

function getCenAnaData(){
	$.post(patrolMgmtPath+"getCenAnaData",
		{ptId:2},
		function(data){
			var reachDayCount=data.reachDayCount;
			var sumDayCount=data.sumDayCount;
			var lineReachPercent=data.lineReachPercent;
			var areaReachPercent=data.areaReachPercent;
			$("#rdc_span").text(reachDayCount);
			$("#sdc_span").text(sumDayCount);
			$("#plrp_span").text(lineReachPercent+"%");
			$("#parp_span").text(areaReachPercent+"%");
		}
	,"json");
}
</script>
</head>
<body>
<%@include file="../../inc/side.jsp"%>
<div class="cen_div" id="cen_div">
	<div class="toolbar" id="toolbar">
		<div class="row_div">
			<span class="firstDept_span">一级部门：</span>
			<input id="firstDept_cbb"/>
			<span class="secondDept_span">二级部门：</span>
			<input id="secondDept_cbb"/>
			<span class="patrolTeam_span">班组：</span>
			<input id="patrolTeam_cbb"/>
			<span class="patrolStaff_span">人员：</span>
			<input id="patrolStaff_cbb"/>
			<span class="date_span">时间：</span>
			<input id="startDate_db"/>
			<span class="to_span">至</span>
			<input id="endDate_db"/>
			<a class="search_but" id="search_but">查询</a>
		</div>
	</div>
	<div class="data_div" id="data_div">
		<div class="reachPercent_div" id="reachPercent_div">
			<div class="item_div rdc_item_div">
				<img class="icon_img" alt="" src="<%=basePath %>/resource/image/002.png">
				<span class="rdc_span" id="rdc_span"></span>
				<span class="fgx_span">/</span>
				<span class="sdc_span" id="sdc_span"></span>
				<span class="text_span">达标天数</span>
			</div>
			<div class="item_div plrp_item_div">
				<img class="icon_img" alt="" src="<%=basePath %>/resource/image/003.png">
				<span class="plrp_span" id="plrp_span"></span>
				<span class="text_span">巡检路线达标率</span>
			</div>
			<div class="item_div parp_item_div">
				<img class="icon_img" alt="" src="<%=basePath %>/resource/image/004.png">
				<span class="parp_span" id="parp_span"></span>
				<span class="text_span">巡检区域达标率</span>
			</div>
		</div>
	</div>
</div>
</body>
</html>