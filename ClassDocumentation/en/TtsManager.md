# Class Description: TtsManager

________________________________________
## 1. General Information
*   **Class Name:** `TtsManager`
*   **Type:** Utility Class / Singleton
*   **Purpose:** This class manages the "Text-to-Speech" (TTS) functionality. Its job is to turn written text into spoken words to help the user navigate the app without looking at the screen.
*   **Interaction:** It is used by `BaseActivity`, which means any screen in the app can use it to "talk" to the user. It is a **Singleton**, meaning the app only creates one of these objects to save memory.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `instance` | `static TtsManager` | Holds the one and only instance of this class. | In `getInstance()`. |
| `tts` | `TextToSpeech` | The actual Android system tool that does the speaking. | Initialized in the constructor. |
| `isInitialized` | `boolean` | A flag that tells us if the speaker is ready to talk. | Checked in `speak()`. |

*   **Singleton Pattern:** A design rule that ensures a class has only one object. This is important for things like speakers or databases so they don't fight over the same resource.
*   **Locale:** A setting that tells the app which language to use (English, Russian, etc.).

________________________________________
## 3. Class Methods

### Method name: `getInstance`
*   **Type:** `public static synchronized`
*   **Return value:** `TtsManager`
*   **Parameters:** `Context context`.
*   **Logic:** If the manager hasn't been created yet, it creates it. If it already exists, it just gives you the existing one.
*   **When called:** Every time an Activity starts and wants to access voice assistance.

### Method name: `onInit`
*   **Type:** `public`
*   **Parameters:** `int status` (Success or Failure).
*   **Logic:**
    1. Checks if the Android TTS system started successfully.
    2. If yes, it sets the language to match the phone's settings and sets `isInitialized` to `true`.
*   **When called:** Automatically by Android once the speech engine is ready.

### Method name: `speak`
*   **Type:** `public`
*   **Parameters:** `String text` (What to say).
*   **Logic:**
    1. Checks if the system is initialized and the text is not empty.
    2. Tells the phone to speak the text.
    3. `QUEUE_FLUSH` means: "If you are already saying something, stop and say this new thing immediately."
*   **When called:** Whenever an Activity calls `speak("Hello")`.

### Method name: `shutdown`
*   **Type:** `public`
*   **Logic:** Stops the speaking and releases the system resources to save battery and memory.
*   **When called:** Usually when the app is being completely closed.

________________________________________
## 4. Lifecycle
This class is not an Activity, so it doesn't have a standard lifecycle. Instead, it lives from the moment it is first called until the app is closed.

________________________________________
## 5. Interface Interaction (UI)
*   This class has no visual interface. It only provides audio feedback.

________________________________________
## 6. Interaction with other components
*   **Android TTS System:** It acts as a wrapper around the system's voice engine.
*   **BaseActivity:** Provides the `speak` capability to all inherited Activities.

________________________________________
## 7. General logic of the class
The class acts as a **translator** and **announcer**. It waits for the system to be ready, listens for requests from the Activities, and converts those requests into sound. Because it's a Singleton, it acts like a single "voice" for the entire app.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of `TtsManager` as a **dedicated reading robot** living inside your phone.
*   When the app starts, the robot gets out its glasses and dictionary (`onInit`).
*   Whenever you click a button, the Activity hands a piece of paper to the robot and says, "Read this out loud!" (`speak`).
*   The robot reads it and then waits for the next paper.

**Analogy:** It's like a **navigator in a car**. There is only one navigator, and they wait for the driver (the Activity) to ask for directions before they start talking.
