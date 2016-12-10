package nikolay.morshchagin.ScreenComponents;

import javax.swing.*;
import java.awt.Container;

public class SCLabel extends ScreenComponent {
	private JLabel	label;

	public SCLabel() {
		super( ScreenComponent.LABEL );
	}

	private void newLabel( SCProperties p ) {
		label = new JLabel( "<html>" + p.getValue() + "</html>" );
		label.setBounds( p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight() );
	}

	@Override
	public Container get() {
		return label;
	}

	@Override
	protected ScreenComponent create( SCProperties properties ) {
		SCLabel scLabel = new SCLabel();
		scLabel.setId( properties.getId() );
		scLabel.newLabel( properties );
		return scLabel;
	}
}