$(document).ready(function(){
	/* 载入包材主菜单 */
	var configResult="";
	var json=eval(configMain);
	//alert(json);
	for(var i=0;i<json.length;i++){
		var pic;
		switch(i){
		case 0:
			pic="fa fa-users";break;
		default:
			pic="fa fa-users";break;
		}
		configResult=configResult+"<li ><a onclick=\"$('#test"+i+"').toggle(500);\"><i class=\""+pic+"\"></i>"+json[i].mainMenu.functionName+"<span class=\"fa fa-chevron-down\"></span></a>";
		configResult=configResult+"<ul class=\"nav child_menu\" id=\"test"+i+"\">";
			for(var j=0;j<json[i].subMenu.length;j++){
				configResult=configResult+"<li><a href=\""+json[i].subMenu[j].functionUrl+"\">"+json[i].subMenu[j].functionName+"</a></li>";
			}
			configResult=configResult+"</ul></li>";
	}
	$("#configMenu").append(configResult);
})

