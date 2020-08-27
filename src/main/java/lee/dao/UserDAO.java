package lee.dao;

import lee.model.User;
import lee.util.DB;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class UserDAO {
    public User insert(String username, String plainPassword) throws SQLException {
        String password = encrypt(plainPassword);
        String sql = "insert into Users (username, password) values (?, ?)";

        try(Connection c = DB.getConnection()) {
            try(PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1,username);
                ps.setString(2,password);
                ps.executeUpdate();

                try(ResultSet rs = ps.getGeneratedKeys()) {
                    if(!rs.next()) {
                        return null;
                    }
                    return new User(rs.getInt(1),username);
                }
            }

        }
    }

    public User select(String username, String plainPassword) throws SQLException {
        String password = encrypt(plainPassword);
        String sql = "select uid from users where username = ? and password = ?";
        try(Connection c = DB.getConnection()) {
            try(PreparedStatement ps = c.prepareStatement(sql)) {
                ps.setString(1,username);
                ps.setString(2,password);


                try(ResultSet rs = ps.executeQuery()) {
                    if(!rs.next()) {
                        return null;
                    }
                    return new User(rs.getInt(1),username);
                }
            }
        }

    }

    private String encrypt(String plain) {
        try {
            final MessageDigest instance = MessageDigest.getInstance("SHA-256");
            byte[] bytes = plain.getBytes();
            final byte[] digest = instance.digest(bytes);
            StringBuilder s = new StringBuilder();
            for (byte b : digest) {
                s.append(String.format("%02x", b));
            }
            return s.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("密码转换出错",e);
        }
    }

//    public static void main(String[] args) {
//        String a = "123";
//        userDAO userDAO = new userDAO();
//        System.out.println(userDAO.encrypt(a));
//
//    }
}
