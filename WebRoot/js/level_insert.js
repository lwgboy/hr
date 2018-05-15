window.onload = function() {
	trChangeColor() ;
	addValidateEvent("level.title", validateTitle) ;
	addValidateEvent("level.losal", validateLosal) ;
	addValidateEvent("level.hisal", validateHisal) ;
	
}

function validateTitle() {
	return validateEmpty("level.title") ;
}

function validateLosal() {
	return validateRegex("level.losal", /^\d+(\.\d+)?$/) ;
}

function validateHisal() {
	return validateRegex("level.hisal", /^\d+(\.\d+)?$/) ;
}

function validateInsert() {
	return 	validateTitle() && 
			validateLosal() && 
			validateHisal() ;
}

