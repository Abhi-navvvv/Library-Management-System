/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package My_classes;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author aayush
 */
public class Func_Class {

    public void displayImage(int width, int height,byte[] iamgebyte, String imagePath, JLabel label) {
        Object imagebyte = null;

        /*
         * URL imageURL = getClass().getResource("/My_image/book_dashboard.png");
         * if (imageURL == null){
         * System.out.println("Resource not found!");
         * }else{
         */
        ImageIcon imgIco ;
        if(imagebyte != null)
        {
         imgIco = new ImageIcon((String) imagebyte);
        }
        
        else
        {
         try{
            imgIco = new ImageIcon(getClass().getResource(imagePath));
         }
         
         catch(Exception e){
                imgIco = new ImageIcon(imagePath);

         }
        }
        

        Image image = imgIco.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        label.setIcon(new ImageIcon(image));
    }

    public void customTable(JTable table) {
        table.setSelectionBackground(new Color(249, 105, 14));
        table.setSelectionForeground(Color.white);
        table.setRowHeight(35);
        table.setShowGrid(false);
        table.setBackground(new Color(248, 248, 248));
        table.setShowHorizontalLines(true);
        table.setGridColor(Color.ORANGE);
    }

    public void customTableHeader(JTable table, Color backColor, Integer fontSize) {
        table.getTableHeader().setBackground(backColor);
        table.getTableHeader().setForeground(backColor);
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.getTableHeader().setFont(new Font("Verdana", Font.BOLD, fontSize));
    }
    
    //create a function to select image
    // the function will return the image path
    public String selectImage()
    {
         JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select Profile Picture");

        fileChooser.setCurrentDirectory(new File("C:\\Users\\Aayush\\Pictures\\projects"));
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Image", ".png", ".jpg", ".jpeg");
        fileChooser.addChoosableFileFilter(extensionFilter);

        int fileState = fileChooser.showSaveDialog(null);
        String path="";
        if (fileState == JFileChooser.APPROVE_OPTION)
        {
            path = fileChooser.getSelectedFile().getAbsolutePath();
        }
        return path;
    }

    public ResultSet getData(String query) {
         if (query == null || query.trim().isEmpty()) {
        Logger.getLogger(Func_Class.class.getName()).log(Level.SEVERE, "SQL query is null or empty!");
        return null;
         }
        PreparedStatement ps;
        ResultSet rs = null;

        try {
            ps = DB.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

        } catch (SQLException ex) {
            Logger.getLogger(Func_Class.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void displayImage(int i, int i0, Object object, String my_Imagegrouppng, JLabel jLabel_FormTitle) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
