/**
 * 在鼠标划过每一行的时候改变对应行的颜色
 * 要求在jsp文件中，所有<tr>元素id="tr"
 */
function trChangeColor() {
	var ele = document.all("tr") ;
	if (ele == null) {
		return ;
	} else if (ele.length == undefined) {
		ele.addEventListener("mouseover", function() {
			this.bgColor = "#FFFFFF" ;
		}, false) ;
		ele.addEventListener("mouseout", function() {
			this.bgColor = "#F2F2F2" ;
		}, false) ;
	} else {
		for (var i=0 ; i<ele.length ; i++) {
			ele[i].addEventListener("mouseover", function() {
				this.bgColor = "#FFFFFF" ;
			}, false) ;
			ele[i].addEventListener("mouseout", function() {
				this.bgColor = "#F2F2F2" ;
			}, false) ;
		}
	}
}

/**
 * 点击更换验证码
 * 要求在jsp文件中，跟换验证码按钮id="changeCode"
 * 验证码图片元素id="codeImage"
 */
function changeCode () {
	document.getElementById("changeCode").addEventListener("click", function() {
		document.getElementById("codeImage").src = "pages/plugin/codeImage.jsp?tm=" + Math.random();
	}, false) ;
}

/**
 * 验证id对应的元素中的值是否为空
 * @param eleId jsp中的元素id
 */
function validateEmpty(eleId) {
	var obj = document.getElementById(eleId) ;
    var msg = document.getElementById(eleId + "Msg") ;
    if (obj.value != "") {
    	obj.className = "right" ;
    	if (msg != null) {
            msg.innerHTML = "<font color='#00FF00'>内容输入正确！</font>" ;
        }
        return true ;
    } else {
        obj.className = "wrong" ;
        if (msg != null) {
            msg.innerHTML = "<font color=#FF0000>内容不允许为空！</font>"  ;
        }
        return false ;
    }
}

/**
 * 验证id对应的元素中的值是否符合指定规则
 * @param eleId jsp中的元素id
 * @param regex 验证规则
 */
function validateRegex(eleId, regex) {
    var obj = document.getElementById(eleId) ;
    var msg = document.getElementById(eleId + "Msg") ;
    if (regex.test(obj.value)) {
        obj.className = "right" ;
        if (msg != null) {
            msg.innerHTML = "<font color='#00FF00'>内容输入正确！</font>" ;
        }
        return true ;
    } else {
        obj.className = "wrong" ;
        if (msg != null) {
            msg.innerHTML = "<font color=#FF0000>输入内容格式错误！</font>"  ;
        }
        return false ;
    }
}

/**
 * 判断两个元素中的值是否相同
 * @param eleIdA
 * @param eleIdB
 * @returns 相同则通过验证，返回true
 */
function validateEquals(eleIdA, eleIdB) {
	var objA = document.getElementById(eleIdA) ;
	var objB = document.getElementById(eleIdB) ;
	var msg = document.getElementById(eleIdB + "Msg") ;
	if (validateEmpty(eleIdA) && validateEmpty(eleIdB)) {
		if (objA.value == objB.value) {
			return true ;
		}
	}
	if (msg != null) {
		objA.className = "wrong" ;
		objB.className = "wrong" ;
		msg.innerHTML = "<font color=#FF0000>两次输入的内容不一致！</font>"
	}
	return false ;
}

/**
 * 在元素获得焦点的时候，变回原始状态
 * @param eleId 指定元素
 */
function InitializeElement(eleId) {
    document.getElementById(eleId).className = "init" ;
    var msg = document.getElementById(eleId + "Msg") ;
    if (msg != null) {
    	msg.innerHTML = "" ;
    }
}

/**
 * 给指定元素添加
 * @param eleId 将要被验证的元素
 * @param funName 验证函数
 */
function addValidateEvent(eleId, funName) {
	document.getElementById(eleId).addEventListener("blur", function() {
		funName() ;
	}, false) ;
	document.getElementById(eleId).addEventListener("focus", function() {
		InitializeElement(eleId) ;
	}, false) ;
}

/**
 * 实现对复选框的“全选”操作
 * @param priKey 所有复选框的id（要求所有复选框使用一个id，全选按钮使用“selall”作为id）
 */
function allRowSelected(priKey) {
	document.getElementById("selall").addEventListener("click", function() {
		var item = document.all(priKey) ;
		if (item.length == undefined) {	// 表示只有一个元素，不是数组
			document.getElementById(priKey).checked = this.checked ;
		} else {
			for (var x=0 ; x<item.length ; ++x) {
				item[x].checked = this.checked ;
			}
		}
	}, false) ;	
}

/**
 * 批量删除操作
 * @param url 要删除的操作路径
 * @param paramName 要传递的参数名称
 * @param eleId 要取得数据的id
 */
function deleteAll(url, paramName, eleId) {
	var data = "" ;		// 保存所有要删除的数据编号
	var item = document.all(eleId) ;
	var count = 0 ;		// 保存要删除的数据个数
	// 判断是否有要删除的数据
	if (item.length == undefined) {	// 表示只有一个元素，不是数组
		if (document.getElementById(eleId).checked) {	// 数据选中
			data += document.getElementById(eleId).value + "|" ;
			++count ;
		}
	} else {
		for (var x=0 ; x<item.length ; ++x) {
			if (item[x].checked) {
				data += (item[x].value + "|") ;
				++count ;
			}
		}
	}
	if (count > 0) {
		if (window.confirm("确定要删除/修改这些数据吗？")) {
			window.location = url + "&" + paramName + "=" + data ;
		}
	} else {
		alert("未选择任何要删除/修改的数据！") ;
	}
}



