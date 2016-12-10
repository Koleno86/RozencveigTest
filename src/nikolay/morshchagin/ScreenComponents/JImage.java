package nikolay.morshchagin.ScreenComponents;

import java.awt.*;
import javax.swing.*;

public class JImage extends JPanel {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6510564615720874321L;
	private Image							image;

	public JImage( String imageName ) {
		loadImage( imageName );
		setSurfaceSize();
	}

	private void loadImage( String imageName ) {
		image = new ImageIcon( imageName ).getImage();
	}

	private void setSurfaceSize() {
		Dimension d = new Dimension();
		d.width = image.getWidth( null );
		d.height = image.getHeight( null );
		setPreferredSize( d );
	}

	private void doDrawing( Graphics g ) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage( image, 0, 0, null );
	}

	@Override
	public void paintComponent( Graphics g ) {
		super.paintComponent( g );
		doDrawing( g );
	}
}