package utility;


import java.util.ArrayList;
import java.util.List;


public class Solution {

	private List<UsedCustomer> resultList = new ArrayList<UsedCustomer>();

	public Solution(List<UsedCustomer> result){
		this.resultList = result;
	}

	public List<UsedCustomer> getSolution(){
		return this.resultList;
	}


}