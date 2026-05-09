# Class Description: Place

________________________________________
## 1. General Information
*   **Class Name:** `Place`
*   **Type:** Model Class / Data Class (implements `Serializable`)
*   **Purpose:** This class represents a single location or "place" in the application. It acts as a container for all the information related to a specific place (name, description, photo, etc.).
*   **Interaction:** It is used by almost all parts of the app: `AddPlace` creates objects of this class, `HelperDB` stores them, and `ViewPlaces` displays them using a list.

________________________________________
## 2. Variables (Class Fields)
All variables are `private`, which means they are protected and can only be accessed through special methods (getters and setters). This is called **Encapsulation**.

| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `placeName` | `String` | The name or title of the place. | Displayed in lists and titles. |
| `placeComment` | `String` | Notes or description of the place. | Shown in the detailed view. |
| `placeImage` | `byte[]` | The photo of the place, stored as a sequence of bytes. | Used to display the image. |
| `placeLink` | `String` | A website link related to the place. | Used for external information. |
| `placeGPS` | `String` | Coordinates (Latitude/Longitude). | Used to show location on a map. |

*   **byte[]:** An array of small numbers that represent the pixels of an image.
*   **Serializable:** A special "tag" that allows the object to be easily converted into a format that can be sent between different screens (Activities).

________________________________________
## 3. Class Methods

### Method name: `Place` (Constructor)
*   **Type:** `public`
*   **Return value:** N/A
*   **Parameters:** All the fields mentioned above (Name, Comment, Image, Link, GPS).
*   **Logic:** When we say `new Place(...)`, this method takes all the provided information and "fills" the variables of the new object.
*   **When called:** When the user saves a new place or when we load a place from the database.

### Method name: `getPlaceName` / `setPlaceName` (Getters and Setters)
*   **Type:** `public`
*   **Return value:** `String` (for getter) / `void` (for setter)
*   **Logic:**
    *   **Getter:** Returns the value of the variable (e.g., "Give me the name").
    *   **Setter:** Changes the value of the variable (e.g., "Change the name to 'Paris'").
*   **When called:** When we need to read or update specific data about a place.

### Method name: `toString`
*   **Type:** `public`
*   **Return value:** `String`
*   **Logic:** Converts all the information about the place into one long text string.
*   **When called:** Usually for debugging, to see what is inside the object in the console.

________________________________________
## 4. Lifecycle
This is a standard class, so it doesn't have an Android Lifecycle. It exists in the computer's memory as long as the application needs it.

________________________________________
## 5. Interaction with other components
*   **SQLite:** Data from this class is mapped to the columns in the `HelperDB`.
*   **Intents:** Objects of this class can be passed between Activities because they are `Serializable`.

________________________________________
## 6. General logic of the class
This class is like a **form** or a **template**. It doesn't "do" anything active; it just holds information in an organized way. If the app is a library, the `HelperDB` is the shelf, and the `Place` object is a single book.

________________________________________
## 7. Simplified explanation
**Explanation in simple words:**
Think of the `Place` class as a **digital postcard**.
*   On the front, there is a **photo** (`placeImage`).
*   On the back, there is a **title** (`placeName`) and a **description** (`placeComment`).
*   In the corner, there are the **GPS coordinates** of where the photo was taken.
*   Whenever the app needs to handle a location, it just grabs this "postcard" and reads what's written on it.

**Analogy:** It's like a **folder** in a filing cabinet. The folder has labels on the outside and documents inside. The `Place` class is the structure of that folder.
