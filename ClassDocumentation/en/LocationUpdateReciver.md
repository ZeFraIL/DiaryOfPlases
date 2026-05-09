# Class Description: LocationUpdateReciver

________________________________________
## 1. General Information
*   **Class Name:** `LocationUpdateReciver`
*   **Type:** BroadcastReceiver
*   **Purpose:** This class is designed to listen for "location changed" messages from the Android system. It allows the app to know if the phone has moved to a new physical location, even if the user isn't actively using the app.
*   **Interaction:** It is triggered by the phone's GPS or network hardware. It extracts coordinate data from the system message.

________________________________________
## 2. Variables (Class Fields)
This class does not have its own persistent variables. It processes data locally within its methods.

________________________________________
## 3. Class Methods

### Method name: `onReceive`
*   **Type:** `public`
*   **Parameters:** `Context context`, `Intent intent`.
*   **Logic:**
    1. Looks into the "Intent" (the message) to find a specific key called `KEY_LOCATION_CHANGED`.
    2. Pulls out the `Location` object, which contains the new Latitude and Longitude.
    3. (Note: Currently, the class receives the data but doesn't perform any further action like saving it or showing it).
*   **When called:** Automatically by the Android system whenever the device detects a significant change in its position.

________________________________________
## 4. Lifecycle
*   **onReceive():** The standard entry point for all receivers. It runs for a split second to process the new location.

________________________________________
## 5. Interaction with other components
*   **LocationManager:** The Android system service that sends the location updates to this receiver.

________________________________________
## 6. General logic of the class
The class acts as an **automatic GPS logger**. Imagine a person walking down the street. Every few minutes, the phone yells: "I am here now!". This class "hears" that shout and grabs the coordinates.

________________________________________
## 7. Simplified explanation
**Explanation in simple words:**
Think of the `LocationUpdateReciver` as a **motion sensor** light in a hallway.
*   The sensor is always waiting.
*   When someone walks by (the location changes), the sensor "trips" (`onReceive`).
*   The light turns on (the coordinates are extracted).
*   The sensor then goes back to waiting for the next movement.

**Analogy:** It's like having a **tracking device** on a package. Every time the package moves to a new city, the device sends a ping with its location. This class is the receiver that catches that ping.
