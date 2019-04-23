package utility;

import java.util.*;
import java.io.*;


public class Customer {
	public String name;
	int campaignSize;
	int pricePerCampaign;
	double pricePerImpression;

	public Customer(String name, int campaignSize, int pricePerCampaign){
		this.name =  name;
		this.pricePerCampaign = pricePerCampaign;
		this.campaignSize = campaignSize;
		this.pricePerImpression = (double) pricePerCampaign/campaignSize;
	} 


    public double getProfitability(){
    	return pricePerImpression;
	}  

	public int getPrice(){
    	return pricePerCampaign;
	} 

	public int getSize(){
    	return campaignSize;
	} 
	public String getName(){
    	return name;
	} 

	public String toString(){
    	return "Company Name: " + name + ", Campaign Size: " + campaignSize + ", Price: " + pricePerCampaign;
	}    


}