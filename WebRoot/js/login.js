window.onload = function() {
	trChangeColor() ;
	changeCode() ;
	addValidateEvent("admin.aid", validateAid) ;
	addValidateEvent("admin.password", validatePassword) ;
	addValidateEvent("code", validateCode) ;
}

function validateAid() {
	return validateEmpty("admin.aid")  ;
}

function validatePassword() {
	return validateEmpty("admin.password")  ;
}

function validateCode() {
	return validateRegex("code", /^\w{4}$/)  ;
}

function validateLogin() {
	return 	validateAid() && 
			validatePassword() && 
			validateCode() ;
}