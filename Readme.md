# Magicpin Vera Bot

## Overview

Magicpin Vera Bot is an AI-powered merchant engagement assistant developed as part of the Magicpin Backend Assignment.

The bot proactively engages merchants using contextual information such as merchant, category, customer, and trigger data. It also maintains conversation history to generate intelligent follow-up responses.

---

# Tech Stack

- Java 21
- Spring Boot 3.5
- Maven
- OpenRouter API (Free LLM)
- REST APIs
- In-Memory Storage

---

# Features

## 1. Health Check

```
GET /v1/healthz
```

Returns application health status.

---

## 2. Metadata

```
GET /v1/metadata
```

Returns:

- Team Name
- Team Members
- Contact Email
- Model Used
- Version

---

## 3. Context API

```
POST /v1/context
```

Stores:

- Merchant Context
- Customer Context
- Category Context
- Trigger Context

Features:

- Version Validation
- Duplicate Detection
- Stale Version Detection

---

## 4. Tick API

```
POST /v1/tick
```

Generates proactive merchant engagement messages using AI.

---

## 5. Reply API

```
POST /v1/reply
```

Handles merchant replies using:

- Conversation Memory
- AI-generated responses
- Smart conversation flow

Supports three actions:

- send
- wait
- end

---

# AI Integration

Provider

OpenRouter

Model

```
openrouter/free
```

---

# Conversation Memory

Conversation history is stored using:

```
Map<String, List<String>>
```

Each conversation is identified using

```
conversationId
```

This enables context-aware conversations.

---

# Project Structure

```
src
└── main
    ├── java
    │   └── com.magicpin.vera_bot
    │       ├── ai
    │       ├── config
    │       ├── controller
    │       ├── dto
    │       ├── exception
    │       ├── model
    │       ├── services
    │       ├── storage
    │       └── util
    │
    └── resources
        └── application.properties
```

---

# Running

Clone

```bash
git clone <repository-url>
```

Move inside

```bash
cd vera-bot
```

Configure

```
application.properties
```

```
openrouter.api.key=YOUR_API_KEY
openrouter.model=openrouter/free
```

Run

```bash
mvn clean spring-boot:run
```

---

# Endpoints

| Method | Endpoint |
|---------|----------|
| GET | /v1/healthz |
| GET | /v1/metadata |
| POST | /v1/context |
| POST | /v1/tick |
| POST | /v1/reply |

---

# Example Flow

Merchant Context

↓

Context Store

↓

Tick API

↓

Prompt Builder

↓

OpenRouter

↓

AI Response

↓

Merchant Reply

↓

Conversation Store

↓

Reply API

---

# Design Decisions

- Spring Boot REST Architecture
- Layered Design
- DTO-based API
- In-memory Context Store
- Conversation Memory
- AI Service Abstraction
- Version Validation

---

# Future Improvements

- PostgreSQL persistence
- Redis conversation cache
- Streaming responses
- Better prompt engineering
- Intent classification
- Analytics dashboard

---

# Author

Name:Tarsem Gulab

Email: work4tarsemgulab@gmail.com

GitHub:https://github.com/gittarsem
