# Class Description: LogOrReg

________________________________________
## 1. General Information
*   **Class Name:** `LogOrReg`
*   **Type:** Activity
*   **Purpose:** This screen acts as the "Gatekeeper" of the application. It handles both user registration (setting a password for the first time) and login (verifying the password to enter the app).
*   **Interaction:** Inherits from `BaseActivity`. It uses the `Security` class to handle encryption and `MainChoise` to proceed into the app.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `security` | `Security` | The logic tool that handles password encryption. | Initialized in `onCreate()`. |
| `isRegisterMode` | `boolean` | A flag that tells if we are creating a new user or logging in an old one. | Used in the button click logic. |
| `etPassword` | `EditText` | The input field for the password. | To get user input. |
| `bLR` | `Button` | The main button (Login or Register). | To trigger the verification. |

*   **Encryption:** The process of turning a readable password (like "1234") into a secret code that only the app can understand.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:**
    1. Initializes the `Security` system.
    2. Calls `setupMode()` to decide if the screen should say "Register" or "Login".
    3. Sets up the click listener for the button to start the authentication process.
*   **When called:** When the user enters the login/registration screen.

### Method name: `setupMode`
*   **Type:** `private`
*   **Logic:** Checks with the `Security` class if a password has already been saved on this phone.
    *   **If No:** Switches to **Register Mode** (tells the user to create a password).
    *   **If Yes:** Switches to **Login Mode** (tells the user to enter their existing password).
*   **When called:** During screen initialization.

### Method name: `handleRegistration`
*   **Type:** `private`
*   **Logic:**
    1. Shows a confirmation pop-up to make sure the user didn't make a typo.
    2. Uses the `Security` class to scramble (encrypt) the password and save it in the phone's memory.
    3. Moves to the main menu.

### Method name: `handleLogin`
*   **Type:** `private`
*   **Logic:**
    1. Uses the `Security` class to "unscramble" (decrypt) the saved password.
    2. Compares what the user typed with the saved password.
    3. If they match, the app opens. If not, it shows an "Incorrect Password" error.

________________________________________
## 4. Lifecycle
*   **onCreate():** Determines the state of the app (New User vs. Returning User).
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   **EditText:** For entering the password (hidden characters).
*   **Button:** Changes its label between "Login" and "Register".
*   **AlertDialog:** Used to confirm the new password during registration.

________________________________________
## 6. Interaction with other components
*   **Security:** This is the most important interaction. This class handles all the heavy math and "secret" storage.
*   **MainChoise:** The destination Activity after successful authentication.

________________________________________
## 7. General logic of the class
The class is a **switch**.
1. It looks at the phone's memory.
2. It decides if you are a "New Friend" or a "Returning Friend".
3. It asks for a password.
4. It either saves the password or checks it against the saved one.
5. If everything is okay, it opens the main door to the app.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `LogOrReg` class as a **smart lock** on your front door.
*   If you just bought the lock, it asks you to **"Set your secret code"** (Registration).
*   If you already set a code, it asks you to **"Enter your secret code"** (Login).
*   If you type the right numbers, the door opens (`MainChoise`). If you type the wrong numbers, it stays locked.

**Analogy:** It's like the **lock screen on your phone**. The first time you get the phone, you pick a PIN. Every time after that, you have to type that same PIN to see your apps.
