$.postjson = function(url, data, successCallback, errorCallback) {
	if (typeof(errorCallback) == 'undefined') {
		errorCallback = function(data) {
			if (data.status == 200) {
				alert("Could not parse JSON");
			}
			if (data.status == 422) {
				var message = data.responseJSON.message;
				if (data.responseJSON.fieldErrors.length > 0) {
					message += " :\n"
				}
				for(var i= 0; i < data.responseJSON.fieldErrors.length; i++) {
					var fieldError = data.responseJSON.fieldErrors[i];
					message += "Field '" + fieldError.field + "' : " + fieldError.message;
					if (i != data.responseJSON.fieldErrors.length) {
						message += "\n";
					}
				}
				alert(message);
			} else {
				alert(data.status + " : " + data.statusText);
			}
		};
	}
	
	$.ajax({
		type: 'POST',
		url : url,
		data : JSON.stringify(data),
		contentType: "application/json",
		dataType : 'json',
	})
	.done(successCallback)
	.fail(errorCallback);
}

