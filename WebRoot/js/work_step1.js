window.onload = function () {
	trChangeColor() ;
	addValidateEvent("work.employee.eid", validateEid) ;
}

function validateEid() {
	return validateEmpty("work.employee.eid") ;
}

function validateSelect() {
	return validateEid() ;
}