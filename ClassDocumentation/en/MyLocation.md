# Class Description: MyLocation

________________________________________
## 1. General Information
*   **Class Name:** `MyLocation`
*   **Type:** Activity
*   **Purpose:** This screen is responsible for obtaining the user's current geographic coordinates (Latitude and Longitude) using the phone's GPS or network.
*   **Interaction:** Inherits from `BaseActivity`. It is usually launched by `AddPlace`. Once it finds the location, it immediately returns the coordinates and closes itself.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `fusedLocationClient` | `FusedLocationProviderClient` | The main tool for interacting with Google's location services. | Initialized in `onCreate()`, used in `getCurrentLocation()`. |
| `requestPermissionLauncher` | `ActivityResultLauncher` | Handles the request for location permissions from the user. | Launched in `checkPermissionAndGetLocation()`. |

*   **FusedLocationProviderClient:** A Google Play Services component that combines GPS, Wi-Fi, and cellular data to provide the most accurate location while saving battery.
*   **Latitude/Longitude:** Numerical coordinates that pinpoint any spot on Earth.

________________________________________
## 3. Class Methods

### Method name: `onCreate`
*   **Type:** `protected`
*   **Logic:**
    1. Sets up the location client (`fusedLocationClient`).
    2. Immediately triggers the permission check and location retrieval process.
*   **When called:** When the user clicks the "GPS" button in `AddPlace`.

### Method name: `checkPermissionAndGetLocation`
*   **Type:** `private`
*   **Logic:** Checks if the app has the `ACCESS_FINE_LOCATION` permission.
    *   If yes: Calls `getCurrentLocation()`.
    *   If no: Asks the user for permission.
*   **When called:** Automatically during `onCreate`.

### Method name: `getCurrentLocation`
*   **Type:** `private`
*   **Logic:**
    1. Asks the system for the "last known location" of the device.
    2. If a location is found:
        *   Extracts Latitude and Longitude.
        *   Formats them into a single string (e.g., "32.0853*34.7818").
        *   Returns this string back to the previous screen via an `Intent`.
        *   Closes the screen.
    3. If no location is found (e.g., GPS is turned off), it shows an error message.
*   **When called:** After permission is confirmed.

________________________________________
## 4. Lifecycle
*   **onCreate():** Starts the location search immediately.
*   **onDestroy():** Inherited from BaseActivity.

________________________________________
## 5. Interface Interaction (UI)
*   This class has a very minimal interface (mostly a blank loading screen or a simple message) because it works quickly and then disappears.

________________________________________
## 6. Interaction with other components
*   **Google Play Services:** Uses the `fusedLocationClient` to talk to the phone's GPS hardware.
*   **Intent:** Returns the coordinate string to `AddPlace` via `setResult(RESULT_OK, ...)`.

________________________________________
## 7. General logic of the class
The class is a **single-purpose tool**. It's like a person who runs out of the house with a compass, finds where they are, writes it on a piece of paper, runs back, hands you the paper, and goes to sleep. It doesn't stay open; it just delivers the coordinates.

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `MyLocation` class as a **digital compass**.
*   When you ask for your location, the app "wakes up" the compass.
*   The compass talks to satellites in space to find your exact numbers.
*   As soon as it has the numbers, it gives them to you and goes back to "sleep" to save battery.

**Analogy:** It's like **dropping a pin** on a digital map. The app finds the exact spot where the pin landed and tells you the secret code (coordinates) for that spot.
