## 💼 Mutual Fund Account Management System (MFAMS)
MFAMS is a full-stack web application that allows users to register, invest in mutual funds, manage their portfolio, and view transaction history. Admins can manage mutual fund schemes, while users can buy/sell units and track performance. Built with Spring Boot, MySQL, React (Vite), and Tailwind CSS.

## 🎨 MFAMS Frontend
This is the frontend for the Mutual Fund Account Management System (MFAMS), built with React, Vite, and Tailwind CSS. It allows users to register, log in, view mutual funds, invest, and manage their portfolios through a responsive and secure interface.

🧰 Tech Stack
React.js (Vite)

Tailwind CSS

Axios

Zustand (for global state)

React Router DOM

JWT Authentication

🔧 Project Setup
Clone the frontend repository and navigate into it:

bash
git clone https://github.com/your-username/mfams-frontend.git
cd mfams-frontend
Install dependencies:

bash
npm install
Create a .env file with your backend URL:

bash
VITE_API_BASE_URL=http://localhost:8080/api
Run the development server:

bash
npm run dev
Visit http://localhost:5173 to access the frontend.

📁 Folder Structure
graphql
src/
├── assets/           # Static images
├── components/       # Reusable UI components
├── layouts/          # Page layouts (dashboard/auth)
├── pages/            # Route-level views
├── services/         # Axios API services
├── store/            # Zustand global state
├── routes/           # AppRoutes with ProtectedRoute logic
├── hooks/            # Custom React hooks
├── utils/            # Token handling, formatters
🔐 Role-Based Access
User can view, buy/sell funds, and track portfolio.

Admin can access /admin/add-fund to create funds.

ProtectedRoute ensures proper role access before rendering routes.



📦 Build for Production
bash
npm run build
Deploy the build folder to platforms like Netlify or Vercel.

────────────────────────────

📁 backend/README.md (Spring Boot + MySQL)

## 🛠️ MFAMS Backend
This is the backend for the Mutual Fund Account Management System (MFAMS), built with Spring Boot. It provides REST APIs for user registration, authentication, mutual fund management, transaction handling, and portfolio summary.

⚙️ Tech Stack
Spring Boot 3.x

Spring Security with JWT

Spring Data JPA + Hibernate

MySQL

Swagger (springdoc-openapi)

Scheduler (for NAV updates)

🚀 Getting Started
Clone the backend repository:

bash
git clone https://github.com/your-username/mfams-backend.git
cd mfams-backend
Configure MySQL in src/main/resources/application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/mfams_db?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=yourpassword
Run the Spring Boot application:

bash
./mvnw spring-boot:run
By default, the server runs on http://localhost:8080

🔐 JWT Authentication
Users register/login to receive a JWT token.

Protected endpoints require Bearer token.

Roles supported: USER and ADMIN

To manually promote a user to admin:

sql
UPDATE users SET role = 'ADMIN' WHERE email = 'admin@example.com';
📚 API Documentation
Access Swagger UI at:

bash
http://localhost:8080/swagger-ui/index.html
🗂️ Key Endpoints
Method	Endpoint	Description
POST	/api/auth/register	Register user
POST	/api/auth/login	Login user
GET	/api/funds	View all mutual funds
POST	/api/funds	Admin adds new fund
POST	/api/transactions/buy	Buy fund (User)
POST	/api/transactions/sell	Sell fund (User)
GET	/api/transactions	Get user transactions
GET	/api/transactions/portfolio	View portfolio summary

## ⏰ NAV Scheduler
NAV updates every 30 seconds via a scheduled task that adjusts NAV by a random percentage for demo purposes.

## 🔐 CORS Config
Configured to allow requests from frontend running on port 5173.

## 📦 Build
bash
mvn clean install
🧪 Test Users
Use Postman or frontend to test flows.

## 📌 Future Enhancements
Pagination for transactions

Filtering/sorting of portfolio

Token refresh mechanism

Unit & integration tests

Chart-based NAV tracking

## 🙋 Author
Developed by Mayur Bagul
