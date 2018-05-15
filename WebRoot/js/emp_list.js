window.onload = function() {
	trChangeColor() ;
	allRowSelected("eid") ;
}

function update(eid) {
	document.getElementById("emp.eid").value = eid ;
	document.getElementById("formData").submit() ;
}

function updateStatus(url, paramName, eleId) {
	deleteAll(url, paramName, eleId) ;
}

