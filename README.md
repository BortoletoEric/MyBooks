# MyBooks

MyBooks is an Android application designed to help users manage their personal book collection. Users can browse a list of books, view detailed information about each title, and mark their favorite books for quick access.

## 🚀 Features

- **Book Listing**: Browse a comprehensive list of books with titles and authors.
- **Detailed View**: Access in-depth information about each book, including its genre.
- **Favorites**: Mark books as favorites and view them in a dedicated section.
- **Modern UI**: Clean and intuitive interface built with Material Design components.
- **Smooth Navigation**: Utilizes the Android Jetpack Navigation component for seamless transitions between fragments.

## 🏗️ Architecture & Design Patterns

- **MVVM (Model-View-ViewModel)**: The app follows the MVVM pattern to ensure a clean separation of concerns, making the code more testable and maintainable.
- **Repository Pattern**: Centralizes data access logic, providing a clean API for the rest of the app to interact with book data.
- **Singleton Pattern**: Used for database and repository instances to ensure consistent data access throughout the app's lifecycle.

## 🛠️ Technologies Used

- **Kotlin**: Primary programming language.
- **SQLite**: Local data storage implemented via `SQLiteOpenHelper` for persistent book management.
- **View Binding**: For safe and efficient interaction with UI components.
- **ConstraintLayout**: Used extensively to create flat, responsive, and complex UI hierarchies that adapt to different screen sizes.
- **Architecture Components**:
    - **ViewModel**: Manages UI-related data and survives configuration changes.
    - **LiveData**: Provides observable data holders for reactive UI updates.
    - **Navigation Component**: Simplifies app navigation and fragment transactions.
- **Material Design**: For a consistent look and feel across the application.

## 🌍 Accessibility & Localization

- **Dark Theme Support**: The UI automatically adapts to the system's dark mode settings, providing a comfortable viewing experience in low-light environments.
- **Internationalization (i18n)**: All strings are extracted into `strings.xml` resources, with support for multiple languages (e.g., Portuguese and English), facilitating easier translations.
- **Responsive Design**: Layouts are designed with `ConstraintLayout` to ensure compatibility with various screen sizes and both **Vertical (Portrait)** and **Horizontal (Landscape)** orientations.

## 📂 Project Structure

- `entity`: Data models representing the core business logic (`BookEntity`).
- `ui`: UI components including Fragments, Activities, Adapters, and ViewHolders.
- `viewmodel`: ViewModels that hold the state and logic for the UI.
- `repository`: Data layer responsible for SQLite operations and data management.
- `helper`: Utility classes and constants.

## ⚙️ Configuration

- **Minimum SDK**: 26
- **Target SDK**: 36
- **Compile SDK**: 36
- **Java Version**: 11

## 🏃 Getting Started

1. Clone the repository.
2. Open the project in **Android Studio**.
3. Sync the project with Gradle files.
4. Run the app on an emulator or a physical device (Android 8.0+).

---
*Developed as a showcase of modern Android development practices.*
