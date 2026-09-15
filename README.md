# StudentIDAssignment2

An Android application showing off authentication, API integration, and data display features.

## Features

- **Login Screen**: One may log-in with a given password, with errors shown if the wrong one is inputted.
- **Dashboard Screen**: A 'list' (sorta) of entities is displayed via recyclerview.
- **Details Screen**: Not yet implemented, but some residual code thereof is still kicking for that page. Sorta.

## Tech Stack

- **Language**: Kotlin
- **Architecture**: Clean Architecture with MVVM pattern
- **Networking**: Retrofit 2 + OkHttp
- **Dependency Injection**: Hilt
- **Navigation**: Android Navigation Component with Safe Args
- **Asynchronous**: Kotlin Coroutines
- **UI**: Android Fragment + ViewBinding
- **Testing**: JUnit 4, MockK, Coroutines Test

## Project Setup

### Prerequisites

- Android Studio 2022.1 or higher
- Kotlin 1.9.0
- Java 11 or higher
- Git

### Installation

1. **Clone or Extract the Project**
     ```bash
     cd StudentIDAssignment2```

2. **Open in Android Studio**
     File → Open → Select StudentIDAssignment2 directory
     Wait for Gradle to sync

3. **Run the Application**
     Connect an Android device or start an emulator (API 24+)
     Click Run → Run 'app'

## API Endpoints
### Base URL

https://nit3213apinew.onrender.com

### Authentication

    POST /footscray/auth - Footscray campus
    POST /sydney/auth - Sydney campus
    POST /br/auth - BR campus

### Request Body:
json

{  "username": "12345678",  "password": "FirstName"}

Response:
json

{  "keypass": "topicName"}

### Dashboard

    GET /dashboard/{keypass}

Response:
json

{  "entities": [    {      "property1": "value1",      "property2": "value2",      "description": "Detailed description"    }  ],  "entityTotal": 7}

## Usage
### Login

    Enter your Student ID (format: 12345678)
    Enter your first name (case-sensitive)
    Select your campus location
    Tap "Login"

### Dashboard

    View all entities received from the API
    Tap on any entity to view full details
