# Class Description: MainChoise

________________________________________
## 1. General Information
*   **Class Name:** `MainChoise`
*   **Type:** Activity
*   **Purpose:** This screen acts as the main menu or "control center" of the application. It allows the user to choose between the three main features: adding a new place, viewing saved places, or reading general information.
*   **Interaction:** Inherits from `BaseActivity`. It serves as a starting point for `AddPlace`, `ViewPlaces`, and `OnlyInfo`.

________________________________________
## 2. Variables (Class Fields)
This class does not have its own persistent variables. It uses temporary objects inside its methods.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:**
    1. Sets the layout (`activity_main_choise.xml`).
    2. Assigns click listeners to three buttons:
        *   "Add Place" -> Calls `buildDialog` for `AddPlace.class`.
        *   "View Places" -> Calls `buildDialog` for `ViewPlaces.class`.
        *   "Info" -> Calls `buildDialog` for `OnlyInfo.class`.
*   **When called:** Automatically after the user successfully logs in or skips authentication.

### Method name: `buildDialog`
*   **Type:** `public`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `title` | `String` | The question to show (e.g., "Add place?"). |
    | `toGo` | `Class<?>` | The Activity to open if the user says "Yes". |
*   **Logic:**
    1. Uses the voice assistant to speak the question.
    2. Displays an `AlertDialog` (pop-up) with "Yes" and "No" buttons.
    3. If "Yes" is clicked, it creates an `Intent` and starts the specified Activity.
    4. If "No" is clicked, it just closes the pop-up.
*   **Why a dialog?** It prevents accidental clicks and provides a better experience for users using voice assistance.

________________________________________
## 4. Lifecycle
*   **onCreate():** Sets up the menu buttons.
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   **Buttons:** `bAddPlace`, `bViewPlaces`, `bInfo`.
*   **AlertDialog:** Used to confirm every navigation choice.

________________________________________
## 6. Interaction with other components
*   **Intent:** Used to transition to the selected feature.
*   **TtsManager:** Used through `speak()` to guide the user.

________________________________________
## 7. General logic of the class
The class is a **traffic controller**. It doesn't perform any data processing itself; it just asks the user "Where do you want to go?" and then opens the right door.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `MainChoise` class as a **main hallway** in a house.
*   There are three big doors with signs on them.
*   Before you open a door, a voice asks: "Are you sure you want to go in here?"
*   If you say "Yes", you enter the room. If you say "No", you stay in the hallway.

**Analogy:** It's like a **main menu** on a DVD or a streaming app. You choose whether you want to "Start Movie" (Add Place), "Scene Selection" (View Places), or "Extra Features" (Info).
