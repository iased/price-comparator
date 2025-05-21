# Price Comparator - Market

Backend application for comparing grocery prices across multiple supermarket chains. 
The system allows users to track price changes, find the best deals, and manage their shopping lists effectively

## Tech stack
* Java 21.0.6
* Spring Boot 3.4.5
* Maven 3.9.9
* CSV files for data

## Setup instructions

### **1. Clone the repository**

`git clone https://github.com/iased/price-comparator.git` \
`cd price-comparator`

### **2. Install dependencies**

Make sure you have Java 21 and Maven installed. You can check Java and Maven versions by running:\
`java -version`\
`mvn -v`\
If they are not installed, install them from their official websites

### **3. Import the project into your IDE**

You can import the project into your favorite IDE (e.g., IntelliJ IDEA, Eclipse, Visual Studio Code) as a Maven project

### **4. Add CSV data files**
Place CSV files inside the following folder:\
`src/main/resources/csv`\
Note: Sample CSV files are already included in the repository. If missing ensure you've cloned the full repo.

### **5. Run the application**
`mvn spring-boot:run`\
Alternatively, run PriceComparatorApplication.java directly from your IDE.

## Project structure overview
- src/
	- main/
		- java/
			- com.accesa.price_comparator/
				- controller
				- domain
				- dto
				- model
				- repository
				- service
				- PriceComparatorApplication.java
		- resources/ 
			- application.properties
			- csv/
				- storename_date.csv (e.g., lidl_2025-05-08.csv, kaufland_2025-05-08.csv)
				- storename_discounts_date.csv (e.g., lidl_discounts_2025-05-08.csv, kaufland_discounts_2025-05-08.csv)

- pom.xml
- README.md

## Core functionality
This application parses product and discount data from CSV files and links them using in-memory maps based on a composite key of productId and store. It exposes RESTful endpoints that allow retrieval of product information, all discounts, best discounts and newly added discounts. The data model separates products and discounts for modularity and scalability, while a central service efficiently binds them together for query operations.

## API Endpoints
### **Get all products**
`GET /api/products`  
Returns a list of all available products across all stores.


### **Get all discounts**
`GET /api/discounts`  
Returns a list of all discounts available across all stores.


### **Get Product details**
`GET /api/market/{store}/{productId}`\
Returns the full product information for the specified store and productId.


### **Get all discounts for a product**
`GET /api/market/{store}/{productId}/discounts`\
Returns **all available discounts** for the specified product in the given store, regardless of date or time.


### **Get best discounts**
`GET /api/market/best-discounts`\
Returns a list of products with the **highest percentage discounts** currently available across all tracked stores, **along with their corresponding best discount**.


### **Get new discounts**
`GET /api/market/new-discounts`\
Returns a list of discounts that have been newly added (e.g., within the last 24 hours).

## Upcoming features

* **Daily Shopping Basket Monitoring**\
Help users split their basket into shopping lists that optimise for cost savings


* **Dynamic Price History Graphs**\
Provide data points that would allow a frontend to calculate and display price
trends over time for individual products.
This data should be filterable by store, product category, or brand.


* **Product Substitutes & Recommendations**\
Highlight "value per unit" (e.g., price per kg, price per liter) to help identify the
best buys, even if the pack size differs.


* **Custom Price Alert**
Allow users to set a target price for a product. 
The system should be able to identify when a product's price drops to or below that target.

