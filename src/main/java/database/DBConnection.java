package database;

import constants.Constants;
import entity.Role;
import entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Natalia Lysenko on 20.06.2015.
 */
public class DBConnection {

    private static final Logger LOGGER = Logger.getLogger(DBConnection.class);
    private Connection conn = null;
    private ResultSet rs = null;

    private static PreparedStatement getRoleById;
    private static PreparedStatement loadRoles;
    private static PreparedStatement userValidate;

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(Constants.CONNECTING_URL);
            loadPreparedStatements();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadPreparedStatements(){
        try {
            getRoleById = conn.prepareStatement("SELECT * FROM test.role WHERE id = ?");
            loadRoles = conn.prepareStatement("SELECT * FROM test.role");
            userValidate = conn.prepareStatement("Select * FROM test.user as TABUser join test.user_role as TABUser_role on TABUser.id = TABUser_role.id_user where (TABUser.login = ?) and (TABUser.password = ?) and (TABUser_role.id_role = ?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closePreparedStatements(){
        try {
            getRoleById.close();
            loadRoles.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Role getRoleById(int id) {
        rs = null;
        Role role = new Role();
        try { // SELECT * FROM role WHERE id = ?
            getRoleById.setInt(1, id);
            rs = getRoleById.executeQuery();
            while (rs.next()){
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return role;
    }

    public List<Role> loadRoles() {
        rs = null;
        List<Role> roles = new LinkedList();
        try { // SELECT * FROM role
            rs = loadRoles.executeQuery();
            while (rs.next()){
                Role role = new Role();
                role.setId(rs.getInt("id"));
                role.setName(rs.getString("role"));
                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    public boolean userLogin(String login, String password, int roleId) {
        rs = null;
        boolean result = false;
        try { // Select * FROM test.user as TABUser join test.user_role as TABUser_role on TABUser.id = TABUser_role.id_user where (TABUser.login = ?) and (TABUser.password = ?)
            userValidate.setString(1, login);
            userValidate.setString(2, password);
            userValidate.setInt(3, roleId);
            rs = getRoleById.executeQuery();
            if (rs.next()){
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

//
//    public City getCityById(int idCity) {
//        rs = null;
//        City city = new City();
//        try {// SELECT * FROM city WHERE id = ?
//            getCityById.setInt(1, idCity);
//            rs = getCityById.executeQuery();
//            while (rs.next()) {
//                city.setId(rs.getInt("id"));
//                city.setName(rs.getString("name"));
//                city.setLinkToFacebook(rs.getString("facebook"));
//                city.setLinkToGoogle(rs.getString("google+"));
//                city.setLinkToLinkedin(rs.getString("linkedin"));
//                city.setLinkToVk(rs.getString("vk"));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return city;
//    }
}
