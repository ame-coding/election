$(document).ready(function(){
	
	$.getJSON('/parties', function(partydata){
		
		partydata.forEach(function(party){
			$('#partydropdown').append($('<option>', { value: party.code, text: party.name }));	
		});
		
	});
		
});