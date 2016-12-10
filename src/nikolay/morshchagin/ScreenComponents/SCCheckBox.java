package nikolay.morshchagin.ScreenComponents;

import javax.swing.*;
import java.awt.Container;

public class SCCheckBox extends ScreenComponent {
	private JCheckBox	checkBox;

	public SCCheckBox() {
		super( ScreenComponent.CHECK_BOX );
	}

	private void newCheckBox( SCProperties p ) {
		checkBox = new JCheckBox( "<html>" + p.getValue() + "</html>" );
		checkBox.setBounds( p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight() );
		checkBox.setActionCommand( p.getCommand() );
	}

	@Override
	public Container get() {
		return checkBox;
	}

	@Override
	protected ScreenComponent create( SCProperties properties ) {
		SCCheckBox scCheckBox = new SCCheckBox();
		scCheckBox.setId( properties.getId() );
		scCheckBox.newCheckBox( properties );
		return scCheckBox;
	}
}
