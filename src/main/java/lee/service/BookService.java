package lee.service;

import lee.dao.BookDAO;
import lee.dao.SectionDAO;
import lee.model.Book;
import lee.model.User;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookDAO bookDAO;
    private SectionDAO sectionDAO;
    public BookService() {
        bookDAO = new BookDAO();
        sectionDAO = new SectionDAO();
    }
    public List<Book> list() throws SQLException {
        return bookDAO.selectAll();
    }

    public Book post(String title, User user) throws SQLException {
        return bookDAO.insert(user,title);
    }

    public Book get(int bid) throws SQLException {
        Book book = bookDAO.selectByBid(bid);
        if (book == null) {
            return null;
        }
        book.setSections(sectionDAO.selectByBid(bid));

        return book;
    }

    public void addSection(int bid, String name) throws SQLException {
        sectionDAO.insert(bid, name);
    }
}
