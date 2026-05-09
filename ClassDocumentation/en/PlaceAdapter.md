# Class Description: PlaceAdapter

________________________________________
## 1. General Information
*   **Class Name:** `PlaceAdapter`
*   **Type:** RecyclerView Adapter
*   **Purpose:** This class acts as a bridge (or translator) between the list of `Place` objects (the data) and the `RecyclerView` (the visual list on the screen). Its job is to take each place from the list and decide how it should look in a single row of the list.
*   **Interaction:** It works closely with `ViewPlaces` (the screen) and `Place` (the data). It also uses an **Interface** called `OnItemClickListener` to notify the screen when the user taps on a specific item.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `places` | `List<Place>` | The source list of all places to be displayed. | Used in `onBindViewHolder()` and `getItemCount()`. |
| `listener` | `OnItemClickListener` | An object that handles what happens when a row is clicked. | Used in `bind()` within the ViewHolder. |

*   **Adapter:** A design pattern used to connect incompatible interfaces. In Android, it connects data to a scrolling list.
*   **Interface:** A "contract" that defines what methods another class must have (like a rulebook).

________________________________________
## 3. Class Methods

### Method name: `onCreateViewHolder`
*   **Type:** `public`
*   **Return value:** `PlaceViewHolder`
*   **Logic:**
    1. Uses a `LayoutInflater` to "inflate" (turn into code) the XML layout for a single row (`list_item_place.xml`).
    2. Returns a new `PlaceViewHolder` object that holds the views for that row.
*   **When called:** When the `RecyclerView` needs a new row to be created (e.g., when the list first loads).

### Method name: `onBindViewHolder`
*   **Type:** `public`
*   **Logic:**
    1. Finds the specific `Place` object for the current row position.
    2. Gives this data to the `ViewHolder` to update the screen.
*   **When called:** When a row becomes visible and needs to show its specific content (e.g., when scrolling).

### Method name: `getItemCount`
*   **Type:** `public`
*   **Return value:** `int` (Total number of places).
*   **Logic:** Tells the `RecyclerView` how many items are in the list.

### Inner Class: `PlaceViewHolder`
*   **Purpose:** This class acts as a "holder" for the views inside a single row (like the title text). It prevents the app from having to constantly search for the views using `findViewById`, which makes the scrolling much smoother.
*   **Method `bind`:** Sets the text of the `TextView` to the name of the place and sets a click listener on the whole row.

________________________________________
## 4. Lifecycle
The adapter is managed by the `RecyclerView`. It lives as long as the list is on the screen.

________________________________________
## 5. Interface Interaction (UI)
*   **LayoutInflater:** Converts XML into Java View objects.
*   **TextView:** Displays the place name in the list.
*   **ItemView:** The entire row that can be clicked.

________________________________________
## 6. Interaction with other components
*   **RecyclerView:** The main list component that controls the adapter.
*   **Place:** The data source.
*   **OnItemClickListener:** Communicates clicks back to `ViewPlaces`.

________________________________________
## 7. General logic of the class
Imagine you have a deck of cards (`places`). You have a person (`PlaceAdapter`) whose job is to take one card at a time and put it into a special frame (`ViewHolder`). The frame is then hung on a wall (`RecyclerView`). If you scroll, the person takes a card out of a frame that left the wall and puts a new card in it to hang it back on the wall.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of `PlaceAdapter` as a **menu designer**.
*   The data is the **list of ingredients** (names of places).
*   The `ViewHolder` is a **blank menu card**.
*   The `PlaceAdapter` takes an ingredient and writes it neatly onto the menu card (`bind`).
*   The `RecyclerView` is the **menu holder** on the table that shows all the cards to the customer.

**Analogy:** It's like a **translator** at a conference. The speaker (data) speaks in one language, and the translator (adapter) converts it into a visual language (the list on screen) so the audience (user) can understand it.
