function validateForm(form){
    var numbers = /^[-+]?[0-9]+$/;  
    var letters = /^[a-zA-Z]+$/; 
      if(!form.empId.value.match(numbers))  
      {    
		alert('Please enter positive natural numbers');  
           form.empId.focus();
		return false;
      }  

	 
      else  
      if(!form.fname.value.match(letters))  
      {  
      	alert('First name should contain alphabets only');  
           form.fname.focus();
		return false;

      	
      }  
       else 
    	  if(!form.lname.value.match(letters))  
      {  
      	alert('Last name should contain alphabets only');  
           form.lname.focus();
		return false;

      	
      } else{

                 insertToDb(form);
			return true;
            }

}



function resetForm(form){
   form.reset();
   
}
