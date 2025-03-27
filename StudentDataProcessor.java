package com.example.faizuu;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class StudentDataProcessor {

    public static void main(String[] args) throws Exception {

        JSONObject jsonData = readJsonFile("C:\\Users\\Admin\\Desktop\\FaizCode\\FaizUUcODE\\faiz\\src\\main\\java\\com\\example\\faizuu\\input.json");
        JSONObject student = getStudent(jsonData); 
        String firstName = getLowercaseString(student, "first_name");
        String rollNumber = getLowercaseString(student, "roll_number");
        String combinedString = firstName + rollNumber;
        String md5Hash = generateMd5Hash(combinedString);
        System.out.println(md5Hash);
    }

    private static JSONObject readJsonFile(String filePath) throws Exception {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(filePath)) {
            return (JSONObject) parser.parse(reader);
        }
    }

    private static JSONObject getStudent(JSONObject jsonData) { // Corrected method name
        return (JSONObject) jsonData.get("student"); // Directly return the student object
    }

    private static String getLowercaseString(JSONObject student, String key) {
        return ((String) student.get(key)).toLowerCase();
    }

    private static String generateMd5Hash(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}