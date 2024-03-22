import controller.AuthorController;
import controller.BookController;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        AuthorController objAuthorController = new AuthorController();
        BookController objBookController = new BookController();

        String optionGeneral;
        String messageGeneral = """
                ....::::::MENU::::::....
                1. Authors menu.
                2. Books menu.
                3. Relation Authors-books menu.
                4. Exit.
                """;
        do {
            optionGeneral = JOptionPane.showInputDialog(null,messageGeneral);
            if (optionGeneral == null) {
                break;
            }
            switch (optionGeneral){
                //AuthorsController
                case "1":
                    String option;
                    String message = """
                ....::::::AUTHORS MENU::::::....
                1. List authors.
                2. Insert author.
                3. Update author.
                4. Delete author.
                5. Find By id.
                6. Exit
                """;
                    do {
                        option = JOptionPane.showInputDialog(null, message);
                        if (option == null) {
                            break;
                        }

                        switch (option) {
                            case "1":
                                objAuthorController.getAll();
                                break;
                            case "2":
                                objAuthorController.create();
                                break;
                            case "3":
                                objAuthorController.update();
                                break;
                            case "4":
                                objAuthorController.delete();
                                break;
                            case "5":
                                objAuthorController.findAuthorbyID();
                                break;
                        }

                    } while (!option.equals("6"));
                    break;
                case "2":

                    String optionBook;
                    String messageBook = """
                ....::::::BOOKS MENU::::::....
                1. List Books.
                2. Insert books.
                3. Update books.
                4. Delete books.
                5. Get books By id.
                6. Exit
                """;
                    do {
                        optionBook = JOptionPane.showInputDialog(null, messageBook);
                        if (optionBook == null) {
                            break;
                        }

                        switch (optionBook) {
                            case "1":
                                objBookController.getAll();
                                break;
                            case "2":
                                objBookController.create();
                                break;
                            case "3":
                                objBookController.update();
                                break;
                            case "4":
                                objBookController.delete();
                                break;
                            case "5":
                                objBookController.findBookbyID();
                                break;
                            case "6":
                                JOptionPane.showMessageDialog(null, "leaving...");
                                break;
                            default:
                                break;
                        }

                    } while (!optionBook.equals("6"));
                    break;
                case "3":
                    objBookController.BooksByAuthor();
                    break;
            }
        }while (!optionGeneral.equals("4"));
    }
}