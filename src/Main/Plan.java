package Main;

import java.time.LocalDate;

public class Plan {
	
private String type;
private String desc;
private String date;

public Plan(){
	
	this.type="";
	this.desc="";
	this.date = "";
}

public Plan(String type, String desc, String date){
	
	
	this.type= type;
	this.desc= desc;
	this.date = date;

}


// GET AND SET TYPE
public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}

//GET AND SET DESC
public String getDesc() {
	return desc;
}
public void setDesc(String desc) {
	this.desc = desc;
}

//GET AND SET DATE
public String getDate() {
	return date;
}
public void setDate(String selectedDate) {
	this.date = selectedDate;
}





	
}
