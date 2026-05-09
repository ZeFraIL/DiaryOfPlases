# Class Description: InternetConnectorReceiver

________________________________________
## 1. General Information
*   **Class Name:** `InternetConnectorReceiver`
*   **Type:** BroadcastReceiver
*   **Purpose:** This class is a "listener" that monitors the phone's internet connection. It automatically detects when you connect to Wi-Fi, turn on mobile data, or lose internet access entirely.
*   **Interaction:** It is registered by the Android system. When the network status changes, it "broadcasts" this news to the application so that features like `Search` can react correctly.

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `listener` | `ConnectivityChangeListener`| An object that receives the updates about network changes. | In `onReceive()`. |

*   **BroadcastReceiver:** A component that allows an app to receive messages (broadcasts) from the Android system or other apps.
*   **ConnectivityManager:** A system service that answers queries about the state of network connectivity.

________________________________________
## 3. Class Methods

### Method name: `onReceive`
*   **Type:** `public`
*   **Parameters:** `Context context`, `Intent intent`.
*   **Logic:**
    1. Gets the `ConnectivityManager` from the system.
    2. Checks the "Active Network Info".
    3. Determines if the connection is **WiFi**, **Mobile**, or **None**.
    4. Sends this status (as a String) to the `listener`.
*   **When called:** Automatically by the Android system whenever the phone's internet status changes.

### Method name: `setConnectivityChangeListener`
*   **Type:** `public`
*   **Logic:** Allows an Activity to "subscribe" to network updates. This way, the Activity can show a warning to the user if the internet goes down.

________________________________________
## 4. Lifecycle
*   **onReceive():** This is the only lifecycle method for a receiver. It must finish its work very quickly (within a few seconds).
*   **Registration:** The receiver must be registered in the `AndroidManifest.xml` or manually in an Activity to start working.

________________________________________
## 5. Interface Interaction (UI)
*   This class has no UI of its own, but it often triggers **Toasts** or UI changes in other screens to inform the user about connection issues.

________________________________________
## 6. Interaction with other components
*   **ConnectivityManager:** Primary data source for network state.
*   **Activities:** Screens like `Search` use this to know if they can load a web page.

________________________________________
## 7. General logic of the class
The class acts as a **news reporter**. It sits and waits for a specific "event" (network change). As soon as the event happens, it checks the details and reports the news: "Hey, we are on WiFi now!" or "Warning: Internet lost!".

________________________________________
## 8. Simplified explanation
**Explanation in simple words:**
Think of the `InternetConnectorReceiver` as a **lookout on a ship**.
*   The lookout doesn't steer the ship or cook the food.
*   The lookout just watches the sky (the internet).
*   If it starts raining (internet lost) or the sun comes out (WiFi connected), the lookout yells to the captain (the Activity).
*   The captain then decides what to do next.

**Analogy:** It's like the **signal icon** on your phone's status bar. This class is the logic that decides which icon to show (LTE, WiFi, or a red X).
