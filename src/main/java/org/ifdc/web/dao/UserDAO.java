package org.ifdc.web.dao;

import org.ifdc.web.dao.bean.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import org.ifdc.web.util.DBUtil;

public class UserDAO {

    private static final ConcurrentHashMap<String, User> users = new ConcurrentHashMap();

    public static User getUserByUsername(String userName) {
        if (users.containsKey(userName)) {
            return users.get(userName);
        } else {
            
            try {
                Client client = ClientBuilder.newClient();
                WebTarget service = client.target(DBUtil.getDBBaseURI());
                Response response = service.path("user").path(userName).request().get();
                
                User ret = null;
                if (response.getStatus() == 200) {
                    String resJson = response.readEntity(String.class);
                    ObjectMapper mapper = new ObjectMapper();
                    ret = mapper.readValue(resJson, User.class);
                    users.put(userName, ret);
                }
                client.close();
                return ret;
                
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        return null;
    }

    public static Collection<User> getAllUserNames() {
        return users.values();
    }
}
