/**
 * Esta funcionalidad requiere jQuery 1.12 o mayor y Bootstrap 3
 * @param idElem
 */
function updateClock(idElem) {
	var currentTimeString = "";
	var currentTime = new Date();
	
	//Date
	var currentDay = currentTime.getDate();
	var currentMonth =  currentTime.getMonth();
	var currentYear = currentTime.getFullYear();
	
	currentDay = (currentDay < 10 ? "0" : "") + currentDay;
	currentMonth = (currentMonth < 10 ? "0" : "") + currentMonth;
	
	currentTimeString += currentDay + "/" + currentMonth + "/" + currentYear;
	
	//Time
	var currentHours = currentTime.getHours();
	var currentMinutes = currentTime.getMinutes();
	var currentSeconds = currentTime.getSeconds();

	// Pad the minutes and seconds with leading zeros, if required
	currentMinutes = (currentMinutes < 10 ? "0" : "") + currentMinutes;
	currentSeconds = (currentSeconds < 10 ? "0" : "") + currentSeconds;

	/*
	// Choose either "AM" or "PM" as appropriate
	var timeOfDay = (currentHours < 12) ? "AM" : "PM";

	// Convert the hours component to 12-hour format if needed
	currentHours = (currentHours > 12) ? currentHours - 12 : currentHours;

	// Convert an hours component of "0" to "12"
	currentHours = (currentHours == 0) ? 12 : currentHours;
	
	// Compose the string for display
	var currentTimeString = currentHours + ":" + currentMinutes + ":" + currentSeconds + " " + timeOfDay;
	*/
	
	currentTimeString += " " + currentHours + ":" + currentMinutes + ":" + currentSeconds;
	
	//Output
	currentTimeString = '<span class="glyphicon glyphicon-time"></span> ' + currentTimeString;
	$("#" + idElem).html(currentTimeString);
}