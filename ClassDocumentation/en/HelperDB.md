# Class Description: HelperDB

________________________________________
## 1. General Information
*   **Class Name:** `HelperDB`
*   **Type:** Database Helper (inherits from `SQLiteOpenHelper`)
*   **Purpose:** This class is responsible for creating and maintaining the local database of the application. It defines the structure (tables and columns) where all information about places and contacts is stored.
*   **Interaction:** It is used by various Activities (like `AddPlace`, `ViewPlaces`, `AddInnerContact`) to save and retrieve data. Think of it as the "librarian" who knows exactly how the books (data) are organized on the shelves.

________________________________________
## 2. Variables (Class Fields)
The class uses many `public static final String` constants. These are fixed names for the database, tables, and columns to avoid typos in the code.

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `DB_NAME` | `String` | The physical name of the database file (`info.db`). | In the constructor. |
| `TABLE_MY_CONTACT`| `String` | The name of the table for personal contacts. | In `onCreate()` and data operations. |
| `TABLE_PLACE` | `String` | The name of the table for saved places. | In `onCreate()` and data operations. |
| `PLACE_NAME`, etc. | `String` | Names of specific columns (fields) in the tables. | Used to define the structure and query data. |

*   **Constants:** Using constants ensures that if we change a column name, we only do it in one place.

________________________________________
## 3. Class Methods

### Method name: `HelperDB` (Constructor)
*   **Type:** `public`
*   **Return value:** N/A (Constructor)
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `context` | `Context` | The environment of the application. |
*   **Logic:** Initializes the database helper with the database name (`info.db`) and version (1).
*   **When called:** When an Activity needs to work with the database for the first time.

### Method name: `onCreate`
*   **Type:** `public`
*   **Return value:** `void`
*   **Parameters:**
    | Name | Type | Description |
    | :--- | :--- | :--- |
    | `db` | `SQLiteDatabase`| The database object to execute SQL commands. |
*   **Logic:**
    1. It constructs SQL queries (`CREATE TABLE`) as strings.
    2. It executes these queries to create two tables: one for contacts and one for places.
    3. Each table has specific columns (e.g., Name, Comment, GPS, Image).
*   **When called:** Only once, the very first time the app is launched or the database is created.
*   **What is important:** If the table already exists, it won't be recreated because of the `IF NOT EXISTS` command.

### Method name: `onUpgrade`
*   **Type:** `public`
*   **Return value:** `void`
*   **Parameters:** `db`, `oldVersion`, `newVersion`.
*   **Logic:** Currently empty. It is intended for when we change the database structure in future app updates.
*   **When called:** When the version number passed in the constructor is increased.

________________________________________
## 4. Lifecycle
`HelperDB` doesn't have a standard Activity lifecycle. However, it follows the **Database Lifecycle**:
1. **Creation:** `onCreate` is called if the file doesn't exist.
2. **Opening:** The database is opened for reading or writing.
3. **Closing:** The connection should be closed when work is finished (usually handled by the OS or manually in Activities).

________________________________________
## 5. Interaction with other components
*   **Activities:** Use this class to get a "writable" or "readable" database.
*   **SQL:** This class is the primary place where raw SQL commands (like `CREATE TABLE`) are written.

________________________________________
## 6. General logic of the class
The class serves as the **architect** of the data. It doesn't perform the actual saving of a place; instead, it defines *how* a place should be saved. It says: "If you want to save a place, I have a table called `Places` with columns for name, comment, image, link, and GPS."

________________________________________
## 7. Simplified explanation
**Explanation in simple words:**
Imagine `HelperDB` is a **blank notebook** that comes with the app.
*   When you open the app for the first time, `HelperDB` draws lines and headers on the pages (this is `onCreate`).
*   It creates one section for "My Contacts" and another section for "Places I Visited".
*   Whenever you want to write something down, you ask this notebook to show you where the correct columns are.

**Analogy:** It's like a **filing cabinet**. `HelperDB` is the person who assembles the cabinet, labels the drawers (tables), and puts folders (columns) inside. It doesn't put the papers inside yet; it just makes sure the cabinet is ready for use.
