package encryptdecrypt.encryption;

public class Unicode extends Encryption {
    @Override
    public String encrypt(String in, int key) {
        StringBuilder out = new StringBuilder();
        for (char ch : in.toCharArray())
            out.append((char) ((ch + key) % 128));
        return out.toString();
    }

    @Override
    public String decrypt(String in, int key) {
        StringBuilder out = new StringBuilder();
        for (char ch : in.toCharArray())
            out.append((char) ((ch - key + 128) % 128));
        return out.toString();
    }
}