
public class program {
    
    public static void main(String[] args) {

        userUtils getUser = new userUtils("mama", "password123");
        System.out.println(getUser.getUser());

        getUser = new userUtils("Neo", "neoiscool123");
        System.out.println(getUser.getUser());

    }

}
