package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;
import entity.Book;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookModel implements CRUD {

    @Override
    public Object create(Object object) {
        Book objBook = (Book) object;
        Connection connection= ConfigDB.openConnection();


        try {
            String sql = "INSERT INTO book (title, year, price, idAuthor) VALUES (?,?,?,?);";
            PreparedStatement objPreparedS = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPreparedS.setString(1,objBook.getTitle());
            objPreparedS.setInt(2,objBook.getYear());
            objPreparedS.setDouble(3,objBook.getPrice());
            objPreparedS.setInt(4,objBook.getIdAuthor());


            objPreparedS.execute();

            ResultSet objResult = objPreparedS.getGeneratedKeys();

            while (objResult.next()){
                objBook.setID(objResult.getInt(1));
            }
            objPreparedS.close();
            System.out.println("Book insertion was successful");
        }catch (Exception e){
            System.out.println("Error adding book "+ e.getMessage());
        }
        ConfigDB.closeConnection();
        return objBook;
    }

    @Override
    public boolean delete(Object object) {
        Book objBook = (Book) object;

        boolean isDeleted = false;
        Connection objConnection = ConfigDB.openConnection();
        try{
            String sql = "DELETE FROM book WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objBook.getID());

            int totalAffeectedRows = objPrepare.executeUpdate();

            if (totalAffeectedRows >0 ){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The delete was successful");
            }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        ConfigDB.closeConnection();
        return isDeleted;
    }

    @Override
    public boolean update(Object object) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();
        //2. Convertir el objeto
        Book objBook = (Book)object;
        //3. Variable bandera para saber si se actualizó
        boolean isUpdated=false;

        try {
            //4. Creamos la sentencia SQL
            String sql  = "UPDATE coder SET title = ?, year = ?, price = ?, idAuthor = ? WHERE id = ?;";
            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valor a los ? parámetros de Query
            objPrepare.setString(1,objBook.getTitle());
            objPrepare.setInt(3,objBook.getYear());
            objPrepare.setDouble(3,objBook.getPrice());
            objPrepare.setInt(4,objBook.getID());

            //7. Ejecutamos el query
            int rowAffected  = objPrepare.executeUpdate();
            if (rowAffected > 0){
                isUpdated= true;
                JOptionPane.showMessageDialog(null,"The update was successful.");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally {
            //8. Cerrar la conexión
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public List<Object> findAll() {
        Connection objConnection = ConfigDB.openConnection();
        List<Object> listBooks= new ArrayList<>();
        try{
            String sql = "SELECT * FROM book ORDER BY book.id ASC;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            while (objResult.next()){
                Book objBook = new Book();
                objBook.setID(objResult.getInt("id"));
                objBook.setTitle(objResult.getString("title"));
                objBook.setYear(objResult.getInt("year"));

                listBooks.add(objBook);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data acquisition Error");
        }
        ConfigDB.closeConnection();
        return listBooks;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Book objBook = null;
        try{
            String sql = "SELECT * FROM book WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objBook = new Book();
                objBook.setID(objResult.getInt("id"));
                objBook.setYear(objResult.getInt("year"));
                objBook.setPrice(objResult.getDouble("price"));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objBook;
    }

    @Override
    public List<Object> findBookByAuthor(int id) {
        Connection objConnection = ConfigDB.openConnection();

        List<Object> listBooksByAuthor = new ArrayList<>();

        try {

            String sql = "SELECT * FROM books INNER JOIN authors ON books.id_author = authors.id_author WHERE authors.id_author = ? ORDER BY books.id_book ASC ";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, id);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();

            while (objResult.next()) {

                Author objAuthor = new Author();
                Book objBook = new Book();

                objBook.setID(objResult.getInt("books.id_book"));
                objBook.setTitle(objResult.getString("books.title"));
                objBook.setYear(objResult.getInt("books.date_pub"));
                objBook.setPrice(objResult.getDouble("books.price"));
                objBook.setIdAuthor(objResult.getInt("books.id_author"));
                objAuthor.setID(objResult.getInt("authors.id_author"));
                objAuthor.setName(objResult.getString("authors.name"));
                objAuthor.setNationality(objResult.getString("authors.nationality"));

                //Agrego el objeto Author al objeto Libro
                objBook.setObjAuthor(objAuthor);

                listBooksByAuthor.add(objBook);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data acquisition error" + e.getMessage());
        }

        ConfigDB.closeConnection();
        return listBooksByAuthor;
    }
}

