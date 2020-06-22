package encryptdecrypt.encryption;

public interface Encryption {
    String encrypt(String in, int key);

    String decrypt(String in, int key);
}