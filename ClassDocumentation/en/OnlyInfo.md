# Class Description: OnlyInfo

________________________________________
## 1. General Information
*   **Class Name:** `OnlyInfo`
*   **Type:** Activity
*   **Purpose:** This screen is designed to display static information or documentation to the user. It is a simple "read-only" screen.
*   **Interaction:** It is usually reached from the `MainChoise` screen. It contains its own menu for navigation to other parts of the app.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `context` | `Context` | Stores a reference to the current Activity. | Used for starting new Intents. |
| `isService` | `boolean` | A flag to track if voice assistance is toggled. | In `onOptionsItemSelected()`. |

*   **Redundancy Note:** This class manually tracks `isService` and `context`, which are already handled in other parts of the app.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:** Sets the visual layout (`activity_only_info.xml`) and initializes the context reference.
*   **When called:** When the user selects "View Information" from the main menu.

### Method name: `onCreateOptionsMenu`
*   **Type:** `public`
*   **Logic:** Loads the main menu design (`R.menu.main`) and ensures icons are visible in the top bar.

### Method name: `onOptionsItemSelected`
*   **Type:** `public`
*   **Logic:** Contains a long list of `if` statements to handle menu clicks (Back, Exit, Credits, Guide, etc.).
*   **What is important:** This method manually handles every possible menu click, which is different from most other screens in this app that use the base class logic.

________________________________________
## 4. Lifecycle
*   **onCreate():** Prepares the info screen.
*   **onDestroy():** Standard Activity destruction.

________________________________________
## 5. Interface Interaction (UI)
*   **Layout:** The actual information is typically defined in the `activity_only_info.xml` file using `TextViews`.
*   **Menu:** The primary interaction is through the standard Android options menu.

________________________________________
## 6. Interaction with other components
*   **Intents:** Can launch almost any other major screen (Guide, Credits, Contacts, Emergency).
*   **finishAffinity():** Closes the entire app.

________________________________________
## 7. General logic of the class
The class acts as a **static viewer**. It doesn't load data from a database or a web server; it just shows what is already written in its layout file. It also provides a way to get back to other parts of the app via the top menu.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `OnlyInfo` class as a **poster on a wall**.
*   You go to the poster to read some information.
*   The poster doesn't change; it always shows the same thing.
*   While you are at the poster, you can decide to go to another room by looking at the signs (the menu) at the top of the wall.

**Technical Improvement Tip:** In a real-world project, this class should inherit from `BaseActivity`. Right now, it repeats a lot of code that is already written elsewhere. By inheriting, we could delete half of this file and the app would work exactly the same!

**Analogy:** It's like having a **manual for a machine**. The manual doesn't operate the machine; it just tells you how it works.
