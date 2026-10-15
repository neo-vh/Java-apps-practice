import org.json.simple.*;
import java.nio.file.*;
import java.nio.charset.*;

public class user {

    public static int generateIdentifier(Path jsonFile) {
        return 0;
    }
    
    @SuppressWarnings("unchecked")
    public static void createAccount(String name, int beginBalance) throws IOException {
        JSONObject account = new JSONObject();

        account.put("ID", null);
        account.put("name", name);
        account.put("balance", beginBalance);

    }  

}
