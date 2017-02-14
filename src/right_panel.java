import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class right_panel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	
	private JPanel result;
	private BufferedImage img;
	
	
	public JPanel right(int id) {
		
		
		result = new JPanel(new GridLayout(5, 2));

		JLabel l1 = new My_JLabel("       Result:", Color.red);
		l1.setFont(new Font("Algerian", Font.BOLD, 36));

		result.add(l1);
		result.add(new JLabel(""));

		ArrayList<String> l = DB_operation.Display_result(id);

		JLabel l2 = new JLabel();

		try {
			img = ImageIO.read(new File("Pictures\\cars\\" + l.get(1)));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ImageIcon icon = new ImageIcon(img);
		l2.setIcon(icon);

		result.add(l2);

		JPanel right = new JPanel(new BorderLayout());

		right.add(new My_JLabel(l.get(0)), BorderLayout.CENTER);
		right.add(new My_JLabel(l.get(2) + "(" + l.get(3) + ")"), BorderLayout.SOUTH);

		result.add(right);

		result.add(new My_JLabel("Accident:  " + l.get(4), SwingConstants.RIGHT, Color.darkGray));
		result.add(new JLabel(""));

		result.add(new My_JLabel("Security:  " + l.get(5), SwingConstants.RIGHT, Color.darkGray));
		result.add(new JLabel(""));

		result.add(new My_JLabel("Modified:  " + l.get(6), SwingConstants.RIGHT, Color.darkGray));
		result.add(new JLabel(""));
	
		result.setBorder(BorderFactory.createRaisedSoftBevelBorder());
		
		return result;
	}

}
