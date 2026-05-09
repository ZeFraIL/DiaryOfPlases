# Class Description: myContact

________________________________________
## 1. General Information
*   **Class Name:** `myContact`
*   **Type:** Model Class / Data Class (implements `Serializable`)
*   **Purpose:** This class represents a single person in the user's private contact list. It stores their name, phone number, email, and any notes the user wants to keep about them.
*   **Interaction:** It is used by `InnerContacts` to display people in a list and by `AddInnerContact` to create or update their information. It is also stored in the database through `HelperDB`.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `contactID` | `String` | A unique identifier for the contact. | Used to delete or update specific rows in the database. |
| `contactName` | `String` | The person's full name. | Displayed in lists and dialogs. |
| `contactPhone`| `String` | Their phone number. | Used to make calls or send SMS. |
| `contactEmail`| `String` | Their email address. | Used to send emails. |
| `contactComment`| `String` | Extra notes about the person. | Shown in the detailed view. |

*   **Serializable:** Allows the entire contact object to be sent as a "package" between different screens.

________________________________________
## 3. Class Methods

### Method name: `myContact` (Constructor)
*   **Type:** `public`
*   **Logic:** When we create a new contact in the code, this method takes all the information (ID, name, etc.) and saves it into the object's variables.

### Method name: `get...` / `set...` (Getters and Setters)
*   **Type:** `public`
*   **Logic:** These methods allow other parts of the app to read the contact's data (`get`) or change it (`set`). For example, `getContactPhone()` returns the person's number.

### Method name: `toString`
*   **Type:** `public`
*   **Return value:** `String`
*   **Logic:** Combines all the person's info into a single block of text separated by new lines.
*   **When called:** Used in `InnerContacts` to show the person's details in a pop-up window or for voice assistance.

________________________________________
## 4. Lifecycle
This is a data class, so it doesn't have an Android lifecycle. It exists in the phone's memory as long as the app is using that specific contact's information.

________________________________________
## 5. Interaction with other components
*   **SQLite:** Its fields correspond to the columns in the `TABLE_MY_CONTACT`.
*   **AlertDialog:** Its `toString()` method is often used to provide the message body of a pop-up.

________________________________________
## 6. General logic of the class
This class is a **blueprint**. It tells the app: "Every contact must have these five pieces of information." It doesn't perform actions like calling or emailing; it simply holds the data needed to perform those actions.

________________________________________
## 7. Simplified explanation
**Explanation in simple words:**
Think of the `myContact` class as a **physical business card**.
*   The card has lines for a **name**, **phone**, **email**, and **notes**.
*   The `myContact` object is one specific card with someone's name written on it.
*   If you want to know how to reach someone, you grab their "card" and read the phone number.

**Analogy:** It's like a **contact entry** in your physical address book. It just sits there holding the info until you decide to pick up the phone and dial.
