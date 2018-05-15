window.onload = function() {
	trChangeColor() ;
	allRowSelected("lid") ;
}

function update(lid) {
	if (validateEmpty("title-" + lid) && 
			validateRegex("losal-" + lid, /^\d+(\.\d+)?$/) && 
			validateRegex("hisal-" + lid, /^\d+(\.\d+)?$/)) {
		document.getElementById("level.lid").value = lid ;
		document.getElementById("level.title").value = 
			document.getElementById("title-" + lid).value ;
		document.getElementById("level.losal").value = 
			document.getElementById("losal-" + lid).value ;
		document.getElementById("level.hisal").value = 
			document.getElementById("hisal-" + lid).value ;
		document.getElementById("levelForm").submit() ;	// 提交表单
	}
}