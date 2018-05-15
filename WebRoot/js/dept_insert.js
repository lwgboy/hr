window.onload = function() {
	trChangeColor() ;
	addValidateEvent("dept.dname", validateDname) ;
}

function validateDname() {
	return validateEmpty("dept.dname") ;
}

function validateInsert() {
	return validateDname() ;
}

