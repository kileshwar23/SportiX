# 🏏 SportiX — Fantasy Cricket Platform Backend

> A production-ready REST API backend for a Dream11-like fantasy cricket platform, built with Spring Boot, JWT Security, Apache Kafka, and PostgreSQL.

---

## 📌 Table of Contents

- [About the Project](#about-the-project)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Features](#features)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Notification Flow](#notification-flow)
- [Getting Started](#getting-started)
- [Environment Configuration](#environment-configuration)
- [Running Tests](#running-tests)
- [Future Improvements](#future-improvements)

---

## 📖 About the Project

**SportiX** is a backend system for a fantasy cricket platform where users can:
- Register and login securely
- Browse and join cricket contests by paying an entry fee from their wallet
- Leave contests and get automatic refunds
- Receive real-time email notifications for every action via Apache Kafka + Gmail SMTP

This project follows a clean **layered architecture** (Controller → Service → Repository) and is designed to be **microservice-ready**.

---

## 🛠 Tech Stack

| Technology | Purpose |
|---|---|
| **Java 17** | Core language |
| **Spring Boot 3.2** | Application framework |
| **Spring Security** | Authentication & Authorization |
| **JWT (jjwt 0.12.3)** | Stateless token-based auth |
| **Spring Data JPA + Hibernate** | ORM & database operations |
| **PostgreSQL (Supabase)** | Cloud-hosted relational database |
| **Apache Kafka** | Async event-driven notification pipeline |
| **JavaMailSender (SMTP)** | HTML email delivery via Gmail |
| **Lombok** | Boilerplate reduction |
| **H2 Database** | In-memory DB for testing |
| **Maven** | Build & dependency management |

---

## 🏗 Architecture

```
Client Request
      ↓
Spring Security Filter Chain (JWT Validation)
      ↓
REST Controller Layer
      ↓
Service Layer (Business Logic)
      ↓
Repository Layer (JPA)
      ↓
PostgreSQL (Supabase)

Side Channel (Async):
Service Layer → Kafka Producer → Kafka Topic
                                      ↓
                             Kafka Consumer → EmailService → Gmail SMTP → User Inbox
```

---

## ✨ Features

### Authentication
- ✅ User registration with role assignment (USER / ADMIN)
- ✅ Login with JWT token generation
- ✅ Password hashing with BCrypt
- ✅ Role-based access control (`@PreAuthorize`)
- ✅ Password reset with token expiry

### Contest Management
- ✅ Create, update, delete contests (ADMIN only)
- ✅ Join contest with wallet balance validation
- ✅ Leave contest with automatic refund
- ✅ Check eligibility before joining
- ✅ Pagination and filtering by category, entry fee
- ✅ Prevent duplicate joins and joining started contests

### Match Management
- ✅ Create, update, delete matches (ADMIN only)
- ✅ Filter by status: SCHEDULED, LIVE, COMPLETED

### Wallet System
- ✅ ₹1000 starting balance for new users
- ✅ Auto-deduct on contest join
- ✅ Auto-refund on contest leave

### Notifications (Kafka + Email)
- ✅ Welcome email on registration
- ✅ Contest joined confirmation email
- ✅ Contest left + refund confirmation email
- ✅ Contest created/deleted notifications
- ✅ Fully async — never blocks main thread

---

## 📁 Project Structure

```
src/main/java/com/example/dream11backend/
├── config/
│   ├── JpaConfig.java          # JPA Auditing + Repository config
│   ├── KafkaTopicConfig.java   # Kafka topic creation
│   └── SecurityConfig.java     # Spring Security filter chain
├── controller/
│   ├── AuthController.java     # /api/auth/**
│   ├── ContestController.java  # /api/contests/**
│   ├── MatchController.java    # /api/matches/**
│   └── UserController.java     # /api/users/**
├── dto/
│   ├── LoginRequest.java
│   ├── RegisterRequest.java
│   └── RegisterResponse.java
├── entity/
│   ├── Contest.java
│   ├── Match.java
│   ├── Role.java
│   └── User.java
├── exception/
│   ├── GlobalExceptionHandler.java
│   ├── ContestFullException.java
│   ├── ContestNotFoundException.java
│   ├── InsufficientBalanceException.java
│   └── ...more custom exceptions
├── kafka/
│   ├── NotificationEvent.java    # Event payload DTO
│   ├── NotificationProducer.java # Publishes events to Kafka
│   └── NotificationConsumer.java # Consumes events, sends emails
├── repository/
│   ├── ContestRepository.java
│   ├── MatchRepository.java
│   ├── RoleRepository.java
│   └── UserRepository.java
├── security/
│   ├── CustomUserDetailsService.java
│   ├── JwtAuthenticationEntryPoint.java
│   ├── JwtAuthenticationFilter.java
│   └── JwtUtil.java
├── service/
│   ├── AuthService.java
│   ├── ContestService.java         # Interface
│   ├── ContestServiceImpl.java     # Implementation
│   ├── EmailService.java
│   ├── MatchService.java           # Interface
│   ├── MatchServiceImpl.java       # Implementation
│   ├── UserService.java
│   └── dto/
│       ├── ContestCreateRequest.java
│       ├── ContestResponse.java
│       ├── ContestUpdateRequest.java
│       ├── EligibilityResponse.java
│       └── UserResponse.java
└── Dream11backendApplication.java
```

---

## 🔗 API Endpoints

### Auth
| Method | Endpoint | Access | Description |
|---|---|---|---|
| POST | `/api/auth/register` | Public | Register new user |
| POST | `/api/auth/login` | Public | Login and get JWT |

### Contests
| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/contests` | Public | Get all contests (paginated) |
| GET | `/api/contests/active` | Public | Get active contests |
| GET | `/api/contests/{id}` | Public | Get contest by ID |
| GET | `/api/contests/category/{category}` | Public | Filter by category |
| GET | `/api/contests/my` | User | Get my joined contests |
| GET | `/api/contests/{id}/eligibility` | User | Check join eligibility |
| GET | `/api/contests/{id}/participants` | User | Get participants list |
| POST | `/api/contests` | Admin | Create contest |
| POST | `/api/contests/{id}/join` | User | Join contest |
| POST | `/api/contests/{id}/leave` | User | Leave contest |
| PUT | `/api/contests/{id}` | Admin | Update contest |
| DELETE | `/api/contests/{id}` | Admin | Delete contest |

### Matches
| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/matches` | Public | Get all matches |
| GET | `/api/matches/upcoming` | Public | Get scheduled matches |
| GET | `/api/matches/live` | Public | Get live matches |
| GET | `/api/matches/{id}` | Public | Get match by ID |
| POST | `/api/matches` | Admin | Create match |
| PUT | `/api/matches/{id}` | Admin | Update match |
| DELETE | `/api/matches/{id}` | Admin | Delete match |

### Users
| Method | Endpoint | Access | Description |
|---|---|---|---|
| GET | `/api/users/profile` | User | Get own profile |
| GET | `/api/users` | Admin | Get all users |
| PUT | `/api/users/{id}` | User | Update own profile |
| DELETE | `/api/users/{id}` | Admin | Delete user |

---

## 📨 Notification Flow

```
User joins contest
       ↓
ContestServiceImpl.joinContest()
       ↓
NotificationProducer.notifyContestJoined()
       ↓
Kafka Topic: dream11-notifications (3 partitions)
       ↓
NotificationConsumer.consume()
       ↓
EmailService.sendContestJoinedEmail()  [@Async]
       ↓
Gmail SMTP (port 587, STARTTLS)
       ↓
HTML Email in User's Inbox ✉️
```

**Kafka Events:**

| Event | Trigger |
|---|---|
| `USER_REGISTERED` | New user signup |
| `CONTEST_JOINED` | User joins a contest |
| `CONTEST_LEFT` | User leaves a contest |
| `CONTEST_CREATED` | Admin creates a contest |
| `CONTEST_DELETED` | Admin deletes a contest |

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- PostgreSQL (or Supabase account)
- Apache Kafka running on `localhost:9092`

### Clone the Repository
```bash
git clone https://github.com/kileshwar23/SportiX.git
cd SportiX
```

### Configure the Application
Update `src/main/resources/application.properties`:

```properties
# Database
spring.datasource.url=jdbc:postgresql://db.YOUR_REF.supabase.co:5432/postgres
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

# Email
spring.mail.username=YOUR_GMAIL@gmail.com
spring.mail.password=YOUR_APP_PASSWORD
```

### Run the Application
```bash
./mvnw spring-boot:run
```

App starts at **http://localhost:8080**

---

## ⚙️ Environment Configuration

| Property | Description |
|---|---|
| `spring.datasource.url` | PostgreSQL JDBC URL |
| `spring.datasource.username` | DB username |
| `spring.datasource.password` | DB password |
| `jwt.secret` | JWT signing secret (min 32 chars) |
| `jwt.expiration` | Token expiry in ms (default: 86400000 = 24h) |
| `spring.kafka.bootstrap-servers` | Kafka broker address |
| `spring.mail.username` | Gmail address for sending emails |
| `spring.mail.password` | Gmail App Password (not your login password) |

> **Get Gmail App Password:** Google Account → Security → 2-Step Verification → App Passwords

---

## 🧪 Running Tests

Tests use H2 in-memory database — no real DB needed:

```bash
./mvnw test
```

---

## 🔮 Future Improvements

- [ ] Convert to **Microservices** with Spring Cloud Gateway + Eureka
- [ ] Add **Redis caching** for contest and user data
- [ ] Add **WebSocket** for live match score updates
- [ ] Add **Swagger/OpenAPI** documentation
- [ ] Add **Docker** support with `docker-compose`
- [ ] Add **CI/CD** with GitHub Actions
- [ ] Add **rate limiting** with Bucket4j
- [ ] Add **Firebase FCM** for push notifications

---

## 👨‍💻 Author

**Kileshwar**
- GitHub: [@kileshwar23](https://github.com/kileshwar23)

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
