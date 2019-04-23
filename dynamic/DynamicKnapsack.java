package dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utility.Customer;
import utility.Solution;
import utility.UsedCustomer;
import utility.Helper;


public class DynamicKnapsack {

    public static void main(String args[]) throws Exception {
    	//get file name from arguments and collect data to save in list of users
    	String filePath = args[0];
    	Helper helper = new Helper();
    	helper.collectData(filePath); 
    	final int inventory = helper.getInventory();
    	final List<Customer> customerList = helper.getCustomerList();

       	Solution solution = dynamicAproach(customerList, inventory);
       	List<UsedCustomer> usedCustomerList = solution.getSolution();

       	//Printing the output
       	helper.printResult(usedCustomerList);
    }


    /* 
     * Solve for the maximum combo by using information about the combos with a more limited inventory
	 * check that it fits in the our left inventory, then if it is better to 
    */
    public static Solution dynamicAproach(List<Customer> customers, int inventory){
    	/* 
    	 * the valueAtCapacity array stores the maximum value achieved at each maximum capacity.
    	 * the customerTracker array stores the index of what elements were added at each maximum capacity, -1 otherwise.
    	 */
    	int[] valueAtCapacity = new int[inventory + 1];
    	int[] customerTracker = new int[inventory + 1];
    	Arrays.fill(customerTracker, -1);
    	/*
		 * for every Item we check that the campaign fits the inventory and which of the 2 options is higher:
    	 * 1. selling another campaign to current customer + maximum value achieved at the capacity we have after delivery
    	 * 2. using the value we already have at this capacity
		*/
    	for(int i = 0; i<customers.size(); i++){
    		Customer currentCustomer = customers.get(i);
    		int currentSize = currentCustomer.getSize();
    		int currentPrice = currentCustomer.getPrice(); 

    		for(int j = currentSize; j <= inventory; j++){
    			int option1 = currentPrice + valueAtCapacity[j-currentSize];
    			int option2 = valueAtCapacity[j]; 
    			if(option1 >= option2){
    				valueAtCapacity[j] = option1; //add it as a max value contributor at the current capacity(c)
    				customerTracker[j] = i; // add it's index at the capacity index in the tracker to indicate it was used to get there.
    			}
    		}
    	}
    	//We now use the information we collected to build our solution 
    	List<UsedCustomer> usedCustomers = new ArrayList<UsedCustomer>();
    	int[] countAtEachIndex = countUsesPerCustomer(customers, inventory, customerTracker);
    	for(int i = 0; i < customers.size(); i++){
    		UsedCustomer usedCustomer = new UsedCustomer(customers.get(i), countAtEachIndex[i]);
    		usedCustomers.add(usedCustomer);
    	}
    	Solution solution = new Solution(usedCustomers);
    	return solution;

    }



    /*
		 * We go through the customer tracker array starting at the max inventory
		 * if the values are negative no item was added there 
		 * if we find a positive entry it is the index of the item used 
		 * we incrment the counter at its index and go down in inventory by it's size
	*/
	public static int[] countUsesPerCustomer(List<Customer> customers, int inventory, int[] customerTracker){	
		int[] countAtEachIndex = new int[customers.size() + 1];
		int leftOverInventory = inventory;
		while(leftOverInventory > 0){
			int indexToIncrement = customerTracker[leftOverInventory]; 
			while (indexToIncrement < 0 && leftOverInventory > 0) {
					leftOverInventory--;
					indexToIncrement = customerTracker[leftOverInventory];
				
			}
			if (indexToIncrement >= 0 && leftOverInventory > 0) {
				countAtEachIndex[indexToIncrement] += 1;
				leftOverInventory -= customers.get(indexToIncrement).getSize();
			}
		}
		return countAtEachIndex;
	}
}

