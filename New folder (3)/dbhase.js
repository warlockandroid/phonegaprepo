function insertToDb(){
   
    var db = openDatabase('dbase', '1.0', 'DB', 5000);
db.transaction(function (tx) {  
   tx.executeSql('CREATE TABLE IF NOT EXISTS check (id unique, first_name, last_name, designnation)');
   tx.executeSql('INSERT INTO check (id , first_name, last_name, designnation) VALUES (1, "foobar" , "bar" ,  "sys")');


tx.executeSql('INSERT INTO check (id , first_name, last_name, designnation) VALUES (2, "obar" , "barcca" ,  "systee")');


});

}