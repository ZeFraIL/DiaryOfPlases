# Class Description: Emergency

________________________________________
## 1. General Information
*   **Class Name:** `Emergency`
*   **Type:** Activity
*   **Purpose:** This screen provides a quick way for the user to call emergency services (Police, Ambulance, Fire) directly from the application.
*   **Interaction:** Inherits from `BaseActivity`. It interacts with the phone's calling system through Android Intents and requires special system permissions.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `requestPermissionLauncher` | `ActivityResultLauncher` | A tool to ask the user for permission to make phone calls. | Initialized as a field, launched in `requestCallPermission()`. |

*   **Permissions:** Making a phone call automatically is a "dangerous" permission in Android, so the app must ask for explicit consent from the user at runtime.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:**
    1. Sets the layout (`activity_emergency.xml`).
    2. Calls `requestCallPermission()` to ensure the app has the right to call.
    3. Sets up three buttons that call `callNow()` with the numbers "100", "101", and "102".
*   **When called:** When the user clicks "Emergency" in the menu.

### Method name: `requestCallPermission`
*   **Type:** `private`
*   **Logic:** Checks if the `CALL_PHONE` permission is already granted. If not, it launches the permission request dialog.
*   **When called:** Automatically during `onCreate`.

### Method name: `callNow`
*   **Type:** `private`
*   **Parameters:** `String phoneNumber` (e.g., "100").
*   **Logic:**
    1. Uses the voice assistant to say "Calling now...".
    2. Checks if permission is granted.
    3. If yes, it creates an `Intent` with the action `ACTION_CALL` and the phone number.
    4. Starts the activity, which opens the phone's calling interface and starts dialing.
*   **When called:** When the user clicks one of the emergency buttons.

________________________________________
## 4. Lifecycle
*   **onCreate():** Initializes UI and handles permissions.
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   **Buttons:** `bCallPolice`, `bCallAmbulance`, `bCallFire`.
*   **Event:** Clicks on these buttons trigger the `callNow` method.

________________________________________
## 6. Interaction with other components
*   **Intent (ACTION_CALL):** Used to communicate with the Android OS system dialer.
*   **Uri:** Used to format the phone number as `tel:100`.

________________________________________
## 7. General logic of the class
The class is designed for **speed**. It asks for permission as soon as it opens. When a button is pressed, it immediately initiates a call to a pre-defined number. It also uses the voice assistant to confirm the action to the user.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `Emergency` class as a **speed-dial panel** on a public phone.
*   It has three big buttons for the most important services.
*   Before you can use them, the phone asks: "Do you allow this app to make calls?"
*   Once you say yes, one tap on a button starts the call immediately.

**Analogy:** It's like the **Red Button** behind glass. You break the glass (grant permission) and push the button to get help instantly.
