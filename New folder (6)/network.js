


  var json ={
  "Device Coolers": [  
    {
      "location_id": "123,",
      "device_id": "123,",
      "start_time": "123,",
      "switch_on_after": "123,",
      "switch_on_for": "123,",
      "switch_off_after": "123,",
      "switch_off_for": "123,",
      "temperature": "123,",
      "end_time": "123,",
      "power_consumption": "123,",
      "number_of_units": "123"
    },
    {
      "location_id": "1234,",
      "device_id": "1234,",
      "start_time": "1234,",
      "switch_on_after": "1234,",
      "switch_on_for": "1234,",
      "switch_off_after": "1234,",
      "switch_off_for": "1234,",
      "temperature": "1234,",
      "end_time": "1234,",
      "power_consumption": "1234,",
      "number_of_units": "1234"
      
      
      
      
    }
  ],
  
  
  "Device Freezer": [
    {
      "location_id": "123,",
      "device_id": "123,",
      "start_time": "123,",
      "switch_on_after": "123,",
      "switch_on_for": "123,",
      "switch_off_after": "123,",
      "switch_off_for": "123,",
      "temperature": "123,",
      "end_time": "123,",
      "power_consumption": "123,",
      "number_of_units": "123"
    },
    {
      "location_id": "1234,",
      "device_id": "1234,",
      "start_time": "1234,",
      "switch_on_after": "1234,",
      "switch_on_for": "1234,",
      "switch_off_after": "1234,",
      "switch_off_for": "1234,",
      "temperature": "1234,",
      "end_time": "1234,",
      "power_consumption": "1234,",
      "number_of_units": "1234"
      
      
      
      
    }
  ]
};






function checkConnection() {

try{
    
    var networkState = navigator.connection.type;

            var states = {};
            states[Connection.UNKNOWN]  = 'Unknown connection';
            states[Connection.ETHERNET] = 'Ethernet connection';
            states[Connection.WIFI]     = 'WiFi connection';
            states[Connection.CELL_2G]  = 'Cell 2G connection';
            states[Connection.CELL_3G]  = 'Cell 3G connection';
            states[Connection.CELL_4G]  = 'Cell 4G connection';
            states[Connection.CELL]     = 'Cell generic connection';
            states[Connection.NONE]     = 'No network connection';

            alert('Connection type: ' + states[networkState]);
}catch(e){
     console.log('&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&'+e);
	alert(""+e);
}

}


 function getValue(){
            
               console.log(json); // this will show the info it in firebug console
			   
			   var temp = [] ;
			   var temptwo = [] ;
			   temp[0] = "hi"
			    temp[1] = "hello"
			   
			   var tempOne =  new person(temp);
			  
			   console.log(tempOne.getlocation_id()); 
			   
         };
					


 function  person (temp){
      /*location_id: "123,",
      device_id: "123,",
      start_time: "123,",
      switch_on_after: "123,",
      switch_on_for: "123,",
      switch_off_after: "123,",
      switch_off_for: "123,",
      temperature: "123,",
      end_time: "123,",
      power_consumption: "123,",
      number_of_units: "123"*/
	  
	  this.location_id =  temp[0];
	  this.device_id =  temp[1];
	  
	  
   
}
person.prototype.setlocation_id= function fn1(){
     return this.device_id ;
}

person.prototype.getlocation_id= function fn2(){
    return this.location_id ;
}
 
 
 getValue();