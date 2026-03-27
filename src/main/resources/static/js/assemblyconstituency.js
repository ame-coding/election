$(document).ready(function(){
	
	$.getJSON('/ac', function(acdata){
		
		acdata.forEach(function(ac){
			$('#acdropdown').append($('<option>', { value: ac.acno, text: ac.name }));	
		});
		
	});
		
});