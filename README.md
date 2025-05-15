# Price Comparator - Market

 Backend application for comparing grocery prices across multiple supermarket chains. 
The system allows users to track price changes, find the best deals, and manage their shopping lists effectively.

## Planned features

* **Daily Shopping Basket Monitoring** 
Help users split their basket into shopping lists that optimise for cost savings

* **Best Discounts**
List products with the highest current percentage discounts across all tracked
stores

* **New Discounts**
List discounts that have been newly added (e.g., within the last 24 hours)

* **Dynamic Price History Graphs**
Provide data points that would allow a frontend to calculate and display price
trends over time for individual products.
This data should be filterable by store, product category, or brand.

* **Product Substitutes & Recommendations**
Highlight "value per unit" (e.g., price per kg, price per liter) to help identify the
best buys, even if the pack size differs.

* **Custom Price Alert**
Allow users to set a target price for a product. 
The system should be able to identify when a product's price drops to or below that target.

## Tech stack
* Java 21.0.6
* Spring Boot 3.4.5
* Maven 3.9.9
* CSV files for data

## Project structure overview
- src/
	- main/
		- java/
			- com.accesa.price_comparator/
				- controller
				- domain
				- repository
				- service
				- PriceComparatorApplication.java
		- resources/ 
			- application.properties
			- csv/
				- storename_date.csv (e.g., lidl_2025-05-08.csv,kaufland_2025-05-08.csv)
- pom.xml
- README.md

## Setup instructions

**1. Clone the repository**
`git clone https://github.com/iased/price-comparator.git`
`cd price-comparator`

**2. Install dependencies**
Make sure you have Java 21 and Maven installed. You can check Java and Maven versions by running:
`java -version`
`mvn -v`
If they are not installed, install them from their official websites

**3. Import the project into your IDE**
You can import the project into your favorite IDE (e.g., IntelliJ IDEA, Eclipse, Visual Studio Code) as a Maven project

**4. Add CSV data files**
Place the files inside the `src/main/resources/data` folder
Note: Sample CSV files are already included in the repository. If missing ensure you've cloned the full repo.

**5. Run the application**
`mvn spring-boot:run`
Alternatively, run PriceComparatorApplication.java directly from your IDE.


