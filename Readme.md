# Magicpin Vera Bot

AI-powered merchant engagement assistant built using **Spring Boot** and **OpenRouter LLM** for the **Magicpin Backend Challenge**.

---

## Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Features](#features)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Running Locally](#running-locally)
- [Docker](#docker)
- [Testing](#testing)
- [API Verification](#api-verification)
- [Design Decisions](#design-decisions)
- [Future Improvements](#future-improvements)
- [Author](#author)

---

# Overview

Magicpin Vera Bot is a RESTful backend service that proactively engages merchants using contextual information and AI-generated responses.

The application supports:

- Context ingestion
- Proactive merchant engagement
- AI-powered conversations
- Conversation memory
- Smart conversation actions (`send`, `wait`, `end`)
- Version-aware context updates

---

# Tech Stack

| Technology | Usage |
|------------|-------|
| Java 21 | Programming Language |
| Spring Boot 3.5 | Backend Framework |
| Maven | Build Tool |
| OpenRouter | LLM Provider |
| REST APIs | Communication |
| Docker | Containerization |
| JUnit 5 + MockMvc | Automated Testing |

---

# Features

- AI-powered merchant engagement
- Context-aware conversations
- Merchant, Category, Customer and Trigger context management
- Conversation memory
- Context version validation
- Duplicate & stale version handling
- Auto-reply detection
- RESTful API architecture
- Docker support
- Automated integration tests

---

# Architecture

```text
                    Merchant
                        │
                        ▼
                Spring Boot REST APIs
                        │
      ┌─────────────────┼──────────────────┐
      ▼                 ▼                  ▼
 Context Service    Tick Service     Reply Service
      │                 │                  │
      └──────────────┬──┴──────────────┬───┘
                     ▼                 ▼
             Context Store     Conversation Store
                     │
                     ▼
                Prompt Builder
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
│   ├── ai
│   ├── config
│   ├── controller
│   ├── dto
│   ├── exception
│   ├── model
│   ├── prompt
│   ├── services
│   ├── storage
│   └── util
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
| GET | `/v1/healthz` | Application Health |
| GET | `/v1/metadata` | Submission Metadata |
| POST | `/v1/context` | Store Merchant Context |
| POST | `/v1/tick` | Generate Merchant Engagement |
| POST | `/v1/reply` | AI Conversation Endpoint |

---

# Running Locally

### Clone Repository

```bash
git clone https://github.com/gittarsem/vera-bot.git
cd vera-bot
```

### Configure

Update **application.properties**

```properties
openrouter.api.key=YOUR_API_KEY
openrouter.model=openrouter/free
```

### Build

```bash
mvn clean install
```

### Run

```bash
mvn spring-boot:run
```

Application:

```
http://localhost:8080
```

---

# Docker

Build image

```bash
docker build -t vera-bot .
```

Run container

```bash
docker run -p 8080:8080 \
-e OPENROUTER_API_KEY=YOUR_API_KEY \
vera-bot
```

---

# Testing

Run all integration tests

```bash
mvn clean test
```

Current test coverage includes:

- Health API
- Metadata API
- Context API
- Duplicate Version Detection
- Stale Version Detection
- Reply API
- Conversation Flow

---

# API Verification

### Health

```bash
GET /v1/healthz
```

Response

```json
{
  "status": "ok"
}
```

---

### Context

```bash
POST /v1/context
```

Response

```json
{
  "accepted": true,
  "ackId": "ack_merchant-final"
}
```

---

### Tick

```bash
POST /v1/tick
```

Response

```json
{
  "actions": [
    {
      "merchantId": "merchant-final",
      "message": "Hi ABC Restaurant..."
    }
  ]
}
```

---

### Reply

```json
{
  "action": "send"
}
```

---

### Wait

```json
{
  "action": "wait",
  "waitSeconds": 86400
}
```

---

### End

```json
{
  "action": "end"
}
```

---

# Design Decisions

- Layered Spring Boot architecture
- Separation of Controller, Service and Storage layers
- Prompt Builder abstraction for AI prompts
- AI provider abstraction through `AIService`
- In-memory context and conversation storage
- Context version management
- Global exception handling
- DTO-based request/response models

---

# Future Improvements

- PostgreSQL persistence
- Redis conversation cache
- Scheduled follow-up engine
- Intent classification
- Retrieval-Augmented Generation (RAG)
- Streaming AI responses
- Metrics & monitoring dashboard

---

# Author

**Tarsem Gulab**

**GitHub**

https://github.com/gittarsem

**Email**

work4tarsemgulab@gmail.com
