package nikolay.morshchagin.ScreenComponents;

import javax.swing.*;
import java.awt.Container;

public class SCButton extends ScreenComponent {
	private JButton	button;

	public SCButton() {
		super( ScreenComponent.BUTTON );
	}

	private void newButton( SCProperties p ) {
		button = new JButton( p.getValue() );
		button.setBounds( p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight() );
		button.setActionCommand( p.getCommand() );
	}

	@Override
	public Container get() {
		return button;
	}

	@Override
	protected ScreenComponent create( SCProperties properties ) {
		SCButton scButton = new SCButton();
		scButton.setId( properties.getId() );
		scButton.newButton( properties );
		return scButton;
	}
}