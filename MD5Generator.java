import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import org.json.JSONObject;

public class MD5Generator {
 public static void main(String[] args) {
     try {  
       String content = new String(Files.readAllBytes(Paths.get("resources/input.json")));
        JSONObject jsonObject = new JSONObject(content);
        JSONObject student = jsonObject.getJSONObject("student");
       String firstName = student.getString("first_name").toLowerCase();
        String rollNumber = student.getString("roll_number").toLowerCase();
         String hash = generateMD5Hash(firstName + rollNumber);
        FileWriter writer = new FileWriter("output.txt");
            writer.write(hash);
            writer.close();

            System.out.println("MD5 Hash: " + hash);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateMD5Hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(input.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
