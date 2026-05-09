# Class Description: Security

________________________________________
## 1. General Information
*   **Class Name:** `Security`
*   **Type:** Logic Class / Utility
*   **Purpose:** This is the most complex class in the app. It is responsible for protecting the user's password. It doesn't just save the password as text; it uses professional-grade encryption to scramble it, making it unreadable to hackers.
*   **Interaction:** It is used by `LogOrReg`. It interacts with the **Android Keystore System** (a secure vault inside the phone) and **SharedPreferences** (a simple storage for settings).

________________________________________
## 2. Variables (Class Fields)
| Name | Type | Purpose | Where is it used |
| :--- | :--- | :--- | :--- |
| `ANDROID_KEYSTORE` | `String` | The name of the phone's built-in secure vault. | In the constructor. |
| `ALIAS` | `String` | The nickname for our specific secret key. | To find the key in the vault. |
| `TRANSFORMATION` | `String` | The mathematical formula used for scrambling (AES). | In the encryption/decryption methods. |
| `keyStore` | `KeyStore` | An object that represents the phone's physical vault. | Throughout the class. |

*   **Keystore:** A special part of the phone's hardware designed specifically to hold cryptographic keys safely. Even if someone steals your phone's data, they cannot easily get the keys from the Keystore.

________________________________________
## 3. Class Methods

### Method name: `Security` (Constructor)
*   **Type:** `public`
*   **Logic:** Initializes the connection to the phone's internal vault (`AndroidKeyStore`).

### Method name: `encryptAndSavePassword`
*   **Type:** `public`
*   **Parameters:** `password` (String), `context` (Context).
*   **Logic:**
    1. Gets a secret key from the vault (or creates one if it's the first time).
    2. Uses a "Cipher" (a digital scrambler) to turn the password into a meaningless sequence of bytes.
    3. Saves the "meaningless sequence" and a "starting code" (IV) into the phone's settings (`SharedPreferences`).
*   **When called:** During user registration.

### Method name: `getDecryptedPassword`
*   **Type:** `public`
*   **Return value:** `String` (The original password).
*   **Logic:**
    1. Reads the scrambled sequence and the starting code from the phone's settings.
    2. Retrieves the secret key from the vault.
    3. Uses the key to "unscramble" the sequence back into the original password.
*   **When called:** During user login.

### Method name: `getOrCreateSecretKey`
*   **Type:** `private`
*   **Logic:** Checks if a secret key named "DiaryOfPlases_PasswordAlias" already exists in the vault. If not, it generates a brand new, highly secure AES key.

________________________________________
## 4. Lifecycle
This class exists only when it is needed by the `LogOrReg` screen. It doesn't have an Android lifecycle.

________________________________________
## 5. Interaction with other components
*   **Cipher:** The mathematical engine that performs the actual scrambling.
*   **SharedPreferences:** Used as a "storage bin" for the scrambled data.
*   **Base64:** A tool used to turn raw bytes into readable text so they can be saved easily.

________________________________________
## 6. General logic of the class
The class acts as a **professional locksmith**.
1. It creates a master key and hides it in a heavy-duty safe inside the phone.
2. It takes your password, locks it in a box using that key, and gives you the box.
3. When you need to see your password again, it takes the box, gets the key from the safe, and opens the box for you.

________________________________________
## 7. Simplified explanation
**Explanation in simple words:**
Think of the `Security` class as a **secret code machine**.
*   You give it a word (the password).
*   It uses a secret pattern (the key) to turn that word into gibberish (e.g., "1234" becomes "xK9!pQ").
*   The gibberish is stored on your phone.
*   Even if a hacker finds the gibberish, they can't guess what your password is because they don't have the secret pattern.
*   The secret pattern is locked inside a safe that only this app can open.

**Analogy:** It's like writing a secret diary entry using a **cipher** that only you and your best friend know. If anyone else finds your diary, they will just see a mess of random letters.
