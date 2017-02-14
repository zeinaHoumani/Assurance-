import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class My_JLabel extends JLabel{
	private static final long serialVersionUID = 1L;


	public My_JLabel(String text,Color x)
	{
		super(text);
		 setFont(new Font("Verdana",Font.BOLD, 17));
		 setForeground(x);
	}
	
	public My_JLabel(String text)
	{
		super(text);
		setFont(new Font("Verdana",Font.BOLD, 20));
		 setForeground(Color.DARK_GRAY);
		
	}
	
	public My_JLabel(String Text,int Alignement){
		
		super(Text,Alignement);
		setFont(new Font("Segoe UI Symbol",Font.PLAIN, 14));
		 setForeground(Color.DARK_GRAY);
	}
	
	
public My_JLabel(String Text,int Alignement,Color x){
		
		super(Text,Alignement);
		setFont(new Font("Segoe UI Symbol",Font.BOLD, 17));
		 setForeground(x);
	}
	


}
