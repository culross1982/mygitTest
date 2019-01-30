$(document).ready(function(){
	/* 载入包材主菜单 */
	var result="";
	var json=eval(packingMain);
	for(var i=0;i<json.length;i++){
		var pic;
		switch(i){
		case 0:
			pic="fa fa-edit";break;
		case 1:
			pic="fa fa-bug";break;
		default:
			pic="fa fa-users";break;
		}
		result=result+"<li ><a onclick=\"$('#test"+i+"').toggle(500);\"><i class=\""+pic+"\"></i>"+json[i].mainMenu.functionName+"<span class=\"fa fa-chevron-down\"></span></a>";
		result=result+"<ul class=\"nav child_menu\" id=\"test"+i+"\">";
			for(var j=0;j<json[i].subMenu.length;j++){
				result=result+"<li><a href=\""+json[i].subMenu[j].functionUrl+"\">"+json[i].subMenu[j].functionName+"</a></li>";
			}
		result=result+"</ul></li>";
	}
	$("#packingMenu").append(result);
	
	/*产品基础数据列表 修改功能按权限展示*/
	if(modifyStatus==1){
		$(".modifyStatus").addClass("hide");
	}
})
