# Class Description: Guide

________________________________________
## 1. General Information
*   **Class Name:** `Guide`
*   **Type:** Activity
*   **Purpose:** This screen provides a user manual for the application. It displays different sections of the guide that can be expanded or collapsed (like an accordion).
*   **Interaction:** Inherits from `BaseActivity`. It reads text files from the app's internal resources (`res/raw`) and dynamically creates buttons and text fields on the screen.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `LL` | `LinearLayout` | A vertical container where buttons and text are added. | In `initViews()` and `buildAccordion()`. |
| `files` | `List<String>` | A list of filenames containing the guide's content. | Populated in `buildListOfFiles()`. |
| `btns` | `List<Button>` | A list of buttons created for each section. | Managed in `buildAccordion()` and `onClick()`. |
| `tvs` | `List<TextView>` | A list of text views that hold the guide's text. | Managed in `buildAccordion()` and `toggleSection()`. |

*   **List:** A dynamic collection used to store multiple items of the same type.
*   **LinearLayout:** A layout that arranges its children in a single column or row.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:**
    1. Sets the layout.
    2. Initializes the container (`LL`).
    3. Loads the list of available guide files from the resources.
    4. Builds the visual "accordion" interface.
*   **When called:** When the user selects "Guide" from the menu.

### Method name: `buildAccordion`
*   **Type:** `private`
*   **Logic:**
    1. Loops through the list of filenames.
    2. For each file, it creates a new `Button` (e.g., "Guide's part #1").
    3. It also creates a `TextView` below each button, sets its visibility to `GONE` (hidden), and adds both to the layout.
*   **When called:** During initialization in `onCreate`.

### Method name: `toggleSection`
*   **Type:** `private`
*   **Parameters:** `int index` (which part was clicked).
*   **Logic:**
    1. Checks if the `TextView` at that index is currently visible.
    2. If it's visible, it hides it (`GONE`).
    3. If it's hidden, it loads the text from the file, displays it (`VISIBLE`), and uses the voice assistant to read it aloud.
*   **When called:** When the user clicks on one of the guide buttons.

### Method name: `loadContentFromFile`
*   **Type:** `private`
*   **Return value:** `String` (the full text of the file).
*   **Logic:**
    1. Finds the internal ID of the file using its name.
    2. Opens the file as an `InputStream`.
    3. Uses a `BufferedReader` to read the file line-by-line and combine it into one string.
*   **What is important:** If the file is missing or unreadable, it shows a "Toast" error message.

________________________________________
## 4. Lifecycle
*   **onCreate():** Sets up the dynamic interface.
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   **LinearLayout:** The vertical "stack" where all elements are placed.
*   **Dynamic Buttons:** Created in code, they act as headers for each guide section.
*   **Dynamic TextViews:** Created in code, they appear and disappear to show content.

________________________________________
## 6. Interaction with other components
*   **Resources (R.raw):** The app reads local text files stored in the `raw` folder.
*   **TtsManager:** Used to provide an audio version of the guide.

________________________________________
## 7. General logic of the class
The class acts as a **dynamic generator**. Instead of hardcoding 10 buttons in XML, it looks at a list of files (`guide_all`) and builds exactly as many buttons as there are files. It saves memory by only loading the text into the screen when the user actually clicks to see a specific part.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `Guide` class as a **set of drawers**.
*   Each drawer has a label (the button).
*   When you open the drawer (click the button), the app "reads" the paper inside and shows it to you.
*   If you close the drawer, the paper is hidden to keep the desk tidy.

**Analogy:** It's like a **folding map**. You only unfold the part you need to look at right now, so the whole map doesn't cover your entire view.
