package nikolay.morshchagin.ScreenComponents;

import javax.swing.*;
import java.awt.Container;

public class SCRadioButton extends ScreenComponent {
	private JRadioButton	radioButton;

	public SCRadioButton() {
		super( ScreenComponent.RADIO_BUTTON );
	}

	private void newRadioButton( SCProperties p ) {
		radioButton = new JRadioButton( p.getValue() );
		radioButton.setBounds( p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight() );
		radioButton.setActionCommand( p.getCommand() );
	}

	@Override
	public Container get() {
		return radioButton;
	}

	@Override
	protected ScreenComponent create( SCProperties properties ) {
		SCRadioButton scRadioButton = new SCRadioButton();
		scRadioButton.setId( properties.getId() );
		scRadioButton.newRadioButton( properties );
		return scRadioButton;
	}
}