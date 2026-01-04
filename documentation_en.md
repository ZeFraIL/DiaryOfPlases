# "DiaryOfPlases" Project Documentation

## 1. Project Overview

**DiaryOfPlaces** is a native Android application that acts as a personal diary for saving information about various locations. The application allows users to authenticate, add new places with detailed descriptions, photos, GPS coordinates, and web links, as well as manage a list of personal contacts.

---

## 2. Core Components

### 2.1. Activities (Screens)

#### `Start.java`
- **Layout:** `activity_start.xml`
- **Purpose:** A splash screen that is displayed for 5 seconds when the app launches.
- **Functionality:** Automatically navigates to the `StartChoise` screen after the timer expires. Tapping the screen interrupts the timer and navigates immediately.

#### `StartChoise.java`
- **Layout:** `activity_start_choise.xml`
- **Purpose:** Provides the user with an initial choice: log in or proceed to the information-only section.
- **Functionality:**
    - "Start" button (`startUser`): Navigates to the `LogOrReg` screen.
    - "Info Only" button (`onlyInfo`): Navigates to the `OnlyInfo` screen.
    - Options Menu: Provides access to "Credits", "Guide", "Contacts", "Emergency" sections, and toggles voice assistance.

#### `LogOrReg.java`
- **Layout:** `activity_log_or_reg.xml`
- **Purpose:** User login or registration screen.
- **Functionality:**
    - **"Register" mode:** If the `mypass.txt` file is not found, it prompts the user to create and save a password.
    - **"Login" mode:** If the `mypass.txt` file exists, it validates the entered password.
    - **Storage:** The password is stored in plain text in internal storage. *Note: This is insecure.*

#### `MainChoise.java`
- **Layout:** `activity_main_choise.xml`
- **Purpose:** The main menu for an authenticated user.
- **Functionality:**
    - "Add Place": Navigates to `AddPlace`.
    - "View Places": Navigates to `ViewPlaces`.
    - "Information": Navigates to `OnlyInfo`.
    - A confirmation dialog is shown before each navigation.

#### `AddPlace.java`
- **Layout:** `activity_add_place.xml`
- **Purpose:** A form for adding a new entry about a place.
- **Functionality:**
    - Allows entering a name and a comment.
    - **Photo:** Launches the camera app to take a picture (`ACTION_IMAGE_CAPTURE`).
    - **GPS:** Launches `MyLocation` to get coordinates.
    - **Link:** Launches `Search` to find and attach a URL.
    - **Save:** Saves all data, including the image as a `BLOB`, to the SQLite database.

#### `ViewPlaces.java`
- **Layout:** `activity_view_places.xml`
- **Purpose:** Displays a list of all saved places.
- **Functionality:**
    - Loads place names from the database and displays them in a `ListView`.
    - Tapping a list item shows a dialog with full details: name, comment, and photo.

#### `InnerContacts.java`
- **Layout:** `activity_inner_contacts.xml`
- **Purpose:** The contact management center.
- **Functionality:**
    - Displays "inner" contacts (from the app's database) or contacts from the device's phone book.
    - For inner contacts, it allows making calls, sending SMS, sending emails, editing, and deleting them.

#### `AddInnerContact.java`
- **Layout:** `activity_add_inner_contact.xml`
- **Purpose:** A form for creating and editing "inner" contacts.
- **Functionality:** Works in "add" mode (empty form) or "update" mode (form pre-filled with contact data).

#### `Search.java`
- **Layout:** `activity_search.xml`
- **Purpose:** An embedded web browser for searching and selecting a URL.
- **Functionality:** Uses a `WebView` to load Google Search with a query passed from `AddPlace`. It returns the selected URL to the previous screen.

#### `MyLocation.java`
- **Layout:** `activity_my_location.xml`
- **Purpose:** Retrieves the current GPS coordinates.
- **Functionality:** Uses `LocationManager` to get the last known location and immediately returns it to the calling screen (`AddPlace`).

---

### 2.2. Helper Classes and Services

#### `HelperDB.java`
- **Purpose:** A helper class for managing the SQLite database.
- **Functionality:** Extends `SQLiteOpenHelper`. It is responsible for creating the `info.db` database and two tables: `Places` and `myContacts`.

#### `Place.java`
- **Purpose:** A model class (POJO) to represent the data of a single place.
- **Structure:** Contains `placeName`, `placeComment`, `placeImage` (byte[]), `placeLink`, and `placeGPS` fields.

#### `myContact.java`
- **Purpose:** A model class (POJO) to represent the data of a single contact.
- **Structure:** Contains `contactID`, `contactName`, `contactComment`, `contactPhone`, and `contactEmail` fields.

#### `SpeakService.java`
- **Purpose:** A service for text-to-speech (voice assistance).
- **Functionality:** Uses `TextToSpeech` to speak text received via an `Intent`. It is activated from the menu in most activities.

---

## 3. User Interface (UI)

### 3.1. Layout Files

- `activity_start.xml`: Layout for the splash screen.
- `activity_start_choise.xml`: Layout for the initial choice screen.
- `activity_log_or_reg.xml`: Layout for the login/registration screen.
- `activity_main_choise.xml`: Layout for the main menu.
- `activity_add_place.xml`: The add place form with `EditText`, `ImageView`, and `Button` fields.
- `activity_view_places.xml`: Contains a `ListView` to display the list of places.
- `activity_inner_contacts.xml`: Contains a `ListView` for contacts and buttons to switch modes.
- `activity_add_inner_contact.xml`: The add/edit contact form.
- `mycontact_dialog.xml`: A custom layout for the dialog box that shows contact details and action buttons.

### 3.2. Design Customization Files

These files are located in the `res/values/` folder:

- **`colors.xml`**: Defines the primary colors used in the application (e.g., background, text, and accent colors).
- **`strings.xml`**: Contains all string resources. Using this file makes it easy to localize the application into other languages.
- **`themes.xml` (or `styles.xml`)**: Defines the overall application theme, including base attributes like the color palette (primary, secondary) and default styles for buttons, text fields, and other UI elements.
