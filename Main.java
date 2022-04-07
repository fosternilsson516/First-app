import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.sql.*;

class run extends JFrame {

    private final JPanel loginPage, createNewUserPage,
            forgotPasswordPage, launchPage, contentFrame;

    private final JButton loginButton, forgotPasswordButton, cancelButton,
            createNewUserButton, OKBUTTON, SENDEMAILBUTTON, SoloPongButton;

    private final JLabel imageLabel, userNameLabel, passwordLabel,
            EMAILLABEL, NEWUSERLABEL, CREATEPASSWORDLABEL,
            CONFIRMPASSWORDLABEL, EMAILLABELFP, USERNAMELABELFP, launchLabel;

    private final JTextField userNameField, NEWUSERNAMEFIELD, NEWEMAILFIELD,
            CHECKEMAILFIELDFP, USERNAMEFIELDFP;

    private final JPasswordField passwordField, CREATEPASSWORDFIELD, CONFIRMPASSWORDFIELD;
    private final CardLayout cardLayout = new CardLayout();
    private final BorderLayout borderLayout = new BorderLayout();
    private final ImageIcon loginBackround;

    public run() {
        super("Login Page");
        // Pages
        loginPage = new JPanel(borderLayout);
        createNewUserPage = new JPanel(borderLayout);
        forgotPasswordPage = new JPanel(borderLayout);
        launchPage = new JPanel(borderLayout);
        contentFrame = new JPanel(cardLayout);

        //images
        loginBackround = new ImageIcon(getClass().getResource("loginBaccground.jpg"));
        imageLabel = new JLabel(loginBackround);


        // Buttons
        loginButton = new JButton("Login");
        forgotPasswordButton = new JButton("forgot password");
        createNewUserButton = new JButton("create a new account");
        cancelButton = new JButton("Cancel");
        OKBUTTON = new JButton("OK");
        SENDEMAILBUTTON = new JButton("Send email");
        SoloPongButton = new JButton("Solo Pong");

        // Labels
        userNameLabel = new JLabel("Username");
        passwordLabel = new JLabel("Password");
        EMAILLABEL = new JLabel("Enter Email");
        NEWUSERLABEL = new JLabel("Enter Username");
        CREATEPASSWORDLABEL = new JLabel("Enter Password");
        CONFIRMPASSWORDLABEL = new JLabel("Confirm Password");
        EMAILLABELFP = new JLabel("Enter your email");
        USERNAMELABELFP = new JLabel("Enter your username");
        launchLabel = new JLabel("Welcome, Click to start game");

        // Fields
        userNameField = new JTextField(50);
        NEWUSERNAMEFIELD = new JTextField(50);
        NEWEMAILFIELD = new JTextField(50);
        CHECKEMAILFIELDFP = new JTextField(50);
        USERNAMEFIELDFP = new JTextField(50);

        // Password Fields
        passwordField = new JPasswordField(50);
        CREATEPASSWORDFIELD = new JPasswordField(50);
        CONFIRMPASSWORDFIELD = new JPasswordField(50);

        // Page Colors
        loginPage.setBackground(Color.GREEN);
        createNewUserPage.setBackground(Color.magenta);
        forgotPasswordPage.setBackground(Color.cyan);
        launchPage.setBackground(Color.GRAY);

        createNewUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentFrame, "createNewUserPage");

            }
        });

        OKBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentFrame, "loginPage");
                try {
                    registerUser();
                } catch (SQLException ex) {

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentFrame, "loginPage");
            }
        });

        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentFrame, "forgotPasswordPage");
            }
        });

        SENDEMAILBUTTON.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(contentFrame, "loginPage");
            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    verifyLogin();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        SoloPongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game();
            }
        });

        // login page
        loginPage.add(loginButton);
        loginPage.add(createNewUserButton);
        loginPage.add(userNameLabel);
        loginPage.add(passwordLabel);
        loginPage.add(userNameField);
        loginPage.add(passwordField);
        loginPage.add(forgotPasswordButton);
        forgotPasswordButton.setBounds(130, 240, 180, 20);
        loginButton.setBounds(170, 210, 100, 20);
        createNewUserButton.setBounds(90, 270, 250, 20);
        userNameLabel.setBounds(100, 120, 100, 20);
        passwordLabel.setBounds(100, 170, 100, 20);
        userNameField.setBounds(170, 120, 100, 20);
        passwordField.setBounds(170, 170, 100, 20);

        // NEW USER PAGE
        createNewUserPage.add(NEWUSERLABEL);
        createNewUserPage.add(CREATEPASSWORDLABEL);
        createNewUserPage.add(EMAILLABEL);
        createNewUserPage.add(CONFIRMPASSWORDLABEL);
        createNewUserPage.add(NEWUSERNAMEFIELD);
        createNewUserPage.add(NEWEMAILFIELD);
        createNewUserPage.add(CREATEPASSWORDFIELD);
        createNewUserPage.add(CONFIRMPASSWORDFIELD);
        createNewUserPage.add(cancelButton);
        createNewUserPage.add(OKBUTTON);

        OKBUTTON.setBounds(200, 260, 100, 20);
        cancelButton.setBounds(80, 260, 100, 20);
        NEWUSERLABEL.setBounds(85, 140, 100, 20);
        EMAILLABEL.setBounds(100, 170, 180, 20);
        CREATEPASSWORDLABEL.setBounds(90, 200, 100, 20);
        CONFIRMPASSWORDLABEL.setBounds(80, 230, 200, 20);
        NEWUSERNAMEFIELD.setBounds(200, 140, 100, 20);
        NEWEMAILFIELD.setBounds(200, 170, 100, 20);
        CREATEPASSWORDFIELD.setBounds(200, 200, 100, 20);
        CONFIRMPASSWORDFIELD.setBounds(200, 230, 100, 20);

        //forgot password page
        forgotPasswordPage.add(CHECKEMAILFIELDFP);
        forgotPasswordPage.add(EMAILLABELFP);
        forgotPasswordPage.add(USERNAMELABELFP);
        forgotPasswordPage.add(USERNAMEFIELDFP);
        forgotPasswordPage.add(SENDEMAILBUTTON);
        forgotPasswordPage.add(cancelButton);

        CHECKEMAILFIELDFP.setBounds(200, 150, 100, 20);
        EMAILLABELFP.setBounds(80, 150, 100, 20);
        USERNAMEFIELDFP.setBounds(200, 110, 100, 20);
        USERNAMELABELFP.setBounds(70, 110, 150, 20);
        SENDEMAILBUTTON.setBounds(210, 200, 100, 20);
        cancelButton.setBounds(90, 200, 100, 20);


        //launchPage
        launchPage.add(launchLabel);
        launchPage.add(SoloPongButton);



        contentFrame.add("loginPage", loginPage);
        contentFrame.add("createNewUserPage", createNewUserPage);
        contentFrame.add("forgotPasswordPage", forgotPasswordPage);
        contentFrame.add("launchPage", launchPage);

        contentFrame.setLayout(cardLayout);

        this.setContentPane(contentFrame);
        cardLayout.show(contentFrame, "loginPage");

    }

    public void game() {

        JFrame wind = new JFrame("RedBall/GamePinfo");
        Redball g = new Redball();
        wind.add(g);
        wind.pack();
        wind.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        wind.setLocationRelativeTo(null);
        wind.setVisible(true);
        wind.addMouseMotionListener(g);

        Timer tt = new Timer(17, g);
        tt.start();
    }


    public void verifyLogin() throws ClassNotFoundException, SQLException {
        String username = userNameField.getText();
        String password = String.valueOf(passwordField.getPassword());
        String sql = "SELECT UserName, PassWord1 FROM users WHERE UserName='" + username + "'";

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            cardLayout.show(contentFrame, "loginPage");
        }

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindata", "root", "1234");
            System.out.println("Connection to Database logindata(users) Successful");
            //Connection to Database Successful

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()) {
                if (password.equals(rs.getString("PassWord1"))) {
                    JOptionPane.showMessageDialog(null,
                            "Login Successful!",
                            "",
                            JOptionPane.PLAIN_MESSAGE);
                    cardLayout.show(contentFrame, "launchPage");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Retry password",
                            "Incorrect password",
                            JOptionPane.ERROR_MESSAGE);
                    cardLayout.show(contentFrame, "loginPage");
                }
            }
            con.close();
            stmt.close();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void registerUser() throws SQLException, ClassNotFoundException {
        String username = NEWUSERNAMEFIELD.getText();
        String email = NEWEMAILFIELD.getText();
        String password1 = String.valueOf(CREATEPASSWORDFIELD.getPassword());
        String password2 = String.valueOf(CONFIRMPASSWORDFIELD.getPassword());

        if (username.isEmpty() || email.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please enter all fields",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
            cardLayout.show(contentFrame, "createNewUserPage");
            return;
        }

        if (!password1.equals(password2)) {
            JOptionPane.showMessageDialog(null,
                    "Password does not match",
                    "try again",
                    JOptionPane.ERROR_MESSAGE);
            cardLayout.show(contentFrame, "createNewUserPage");
            return;
        }
        addUserToDatabase();

    }

    public void addUserToDatabase() throws ClassNotFoundException, SQLException {
        String username = NEWUSERNAMEFIELD.getText();
        String email = NEWEMAILFIELD.getText();
        String password1 = String.valueOf(CREATEPASSWORDFIELD.getPassword());

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/logindata", "root", "1234");
        System.out.println("Connection to Database logindata(users) Successful");
        //Connection to Database Successful

        String sql = "INSERT INTO users (UserName, EmaiL, PassWord1)" +
                " VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = con.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, email);
        preparedStatement.setString(3, password1);
        preparedStatement.executeUpdate();
        con.close();
    }

    public static void main(String[] args) {
        run r = new run();
        r.setVisible(true);
        r.setMinimumSize(new Dimension(420, 420));
        r.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}