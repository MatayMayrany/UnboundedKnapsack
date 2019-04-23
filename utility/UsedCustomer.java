package utility;

import java.util.*;
import java.io.*;

public class UsedCustomer {
	Customer customer; 
	int counter;

	public UsedCustomer(Customer customer, int counter){
		this.customer = customer;
		this.counter = counter;
	}	

	//returns (name, count, total size taken by this company, total money earned from this company)
	public String toString(){
    	return (this.customer.getName() + ", " + this.counter + ", " + (counter * customer.getSize()) + ", " + (counter * customer.getPrice()));
	}    

	public int getTotalBought(){
		return (counter*this.customer.getSize());
	}

	public int getTotalPaid(){
		return (counter*this.customer.getPrice());
	}
}