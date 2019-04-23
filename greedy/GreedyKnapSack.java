package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import utility.Customer;
import utility.Solution;
import utility.UsedCustomer;
import utility.Helper;


public class GreedyKnapSack {

    public static void main(String args[]) throws Exception {
        //get file name from arguments and collect data to save in list of users, and inventory value
        String filePath = args[0];
        Helper helper = new Helper();
        helper.collectData(filePath); 
        final int inventory = helper.getInventory();
        final List<Customer> customerList = helper.getCustomerList();

        List<UsedCustomer> result = new ArrayList<UsedCustomer>();
        greedyAproach(customerList, result, inventory, 0);

        Solution solution = new Solution(result);
        result = solution.getSolution();
        helper.printResult(result);
    }

    // if there are no more customers or impressions to sell we return
    public static void greedyAproach(List<Customer> customers, List<UsedCustomer> result, int totalImpressionsLeft, int totalRevenue) throws Exception {

     if(totalImpressionsLeft <= 0 || customers.size() <= 0){
        return;
     }

     //get next most profitable member in the list
     Customer mostProfitable = customers.get(0); 
     int mostProfitableSize = mostProfitable.getSize();
     int mostProfitablePricePerCampaign = mostProfitable.getPrice();
     int mostProfitableCounter = 0;

     //loop as long as we can sell more of the profitable one
     while(totalImpressionsLeft - mostProfitableSize > 0){
         mostProfitableCounter++;
         totalRevenue += mostProfitablePricePerCampaign;
         totalImpressionsLeft -= mostProfitableSize;
     }
     UsedCustomer usedCustomer = new UsedCustomer(mostProfitable, mostProfitableCounter);
     result.add(usedCustomer);

     //remove most profitable and call again;
     customers.remove(0); 
     greedyAproach(customers, result, totalImpressionsLeft, totalRevenue);   
    }

}
   