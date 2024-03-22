package controller;

import entity.Author;
import entity.Book;
import model.AuthorModel;
import model.BookModel;

import javax.swing.*;
import java.util.List;

public class BookController {
    AuthorModel objAuthorModel= new AuthorModel();
    BookModel objBookModel = new BookModel();
    AuthorController objAuthorController = new AuthorController();
    public void create(){
        Book objBook = new Book();

        String title = JOptionPane.showInputDialog(null,"Insert title");
        int year = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert year"));
        double price = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert price"));
        AuthorController controller = new AuthorController();
        int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null,"Insert ID of Author for book"));

        objBook.setTitle(title);
        objBook.setYear(year);
        objBook.setPrice(price);
        objBook.setIdAuthor(idAuthor);

        objBook =(Book) objBookModel.create(objBook);
    }
    public void getAll(){
        String list = this.getAll(this.objBookModel.findAll());
        JOptionPane.showMessageDialog(null,list);
    }
    public String getAll(List<Object> listObject){
        String list = "List Books\n";
        for (Object obj: listObject){
            Book objBook = (Book)obj;
            list += objBook.toString()+"\n";

        } return list;
    }
    public void update(){
        //Listamos
        String listBooks = this.getAll(this.objBookModel.findAll());

        //Pedimos el id
        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listBooks +"\nEnter the ID of book to edit"));

        //Verificar el id
        Book objBook = (Book) this.objBookModel.findById(idUpdate);

        if (objBook == null){
            JOptionPane.showMessageDialog(null, "Author not found.");
        }else {
            String title = JOptionPane.showInputDialog(null,"Enter new name",objBook.getTitle());
            int year = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter new nationality",objBook.getYear()));
            double price = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the price",objBook.getPrice()));
            int idAuthor = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter ID author",objBook.getIdAuthor()));


            objBook.setTitle(title);
            objBook.setYear(year);
            objBook.setPrice(price);
            objBook.setIdAuthor(idAuthor);

            this.objBookModel.update(objBook);
        }
    }

    public void delete(){
        String listBook = "Book list";

        for (Object obj: this.objBookModel.findAll()){
            Author objAuthor = (Author) obj;
            listBook += objAuthor.toString() + "\n";
        }
        int confirm = 1;
        int isDelete = Integer.parseInt(JOptionPane.showInputDialog(listBook + "Enter the ID of the book to delete"));
        Book objBook = (Book) this.objBookModel.findById(isDelete);
        if (objBook == null){
            JOptionPane.showMessageDialog(null, "Book not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the book?");
            if (confirm == 0){
                this.objBookModel.delete(objBook);
            }
        }
    }

    public void findBookbyID(){
        this.getAll();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Enter the ID of the book"));
        Book objbook = (Book)objBookModel.findById(id);
        JOptionPane.showMessageDialog(null,objbook.toString());
    }
    public void BooksByAuthor(){

        String list;

        // LLamo la lista de autores para mostrar al usuario el Id del autor que quiere relacionar con el libro

        String listAuthors = objAuthorController.getAll(this.objAuthorModel.findAll());
        int id_author = Integer.parseInt(JOptionPane.showInputDialog(null,listAuthors + "\n Insert the author ID:"));

        // Verificamos si el ID del autor existe
        Author objAuthor = (Author) this.objAuthorModel.findById(id_author);

        list = "Author's books: " ;

        // Valido si el autor existe
        if (objAuthor == null){
            JOptionPane.showMessageDialog(null,"Author not found");
        }
        else{
            // Valido si la lista de libros del autor está vacía
            if (objBookModel.findBookByAuthor(id_author).isEmpty()) {
                list += "No Books found for this author";
            } else {

                // Iteramos sobre la lista que devuelve el método find All
                for (Object obj : this.objBookModel.findBookByAuthor(id_author)) {

                    // Convertimos o casteamos el objeto tipo Objetct a un libro
                    Book objBook = (Book) obj;

                    // Concatenamos la información
                    list += objBook.toString() + "\n";

                }

            }

            // Mostramos toda la lista
            JOptionPane.showMessageDialog(null, list);
        }

    }

}

