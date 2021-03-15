function change_myselect() {

	var table = document.getElementById('curReimb').innerHTML="<tr><th>ID</th><th>Amount</th><th>Time Submitted</th><th>Time Resolved</th><th>Description</th><th>Employee ID</th><th>Resolver ID</th><th>Status</th><th>Type</th></tr>";
	httpRequest = new XMLHttpRequest();

	if (!httpRequest) {
		console.log('Unable to create XMLHTTP instance');
		return false;
	}
	
	var dest = '/project-1---ers-app-mroszel/emprecSubmit.json';
	httpRequest.open('GET',dest);

	httpRequest.responseType = 'json';
	httpRequest.send();
	httpRequest.onreadystatechange = function() {
		if (httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200) {
			var array = httpRequest.response;
			for (var i = 0; i < array.length; i++) {

				var table = document.getElementById('curReimb');
				var row = table.insertRow(table.rows.length);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				var cell6 = row.insertCell(5);
				var cell7 = row.insertCell(6);
				var cell8 = row.insertCell(7);
				var cell9 = row.insertCell(8);

				var rid = document.createTextNode(array[i].id);
				var amount = document.createTextNode(array[i].amount);
				var author = document.createTextNode(array[i].author);
				var description = document.createTextNode(array[i].description);
				var reimbType = document.createTextNode(array[i].reimbType);
				var resolver = document.createTextNode(array[i].resolver);
				var status = document.createTextNode(array[i].status);
				var submitTime = document.createTextNode(array[i].submitTime);
				var resolveTime = document.createTextNode(array[i].resolveTime);

				cell1.appendChild(rid);
				cell2.appendChild(amount);
				cell3.appendChild(submitTime);
				cell4.appendChild(resolveTime);
				cell5.appendChild(description);
				cell6.appendChild(author);
				cell7.appendChild(resolver);
				cell8.appendChild(status);
				cell9.appendChild(reimbType);
			}
		}
	}
}	
