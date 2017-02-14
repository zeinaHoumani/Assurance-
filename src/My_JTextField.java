import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class My_JTextField extends JTextField{
	private static final long serialVersionUID = 1L;

	
	public My_JTextField(int integer){
		
		super(integer);
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		setForeground(new Color (5, 180, 206));
		setFont(new Font("Segoe UI Semibold",Font.BOLD, 13));
	}
}
