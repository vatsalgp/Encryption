package encryptdecrypt;

import encryptdecrypt.encryption.*;
import encryptdecrypt.io.*;

public class Main {
    public static void main(String[] args) {
        int key = 0;
        String alg = "shift";
        String mode = "enc";
        String result = "";
        String data = "";
        String in = null;
        String out = null;
        Encryption encryption = null;
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
            data = IOString.readFromFile(in);
        if (alg.equals("shift"))
            encryption = new Shift();
        else
            encryption = new Unicode();
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
}
