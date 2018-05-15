window.onload = function () {
	trChangeColor() ;
	addValidateEvent("salary.employee.eid", validateEid) ;
}

function validateEid() {
	return validateEmpty("salary.employee.eid") ;
}

function validateSelect() {
	return validateEid() ;
}