$(document).ready(function(){
		/* 载入模块菜单 */
		var moduleResult="";
		var json=eval(tt);
		for(var i=0;i<json.length;i++){
			moduleResult=moduleResult+"<li><a href=\""+json[i].moduleMenu.functionUrl+"\">"+json[i].moduleMenu.functionName+"</a></li>";
		}
		$("#moduleMenu").append(moduleResult);
	})
