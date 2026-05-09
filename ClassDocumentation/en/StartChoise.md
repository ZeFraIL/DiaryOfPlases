# Class Description: StartChoise

________________________________________
## 1. General Information
*   **Class Name:** `StartChoise`
*   **Type:** Activity
*   **Purpose:** This screen is the first interactive choice the user makes. It appears right after the splash screen and asks the user whether they want to use the full app (with login/database) or just see general information.
*   **Interaction:** Inherits from `BaseActivity`. It leads to either `LogOrReg` or `OnlyInfo`.

________________________________________
## 2. Variables (Class Fields)
This class does not have its own persistent variables.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:** Simply sets the visual layout (`activity_start_choise.xml`).
*   **When called:** Automatically after the `Start` (Splash) screen finishes.

### Method name: `startUser`
*   **Type:** `public`
*   **Parameters:** `View view` (The "Start" button).
*   **Logic:**
    1. Uses the voice assistant to say "You go now to registry or login".
    2. Opens the `LogOrReg` screen.
*   **When called:** When the user clicks the primary button to enter the application.

### Method name: `onlyInfo`
*   **Type:** `public`
*   **Parameters:** `View view` (The "Information" button).
*   **Logic:**
    1. Uses the voice assistant to say "You go now to information part only".
    2. Opens the `OnlyInfo` screen.
*   **When called:** When the user clicks the secondary button to see only documentation.

________________________________________
## 4. Lifecycle
*   **onCreate():** Sets up the initial choice screen.
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   **Buttons:** Two large buttons to redirect the user.
*   **Events:** Taps on these buttons trigger screen transitions.

________________________________________
## 6. Interaction with other components
*   **Intent:** Used to move to the next phase of the app launch process.
*   **TtsManager:** Used to vocalize the user's choice.

________________________________________
## 7. General logic of the class
The class acts as a **directional signpost**. It helps the app decide which path to take: the "secure path" (authentication required) or the "public path" (information only).

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `StartChoise` class as a **reception desk** in a building.
*   The receptionist asks: "Do you have a membership card, or are you just here for a brochure?"
*   If you want to use the gym (the app's features), you go to the desk (`LogOrReg`).
*   If you just want to read the pamphlet, you go to the rack (`OnlyInfo`).

**Analogy:** It's like the **"Start Game"** and **"Options"** screen in a video game. You decide if you want to jump into the action or just see some settings/info first.
