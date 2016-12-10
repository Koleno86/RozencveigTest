package nikolay.morshchagin.ScreenComponents;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.Container;

public class SCTextField extends ScreenComponent {
	public class JTextFieldLimit extends PlainDocument {
		/**
		 * 
		 */
		private static final long	serialVersionUID	= -3682592919566506533L;
		private int								limit;

		JTextFieldLimit( int limit ) {
			super();
			this.limit = limit;
		}

		public void insertString( int offset, String str, AttributeSet attr ) throws BadLocationException {
			if ( str == null )
				return;
			if ( ( getLength() + str.length() ) <= limit ) {
				super.insertString( offset, str, attr );
			}
		}
	};

	private JTextField	textField;

	public SCTextField() {
		super( ScreenComponent.TEXT_FIELD );
	}

	private void newTextField( SCProperties p ) {
		textField = new JTextField();
		textField.setBounds( p.getPosX(), p.getPosY(), p.getWidth(), p.getHeight() );
		textField.setDocument( new JTextFieldLimit( p.getLength() ) );
		textField.setText( p.getValue() );
	}

	@Override
	public Container get() {
		return textField;
	}

	@Override
	protected ScreenComponent create( SCProperties properties ) {
		SCTextField scTextField = new SCTextField();
		scTextField.setId( properties.getId() );
		scTextField.newTextField( properties );
		return scTextField;
	}
}