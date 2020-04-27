package ua.testing.repairagency.service;

import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.util.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

    public class LoginService {

        public String authenticateUser(UserDTO userDTO)
        {
            String userName = userDTO.getUsername();
            System.out.println(userName);
            String password = userDTO.getPassword();
            System.out.println(password);

            Connection con = null;
            Statement statement = null;
            ResultSet resultSet = null;

            String userNameDB = "";
            String passwordDB = "";
            String roleDB = "";

            try
            {
                con = DBConnection.createConnection();
                statement = con.createStatement();
                resultSet = statement.executeQuery("select username,password,role from `user`");


                while(resultSet.next())
                {
                    userNameDB = resultSet.getString("username");
                    System.out.println("userNameDB= " + userNameDB);
                    passwordDB = resultSet.getString("password");
                    System.out.println("password DB= " + passwordDB);
                    roleDB = resultSet.getString("role");
                    System.out.println("roleDB= " + roleDB);

                    if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Admin"))
                        return "Admin_Role";
                    else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Editor"))
                        return "Editor_Role";
                    else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("User"))
                        return "User_Role";
                }
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            return "Invalid user credentials";
        }
    }
