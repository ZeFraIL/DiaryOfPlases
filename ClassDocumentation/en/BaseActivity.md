# Class Description: BaseActivity

________________________________________
## 1. General Information
*   **Class Name:** `BaseActivity`
*   **Type:** Abstract Activity (Base Class)
*   **Purpose:** This is a "parent" class from which almost all other Activities in the application are inherited. It contains common logic that is needed everywhere: managing the main menu, navigation between screens, and voice assistance.
*   **Interaction:** It serves as a template for other Activities (like `AddPlace`, `ViewPlaces`, etc.). It also interacts with `TtsManager` to play sounds and starts other Activities through the menu.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `isService` | `boolean` | Stores the state of the voice assistant (on/off). | Used in `speak()` and menu item selection. |
| `ttsManager` | `TtsManager` | An object for working with the Text-to-Speech system. | Initialized in `onCreate()`, used in `speak()`. |

*   **isService:** A flag (true/false) that tells the application whether it is necessary to voice actions.
*   **ttsManager:** A tool that converts text into human speech.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Return value:** `void` (returns nothing)
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `savedInstanceState` | `Bundle` | Object for restoring state after a restart. |
*   **Logic:**
    1. Calls the parent class constructor (`super.onCreate`).
    2. Receives a singleton instance of `TtsManager` to work with sound.
*   **When called:** Automatically by the Android system when creating the Activity.

### Method name: `onCreateOptionsMenu`
*   **Type:** `public`
*   **Return value:** `boolean` (true if the menu should be displayed)
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `menu` | `Menu` | Object into which the menu is loaded. |
*   **Logic:**
    1. Loads the menu layout from the `main.xml` resource.
    2. Makes icons in the menu visible.
*   **When called:** When the user opens the Activity to display the menu in the top bar.

### Method name: `onOptionsItemSelected`
*   **Type:** `public`
*   **Return value:** `boolean`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `item` | `MenuItem` | The specific menu item that was clicked. |
*   **Logic:** Checks which button was pressed (Back, Exit, Voice On, etc.) and performs the corresponding action (closes the screen, opens a new one, or toggles `isService`).
*   **When called:** When the user clicks on any item in the top menu.

### Method name: `speak`
*   **Type:** `protected`
*   **Return value:** `void`
*   | Name | Type | Description |
    | :--- | :--- | :--- |
    | `message` | `String` | The text that needs to be voiced. |
*   **Logic:** If `isService` is `true`, it sends the text to `ttsManager` for playback.
*   **What is important:** If the user has turned off the voice assistant, this method will do nothing.

________________________________________
## 4. Lifecycle (Activity)
*   **onCreate():** Called when the activity starts. Here we initialize the common voice assistant tool.
*   **onDestroy():** Called when the activity is destroyed. It contains a placeholder for shutting down the assistant to save memory.

________________________________________
## 5. Interface Interaction (UI)
*   The class works with the **Options Menu** (the three dots or top bar buttons).
*   It handles clicks on menu icons to navigate the app.

________________________________________
## 6. Interaction with other components
*   **Intents:** Used to transition to `Credits`, `Guide`, `InnerContacts`, and `Emergency`.
*   **finish():** Closes the current screen.
*   **finishAffinity():** Completely exits the application.

________________________________________
## 7. General logic of the class
This class acts as a "glue". Instead of writing the menu code in 10 different files, we write it once in `BaseActivity`, and other classes simply "inherit" this behavior. If we turn on the voice assistant on one screen, the `isService` flag will control speech on subsequent screens.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Imagine that `BaseActivity` is a **universal remote control**. Every screen in your app is a different TV, but they all use the same remote. This remote has buttons for "Menu", "Back", "Exit", and a "Voice" button. Instead of giving each TV its own remote, we made one master remote that works for everyone.

**Analogy:** This is like a school uniform. All students wear it, so we don't have to describe what each student is wearing separately. We just say "they are in uniform", and everyone knows they have a white shirt and dark pants. `BaseActivity` is that "uniform" for our screens.
