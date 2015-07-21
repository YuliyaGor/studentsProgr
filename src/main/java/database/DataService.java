package database;

import constants.Constants;
import entity.Role;
import entity.User;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Natalia Lysenko on 20.06.2015.
 */
public class DataService {

    private static final Logger LOGGER = Logger
            .getLogger(DataService.class);

    private static List<DBConnection> conPool = new ArrayList<DBConnection>();
    private static Object monitor = new Object();

    public boolean init() {
        try{
            LOGGER.info("init database");
            for (int i = 0; i < Constants.CONNECTING_POOL_SIZE; i++){
                newConnection();
            }
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public DBConnection getDBConnection() {
        synchronized (monitor) {
            if (conPool.isEmpty()){
                newConnection();
            }
            DBConnection conn = conPool.remove(0);
            return conn;
        }
    }

    public void putDBConnection(DBConnection conn) {
        synchronized (monitor){
            conPool.add(conn);
        }
    }

    private void newConnection() {
        DBConnection conn = new DBConnection();
        synchronized (monitor) {
            conPool.add(conn);
        }
    }

    public void close() {
        DBConnection conn = getDBConnection();
        conn.closePreparedStatements();
        this.putDBConnection(conn);
    }

    public Role getRoleById(int id) {
        DBConnection conn = getDBConnection();
        Role role = conn.getRoleById(id);
        return role;
    }

    public boolean userLogin(String login, String password, int roleId) {
        DBConnection conn = getDBConnection();
        boolean userId = conn.userLogin(login, password, roleId);
        return userId;
    }

    public List<Role> loadRoles() {
        DBConnection conn = getDBConnection();
        List<Role> roles = conn.loadRoles();
        return roles;
    }
}
