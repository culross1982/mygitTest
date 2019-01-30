$(".daterange input").each(function() {
	var $this = $(this);
	$this.daterangepicker({
		locale : {
			"format" : "YYYY-MM-DD",// 显示格式
			"separator" : " ~ ",// 两个日期之间的分割线
			// 中文化
			"applyLabel" : "确定",
			"cancelLabel" : "取消",
			"fromLabel" : "开始",
			"toLabel" : "结束",
			"daysOfWeek" : [ "日", "一", "二", "三", "四", "五", "六" ],
			"monthNames" : [ "一月", "二月", "三月", "四月", "五月", "六", "七月", "八月", "九月", "十月", "十一月", "十二月" ],
			"firstDay" : 1
		},
		format: 'YYYY-MM-DD',//设置input显示日期格式
		separator : " ~ ",// 设置input显示两个日期之间的分割线
	}, function(start, end, label) {
		// 点击确定后的事件，下面是为了bootstrap validate得校验，
		// 若未使用，可忽视
		/*if ($this.parents("form.required-validate").length > 0) {
				var $form = $this.parents("form.required-validate");

				var name = $this.attr("name");
				if ($form.length > 0) {
					var data = $form.data('bootstrapValidator');
					data.updateStatus(name, 'NOT_VALIDATED', null)
					// Validate the field
					.validateField(name);
				}
			}*/
		// 设置最小宽度，否则显示不全
	}).css("min-width", "210px").next("i").click(function() {
		// 对日期的i标签增加click事件，使其在鼠标点击时可以拉出日期选择
		$(this).parent().find('input').click();
	});
});

//将包材历史清单导入Excel中  #到服务器端#
/*$("#export").click(function(){
	$.ajax({
		url:"export.ajax",
		dataType:"json",
		type:"post",
		success:function(result){
			if(result.result=="false"){
				humane.log("导出失败！");
			}else if(result.result=="success"){
				humane.log("导出成功！");
			}
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			humane.log("发生错误");
		}
	})
})*/

/*撤销历史记录*/
$(".cancelHistory").click(function(){
	var obj=$(this)
	var id=obj.attr("hid");
	$.ajax({
		url:"cancelHistory.ajax",
		data:{"id":id},
		type:"post",
		dataType:"json",
		success:function(data){
			if(data.result=="failed"){
				humane.log("撤销失败！");
			}else if(data.result=="error"){
				humane.log("id错误！");
			}else if(data.result=="success"){
				humane.log("撤销成功！");
				obj.parents("tr").remove();
			}
		},
		error:function(){
			humane.log("发生错误");
		}
	})
})