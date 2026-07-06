# Magicpin Vera Bot

AI-powered merchant engagement assistant built using **Spring Boot** and **OpenRouter LLM** as part of the **Magicpin Backend Assignment**.

---

# Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Running the Project](#running-the-project)
- [Testing](#testing)
- [Design Decisions](#design-decisions)
- [Future Improvements](#future-improvements)
- [Author](#author)

---

# Overview

Vera Bot is a RESTful backend service that intelligently engages merchants using AI-generated responses and contextual information.

The bot supports:

- Merchant engagement generation
- Context management
- Conversation memory
- AI-powered replies
- Smart conversation actions (`send`, `wait`, `end`)

---

# Features

- AI-powered merchant engagement
- Context-aware conversations
- Merchant, Customer, Category & Trigger context support
- Context version validation
- Duplicate & stale version handling
- Auto-reply detection
- Conversation memory
- REST APIs
- Automated integration tests

---

# Architecture

```text
                    Merchant
                        │
                        ▼
                  Spring Boot APIs
                        │
      ┌─────────────────┼─────────────────┐
      ▼                 ▼                 ▼
Context Service    Tick Service    Reply Service
      │                 │                 │
      └────────────┬────┴────────────┬────┘
                   ▼                 ▼
           Context Store     Conversation Store
                   │
                   ▼
              AI Service
                   │
                   ▼
           OpenRouter LLM
```

---

# Project Structure

```text
src
├── main
│   ├── controller
│   ├── services
│   ├── ai
│   ├── dto
│   ├── model
│   ├── storage
│   ├── config
│   └── exception
│
├── resources
│   └── application.properties
│
└── test
    └── VeraBotApplicationTests.java
```

---

# API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/v1/healthz` | Health Check |
| GET | `/v1/metadata` | Bot Metadata |
| POST | `/v1/context` | Store Context |
| POST | `/v1/tick` | Generate Merchant Engagement |
| POST | `/v1/reply` | Handle Merchant Reply |

---

# Running the Project

### Clone Repository

```bash
git clone https://github.com/<your-username>/vera-bot.git
cd vera-bot
```

### Configure

Update `application.properties`

```properties
openrouter.api.key=YOUR_API_KEY
openrouter.model=openrouter/free
```

### Run

```bash
mvn clean spring-boot:run
```

Application runs at:

```
http://localhost:8080
```

---

# Testing

Run all automated tests:

```bash
mvn test
```

The project includes integration tests for:

- Health API
- Metadata API
- Context API
- Version validation
- Reply API
- Conversation flow

---

# Design Decisions

- Layered Spring Boot architecture
- Service-oriented business logic
- DTO-based communication
- Global exception handling
- AI abstraction through `AIService`
- In-memory context & conversation storage
- Context version management

---

# Future Improvements

- PostgreSQL persistence
- Redis conversation cache
- Scheduled follow-ups
- Intent classification
- Streaming AI responses

---

# Author

**Tarsem Gulab**

- Email: work4tarsemgulab@gmail.com
- GitHub: https://github.com/gittarsem