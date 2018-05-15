window.onload = function() {
	trChangeColor() ;
	addValidateEvent("jobs.title", validateTitle) ;
}

function validateTitle() {
	return validateEmpty("jobs.title") ;
}

function validateInsert() {
	return 	validateTitle() ;
}

