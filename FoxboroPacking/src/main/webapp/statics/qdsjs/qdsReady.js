$(document).ready(function(){
	/* 载入QDS主菜单 */
	var result="";
	var json=eval(qdsMain);
	for(var i=0;i<json.length;i++){
		var pic;
		switch(i){
		case 0:
			pic="fa fa-gavel";break;
		case 1:
			pic="fa fa-gears";break;
		case 2:
			pic="fa fa-search";break;
		case 3:
			pic="fa fa-stack-overflow";break;
		case 4:
			pic="fa fa-history";break;
		default:
			pic="fa fa-database";break;
		}
		result=result+"<li ><a onclick=\"$('#test"+i+"').toggle(500);\"><i class=\""+pic+"\"></i>"+json[i].mainMenu.functionName+"<span class=\"fa fa-chevron-down\"></span></a>";
		result=result+"<ul class=\"nav child_menu\" id=\"test"+i+"\">";
			for(var j=0;j<json[i].subMenu.length;j++){
				result=result+"<li><a href=\""+json[i].subMenu[j].functionUrl+"\">"+json[i].subMenu[j].functionName+"</a></li>";
			}
		result=result+"</ul></li>";
	}
	$("#qdsMenu").append(result);
	
	/*产品基础数据列表 修改功能按权限展示*/
	if(modifyStatus==1){
		$(".modifyStatus").addClass("hide");
	}
	
})
