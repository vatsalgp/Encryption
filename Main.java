package encryptdecrypt;

import java.nio.file.*;

public class Main {
    public static void main(String[] args) {
        int key = 0;
        String mode = "enc";
        String data = "";
        String result = "";
        String in = null;
        String out = null;
        String alg = "shift";
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-in":
                    in = args[i + 1];
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
                default:
            }
        }
        if (in != null)
            data = readFromFile(in);
        // TODO
        // Change alg depending on -alg
        switch (mode) {
            case "enc":
                result = encryptionUnicode(data, key);
                break;
            case "dec":
                result = decryptionUnicode(data, key);
                break;
            default:
        }
        if (out != null)
            writeToFile(out, result);
        else
            System.out.println(result);
    }

    private static String encryptionFlip(String in) {
        StringBuilder out = new StringBuilder();
        for (char ch : in.toCharArray())
            if (ch >= 'a' && ch <= 'z')
                out.append((char) ('z' - ch + 'a'));
            else
                out.append(ch);
        return out.toString();
    }

    private static String encryptionShift(String in, int key) {
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

    private static String decryptionShift(String in, int key) {
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

    private static String encryptionUnicode(String in, int key) {
        StringBuilder out = new StringBuilder();
        for (char ch : in.toCharArray())
            out.append((char) ((ch + key) % 128));
        return out.toString();
    }

    private static String decryptionUnicode(String in, int key) {
        StringBuilder out = new StringBuilder();
        for (char ch : in.toCharArray())
            out.append((char) ((ch - key + 128) % 128));
        return out.toString();
    }

    public static String readFromFile(final String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (final Exception e) {
            System.out.println("Error in reading the file: " + filename);
            return "";
        }
    }

    public static void writeToFile(final String filename, final String string) {
        try {
            Files.write(Paths.get(filename), string.getBytes());
        } catch (final Exception e) {
            System.out.println("Error in reading the file: " + filename);
        }
    }
}
