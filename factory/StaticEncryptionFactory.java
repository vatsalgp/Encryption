package encryptdecrypt.factory;

import encryptdecrypt.encryption.*;

public class StaticEncryptionFactory {
    public static Encryption newEncryption(String string) {
        switch (string.toLowerCase()) {
            case "shift":
                return new Shift();
            case "unicode":
                return new Unicode();
            default:
                return null;
        }
    }
}