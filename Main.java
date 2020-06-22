package encryptdecrypt;

import encryptdecrypt.encryption.*;
import encryptdecrypt.io.*;

public class Main {
    private static int key = 0;
    private static String alg = "shift";
    private static String mode = "enc";
    private static String result = "";
    private static String data = "";
    private static String in = null;
    private static String out = null;
    private static Encryption encryption = null;

    public static void main(String[] args) {
        parse(args);
        if (in != null)
            data = IOString.readFromFile(in);
        switch (mode) {
            case "enc":
                result = encryption.encrypt(data, key);
                break;
            case "dec":
                result = encryption.decrypt(data, key);
                break;
            default:
        }
        if (out != null)
            IOString.writeToFile(out, result);
        else
            System.out.println(result);
    }

    private static void parse(String[] args) {
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
        switch (alg) {
            case "shift":
                encryption = new Shift();
                break;
            case "unicode":
                encryption = new Unicode();
                break;
            default:
        }
    }
}
