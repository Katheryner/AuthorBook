package model;

import database.CRUD;
import database.ConfigDB;
import entity.Author;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorModel implements CRUD {
    @Override
    public Object create(Object object) {
        Author objAuthor = (Author) object;
        Connection connection= ConfigDB.openConnection();


        try {
            String sql = "INSERT INTO author (name, nationality) VALUES (?,?);";
            PreparedStatement objPreparedS = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            objPreparedS.setString(1,objAuthor.getName());
            objPreparedS.setString(2,objAuthor.getNationality());

            objPreparedS.execute();

            ResultSet objResult = objPreparedS.getGeneratedKeys();

            while (objResult.next()){
                objAuthor.setID(objResult.getInt(1));
            }
            objPreparedS.close();
            System.out.println("Author insertion was successful");
        }catch (Exception e){
            System.out.println("Error adding author "+ e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAuthor;
    }

    @Override
    public boolean delete(Object object) {
        Author objAuthor = (Author) object;

        boolean isDeleted = false;
        Connection objConnection = ConfigDB.openConnection();
        try{
            String sql = "DELETE FROM author WHERE id = ?;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            objPrepare.setInt(1, objAuthor.getID());

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
        Author objAuthor = (Author)object;
        //3. Variable bandera para saber si se actualizó
        boolean isUpdated=false;

        try {
            //4. Creamos la sentencia SQL
            String sql  = "UPDATE coder SET name = ?, nationality = ? WHERE id = ?;";
            //5. Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);

            //6. Dar valor a los ? parámetros de Query
            objPrepare.setString(1,objAuthor.getName());
            objPrepare.setString(3,objAuthor.getNationality());
            objPrepare.setInt(4,objAuthor.getID());

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
        List<Object> listAuthors= new ArrayList<>();
        try{
            String sql = "SELECT * FROM author ORDER BY author.id ASC;";
            PreparedStatement objPrepare = (PreparedStatement) objConnection.prepareStatement(sql);

            ResultSet objResult = (ResultSet) objPrepare.executeQuery();
            while (objResult.next()){
                Author objAuthor = new Author();
                objAuthor.setID(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));

                listAuthors.add(objAuthor);
            }
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Data acquisition Error");
        }
        ConfigDB.closeConnection();
        return listAuthors;
    }

    @Override
    public Object findById(int id) {
        Connection objConnection = ConfigDB.openConnection();
        Author objAuthor = null;
        try{
            String sql = "SELECT * FROM author WHERE id = ?;";
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1,id);
            ResultSet objResult = objPrepare.executeQuery();

            while (objResult.next()){
                objAuthor = new Author();
                objAuthor.setID(objResult.getInt("id"));
                objAuthor.setName(objResult.getString("name"));
                objAuthor.setNationality(objResult.getString("nationality"));
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        ConfigDB.closeConnection();
        return objAuthor;
    }

    @Override
    public List<Object> findBookByAuthor(int id) {
        return null;
    }
}
