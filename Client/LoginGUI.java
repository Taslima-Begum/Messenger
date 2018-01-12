package Client;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class LoginGUI implements ActionListener{

	private JFrame frmLogin;
	private JTextField usernameField;
	private JLabel lblLogo;
	private JPasswordField passwordField = new JPasswordField();
	private JButton loginBtn = new JButton("Login");
	private final String salt = "$2a$10$lGOj9fZo88fkWeAoSQscbu";
	private JButton registerBtn = new JButton("Register");
	private String username;
	private Communication c;
	private RegisterGUI registerGUI;

	public LoginGUI(Communication c) { 
		this.c=c;
		initialize(); 
		frmLogin.setVisible(true);
	}


	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				c.send(new Message("CLOSE"));
			}
		});
		frmLogin.setTitle("Login");
		frmLogin.getContentPane().setBackground(new Color(211, 211, 211)); 
		frmLogin.setBounds(100, 100, 763, 595);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(150, 216, 444, 254);
		frmLogin.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblUsername = new JLabel("USERNAME:");
		lblUsername.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblUsername.setForeground(Color.GRAY);
		lblUsername.setBounds(21, 78, 98, 16);
		panel.add(lblUsername);

		usernameField = new JTextField();
		usernameField.setBounds(120, 73, 240, 28);
		panel.add(usernameField);
		usernameField.setColumns(10);

		JLabel lblNewLabel = new JLabel("PASSWORD:");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(21, 128, 83, 22);
		panel.add(lblNewLabel);
		passwordField.setBounds(120, 126, 240, 28);
		panel.add(passwordField);
		registerBtn.setBounds(122, 181, 112, 23);
		panel.add(registerBtn);
		loginBtn.setBounds(238, 181, 122, 23);
		panel.add(loginBtn);
		loginBtn.setBackground(new Color(255, 255, 255));
		JLabel lblSingIn = new JLabel("Sign in ");
		lblSingIn.setBounds(192, 11, 58, 22);
		panel.add(lblSingIn);
		lblSingIn.setForeground(Color.GRAY);
		lblSingIn.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 17));

        lblLogo = new JLabel();
        lblLogo.setForeground(new Color(105, 105, 105));
        lblLogo.setBounds(150, 65, 450, 106);
        lblLogo.setIcon(new ImageIcon(MainActivityGUI.class.getResource("/images/jsm.png")));
        frmLogin.getContentPane().add(lblLogo);

		loginBtn.addActionListener(this);
		registerBtn.addActionListener(this);
		frmLogin.setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(211, 211, 211));
		frmLogin.setJMenuBar(menuBar);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==loginBtn) {
			if(usernameField.getText().isEmpty()||passwordField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Incomplete fields!", "Error", JOptionPane.WARNING_MESSAGE);
			}else {
				username=usernameField.getText();
				c.send(new Message("LOGIN",usernameField.getText(),BCrypt.hashpw(passwordField.getText(), salt)));
			}
		}
		if(e.getSource()==registerBtn) {
			registerGUI = new RegisterGUI(this,c);
		}
	}

	public String getUsername() {
		return username;
	}

	public String getSalt() {
		return salt;
	}

	public void disposeGUI() {
		frmLogin.dispose();
	}

	public RegisterGUI getRegisterGUI() {
		return registerGUI;
	}

	public JFrame getloginGUIFrame() {
		return frmLogin;
	}
}
