import java.awt.Color;
import java.awt.Font;

import javax.swing.JCheckBox;

public class My_JCheckBox extends JCheckBox{
	private static final long serialVersionUID = 1L;

	
	
	public My_JCheckBox(String s){
		
		super(s);
		setFont(new Font("calibri",Font.BOLD, 14));
		 setForeground(Color.DARK_GRAY);
	}

}
