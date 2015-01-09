<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YOUKU Crowdfunding</title>
<!--  
<link type="text/css" rel="stylesheet" href="jqGrid/themes/cupertino/jquery-ui-1.9.0.custom.min.css">
<link type="text/css" rel="stylesheet" href="jqGrid/themes/ui.jqgrid.css">
<script type="text/javascript" src="jquery-1.8.2.min.js" />
<script type="text/javascript" src="jqGrid/js/jquery-ui-1.9.0.custom.min.js"/>
<script type="text/javascript" src="jqGrid/js/i18n/grid.locale-cn.js"/>
<script type="text/javascript" src="jqGrid/js/jquery.jqGrid.min.js"/>
-->

<%
System.out.println("contextPath====="+request.getContextPath());
%>

<script type="text/javascript">
var contextPath = '<%=request.getContextPath()%>';
</script>

<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/static/jquery-ui-1.11.2.custom/jquery-ui.min.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/static/jquery-ui-1.11.2.custom/jquery-ui.structure.min.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/static/jquery-ui-1.11.2.custom/jquery-ui.theme.min.css"/>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/static/Guriddo_jqGrid_JS_4.7.1/css/ui.jqgrid.css"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/Guriddo_jqGrid_JS_4.7.1/js/jquery-1.11.0.min.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/jquery-ui-1.11.2.custom/jquery-ui.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/Guriddo_jqGrid_JS_4.7.1/js/i18n/grid.locale-cn.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/Guriddo_jqGrid_JS_4.7.1/js/jquery.jqGrid.min.js"></script>

<script type="text/javascript">

function dateFormator(cellvalue){
    var datetime = new Date();
    datetime.setTime(cellvalue);
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}

function optFormator(cellvalue, options, rawObject) {
	return "<a onclick='updateUser("+ cellvalue +")'>update</a>&nbsp;<a onclick='deleteUser("+ cellvalue +")'>delete</a>";
}

function deleteUser(cellvalue){
    $.ajax({
        type: 'POST',
        url: contextPath + "/user/delete",
        data: {id:cellvalue} ,
        success: function(retData){
            var ok = false;
            if(retData){
                if(retData.result && retData.result == true){
                    ok = true;
                }
            }
            if(ok){
                alert("删除成功");
                $("#list4").jqGrid().trigger('reloadGrid');
            }else{
                alert("删除失败");
            }
        } ,
        dataType: 'json'
    });
}
function updateUser(cellvalue){
	alert("userId="+cellvalue);
}

function saveUser(){
	var user={
            id:$("#id").val(),
            name:$("#name").val(),
            age:$("#age").val(),
            gender:$("#gender").val(),
            backup:$("#backup").val()
	};
	$.ajax({
	    type: 'POST',
	    url: contextPath + "/user/save",
	    data: user ,
	    success: function(retData){
	    	var ok = false;
	    	if(retData){
	    		if(retData.result && retData.result == true){
	    			ok = true;
	    		}
	    	}
	    	if(ok){
		    	alert("保存成功");
		    	$("#list4").jqGrid().trigger('reloadGrid');
	    	}else{
	    		alert("保存失败");
	    	}
	    } ,
	    dataType: 'json'
	});
}

$(document).ready(function(){
    $("#list4").jqGrid({
        url: contextPath + "/user/list",
        datatype:"json", //数据来源，本地数据
        mtype:"POST",//提交方式
        height:300,//高度，表格高度。可为数值、百分比或'auto'
        width:1000,//这个宽度不能为百分比
        //autowidth:true,//自动宽
        colNames:['ID', '姓名', '年龄', '性别', '备注', '创建时间', '修改时间','操作'],
        colModel:[
            {name:'id',index:'id', width:'10%', align:'left' },
            {name:'name',index:'name', width:'10%',align:'center'},
            {name:'age',index:'age', width:'10%',align:'left'},
            {name:'gender',index:'gender', width:'10%', align:"center"},
            {name:'backup',index:'backup', width:'15%', align:"center"},
            {name:'createTime',index:'create_time', width:'15%',align:"center", formatter:dateFormator},
            {name:'updateTime',index:'update_time', width:'15%',align:"center", formatter:dateFormator, sortable:true},
            {name:'id',index:'opt', width:'15%', align:'center',formatter:optFormator }
        ],
        prmNames:{page:"user.pageNum_",rows:"user.pageSize_", sort: "user.order_",order: "user.sort_", search:"_search", nd:"nd", npage:null},
        rownumbers:true,//添加左侧行号
        //altRows:true,//设置为交替行表格,默认为false
        sortname:'id',
        sortorder:'desc',
        viewrecords: true,//是否在浏览导航栏显示记录总数
        rowNum:10,//每页显示记录数
        rowList:[10,20,30],//用于改变显示行数的下拉列表框的元素数组。
        jsonReader:{
        	root: "rows",
            page: "page",   // json中代表当前页码的数据  
            total: "total", // json中代表页码总数的数据  
            records: "records", // json中代表数据行总数的数据
            repeatitems: true
        },
        pager:$('#gridPager')
    });
});
</script>

</head>
<body>
YOUKU Crowdfunding</br>
<!-- 
words========<%=request.getAttribute("word")%></br>
words========${word}
 -->
<input id="id" name="id" type="hidden"/><br/>
姓名：<input id="name" name="name" type="text"/><br/>
年龄：<input id="age" name="name" type="text"/><br/>
性别：<input id="gender" name="name" type="text"/><br/>
备注：<textarea id="backup" name="backup" rows="" cols="" style="width:167px;"></textarea><br/>
<br/><input type="button" id="save" value="save" onclick="saveUser()"/><br/><br/>
<!-- jqGrid table list4 -->
<table id="list4" style="width:200px;"></table>
<!-- jqGrid 分页 div gridPager -->
<div id="gridPager"></div>

</body>
</html>