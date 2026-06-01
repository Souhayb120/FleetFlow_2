🚚 FleetFlow – Fleet Management System


FleetFlow is a fleet management application designed to simplify the management of vehicles, chauffeurs, clients, and deliveries. It helps businesses track operations efficiently and organize logistics in a structured way.

⚙️ Main Features
👤 Client Management: Create and manage client information
🚗 Vehicle Management: Track and assign vehicles
🧑‍✈️ Chauffeur Management: Manage drivers and their assignments
📦 Delivery Management: Handle deliveries and link them with clients, vehicles, and chauffeurs
🏗️ Tech Stack
Backend: Java (Spring Boot)
Database: MySQL
ORM: JPA / Hibernate
Migration Tool: Flyway
Containerization: Docker & Docker Compose
🚀 Getting Started
Prerequisites
Java 21
Maven
Docker & Docker Compose
Installation
Clone the repository:
git clone https://github.com/your-username/fleetflow.git
cd fleetflow
Build the project:
mvn clean install
Run with Docker:
docker-compose up --build
🗂️ Project Structure
FleetFlow/
│── src/
│   ├── controllers/
│   ├── services/
│   ├── repositories/
│   ├── models/
│   ├── DTO/
│   └── mapper/
│── resources/
│   ├── application.properties
│   └── db/migration (Flyway scripts)
│── Dockerfile
│── docker-compose.yml
🔄 Database Migration

FleetFlow uses Flyway for database versioning.

Migration files are located in:
src/main/resources/db/migration
Example:
V1__init.sql
V2__add_relations.sql
📡 API Endpoints (Examples)
POST /clients → Create a client
GET /clients → Get all clients
POST /chauffeurs → Add chauffeur
POST /vehicules → Add vehicle
POST /livraisons → Create delivery
🎯 Goal of the Project

The goal of FleetFlow is to provide a scalable and modular backend system for managing logistics operations, improving efficiency, and reducing manual work.

👨‍💻 Authors
Souhayb Hadi
Ayoub Hadi
📄 License

This project is for educational purposes.
