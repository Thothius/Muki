package Main;


public class Plan {
	
	
// The Plan class is used to create a new plan object. The object is then used to populate the Observable List called plans.
// Methods in this class are for getting and setting the plan properties. An Override toString() method is used to get the
// values of a plan that is selected in the plan list. 
// A plan has three value properties: A type, a description and a date, which are are stored as a string value.
	

	
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

@Override
// To get the value of a selected plan in the plan list. Used for plan deleting.
public String toString() {
	
	String fullPlan = type+";"+desc+";"+date;
	
  return fullPlan;
}



	
}
