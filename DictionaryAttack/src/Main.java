import java.io.*;
import java.util.ArrayList;

/**
 * Created by John on 30.12.2016.
 */
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        /*
        The file is encrypted with the password
        6666666666666666
        and the cipher text is :

         */
        String encrypted = "B676E3468280E47D8F1E3EE54DA6984E"; // Ciphertext.

        ArrayList<String> keyList = lineByLine("keys.txt");

        System.out.println(keyList.get(2));

        for(String s : keyList){

            try{
                PrintWriter writer = new PrintWriter("key.txt", "UTF-8");
                writer.println(s);
                writer.flush();
            } catch (IOException e) {
                System.out.println(e.getMessage().toString());
            }


            runCmd("java AES e key.txt words.txt");

            Thread.sleep(2000);

            String temp = read("words.txt.enc").trim();

            if(temp.equals(encrypted)){
                System.out.println("******* Key found : " + s);
            }else{
                System.out.println("Incorrect key : " + s);
            }

        }

        System.out.println(encrypted);

        //runCmd("mkdir mm");




    } // End of main method.


    public static void runCmd(String c) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", c);
        builder.redirectErrorStream(true);
        Process p = builder.start();
        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while (true) {
            line = r.readLine();
            if (line == null) { break; }
            System.out.println(line);
        }
    }




    public static String read(String f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        String everything = "";
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } finally {
            br.close();
        }

        return everything;
    }



    public static ArrayList<String> lineByLine(String f) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(f));
        ArrayList<String> x = new ArrayList<String>();

        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            x.add(line);
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                x.add(line);
            }

        } finally {
            br.close();
        }

        return x;
    }

} // End of Main class.
