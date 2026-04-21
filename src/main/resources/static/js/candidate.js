$(document).ready(function(){
	
	$.getJSON('/dropdown/candidates', function(candidatedata){
		
		candidatedata.forEach(function(candidate){
			$('#candidatedropdown').append($('<option>', { value: candidate.code, text: candidate.name }));	
		});
		
	});
		
});