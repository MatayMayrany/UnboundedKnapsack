package utility;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;


public class Helper {

	private List<Customer> customerList =  new ArrayList<Customer>();
	private int inventory; 

	public void collectData(String path) throws Exception {
	    // read file line by line and save customer objects to list
		BufferedReader reader = new BufferedReader(new FileReader(path));
		String line = null;
		String[] customerInfo = new String[3];
		line = reader.readLine();
		inventory = Integer.parseInt(line);
		while ((line = reader.readLine()) != null) {
			customerInfo = line.split(",");
			if(customerInfo.length != 3){
				System.err.println("Invalid Data Format !");
			}
			Customer customer = new Customer(customerInfo[0], Integer.parseInt(customerInfo[1]), Integer.parseInt(customerInfo[2]));
			customerList.add(customer);
		} 
		reader.close();	
			//sort the list based on the profitability of each customer (Price per impression)
		Comparator<Customer> comparator = Comparator.comparing(e -> e.getProfitability());
		customerList.sort(comparator.reversed());
	}

	public List<Customer> getCustomerList(){
		return this.customerList;
	}

	public int getInventory(){
		return this.inventory;
	}

	public void printResult(List<UsedCustomer> usedCustomerList){
		int totalProfit = 0;
       	int totalInventorySold = 0; 
        for(int i = 0; i < usedCustomerList.size(); i++) { 
		    System.out.println(usedCustomerList.get(i).toString());
		    totalProfit += usedCustomerList.get(i).getTotalPaid();
		    totalInventorySold += usedCustomerList.get(i).getTotalBought();
		}  
		System.out.println(totalInventorySold + ", " + totalProfit);
	}

}