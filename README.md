# Price Comparator

A web application for comparing grocery prices across multiple supermarket chains. 

## Tech stack

- Java 17
- Spring Boot 3.5.6
- Maven 3.9.11
- Angular 19.2.3
- PostgreSQL 15 (via Docker)
- Docker: For full application containerization (backend, frontend build and database)
- Docker Compose: For orchestrating services

## Setup instructions

### Prerequisites
Ensure you have the following installed:

- **Git**: For cloning the repo.

Check with `git --version`.
- **Docker and Docker Compose**: Docker Desktop is recommended, as it includes Compose. 

Check with:
`docker --version` and 

\# Docker Compose v2+\
`docker compose version`

\# Docker Compose v1:\
`docker-compose version`

You do not need local Java, Maven, Node.js, or npm installations; Docker handles building and running everything in containers.

### 1. Clone the repository

`git clone https://github.com/iased/price-comparator.git` 

`cd price-comparator`

### 2. Database and Environment setup

The PostgreSQL database is containerized and managed via Docker Compose, with demo credentials (username: `postgres`, password: `postgres`) as fallbacks for quick starts.

1. Copy `.env.example` to `.env`:

Linux/macOS: `cp .env.example .env`

Windows PowerShell: `copy-item .env.example .env`

Windows cmd.exe: `copy .env.example .env`

`.env.example` includes demo credentials that match the fallbacks in `docker-compose.yml` and `application.properties`. This enables the app to run without further changes. 

### 3. Build and Run the Entire Application with Docker

This builds the backend (Spring Boot JAR with embedded Angular static files) and starts both the application and PostgreSQL containers.

1. From the project root (contains `docker-compose.yml`), build and start:
    
    \# Docker Compose v2+\
    `docker compose up --build -d`   

    \# Docker Compose v1\
    `docker-compose up --build -d`

2. Wait for the build and startup. This may take a few minutes the first time due to dependency downloads.

### 4. Access the Application

**Full App (Frontend served by Backend)**:\
 Open `http://localhost:8080` in your browser.

  The Angular app is built and served as static files from Spring Boot.

### 5. Stopping and Cleanup
From the project root:

- **Stop containers (keep data)**: 

`docker compose down`
- **Reset containers and remove volumes (wipe DB)**: 

`docker compose down -v`

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
**Current Implementation**

Fully containerized setup: Spring Boot backend serves the built Angular frontend, connected to PostgreSQL via Docker.\
Secure configuration via `.env` file.\
Database persistence via Docker volumes.

**Planned Features**

RESTful APIs for retrieving product information, discounts, and best-buy recommendations.\
Price alerts for user-defined target prices.\
Basket optimization to group items by store for cost-effective shopping.
