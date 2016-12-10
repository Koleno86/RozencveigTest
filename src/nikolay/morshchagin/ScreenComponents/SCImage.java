package nikolay.morshchagin.ScreenComponents;

import javax.swing.*;
import java.awt.*;

public class SCImage extends ScreenComponent {
	private JImage	image;

	public SCImage() {
		super( ScreenComponent.IMAGE );
	}

	private void newImage( SCProperties p ) {
		image = new JImage( p.getValue() );
		image.setBounds( p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight() );
		image.setBorder( BorderFactory.createCompoundBorder( BorderFactory.createLineBorder( Color.GRAY, 1 ),
				BorderFactory.createEmptyBorder( p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight() ) ) );
	}

	@Override
	public Container get() {
		return image;
	}

	@Override
	protected ScreenComponent create( SCProperties properties ) {
		SCImage scImage = new SCImage();
		scImage.setId( properties.getId() );
		scImage.newImage( properties );
		return scImage;
	}
}