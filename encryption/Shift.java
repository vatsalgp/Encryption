package encryptdecrypt.encryption;

public class Shift implements Encryption {
    @Override
    public String encrypt(String in, int key) {
        StringBuilder out = new StringBuilder();
        for (char ch : in.toCharArray())
            if (ch >= 'a' && ch <= 'z')
                out.append((char) ((ch - 'a' + key) % 26 + 'a'));
            else if (ch >= 'A' && ch <= 'Z')
                out.append((char) ((ch - 'A' + key) % 26 + 'A'));
            else
                out.append(ch);
        return out.toString();
    }

    @Override
    public String decrypt(String in, int key) {
        StringBuilder out = new StringBuilder();
        for (char ch : in.toCharArray())
            if (ch >= 'a' && ch <= 'z')
                out.append((char) ((ch - 'a' - key + 26) % 26 + 'a'));
            else if (ch >= 'A' && ch <= 'Z')
                out.append((char) ((ch - 'A' - key + 26) % 26 + 'A'));
            else
                out.append(ch);
        return out.toString();
    }
}