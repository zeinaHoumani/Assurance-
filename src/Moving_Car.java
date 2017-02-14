
import java.awt.*;
import javax.swing.*;

public class Moving_Car extends JPanel {
	private static final long serialVersionUID = 1L;

	private static JLabel LB;

	public Moving_Car() {

		LB = new JLabel("");
		LB.setSize(new Dimension(150, 30));

		Image image = Toolkit.getDefaultToolkit().createImage("Pictures\\moving-car.gif");
		ImageIcon clienticon = new ImageIcon(image);

		LB.setIcon(clienticon);

		add(LB);

		/** Create a thread just to move the image **/
		new Thread(new Runnable() {

			@SuppressWarnings("static-access")
			@Override
			public void run() {

				while (true) {

					/**
					 * the movement of the car is from left to right at the
					 * bottom of the frame
					 **/
					for (int i = -(Moving_Car.LB.getPreferredSize().width); i < Moving_Car.LB.getPreferredSize().width
							+ 850; i++) {

						Moving_Car.LB.setBounds(i, 0, Moving_Car.LB.getPreferredSize().width,
								Moving_Car.LB.getPreferredSize().height);
						try {
							Thread.currentThread().sleep(50);

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}
			}
		}).start();

	}

}
