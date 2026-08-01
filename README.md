# 🎵 Raghoulwave YT Telegram Bot

A feature-rich Telegram bot built with **Java** and **Spring Boot** that allows users to search YouTube Music, download audio as MP3, automatically tag tracks with metadata, and receive them directly in Telegram.

---

## ✨ Features

- 🔍 Search YouTube Music by title, artist, or keywords
- 🎧 Convert audio to MP3 using **FFmpeg**
- 👤 Automatically register and manage Telegram users
- 💾 Store users and tracks in PostgreSQL
- ⚡ Webhook-based Telegram Bot API integration
- 🧩 Clean layered architecture using Spring Boot

---

## 🛠 Tech Stack

### Backend

- Java 25
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- H2 Database (tests)

### External APIs

- YouTube Data API v3
- Telegram Bot API

### Libraries

- MapStruct
- Lombok
- JAudioTagger

---

## Project Structure

```
src/main/java
│
├── bot
├── configuration
├── controller
├── dispatcher
├── entity
├── handler
├── mapper
├── properties
├── repository
└── service
```

---

## Features

### Search

Users can search YouTube directly from Telegram.

```
User

↓

@raghoulwave_yt_bot lana del rey summertime sadness
```

↓

```
🎵 Lana Del Rey - Summertime Sadness

🎵 Lana Del Rey - Video Games

🎵 Lana Del Rey - Born To Die
```

---

### Metadata

The bot automatically writes:

- Artist
- Title
- Album
- Cover Art (planned)

using **JAudioTagger**.

---

## Database

### BotUser

Stores Telegram users.

Example fields:

- UUID
- Telegram ID
- Username
- First Name
- Last Name
- Language

### Track

Stores searchable tracks.

Example fields:

- UUID
- YouTube ID
- Title
- Artist
- Album
- Telegram File ID *(planned)*

---

## Configuration

Example `application.yml`

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: password

raghoulwave:
  config:
    telegram:
      bot-token: YOUR_TOKEN
      webhook-url: https://example.com/webhook
      webhook-secret: YOUR_SECRET
      administrator-id: 123456789

    google:
      youtube-data-api-v3:
        api-key: YOUR_API_KEY

    ytdlp:
      download-dir: /tmp/downloads
      ffmpeg-location: /opt/homebrew/bin
```

---

## Requirements

- Java 25+
- Maven
- PostgreSQL
- FFmpeg
- yt-dlp

Install dependencies on macOS:

```bash
brew install ffmpeg
brew install yt-dlp
```

---

## Running

Clone the repository:

```bash
git clone https://github.com/raghoulwave/raghoulwave_yt_bot.git
```

Build:

```bash
mvn clean install
```

Run:

```bash
mvn spring-boot:run
```

---

## Planned Features

- Inline mode
- Spotify integration
- Telegram file_id cache
- Cover art embedding
- Docker deployment
- Unit & integration tests
- GitHub Actions CI/CD

---

## Design Principles

- Clean Architecture
- SOLID
- Layered architecture
- Service-oriented design
- Constructor dependency injection
- Configuration Properties
- Structured logging with SLF4J

---

## License

This project is released under the MIT License.

---

## Author

**Maksym Rahulin a.k.a. Raghoulwave**

Java Backend Developer

- Java
- Spring Boot
- REST APIs
- PostgreSQL
- Telegram Bot API
- YouTube Data API