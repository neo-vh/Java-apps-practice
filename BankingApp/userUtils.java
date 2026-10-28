import java.io.*;
import java.util.*;
import juanparser.*;

public class userUtils {

    final String HELPER = "helper.json";
    final String USERS = "users.json";
    final String USER;
    final String PASS;
    final int START_BALANCE = 100;

    public userUtils(String name, String password) { // having password as required may be an issue keep in mind

        this.USER = name;
        this.PASS = password;

    }

    public void newUser() { // store UUID and matching password name and balance in Users and store UUID and name in helper

        hashPassword encrypt = new hashPassword(PASS);
        String uuid = UUID.randomUUID().toString();

        // create the map containing the user info

        Map<String, Object> user = new HashMap<>();

        user.put("name", USER);
        user.put("password", encrypt.getHashed());
        user.put("balance", START_BALANCE);

        JsonRead users = new JsonRead(USERS, true);
        Map<String, Object> oldUsersContent = users.fromJson();

        oldUsersContent.put(uuid, user);

        // create the map containing the user uuid and user name

        JsonRead helper = new JsonRead(HELPER, true);
        Map<String, Object> oldHelperContent = helper.fromJson();

        oldHelperContent.put(USER, uuid);

        // convert to hashmaps for jsonwrite
        HashMap<String, Object> newUsersContent = new HashMap<>(oldUsersContent);
        HashMap<String, Object> newHelperContent = new HashMap<>(oldHelperContent);

        try { // write new user to helper and users

            JsonWrite.writes(newUsersContent, USERS);
            JsonWrite.writes(newHelperContent, HELPER);
        } catch (IOException e) {

            System.out.println("unable to write new user " + e);

        }


    }


}
