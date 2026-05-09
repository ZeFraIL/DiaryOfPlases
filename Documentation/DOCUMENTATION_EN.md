# 📱 Android Application Documentation (Level 10/10)

________________________________________
## 🧾 General Information
**Project Name:**  
DiaryOfPlases (Diary of Places)

**Author(s):**  
Mika Gorelik

**Date:**  
May 2024

**Language:**  
Java

**Development Environment:**  
Android Studio

**Android Version (minSdk / targetSdk):**  
28 / 33
________________________________________
## 🎯 Project Goal
*   **What problem does the app solve:** Provides a convenient way to document and organize visited locations with photos, notes, and GPS coordinates.
*   **Why is this task important:** It helps users preserve memories of their travels and easily find important locations in the future.
*   **Target Audience:** Travelers, explorers, and people who want to keep a personal log of places.
________________________________________
## 📌 Application Requirements
### Functional Requirements
*   Create, view, edit, and delete place entries.
*   Attach photos from the camera to locations.
*   Save GPS coordinates of places.
*   Search for locations.
*   Emergency call buttons (Police, Ambulance, Fire).
*   Voice assistance (Text-to-Speech) for accessibility.
*   Management of personal contacts.

### Non-functional Requirements
*   **Performance:** Fast data retrieval from local SQLite database.
*   **Usability:** Simple navigation and voice-guided interface.
*   **Reliability:** Persistent local storage ensuring no data loss on app restart.
________________________________________
## 🧠 General Architecture
*   **Chosen Approach:**  
    MVC (Model-View-Controller)
*   **Why this approach:**  
    It is well-suited for an activity-based application where the Activity acts as both Controller and View, interacting with a simple Data Layer (SQLite).
*   **Main System Components:**  
    *   **View/Controller:** Activities (`Start`, `MainChoise`, `AddPlace`, `ViewPlaces`).
    *   **Data Layer:** `HelperDB` (SQLiteOpenHelper).
    *   **Model:** `Place`, `myContact`.
    *   **Utility:** `TtsManager` for voice assistance.
________________________________________
## 🧩 UML Diagram (Description)
**Workflow:**  
`[Start]` –> `[MainChoise]`  
`[MainChoise]` –> `[AddPlace]` / `[ViewPlaces]` / `[Search]` / `[Emergency]` / `[Guide]`  
`[ViewPlaces]` –> `[PlaceAdapter]` –> `[Place]`  
`[HelperDB]` <–> `[Place]` / `[myContact]`  

**Scaling:**  
The project uses a base activity (`BaseActivity`) to share logic (menus, TTS), which facilitates scaling by adding new features without duplicating boilerplate code.
________________________________________
## 🧩 Detailed Class Description
### 📌 Class: Start
*   **Role:** Entry Point.
*   **Responsibility:** Initializing the app and navigating the user to the main menu or authentication.
*   **Main Methods:**
    *   `onCreate()` — Sets up the initial UI and navigation logic.
*   **Interaction:** Calls `LogOrReg` or `MainChoise`.

### 📌 Class: BaseActivity
*   **Role:** Foundation class for all activities.
*   **Responsibility:** Handling common menu items (Back, Exit, Credits) and initializing the `TtsManager`.
*   **Main Methods:**
    *   `speak(String message)` — Provides voice assistance if enabled.

### 📌 Class: HelperDB (Data Layer)
*   **Role:** Database Manager.
*   **Responsibility:** Creating and upgrading the SQLite database, providing methods for CRUD operations.
________________________________________
## 🔄 Application Workflow
1.  **Launch:** User starts the app via `Start`.
2.  **Navigation:** User chooses to add a place in `MainChoise`.
3.  **Data Entry:** In `AddPlace`, user fills in details, takes a photo, and saves.
4.  **Storage:** `HelperDB` saves the `Place` object to the SQLite database.
5.  **Viewing:** User goes to `ViewPlaces` to see the list of saved locations.
________________________________________
## 🎨 UI/UX Analysis
*   **Why the interface is designed this way:** Focus on simplicity and accessibility. Large buttons and clear labels make it easy to use on the go.
*   **Principles used:**
    *   **Simplicity:** Minimalistic layout for core features.
    *   **Logic:** Hierarchical navigation following standard Android patterns.
    *   **Accessibility:** Integrated Text-to-Speech to assist users with visual impairments.
*   **Improvements:** Migration to Material 3 design and adding a Dark Mode.
________________________________________
## ⚙️ Threading
*   **Techniques used:**
    *   Standard UI Thread for interactions.
    *   Implicitly handled threading for camera and location updates via Android system APIs.
*   **Why chosen:** Simplifies the codebase while relying on robust system-level handling for specialized tasks.
*   **Prevention:**
    *   **ANR:** Avoiding heavy processing on the main thread.
    *   **Leaks:** Using proper lifecycle management in Activities.
________________________________________
## 💾 Data Management
*   **Storage:** Local SQLite database (`info.db`).
*   **Why chosen:** Reliable, requires no internet connection, and handles structured data efficiently.
*   **Assurance:**
    *   **Safety:** SQLite's atomic operations.
    *   **Correctness:** Defined schemas for Places and Contacts.
________________________________________
## 🌐 Networking
*   **Usage:** Used in `Search.java` to perform external lookups (via WebView/Intents).
*   **Error Handling:** Checks for internet availability using `InternetConnectorReceiver`.
________________________________________
## 🔐 Security
*   **Sensitive Data:** User credentials and personal contacts.
*   **Protection:** Local authentication check in `Security.java` and `LogOrReg`.
________________________________________
## 🧪 Testing
*   **Types:**
    *   **Manual Testing:** Extensive verification of camera, GPS, and database operations.
    *   **Unit Tests:** Verification of data models (`Place`, `myContact`).
________________________________________
## 🐞 Error Handling
*   **Anticipated Errors:** Missing permissions (Camera, Location, Call), database errors, null data.
*   **Response:** Toast messages and graceful fallbacks (e.g., dialer instead of direct call if permission denied).
________________________________________
## ⚡ Performance
*   **Optimizations:** Use of `Glide` for efficient image loading and caching.
*   **Bottlenecks:** Large image storage in SQLite (could be optimized by storing paths instead of blobs).
________________________________________
## 🚀 Extension Possibilities
*   Cloud synchronization (Firebase).
*   Social sharing features.
*   Interactive Map view for all saved places.
________________________________________
## 📊 Project Self-Assessment
| Criterion | Rating (1–10) |
| :--- | :--- |
| Architecture | 8 |
| Code | 9 |
| UI/UX | 8 |
| Reliability | 9 |
| **Overall Level** | **8.5** |
________________________________________
## 🏁 Conclusion
*   **Best Achievement:** Successful integration of voice assistance and camera/location features.
*   **Challenges:** Managing lifecycle and permissions across multiple activities.
*   **Skills Acquired:** Deep understanding of SQLite, Android Permissions, and TTS integration.
________________________________________
## 📎 Appendices
*   **Screenshots:** [Attached in project folder]
*   **Repository:** [Link to GitHub/GitLab]
