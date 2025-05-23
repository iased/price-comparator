# Price Comparator - Market

Backend application for comparing grocery prices across multiple supermarket chains. 
The system allows users to track price changes, find the best deals, and manage their shopping lists effectively.

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
If they are not installed, install them from their official websites.

### **3. Import the project into your IDE**

You can import the project into your favorite IDE (e.g., IntelliJ IDEA, Eclipse, Visual Studio Code) as a Maven project.

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
This application parses product and discount data from CSV files and links them using in-memory maps based on a composite key of `productId` and `store`. It exposes a set of RESTful API endpoints for:
* Retrieving product information and associated discounts
* Getting the best currently available discounts
* Identifying newly added discounts
* Viewing historical price trends for individual products
  * Provides timestamped price data (including discounted prices) per product
  * Allows filtering by store
* Enabling users to find the best buy products by name (with optional brand filtering) to help compare prices effectively regardless of pack size
  * Highlights "value per unit" (e.g., price per kg, price per liter)
  * Allows filtering by product brand
* Creating and monitoring price alerts
  * Users can set a target price for a product
  * The system checks for active discounts and alerts users when the target price is reached
  
The data model separates products and discounts for modularity and scalability, while a central service efficiently binds them together for query operations.

## API Endpoints
### **Get all products**
`GET /api/products`  
Returns a list of all available products across all stores.


### **Get all discounts**
`GET /api/discounts`  
Returns a list of all discounts available across all stores.


### **Get product details**
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

### **Get price history points for a product**
`GET /api/market/{productId}/price-history`\
Returns the price history for a specific product across all stores.

Optional query parameter `store` can be used to filter by a specific store:\
`GET /api/market/{productId}/price-history?store={store}`

### **Get best-buy recommendation for a product**
`GET /api/products/{name}/best-buy`\
Returns the product with the best price per unit for the given product name, across all brands.

Optional query parameter `brand` can be used to filter by a specific product brand:\
`GET /api/products/{name}/best-buy?brand={brand}`

### **Create a new price alert**
`POST /api/price-alerts`\
Creates a new price alert for a specific product and store. The alert is triggered when the product’s current or discounted price falls below the specified target price.\
Request body:\
`{`\
`"productId": "string",`\
`"store": "string",`\
`"targetPrice": double`\
`}`

### **Check for triggered alerts**
`GET /api/price-alerts/check`\
Checks all stored price alerts and returns the ones that have been triggered based on current or discounted product prices. An alert is marked as triggered once the condition is met.\

Response:\
Returns a list of triggered PriceAlert objects.


## Upcoming features

* **Daily Shopping Basket Monitoring**\
Help users split their basket into shopping lists that optimise for cost savings
