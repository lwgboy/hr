window.onload = function() {
	trChangeColor() ;
	addValidateEvent("oldPassword", validateOldPassword) ;
	addValidateEvent("newPassword1", validateNewPassword1) ;
	addValidateEvent("newPassword2", validateNewPassword2) ;
}

function validateOldPassword() {
	return validateEmpty("oldPassword") ;
}

function validateNewPassword1() {
	return validateEmpty("newPassword1") ;
}

function validateNewPassword2() {
	return validateEmpty("newPassword2") ;
}

function validatePasswordSame() {
	return validateEquals("newPassword1", "newPassword2") ;
}

function validateChangePassword() {
	return 	validateOldPassword() && 
			validateNewPassword1() && 
			validateNewPassword2() &&
			validatePasswordSame() ;
}