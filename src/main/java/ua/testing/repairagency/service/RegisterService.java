package ua.testing.repairagency.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.testing.repairagency.dto.UserDTO;
import ua.testing.repairagency.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class RegisterService {
    public void createNewUser(UserDTO userDTO){
        final Logger logger = LoggerFactory.getLogger(RegisterService.class);
        Connection con = DBConnection.createConnection();
        try {
            PreparedStatement ps = con.prepareStatement("insert into `user` (username, password,email,role)" +
                    " values (?,?,?,?)");

            ps.setString(1, userDTO.getUsername());
            ps.setString(2, userDTO.getPassword());
            ps.setString(3, userDTO.getEmail());
            ps.setString(4, "User");
            int i = ps.executeUpdate();

            if(i>0){
                logger.info("User created successfully");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
