package Main;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ObservableValue;

public class InputEater {


	static List<String> planList = new ArrayList<String>();
	static String emptyPlanList = "No Plans";
	static String newPlan ="";

	public static void GetPlan (String result) {

		planList.add(result);
		System.out.println("InputEater: A plan was added to the plan list.");

	}

	public static String Update (){


		if (planList.isEmpty()) {
			System.out.println("InputEater: There are no plans in the plan list.");
			return emptyPlanList;
		} 
			 newPlan = planList.get(0);
			//System.out.println("InputEater: A plan was found in the plan list!  " + newPlan);
		

		return newPlan;

	}



}
