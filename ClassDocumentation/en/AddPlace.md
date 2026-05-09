# Class Description: AddPlace

________________________________________
## 1. General Information
*   **Class Name:** `AddPlace`
*   **Type:** Activity
*   **Purpose:** This screen allows the user to create a new entry in their "Diary of Places". It collects a name, comment, photo, web link, and GPS coordinates, and then saves them to the local database.
*   **Interaction:** It inherits from `BaseActivity` (getting its menu and voice assistance). It interacts with the Camera, the `Search` screen for links, the `MyLocation` screen for coordinates, and `HelperDB` for saving.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `etName` | `EditText` | Input field for the name of the place. | To get user input and name the image file. |
| `etComment` | `EditText` | Input field for the description. | To get the user's notes. |
| `ivImage` | `ImageView` | Displays the photo taken by the camera. | To show the user the captured image. |
| `stGPS` | `String` | Stores coordinates received from `MyLocation`. | Passed to the `Place` object for saving. |
| `stLink` | `String` | Stores a URL received from the `Search` screen. | Passed to the `Place` object for saving. |
| `imageUri` | `Uri` | The internal path where the photo will be saved. | Used when launching the camera. |

*   **Uri:** A "Uniform Resource Identifier" - basically a digital address to a file on the phone.
*   **ActivityResultLauncher:** A modern tool to start another Activity (like Camera or Search) and wait for it to return a result (like a photo or a link).

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:**
    1. Initializes UI elements.
    2. Requests needed permissions (Camera, Storage).
    3. Sets up click listeners for the Image (to take a photo), Link button (to search), GPS button (to get location), and Save button.
*   **When called:** When the user enters the "Add Place" screen.

### Method name: `createImageUri`
*   **Type:** `private`
*   **Return value:** `Uri` (path to the new file)
*   **Logic:** Generates a unique filename based on the place name and current date, creates a temporary file in the phone's pictures folder, and returns its URI.
*   **What is important:** It uses `FileProvider` to safely share the file path with the camera app.

### Method name: `confirmAndSavePlace`
*   **Type:** `private`
*   **Logic:**
    1. Validates that Name, Comment, and Photo are not empty.
    2. Shows a confirmation dialog (`AlertDialog`).
    3. If the user clicks "Ok", it triggers the background saving process.
*   **When called:** When the user clicks the "Save" button.

### Method name: `savePlaceInBackground`
*   **Type:** `private`
*   **Logic:**
    1. Starts a background thread (`ExecutorService`) so the UI doesn't freeze.
    2. Converts the image from the `ImageView` into a byte array (compressed as PNG).
    3. Creates a `Place` object.
    4. Uses `HelperDB` to insert the data into the SQLite database.
    5. Returns to the main thread (`Handler`) to show a "Success" message and close the screen.
*   **Why background?** Converting images and writing to a database can be slow; doing it in the background keeps the app smooth.

________________________________________
## 4. Lifecycle
*   **onCreate():** Sets up the screen and listeners.
*   **onDestroy():** Inherited from `BaseActivity`.

________________________________________
## 5. Interface Interaction (UI)
*   **EditTexts:** Used for text input.
*   **ImageView:** Acts as a button to launch the camera and then displays the result.
*   **Buttons:** `bLink` (Search), `bGPS` (Location), `bSavePlace` (Save).

________________________________________
## 6. Interaction with other components
*   **Intent:** Used to launch `Search` and `MyLocation`.
*   **ActivityResultContracts:** Used to get data back from these screens.
*   **SQLite:** Data is saved using `ContentValues` through `HelperDB`.

________________________________________
## 7. General logic of the class
1. User types the name of the place.
2. User clicks the camera, takes a photo, which appears on screen.
3. User can optionally search for a link or get current GPS.
4. User clicks "Save".
5. The app double-checks everything, compresses the photo, and writes a new row into the database "diary".

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of `AddPlace` as a **blank page in your diary**.
*   You write the title (`etName`) and the story (`etComment`).
*   You "glue" a photo to the page (`ivImage`).
*   You might add a sticker with a website link or the exact coordinates of where you are.
*   Finally, you close the diary and put it in a safe place (`HelperDB`).

**Analogy:** It's like **creating a profile** on social media. you upload a picture, write a bio, and tag your location. Once you hit "Post", everything is saved on the server (in our case, the local database).
