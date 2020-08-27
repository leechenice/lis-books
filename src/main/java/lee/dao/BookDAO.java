package lee.dao;

import lee.model.Book;
import lee.model.User;
import lee.util.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDAO {
    public Book insert(User user, String title) throws SQLException {
        String sql = "insert into books (uid, title) values (?, ?)";
        try(Connection c = DB.getConnection()) {
            try(PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1,user.getUid());
                ps.setString(2,title);
                ps.executeUpdate();

                try(ResultSet rs = ps.getGeneratedKeys()) {
                    if (!rs.next()) {
                        return null;
                    }
                    return new Book(rs.getInt(1),user,title);
                }

            }
        }

    }

    public List<Book> selectAll() throws SQLException {
        String sql = "select bid, title, users.uid, username " +
                "from books, users " +
                "where users.uid = books.uid " +
                "order by bid desc";
        List<Book> books = new ArrayList<>();
        try(Connection c = DB.getConnection()) {
            try(PreparedStatement ps = c.prepareStatement(sql)) {
                try(ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User(rs.getInt("uid"),rs.getString("username"));
                        Book book = new Book(rs.getInt("bid"),user,rs.getString("title"));
                        books.add(book);
                    }
                }
            }
        }
        return books;
    }

    public Book selectByBid(int bid) throws SQLException {
        String sql = "select bid, title, users.uid, users.username " +
                "from books, users " +
                "where books.uid = users.uid and bid = ?";

        try (Connection c = DB.getConnection()) {
            try (PreparedStatement s = c.prepareStatement(sql)) {
                s.setInt(1, bid);

                try (ResultSet r = s.executeQuery()) {
                    if (!r.next()) {
                        return null;
                    }

                    User user = new User(r.getInt("uid"), r.getString("username"));
                    return new Book(r.getInt("bid"), user, r.getString("title"));
                }
            }
        }
    }
}
