# Class Description: AddInnerContact

________________________________________
## 1. General Information
*   **Class Name:** `AddInnerContact`
*   **Type:** Activity
*   **Purpose:** This screen allows the user to add a new person to their private "Inner Contacts" list or update the details of an existing contact.
*   **Interaction:** Inherits from `BaseActivity`. It interacts with `HelperDB` to save data to the SQLite database and receives data from `InnerContacts` if it is performing an update.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `etAddName` | `EditText` | Input field for the contact's name. | In `saveContactInBackground()`. |
| `etAddPhone` | `EditText` | Input field for the phone number. | In `saveContactInBackground()`. |
| `etAddEmail` | `EditText` | Input field for the email address. | In `saveContactInBackground()`. |
| `etAddComment`| `EditText` | Input field for any extra notes. | In `saveContactInBackground()`. |
| `contactToUpdate`| `myContact` | Stores the existing contact data if we are in "Update" mode. | Checked in `onCreate()`. |
| `helperDB` | `HelperDB` | The database assistant. | Used to write to the table. |

*   **Serializable:** A way to package the `myContact` object so it can be passed through an `Intent` from one screen to another.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:**
    1. Initializes the UI.
    2. Checks if an existing contact was sent to this screen (for updating).
    3. If yes, it calls `populateFieldsForUpdate()` to fill the text boxes.
    4. Sets up the click listener for the "Save/Update" button.
*   **When called:** When the user clicks "Add" or "Update" in the contacts manager.

### Method name: `populateFieldsForUpdate`
*   **Type:** `private`
*   **Logic:** Takes the data from `contactToUpdate` and puts it into the `EditText` fields so the user doesn't have to re-type everything. It also changes the screen title to "Update".

### Method name: `saveContactInBackground`
*   **Type:** `private`
*   **Logic:**
    1. Validates that all fields are filled.
    2. Starts a background thread.
    3. If we are updating an existing contact, it first deletes the old record from the database.
    4. Generates a new random ID for the contact.
    5. Uses `ContentValues` to package the new information.
    6. Inserts the new record into the `myContacts` table.
    7. Closes the database and returns to the main thread to show a success message and close the screen.
*   **What is important:** The "Update" logic here works by deleting the old version and inserting a new one.

________________________________________
## 4. Lifecycle
*   **onCreate():** Sets up the form.
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   **EditTexts:** Four fields for Name, Phone, Email, and Comment.
*   **Button:** `bAdd` - changes its text based on whether we are adding or updating.

________________________________________
## 6. Interaction with other components
*   **Intent:** Receives a `myContact` object via `EXTRA_CONTACT`.
*   **SQLite:** Writes records to the `TABLE_MY_CONTACT` through `HelperDB`.

________________________________________
## 7. General logic of the class
The class is a **form handler**. It takes user input from the screen, organizes it into a format the database understands, and stores it permanently. It is smart enough to know if it's creating a brand-new person or just fixing the information of someone already in the list.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of `AddInnerContact` as a **blank business card**.
*   You write down the person's name, phone, and email on the card.
*   When you are done, you "staple" the card into your private address book (`HelperDB`).
*   If you want to change something later, the app brings that card back out, lets you erase and rewrite the info, and then staples it back in.

**Analogy:** It's like **filling out a registration form**. You enter your details, and once you hit "Submit", the information is sent to the office (the database) to be filed away.
