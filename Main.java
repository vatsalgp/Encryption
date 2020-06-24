package encryptdecrypt;

import encryptdecrypt.io.IOString;
import encryptdecrypt.encryption.Encryption;
import encryptdecrypt.factory.StaticEncryptionFactory;

public class Main {
    private static int key = 0;
    private static String alg = "shift";
    private static String mode = "enc";
    private static String data = "";
    private static String in = null;
    private static String out = null;

    public static void main(String[] args) {
        parse(args);
        getInput();
        String result = getResult();
        writeOutput(result);
    }

    private static void writeOutput(String result) {
        if (out != null)
            IOString.writeToFile(out, result);
        else
            System.out.println(result);
    }

    private static void getInput() {
        if (in != null)
            data = IOString.readFromFile(in);
    }

    private static String getResult() {
        Encryption encryption = StaticEncryptionFactory.newEncryption(alg);
        switch (mode) {
            case "enc":
                return encryption.encrypt(data, key);
            case "dec":
                return encryption.decrypt(data, key);
            default:
                return "";
        }
    }

    private static void parse(String[] args) {
        for (int i = 0; i < args.length; i++)
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
}
