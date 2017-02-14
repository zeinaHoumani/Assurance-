import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Client_ID extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel idPanel;
	private BufferedImage image;
	private JLabel idLB;
	private JLabel cardLB;
	private JTextField idTF;
	private JFrame frame;

	public Client_ID(JFrame frame) {
        this.frame=frame; // used for hide /show 
		DisplayGUI();
		
		addWindowListener(new Adapter());
	}

	public void DisplayGUI() {

		setTitle("Client LogIn");
		setSize(460, 330);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);

		cardLB = new JLabel();
		cardLB.setSize(450, 300);

		try {
			image = ImageIO.read(new File("Pictures\\card.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		setLayout(new BorderLayout());
		ImageIcon icon = new ImageIcon(DB_operation.scaledImage(image, cardLB.getWidth(), cardLB.getHeight()));
		cardLB.setIcon(icon);
		setContentPane(cardLB);

		idLB = new JLabel("ID: ");
		idLB.setFont(new Font(idLB.getFont().getName(), idLB.getFont().getStyle(), 17));
		idTF = new JTextField(10);
		idTF.setBorder(BorderFactory.createLoweredSoftBevelBorder());

		idPanel = new JPanel(new FlowLayout());
		idPanel.add(idLB);
		idPanel.add(idTF);
		idPanel.setBackground(Color.WHITE);

		setLayout(new FlowLayout());
		JLabel empty = new JLabel("                                                                         ");
		add(empty);
		JLabel empty1 = new JLabel("                                                                         ");
		add(empty1);
		JLabel empty2 = new JLabel("                                                                         ");
		add(empty2);
		JLabel empty3 = new JLabel("                                                                         ");
		add(empty3);
		JLabel empty4 = new JLabel("                                                                        ");
		add(empty4);
		add(idPanel);

		idTF.addActionListener(new handler());
	}

	private class handler implements ActionListener {

		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent event) {
			hide();
			String s = idTF.getText();
			new Client_Frame(s);

		};

	}
	
	private class Adapter extends WindowAdapter {
		@SuppressWarnings("deprecation")
		@Override
	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	       frame.show();
			
	    }
	}


}
