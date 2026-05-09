# Class Description: InnerContacts

________________________________________
## 1. General Information
*   **Class Name:** `InnerContacts`
*   **Type:** Activity
*   **Purpose:** This screen is the contact manager of the application. It allows the user to maintain a private list of "Inner Contacts" (saved within the app) and also view all contacts stored on the phone.
*   **Interaction:** Inherits from `BaseActivity`. It communicates with the phone's system address book using a `ContentResolver` and with the app's private database using `HelperDB`.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `recyclerViewContacts`| `RecyclerView` | Displays the list of contacts. | Initialized in `initViews()`. |
| `progressBar` | `ProgressBar` | Shows a spinner while loading contacts. | In `setLoading()`. |
| `helperDB` | `HelperDB` | Accesses the app's local database. | In `displayInnerContacts()`. |
| `currentContact` | `myContact` | Stores the specific contact the user is currently interacting with. | Used in the contact dialog. |

*   **ContentResolver:** An Android tool that allows one app to read data from another app (like reading the phone's contacts).
*   **myContact:** A custom data class representing a single person (ID, Name, Phone, Email, Comment).

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:**
    1. Sets up the layout.
    2. Requests permissions to read contacts and make phone calls.
    3. Sets up buttons to toggle between "Inner" and "Total" contacts.
*   **When called:** When the user selects "Contacts" from the main menu.

### Method name: `displayInnerContacts`
*   **Type:** `private`
*   **Logic:**
    1. Reads all records from the `myContacts` table in the local database.
    2. Converts database rows into a list of `myContact` objects.
    3. Updates the screen using the `ContactAdapter`.
*   **What is important:** It runs in a background thread so the app doesn't freeze.

### Method name: `displayDeviceContacts`
*   **Type:** `private`
*   **Logic:**
    1. Asks the Android system for a list of all names and phone numbers stored on the device.
    2. Formats them as "Name: Number".
    3. Displays them in the same list as the inner contacts.

### Method name: `showContactDialog`
*   **Type:** `private`
*   **Logic:**
    1. Creates a custom pop-up window (`AlertDialog`) using a special layout (`mycontact_dialog.xml`).
    2. Provides buttons for: **Call**, **SMS**, **Email**, **Update**, and **Delete**.
*   **When called:** When the user taps on an "Inner Contact" in the list.

### Method name: `makeCall` / `sendSms` / `sendEmail`
*   **Type:** `private`
*   **Logic:** These methods use **Intents** to tell the Android system to:
    *   Open the phone dialer and start a call.
    *   Open the SMS app with a pre-written message.
    *   Open the Email app with the contact's address filled in.

________________________________________
## 4. Lifecycle
*   **onCreate():** Prepares the screen and default contact list.
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   **RecyclerView:** Displays either internal or system contacts.
*   **Buttons:** `bInner` (Show app contacts), `bTotal` (Show phone contacts).
*   **Custom Dialog:** Shows detailed options for an individual contact.

________________________________________
## 6. Interaction with other components
*   **ContentResolver:** To access system contacts.
*   **HelperDB / SQLite:** To manage the app's private contact list.
*   **Intents:** To start external communication apps (Phone, SMS, Email).

________________________________________
## 7. General logic of the class
The class provides a **dual-view address book**. You can manage your "important" contacts separately inside the app, but you can also quickly check your entire phone list. For internal contacts, it provides a full suite of communication tools (Call/Text/Email) at the touch of a button.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of `InnerContacts` as a **VIP list** in your phone.
*   You have your "Main phone book" (Total Contacts).
*   And you have your "VIP list" (Inner Contacts) saved specifically for this app.
*   When you click on a person in your VIP list, the app asks: "Do you want to call them, send a message, or write an email?"

**Analogy:** It's like having a **refrigerator door** where you stick important phone numbers. You can look at the big phone book if you need to, but the most important ones are right there in front of you with quick actions.
