# Price Comparator

A web application for comparing grocery prices across multiple supermarket chains, helping users identify the best deals, active discounts, and optimize shopping baskets across stores.

## Tech stack

- Java 17
- Spring Boot 3.5.6
- Maven 3.9.11
- Angular 19.2.3
- PostgreSQL 15 (via Docker)
- Docker: For full application containerization (backend, frontend build and database)
- Docker Compose – service orchestration
- Flyway – database schema versioning and migrations

## Setup instructions

### Prerequisites
Ensure you have the following installed:

- **Git**: For cloning the repo.

Check with `git --version`.
- **Docker and Docker Compose**: Docker Desktop is recommended

Check with:

`docker --version` 

`docker compose version`

(or `docker-compose version` for Compose v1)

You do **not** need local Java, Maven, Node.js, or npm installations.\
Docker handles building and running everything in containers.

### 1. Clone the repository

`git clone https://github.com/iased/price-comparator.git` 

`cd price-comparator`

### 2. Database and Environment setup

The PostgreSQL database is containerized and managed via Docker Compose.\
Demo credentials are provided for easy startup.

Copy `.env.example` to `.env`:

- Linux/macOS: `cp .env.example .env`

- Windows PowerShell: `copy-item .env.example .env`

- Windows cmd: `copy .env.example .env`

The `.env.example` file contains demo credentials that match the fallbacks in `docker-compose.yml` and `application.properties`, allowing the application to run without further changes. 

### 3. Build and Run the Entire Application with Docker

This builds the Spring Boot backend (which serves the Angular frontend as static files) and starts both the application and PostgreSQL containers.

From the project root (where `docker-compose.yml` is located):
    
`docker compose up --build -d`   

(or `docker-compose up --build -d` for Compose v1)

The first build may take a few minutes due to dependency downloads.

### 4. Access the Application

**Full Application (frontend served by backend)**:\

 Open `http://localhost:8080` in your browser.

### 5. Stopping and Cleanup
From the project root:

- **Stop containers (keep database data)**: 

`docker compose down`
- **Stop containers and reset everything (wipe database):** 

`docker compose down -v`

## Demo Data & First Run

On first startup, the database schema is automatically created using Flyway migrations.

The application includes demo data (products, supermarkets, prices, and discounts), allowing all features to be tested immediately without manual data entry.

If the database volume already exists and you want to reinitialize everything:

`docker compose down -v`

`docker compose up --build`

## Authentication

Some features, such as price alerts and grocery lists, require authentication.

Users can create a new account directly from the application interface.
No email confirmation is required for demo purposes.

## Project structure overview
- price-comparator/
    - backend/
        - src/
	        - main/
		        - java/
			        - com.uo.price_comparator/
				        - PriceComparatorApplication.java
		        - resources/ 
			        - application.properties
            - test/
                - java/
                    - com.uo.price_comparator/
                        - PriceComparatorApplicationTests.java
        - .mvn/
        - mvnw
        - mvnw.cmd
        - pom.xml
    - frontend/
    - .env.example
    - .gitignore
    - docker-compose.yml
    - Dockerfile
    - LICENSE
    - README

## Core functionality

- Fully containerized setup: Spring Boot backend serves the built Angular frontend, connected to PostgreSQL via Docker
- Database persistence via Docker volumes
- Product price comparison across multiple supermarkets
- Best price detection (including active discounts)
- Discount listing (Today / This Week)
- Search and filtering by product name, category, and store
- User authentication and account management using JWT (profile update, account deletion)
- Grocery list creation with basket optimization, grouping items by store to minimize overall shopping cost
- Price alerts for user-defined target prices
