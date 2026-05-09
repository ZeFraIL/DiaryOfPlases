# Class Description: Search

________________________________________
## 1. General Information
*   **Class Name:** `Search`
*   **Type:** Activity
*   **Purpose:** This screen acts as a mini web browser within the app. Its main job is to help the user search for information about a place on Google and "capture" the web address (URL) of the page they found.
*   **Interaction:** Inherits from `BaseActivity`. It is usually called by `AddPlace`. When finished, it returns the URL back to the screen that opened it.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `et` | `EditText` | The text box where the search query is typed. | Initialized in `initElements()`, read in `goSearch()`. |
| `wvs` | `WebView` | The component that displays real websites. | Used to show Google and navigate. |
| `stUrl` | `String` | Stores the search term or the current URL. | Used for loading the web page. |
| `GOOGLE_SEARCH_URL` | `String` | The base address for Google searches. | Combined with the search term. |

*   **WebView:** A special "window" in Android that can load and display internet content just like a browser (Chrome/Safari).
*   **Intent Extra:** A way to send data (like the place name) between two screens.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:** Sets the layout and calls `initElements()`.
*   **When called:** When the user clicks the "Search Link" button in `AddPlace`.

### Method name: `initElements`
*   **Type:** `private`
*   **Logic:**
    1. Receives the search term (e.g., "Eiffel Tower") from the previous screen.
    2. Puts that term into the text box.
    3. Configures the `WebView` (enables JavaScript so websites work correctly).
    4. Automatically searches Google for that term.
*   **When called:** During the initial setup of the screen.

### Method name: `goSearch`
*   **Type:** `public`
*   **Parameters:** `View view` (the Search button).
*   **Logic:**
    1. Reads whatever text is currently in the text box.
    2. Uses the voice assistant to say "Search now for...".
    3. Tells the `WebView` to load a new Google search page with that text.
*   **When called:** When the user manually types something and clicks "Search".

### Method name: `backWithLink`
*   **Type:** `public`
*   **Logic:**
    1. Gets the current web address (URL) of the page the user is looking at.
    2. Creates a "result intent" containing this URL.
    3. Sends it back to the previous screen and closes the search screen.
*   **When called:** When the user clicks the button to "save this link" to the place entry.

### Class name: `MyWebViewClient` (Inner Class)
*   **Purpose:** This small class ensures that when you click a link inside the browser, it stays *inside* our app instead of opening the phone's external Chrome browser.

________________________________________
## 4. Lifecycle
*   **onCreate():** Initializes the browser and starts the first search.
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   **EditText:** For entering search terms.
*   **WebView:** The main area for browsing.
*   **Buttons:** "Search" (to update the page) and "Back with Link" (to return the result).

________________________________________
## 6. Interaction with other components
*   **Intent:** Receives a query via `EXTRA_SEARCH_QUERY` and returns a URL via `EXTRA_RESULT_URL`.
*   **setResult():** This is how the app tells the previous screen: "I'm done, here is the data you asked for".

________________________________________
## 7. General logic of the class
The class is like a **middleman**. You tell it what you want to find, it opens the internet for you, you browse until you find the right page, and then you "grab" the link to that page and bring it back to your diary.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `Search` class as a **built-in magnifying glass** for the internet.
*   You tell the magnifying glass what you are looking for.
*   It shows you the Google search results.
*   When you find the perfect website, you tell the app: "Remember this address!"
*   The app then takes that address and pastes it into your place entry.

**Analogy:** It's like **going to a library** to find a specific book. The `Search` screen is the trip to the library. Once you find the book, you write down its location (the link) and go back home to put it in your notes.
