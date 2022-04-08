<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
.tab1_div{
	margin-top:65px;
	margin-left: 220px;
	position: fixed;
}
.tab1_div .toolbar{
	height:32px;
}
.tab1_div .toolbar .row_div{
	height:32px;
}
.tab1_div .toolbar .row_div .name_span,
.tab1_div .toolbar .row_div .createTime_span,
.tab1_div .toolbar .row_div .search_but{
	margin-left: 13px;
}
.tab1_div .toolbar .row_div .name_inp{
	width: 120px;
	height: 25px;
}
a {
  color: #333;
  text-decoration: none;
}

.add_line_bg_div{
	width: 100%;
	height: 100%;
	background-color: rgba(0,0,0,.45);
	position: fixed;
	z-index: 9016;
	display:none;
}
.add_line_div{
	width: 500px;
	height: 210px;
	margin: 200px auto 0;
	background-color: #fff;
	border-radius:5px;
	position: absolute;
	left: 0;
	right: 0;
}
.add_line_div .name_inp{
	width: 150px;
	height: 25px;
}
</style>
<title>Insert title here</title>
<%@include file="../../inc/js.jsp"%>
<script type="text/javascript">
var path='<%=basePath %>';
var patrolMgmtPath=path+'patrolMgmt/';
var dialogTop=10;
var dialogLeft=20;
var aldNum=0;
$(function(){
	initCreateTimeStDTB();
	initCreateTimeEtDTB();
	initSearchLB();
	initAddLB();
	initRemoveLB();
	initTab1();
	initAddLineDialog();//1
	
	initDialogPosition();//将不同窗体移动到主要内容区域
});

function initDialogPosition(){
	var aldpw=$("body").find(".panel.window").eq(aldNum);
	var aldws=$("body").find(".window-shadow").eq(aldNum);

	var aldDiv=$("#add_line_div");
	aldDiv.append(aldpw);
	aldDiv.append(aldws);
}

function initCreateTimeStDTB(){
	createTimeStDTB=$("#createTimeSt_dtb").datetimebox({
        required:false
    });
}

function initCreateTimeEtDTB(){
	createTimeEtDTB=$("#createTimeEt_dtb").datetimebox({
        required:false
    });
}

function initSearchLB(){
	$("#search_but").linkbutton({
		iconCls:"icon-search",
		onClick:function(){
			var name=$("#toolbar #name").val();
			var createTimeStart=createTimeStDTB.datetimebox("getValue");
			var createTimeEnd=createTimeEtDTB.datetimebox("getValue");
			tab1.datagrid("load",{name:name,createTimeStart:createTimeStart,createTimeEnd:createTimeEnd});
		}
	});
}

function initAddLB(){
	$("#add_but").linkbutton({
		iconCls:"icon-add",
		onClick:function(){
			openAddLineDialog(true);
		}
	});
}

function initRemoveLB(){
	removeLB=$("#remove_but").linkbutton({
		iconCls:"icon-remove",
		onClick:function(){
			deleteByIds();
		}
	});
}

function initTab1(){
	tab1=$("#tab1").datagrid({
		title:"路线查询",
		url:patrolMgmtPath+"queryLineList",
		toolbar:"#toolbar",
		width:setFitWidthInParent("body","tab1_div"),
		pagination:true,
		pageSize:10,
		columns:[[
			{field:"name",title:"名称",width:150},
			{field:"createTime",title:"创建时间",width:180},
            {field:"id",title:"操作",width:110,formatter:function(value,row){
            	var str="<a href=\"detail?id="+value+"\">详情</a>";
            	return str;
            }}
	    ]],
        onLoadSuccess:function(data){
			if(data.total==0){
				$(this).datagrid("appendRow",{name:"<div style=\"text-align:center;\">暂无信息<div>"});
				$(this).datagrid("mergeCells",{index:0,field:"name",colspan:3});
				data.total=0;
			}
			
			$(".panel-header .panel-title").css("color","#000");
			$(".panel-header .panel-title").css("font-size","15px");
			$(".panel-header .panel-title").css("padding-left","10px");
			$(".panel-header, .panel-body").css("border-color","#ddd");
		}
	});
}

function initAddLineDialog(){
	$("#add_line_dialog_div").dialog({
		title:"路线添加",
		width:setFitWidthInParent("#add_line_div","add_line_dialog_div"),
		height:150,
		top:5,
		left:dialogLeft,
		buttons:[
           {text:"确定",id:"ok_but",iconCls:"icon-ok",handler:function(){
        	   checkAddLine();
           }},
           {text:"取消",id:"cancel_but",iconCls:"icon-cancel",handler:function(){
        	   openAddLineDialog(false);
           }}
        ]
	});

	$("#add_line_dialog_div table").css("width",(setFitWidthInParent("#add_line_div","add_line_dialog_table"))+"px");
	$("#add_line_dialog_div table").css("magin","-100px");
	$("#add_line_dialog_div table td").css("padding-left","40px");
	$("#add_line_dialog_div table td").css("padding-right","20px");
	$("#add_line_dialog_div table td").css("font-size","15px");
	$("#add_line_dialog_div table .td1").css("width","30%");
	$("#add_line_dialog_div table .td2").css("width","60%");
	$("#add_line_dialog_div table tr").css("height","45px");

	$(".panel.window").eq(aldNum).css("margin-top","20px");
	$(".panel.window .panel-title").eq(aldNum).css("color","#000");
	$(".panel.window .panel-title").eq(aldNum).css("font-size","15px");
	$(".panel.window .panel-title").eq(aldNum).css("padding-left","10px");
	
	$(".panel-header, .panel-body").css("border-color","#ddd");
	
	//以下的是表格下面的面板
	$(".window-shadow").eq(aldNum).css("margin-top","20px");
	$(".window,.window .window-body").eq(aldNum).css("border-color","#ddd");

	$("#add_line_dialog_div #ok_but").css("left","30%");
	$("#add_line_dialog_div #ok_but").css("position","absolute");

	$("#add_line_dialog_div #cancel_but").css("left","50%");
	$("#add_line_dialog_div #cancel_but").css("position","absolute");
	
	$(".dialog-button").css("background-color","#fff");
	$(".dialog-button .l-btn-text").css("font-size","20px");
}

function openAddLineDialog(flag){
	if(flag){
		$("#add_line_bg_div").css("display","block");
	}
	else{
		$("#add_line_bg_div").css("display","none");
	}
}

function focusAddLineName(){
	var name = $("#add_line_div #name").val();
	if(name=="路线名称不能为空"){
		$("#add_line_div #name").val("");
		$("#add_line_div #name").css("color", "#555555");
	}
}

//验证路线名称
function checkAddLineName(){
	var name = $("#add_line_div #name").val();
	if(name==null||name==""||name=="路线名称不能为空"){
		$("#add_line_div #name").css("color","#E15748");
    	$("#add_line_div #name").val("路线名称不能为空");
    	return false;
	}
	else
		return true;
}

function checkAddLine(){
	if(checkAddLineName()){
		newLine();
	}
}

function newLine(){
	var name = $("#add_line_div #name").val();
	$.post(patrolMgmtPath+"newLine",
		{name:name},
		function(data){
			if(data.message=="ok"){
				alert(data.info);
				openAddLineDialog(false);
				tab1.datagrid("load");
			}
		}
	,"json");
}

function setFitWidthInParent(parent,self){
	var space=0;
	switch (self) {
	case "tab1_div":
		space=250;
		break;
	case "add_line_dialog_div":
		space=50;
		break;
	case "add_line_dialog_table":
		space=68;
		break;
	}
	var width=$(parent).css("width");
	return width.substring(0,width.length-2)-space;
}
</script>
</head>
<body>
<%@include file="../../inc/side.jsp"%>
<div class="tab1_div" id="tab1_div">
	<div class="toolbar" id="toolbar">
		<div class="row_div">
			<span class="name_span">名称：</span>
			<input type="text" class="name_inp" id="name" placeholder="请输入路线名称"/>
			<span class="createTime_span">创建时间：</span>
			<input id="createTimeSt_dtb"/>
			-
			<input id="createTimeEt_dtb"/>
			<a class="search_but" id="search_but">查询</a>
			<a id="add_but">添加</a>
			<a id="remove_but">删除</a>
		</div>
	</div>
	<table id="tab1">
	</table>
</div>

<div class="add_line_bg_div" id="add_line_bg_div">
	<div class="add_line_div" id="add_line_div">
		<div class="add_line_dialog_div" id="add_line_dialog_div">
			<table>
			  <tr>
				<td class="td1" align="right">
					名称
				</td>
				<td class="td2">
					<input type="text" class="name_inp" id="name" placeholder="请输入路线名称" onfocus="focusAddLineName()" onblur="checkAddLineName()"/>
				</td>
			  </tr>
			</table>
		</div>
	</div>
</div>
</body>
</html>