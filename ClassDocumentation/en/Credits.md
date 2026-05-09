# Class Description: Credits

________________________________________
## 1. General Information
*   **Class Name:** `Credits`
*   **Type:** Activity
*   **Purpose:** This screen provides information about the developer and offers a way for the user to reach out for help or support.
*   **Interaction:** Inherits from `BaseActivity`. It uses Android Intents to launch external communication apps (SMS and Email).

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `MY_PHONE_NUMBER` | `String` | The developer's contact phone number. | In the SMS intent. |
| `MY_EMAIL_ADDRESS`| `String` | The developer's support email address. | In the Email intent. |

*   **Constants:** These are fixed values that do not change during the app's execution.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:**
    1. Sets the layout (`activity_credits.xml`).
    2. Initializes two buttons: `bSmsMe` and `bMailMe`.
    3. **SMS Logic:** When clicked, it creates an intent with the action `SENDTO` and the developer's phone number. It also adds a default help message.
    4. **Email Logic:** When clicked, it creates an intent with the developer's email address, a subject line, and a body text.
    5. It checks if the phone actually has an app that can handle these requests before trying to open them.
*   **When called:** When the user clicks "Credits" in the menu.

### Method name: `onCreateOptionsMenu`
*   **Type:** `public`
*   **Logic:** This method overrides the base menu. It hides the "Credits" button from its own screen so the user doesn't try to open the screen they are already on.

________________________________________
## 4. Lifecycle
*   **onCreate():** Sets up the contact buttons.
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   **Buttons:** `bSMSme` and `bMailMe`.
*   **Toast:** Shows an error message if no SMS/Email app is found on the device.

________________________________________
## 6. Interaction with other components
*   **Intent (ACTION_SENDTO):** The standard way to ask the Android system to "send something to someone" via a specific protocol (like `smsto:` or `mailto:`).

________________________________________
## 7. General logic of the class
The class is a **contact portal**. It doesn't contain complex algorithms; it simply holds the developer's contact information and provides shortcuts for the user to send messages without having to type or copy addresses.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `Credits` class as a **business card** with two buttons on it.
*   One button says: **"Send me a text message"**.
*   The other says: **"Write me an email"**.
*   When you press a button, the app opens your phone's messaging app and types the address for you, so you only have to hit "Send".

**Analogy:** It's like the **"Help" button** in an elevator. You don't need to know who is on the other end or what their number is; you just press it, and the system connects you to the right person.
