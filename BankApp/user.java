import java.util.Random;

public class user {
    
    public static String generateID(String[] existing, String name, String surname) {

        Random r = new Random();

        String finalId = null;
        boolean available = false;

        while (!available) {

            String id = String.format("%s#%d#%d#%s", surname, r.nextInt(100), r.nextInt(50), name);

            for (String e : existing) {
                if (!id.equals(e)) {
                    available = true;
                    finalId = id;
                }
            }
        }
        return finalId;

    }

    public static void createAccount(String name, String surname, double beginBalance) { // implement json logic

        String[] temp = {"none"}; // Replace with json reading (should be every currently created key (ID) in the json file) this is temporary so I can test the code

        String id = generateID(temp, name, surname);

    }

}
