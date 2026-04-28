$(document).ready(function(){
	
	$.getJSON('/dropdown/ac', function(acdata){
		
		acdata.forEach(function(ac){
			$('#acdropdown').append($('<option>', { value: ac.acno, text: ac.acno + ' - ' + ac.name }));	
		});
		
	});
		
});