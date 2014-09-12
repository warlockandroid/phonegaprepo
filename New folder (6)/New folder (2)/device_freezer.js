


var device_freezer_Obj_Arr = [];

function  Device_Freezer (temp){
  	   this.location_id =  temp[0];
	   this.device_id =  temp[1];
	   this.start_time =  temp[2];
	   this.switch_on_after =  temp[3];
	   this.switch_on_for =  temp[4];
	   this.switch_off_after =  temp[5];
	   this.switch_off_for =  temp[6];
	   this.temperature =  temp[7];
	   this.end_time =  temp[8];
	   this.power_consumption =  temp[9];
	   this.number_of_units =  temp[10];
}
Device_Freezer.prototype.getLocation_id= function fn1(){
     return this.location_id ;
};

Device_Freezer.prototype.getDevice_id= function fn2(){
    return this.device_id ;
};

Device_Freezer.prototype.getStart_time= function fn3(){
    return this.start_time ;
};
 
 Device_Freezer.prototype.getSwitch_on_after= function fn4(){
    return this.switch_on_after ;
};

Device_Freezer.prototype.getSwitch_on_for= function fn5(){
    return this.switch_on_for ;
};
        
Device_Freezer.prototype.getSwitch_off_after= function fn6(){
    return this.switch_off_after ;
};  

Device_Freezer.prototype.getSwitch_off_for= function fn7(){
    return this.switch_off_for ;
};
Device_Freezer.prototype.getTemperature= function fn8(){
    return this.temperature ;
};
Device_Freezer.prototype.getEnd_time= function fn9(){
    return this.end_time ;
};
Device_Freezer.prototype.getPower_consumption= function fn10(){
    return this.power_consumption ;
};
Device_Freezer.prototype.getNumber_of_units= function fn11(){
    return this.number_of_units ;
};

