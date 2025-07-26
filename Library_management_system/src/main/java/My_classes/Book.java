
package My_classes;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.or;
import javax.swing.JOptionPane;

/**
 *
 * @author Arjun Saxena
 */
public class Book {
    private Integer id;
    private String publisher;
    private double price;
    private Integer genre_id;
    private Integer quantity;
    private String date_recieved;
    private String description;
    private byte[] cover;
    
    public Book(){}
    
    public Book(Integer _id,String _isbn,String _name,Integer _author_id,Integer _genre_id,Integer _quantity,
    String _publisher,double _price, String _date_recieved, String _description, byte[] _cover)
    {
        this.id = _id;
        this.isbn = _isbn;
        this.name = _name;
        this.author_id =_author_id;
        this.genre_id = _genre_id;
        this.quantity = _quantity;
        this.publisher = _publisher;
        this.price = _price;
        this.date_recieved= _date_recieved;
        this.description = _description;
        this.cover = _cover;
    }
    
    private String isbn;
    private String name;
    private Integer author_id;
    public Integer setId() {
        return id;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public void setGenre_id(Integer genre_id) {
        this.genre_id = genre_id;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDate_recieved(String date_recieved) {
        this.date_recieved = date_recieved;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCover(byte[] cover) {
        this.cover = cover;
    }
    public Integer getId() {
        return id;
    }
    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public Integer getGenre_id() {
        return genre_id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getPublisher() {
        return publisher;
    }

    public double getPrice() {
        return price;
    }

    public String getDate_recieved() {
        return date_recieved;
    }

    public String getDescription() {
        return description;
    }

    public byte[] getCover() {
        return cover;
    }
    
     Func_Class func = new Func_Class();

    public void addBook(String _isbn,String _name,Integer _author_id,Integer _genre_id,Integer _quantity,
    String _publisher,double _price, String _date_recieved, String _description, byte[] _cover) {
        String insertQuery = "INSERT INTO `books`(`isbn`, `name`, `author_id`, `genre_id`,`quantity`, `publisher`, `price`,  `date_recieved`, `description`, `cover_image`) VALUES(?,?,?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement ps = DB.getConnection().prepareStatement(insertQuery);
            ps.setString(1, _isbn);
            ps.setString(2, _name);
            ps.setInt(3, _author_id);
            ps.setInt(4, _genre_id);
            ps.setInt(5, _quantity);
            ps.setString(6, _publisher);
            ps.setDouble(7,_price);
            ps.setString(8, _date_recieved);
            ps.setString(9,_description);
            ps.setBytes(10,_cover);

            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Book Added", "add Book", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Book Not Added", "add Book", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    public void EditBook(int _id,String _name,Integer _author_id,Integer _genre_id,Integer _quantity,
    String _publisher,double _price, String _date_recieved, String _description, byte[] _cover) {
        String updateQuery = "UPDATE books` SET `name`=?,`author_id`=?,`genre_id`=?,`quantity`=?,`publisher`=?,`price`=?,`date_recieved`=?,`description`=?,`cover_image`=? WHERE `id`=?";
        try {

            PreparedStatement ps = DB.getConnection().prepareStatement(updateQuery);
            //ps.setString(1, _isbn);
            ps.setString(1, _name);
            ps.setInt(2, _author_id);
            ps.setInt(3, _genre_id);
            ps.setInt(4, _quantity);
            ps.setString(5, _publisher);
            ps.setDouble(6,_price);
            ps.setString(7, _date_recieved);
            ps.setString(8,_description);
            ps.setBytes(9,_cover);
            ps.setInt(10, _id);
            if (ps.executeUpdate() != 0) {
                JOptionPane.showMessageDialog(null, "Book Edited", "edit Book", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Book Not Edited", "add Edit", 2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    //create a unction to check if the isbn already exists
    public boolean isISBNexists(String _isbn)
    {
        String query = "SELECT * FROM `books` WHERE `isbn` = '"+_isbn+"'";
       ResultSet rs = func.getData(query);
        try {
             if(rs.next()){
                return true;
             }
             else
             {
                return false;
             }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }
    
    public Book searchBookbyId_Isbn(int _id, String _isbn)
    {
        
            String query = "SELECT * FROM `books` WHERE `id` = "+_id+" or `isbn` = '"+_isbn+"'"; 
       
        
        

       ResultSet rs = func.getData(query);
       Book book = null;
        try {
             if(rs.next())
             {
                 book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),
                 rs.getInt(5),rs.getInt(6),rs.getString(7),rs.getDouble(8),rs.getString(9),
                 rs.getString(10),rs.getBytes(11));
             }
             else
             {
                return book; 
             }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(Book.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return book;
 
    }

    public String getCoverImagePath() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void editBook(int id, String name, Integer author_id, Integer genre_id, Integer quantity, String publisher, Double price, String date_recieved, String description, byte[] img) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
