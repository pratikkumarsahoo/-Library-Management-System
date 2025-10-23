import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// -------------------- Book Class --------------------
class Book {
    private int id;
    private String title;
    private String author;
    private boolean isIssued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isIssued() { return isIssued; }

    public void issue() { isIssued = true; }
    public void returnBook() { isIssued = false; }

    @Override
    public String toString() {
        return id + " | " + title + " | " + author + " | " + (isIssued ? "Issued" : "Available");
    }
}

// -------------------- User Class --------------------
class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return userId + " | " + name;
    }
}

// -------------------- Library Class --------------------
class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void showBooks() {
        System.out.println("\n--- List of Books ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }

    public void showUsers() {
        System.out.println("\n--- List of Users ---");
        for (User u : users) {
            System.out.println(u);
        }
    }

    public void issueBook(int bookId, int userId) {
        Book bookToIssue = findBook(bookId);
        User user = findUser(userId);

        if (bookToIssue == null) {
            System.out.println("Book not found!");
            return;
        }

        if (user == null) {
            System.out.println("User not found!");
            return;
        }

        if (bookToIssue.isIssued()) {
            System.out.println("Book already issued!");
        } else {
            bookToIssue.issue();
            System.out.println("Book '" + bookToIssue.getTitle() + "' issued to " + user.getName());
        }
    }

    public void returnBook(int bookId) {
        Book bookToReturn = findBook(bookId);

        if (bookToReturn == null) {
            System.out.println("Book not found!");
            return;
        }

        if (!bookToReturn.isIssued()) {
            System.out.println("Book was not issued!");
        } else {
            bookToReturn.returnBook();
            System.out.println("Book '" + bookToReturn.getTitle() + "' returned successfully!");
        }
    }

    private Book findBook(int id) {
        for (Book b : books) {
            if (b.getId() == id) return b;
        }
        return null;
    }

    private User findUser(int id) {
        for (User u : users) {
            if (u.getUserId() == id) return u;
        }
        return null;
    }
}

// -------------------- Main Class --------------------
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        // Sample data
        library.addBook(new Book(1, "Java Basics", "James Gosling"));
        library.addBook(new Book(2, "Python Guide", "Guido van Rossum"));
        library.addBook(new Book(3, "C++ Primer", "Bjarne Stroustrup"));

        library.addUser(new User(101, "Alice"));
        library.addUser(new User(102, "Bob"));

        int choice;
        do {
            System.out.println("\n===== LIBRARY MANAGEMENT SYSTEM =====");
            System.out.println("1. Show all Books");
            System.out.println("2. Show all Users");
            System.out.println("3. Issue a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.showBooks();
                    break;
                case 2:
                    library.showUsers();
                    break;
                case 3:
                    System.out.print("Enter Book ID to issue: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    library.issueBook(bookId, userId);
                    break;
                case 4:
                    System.out.print("Enter Book ID to return: ");
                    int returnId = sc.nextInt();
                    library.returnBook(returnId);
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
