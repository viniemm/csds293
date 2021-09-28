import edu.cwru.vxm167.gis.*;
import java.awt.*;

public class GISGUI extends Frame {

	// initializing using constructor
	GISGUI() {

		// creating a button
		Button b = new Button("New interest point");

		// setting button position on screen
		b.setBounds(30,100,80,30);

		// adding button into frame
		add(b);

		// frame size 300 width and 300 height
		setSize(1280,720);

		// setting the title of Frame
		setTitle("GIS gui");

		// no layout manager
		setLayout(null);

		// now frame will be visible, by default it is not visible
		setVisible(true);
	}

	// main method
	public static void main(String[] args) {

// creating instance of Frame class
		GISGUI f = new GISGUI();

	}

}