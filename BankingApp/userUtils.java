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

    public String getUser() { // grab a user's uuid by username and verify with password

        String finalUUID = new String();
        JsonRead readHelper = new JsonRead(HELPER, true);
        Map<String, Object> helperContent = new HashMap<>(readHelper.fromJson());
        JsonRead readUsers = new JsonRead(USERS, true);
        Map<String, Object> usersContents = new HashMap<>(readUsers.fromJson());
        List<?> targetUserName = new ArrayList<>();


        if (helperContent.get(USER) instanceof String || helperContent.get(USER) instanceof List<?>) { // check if the contents are a string or a list (return a list of contents)

            if (helperContent.get(USER) instanceof String) {
                targetUserName = List.of(helperContent.get(USER));
            } else if (helperContent.get(USER) instanceof List<?>) {
                targetUserName = (List<?>) helperContent.get(USER);
            }

        } else {

            return "FAILED";

        }

        for (Object uuid : targetUserName) {

            if (!(uuid instanceof String stringUUID)) continue;

            if (!(usersContents.get(stringUUID) instanceof Map<?, ?> mappedUserInformation)) continue;

            if (mappedUserInformation.containsKey("password")) {
                
                hashPassword checkLogin = new hashPassword(PASS); // get the loginAttempt and match to the current UUID password
                
                if (checkLogin.verify((String) mappedUserInformation.get("password"))) { // if passwords match 

                    finalUUID = stringUUID;

                }  else {

                    continue;

                }

            } else {
                continue;
            }


        }


        return finalUUID;

    }

    public void newUser() { // store UUID and matching password name and balance in Users and store UUID and name in helper

        String uuid = UUID.randomUUID().toString();

        // create the map containing the user info

        Map<String, Object> user = new HashMap<>();
        hashPassword encrypt = new hashPassword(PASS);

        user.put("name", USER);
        user.put("password", encrypt.getHashed());
        user.put("balance", START_BALANCE);

        JsonRead users = new JsonRead(USERS, true);
        Map<String, Object> oldUsersContent = users.fromJson();

        oldUsersContent.put(uuid, user);

        // create the map containing the user uuid and user name

        JsonRead helper = new JsonRead(HELPER, true);
        Map<String, Object> oldHelperContent = helper.fromJson();

        if (!(oldHelperContent.containsKey(USER))) { // Username is unique (key does not exist yet)

            oldHelperContent.put(USER, uuid);

        } else {

            if (oldHelperContent.get(USER) instanceof String existingUser) {

                List<Object> userList  = new ArrayList<>();
                userList.add(existingUser);
                userList.add(uuid);
                oldHelperContent.put(USER, userList);

            } else if (oldHelperContent.get(USER) instanceof List<?> existingUsers) {

                List<Object> userList = new ArrayList<>(existingUsers);
                userList.add(uuid);
                oldHelperContent.put(USER, userList);

            }

        }

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
