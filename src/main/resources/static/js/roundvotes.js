$(document).ready(function(){
	
	//For Assembly Constituencies
	$.getJSON('/dropdown/ac', function(acdata){
		
		acdata.forEach(function(ac){
			$('#acdropdown').append($('<option>', { value: ac.acno, text: ac.acno + ' - ' + ac.name }));	
		});
		
	});
	
	//For Rounds, after assembly constituency is selected
	
	$('#acdropdown').on('change', function(){
		
		const acno=$(this).val();
		
		resetRounds();
		resetCandidates();
		
		if(!acno) return;
		
		$.getJSON('/roundvotes/rounds', {acno}).done(function (rounds){
			
			const $round = $('#roundsdropdown').empty().append('<option value="">Select Round</option>');
			
			rounds.forEach(roundno => {
				
				$round.append(`<option value="${roundno}">${roundno}</option>`);
				
			});		
			
			$round.prop('disabled', false);			
			
		}).fail(() => alert('Failed to load rounds'));
		
		
	});
	
	// For Candidates, after round is selected
	
	$('#roundsdropdown').on('change', function () {
		
		const roundno=$(this).val();
		const acno=$('#acdropdown').val();
		
		resetCandidates();
		if(!roundno) return;
		
		$.getJSON('/roundvotes/candidates', {acno}).done(function (candidates) {
			
			const $container = $('#candidateContainer');
			
			candidates.forEach((c, index) => {
				$container.append(`
					<div class="card" data-candidate-id="${c.code}">
						<input type="hidden" name="candidatevotes[${index}].candidatecode" value="${c.code}" />
						<h3>${c.name}</h3>
						<p>${c.party.name}</p>
						<input type="number" class="form-control" name="candidatevotes[${index}].votes" min="0" placeholder="Votes" required/>	
					</div>
					`);
				
				
			});
			
			
		}).fail(() => alert('Failed to load candidates'));
		
	});
	
	
	
	
	
	function resetRounds(){
		
		$('#roundsdropdown').prop('disabled', true).html('<option value="">Select Round</option>');
		
	}
	
	function resetCandidates(){
		
		$('#candidateContainer').empty();
		
	}
	
		
});