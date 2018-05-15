window.onload = function() {
	trChangeColor() ;
	
	addValidateEvent("emp.eid", validateEid) ;
	addValidateEvent("emp.ename", validateEname) ;
	addValidateEvent("emp.idcard", validateIdcard) ;
	addValidateEvent("emp.birthday", validateBirthday) ;
	addValidateEvent("emp.school", validateSchool) ;
	addValidateEvent("emp.profession", validateProfession) ;
	addValidateEvent("emp.grad", validateGrad) ;
	addValidateEvent("emp.sal", validateSal) ;
	
	document.getElementById("emp.photo").addEventListener("change", function() {
		preview(this) ;
	}, false) ;
}

function validateEid() {
	return validateRegex("emp.eid", /^\d+$/)
}

function validateEname() {
	return validateEmpty("emp.ename") ;
}

function validateIdcard () {
	return validateRegex("emp.idcard", /^\d{17}\w$/) ;
}

function validateBirthday () {
	if (validateRegex("emp.birthday", /^\d{4}-\d{2}-\d{2}$/)) {
		return true ;
	}
	return validateRegex("emp.birthday", /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/) ;
}

function validateSchool() {
	return validateEmpty("emp.school") ;
}

function validateProfession() {
	return validateEmpty("emp.profession") ;
}

function validateGrad() {
	if (validateRegex("emp.grad", /^\d{4}-\d{2}-\d{2}$/)) {
		return true ;
	}
	return validateRegex("emp.grad", /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/) ;
}

function validateSal() {
	return validateRegex("emp.sal", /^\d+(\.\d{1,2})?$/) ;
}

function validateInsert() {
	return 	validateEid() && 
			validateEname() && 
			validateIdcard() && 
			validateBirthday() && 
			validateSchool() && 
			validateProfession() && 
			validateGrad() && 
			validateSal() ;
}

function preview(file) {
	var prevDiv = document.getElementById("preview") ;
	if (file.files && file.files[0]) {
		var reader = new FileReader() ;
		reader.onload = function(evt) {
			prevDiv.innerHTML = '<img class="photo" src="' + evt.target.result + '" />' ;
		}
		reader.readAsDataURL(file.files[0]) ;
	} else {
		prevDiv.innerHTML = '<div class="photo" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>' ;
	}
}



