package controller;

import entity.Author;
import entity.Book;
import model.AuthorModel;

import javax.swing.*;
import java.util.List;

public class AuthorController {

    AuthorModel objAuthorModel = new AuthorModel();

    public AuthorController(){
        this.objAuthorModel= new AuthorModel();
    }

    public void create(){
        Author objAuthor = new Author();

        String name = JOptionPane.showInputDialog("Insert name");
        String nationality = JOptionPane.showInputDialog("Insert nationality");
        objAuthor.setName(name);
        objAuthor.setNationality(nationality);

        objAuthor =(Author) this.objAuthorModel.create(objAuthor);
    }
    public void delete(){
        String listAuthor = "Author list";

        for (Object obj: this.objAuthorModel.findAll()){
            Author objAuthor = (Author) obj;
            listAuthor += objAuthor.toString() + "\n";
        }
        int confirm = 1;
        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listAuthor + "Enter the ID of the author to delete"));
        Author objAuthor = (Author) this.objAuthorModel.findById(isDelete);
        if (objAuthor == null){
            JOptionPane.showMessageDialog(null, "Author not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the author?");
                if (confirm == 0){
                    this.objAuthorModel.delete(objAuthor);
                }
            }
        }


    public void getAll(){
        String list = this.getAll(this.objAuthorModel.findAll());
        JOptionPane.showMessageDialog(null,list);
    }
    public String getAll(List<Object>listObject){
        String list = "List Authors\n";
        for (Object obj: listObject){
            Author objAuthor = (Author)obj;
            list += objAuthor.toString()+"\n";

        } return list;
    }
    public void update(){
        //Listamos
        String listAuthors = this.getAll(this.objAuthorModel.findAll());

        //Pedimos el id
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listAuthors +"\nEnter the ID of the author to edit"));

        //Verificar el id
        Author objAuthor = (Author) this.objAuthorModel.findById(idUpdate);

        if (objAuthor == null){
            JOptionPane.showMessageDialog(null, "Author not found.");
        }else {
            String name = JOptionPane.showInputDialog(null,"Enter new name",objAuthor.getName());
            String nationality = JOptionPane.showInputDialog(null,"Enter new nationality",objAuthor.getNationality());

            objAuthor.setName(name);
            objAuthor.setNationality(nationality);

            this.objAuthorModel.update(objAuthor);
        }
    }

    public void findAuthorbyID(){
        this.getAll();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the ID of the author"));
        Author objAuthor = (Author)objAuthorModel.findById(id);
        JOptionPane.showMessageDialog(null,objAuthor.toString());
    }

}

