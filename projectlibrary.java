import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Book {
    int id;
    String name;
    String author;
    boolean issued;

    Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.issued = false;
    }
}

public class projectlibrary{

    static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        loginUI();
    }

    static void loginUI() {
        JFrame frame = new JFrame("Login");
        frame.setSize(350, 250);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JTextField user = new JTextField();
        JPasswordField pass = new JPasswordField();
        JButton login = new JButton("Login");

        panel.add(new JLabel("Username"));
        panel.add(user);
        panel.add(new JLabel("Password"));
        panel.add(pass);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(login, BorderLayout.SOUTH);

        login.addActionListener(e -> {
            if (user.getText().equals("delulu") &&
                new String(pass.getPassword()).equals("92")) {
                frame.dispose();
                dashboard();
            } else {
                JOptionPane.showMessageDialog(frame, "Go back U hacker ");
            }
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    static void dashboard() {
        JFrame frame = new JFrame("Library Admin");
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());

        JLabel header = new JLabel("Library System", JLabel.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 22));
        header.setOpaque(true);
        header.setBackground(new Color(70,130,180));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(100, 50));

        JPanel panel = new JPanel(new GridLayout(3, 2, 15, 15));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JButton add = new JButton("Add Book");
        JButton view = new JButton("View Books");
        JButton issue = new JButton("Issue Book");
        JButton ret = new JButton("Return Book");
        JButton search = new JButton("Search Book");

        panel.add(add);
        panel.add(view);
        panel.add(issue);
        panel.add(ret);
        panel.add(search);

        frame.add(header, BorderLayout.NORTH);
        frame.add(panel, BorderLayout.CENTER);

        add.addActionListener(e -> addBook());
        view.addActionListener(e -> viewBooks());
        issue.addActionListener(e -> issueBook());
        ret.addActionListener(e -> returnBook());
        search.addActionListener(e -> searchBook());

        frame.setVisible(true);
    }

    static void addBook() {
        JTextField id = new JTextField();
        JTextField name = new JTextField();
        JTextField author = new JTextField();

        Object[] fields = {
            "ID:", id,
            "Student Name", name,
            "Book Name", author
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Book", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            books.add(new Book(
                Integer.parseInt(id.getText()),
                name.getText(),
                author.getText()
            ));
            JOptionPane.showMessageDialog(null, "Book Added!");
        }
    }

    static void viewBooks() {
        StringBuilder data = new StringBuilder();

        for (Book b : books) {
            data.append(b.id).append(" | ")
                .append(b.name).append(" | ")
                .append(b.author).append(" | Issued: ")
                .append(b.issued ? "Yes" : "No")
                .append("\size");
        }

        JTextArea area = new JTextArea(data.toString());
        JOptionPane.showMessageDialog(null, new JScrollPane(area));
    }

    static void issueBook() {
        String input = JOptionPane.showInputDialog("Enter ID:");
        int id = Integer.parseInt(input);

        for (Book b : books) {
            if (b.id == id) {
                if (!b.issued) {
                    b.issued = true;
                    JOptionPane.showMessageDialog(null, "Issued!");
                } else {
                    JOptionPane.showMessageDialog(null, "Already Issued!");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Not Found!");
    }

    static void returnBook() {
        String input = JOptionPane.showInputDialog("Enter ID:");
        int id = Integer.parseInt(input);

        for (Book b : books) {
            if (b.id == id) {
                if (b.issued) {
                    b.issued = false;
                    JOptionPane.showMessageDialog(null, "Returned!");
                } else {
                    JOptionPane.showMessageDialog(null, "Not Issued!");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Not Found!");
    }

    static void searchBook() {
        String keyword = JOptionPane.showInputDialog("Enter Book Name or ID:");

        StringBuilder result = new StringBuilder();

        for (Book b : books) {
            if (String.valueOf(b.id).equals(keyword) ||
                b.name.toLowerCase().contains(keyword.toLowerCase())) {

                result.append("ID: ").append(b.id)
                      .append("\nName: ").append(b.name)
                      .append("\nAuthor: ").append(b.author)
                      .append("\nIssued: ").append(b.issued ? "Yes" : "No")
                      .append("\size\size");
            }
        }

        if (result.length() == 0) {
            JOptionPane.showMessageDialog(null, "No Book Found!");
        } else {
            JTextArea area = new JTextArea(result.toString());
            JOptionPane.showMessageDialog(null, new JScrollPane(area));
        }
    }
}