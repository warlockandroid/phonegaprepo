// Wait for PhoneGap to load
//

// PhoneGap is ready
//

    function insertToDb(form){
    
     var db = window.openDatabase("employeedatabase", "1.0", "EmpDetails", 5000);
	//var db = window.openDatabase({name: "DB"});
      db.transaction(function (tx){
		tx.executeSql('CREATE TABLE IF NOT EXISTS employDb (id unique, first_name, last_name,  designation)')
		
		tx.executeSql('INSERT INTO employDb (id , first_name, last_name,  designation) VALUES ('"+ form.empId.value +"','"+ form.fname.value+"' , "+ form.lname.value+", '"+ form.Designation.value +"' )');
	
	
	}
	
	
	
	
	
	
	, errorCB, successCB);


}

// Populate the database 
//

    // tx.executeSql('DROP TABLE IF EXISTS DEMO');
       
     //tx.executeSql('INSERT INTO DEMO (id, data) VALUES (1, "First row")');
     //tx.executeSql('INSERT INTO DEMO (id, data) VALUES (2, "Second row")');


// Transaction error callback
//
function errorCB(tx, err) {
    alert("Error processing SQL: "+err);
}

// Transaction success callback
//
function successCB() {
    alert("success!");
}



