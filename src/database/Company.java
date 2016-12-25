package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Company {
	private int id;
	private String name;
	private double marketValue;
	private double lastValue;
	private double currentValue;
	private String ticker;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getMarketValue() {
		return marketValue;
	}
	
	public void setMarketValue(double marketValue) {
		this.marketValue = marketValue;
	}
	
	public double getLastValue() {
		return lastValue;
	}
	
	public void setLastValue(double lastValue) {
		this.lastValue = lastValue;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public double getCurrentValue() {
		return currentValue;
	}
	
	public void setCurrentValue(double currentValue) {
		this.currentValue = currentValue;
	}
	
	public String toString() {
		return ("Name of Company: " + this.name + ", Last stock price: " + this.lastValue + ", Company Cap: " + this.marketValue + ", ID: " + this.id);
	}

	public static ArrayList<Company> getCompanies() throws Exception {
	    ArrayList<Company> companyList = new ArrayList<Company>();
	    try {
	    	Connection conn = null;
	    	conn = SqlConnection.getConnection("jdbc:mysql://localhost:3306/stocks");
	    	Statement stmt = conn.createStatement();
	        ResultSet result = stmt.executeQuery("SELECT companies.companies_id, companies.Companies, companies.ticker, companies.marketcap, stockvalue.lastprice FROM stocks.companies, stocks.stockvalue WHERE companies.companies_id = stockvalue.stockvalue_id");
	        while (result.next()) {
	            Company company = new Company();
	            company.setId(result.getInt("companies_id"));
	            company.setName(result.getString("Companies"));
	            company.setMarketValue(result.getDouble("marketcap"));
	            company.setLastValue(result.getDouble("lastprice"));
	            company.setTicker(result.getString("ticker"));
	            companyList.add(company);
	        }
	    }
	    catch (SQLException e) {
	        System.out.println(e.getMessage());
	    }
	    return companyList;
	    
	}
	
}
