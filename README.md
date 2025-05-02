# 📱 Tappy - Android Quiz App

Tappy is an interactive Android app where users can take quizzes in different categories (Android, Web Development, Computer Network, IBM). It utilizes Clean Architecture, Firebase, and the modern Android stack.

---

## 🚀 Features

- 🔐 Firebase authorization (login and logout)
- ❓ Quiz passing with result calculation
- 🧠 Question categories: Android, Web, Network, IBM
- 📦 Caching questions with Room
- 🔔 Notification via WorkManager after login
- 🌐 Deep Links (e.g. `tappy://quiz`, `tappy://result/8`)
- ⚙️ Logging HTTP requests via OkHttp Interceptor
- 📤 Connecting external `logger` library via GitHub Packages

---

## 🧱 Architecture

Modules:
- `app` - UI on Jetpack Compose + navigation
- `domain` - business logic (UseCases, models)
- `data` - work with API and Room
- `core` - common classes, models

Patterns:
- Clean Architecture
- Dependency Injection via Koin
- WorkManager for background tasks
- Firebase for authentication

---

## 🧪 Tests

- ✅ Unit test: `InsertQuestionsUseCaseTest`.
- ✅ Easy way to test ViewModel
- ✅ Coverage of basic business logic scenarios

---

## 🔌 External dependencies

- ✅ [Logger](https://github.com/ZhumabayDias/logger) is a proprietary library published in GitHub Packages
```kotlin
implementation(“com.github.ZhumabayDias:logger:1.0.0”)
