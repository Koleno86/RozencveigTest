package nikolay.morshchagin.ScreenComponents;

import java.awt.Container;

public abstract class ScreenComponent {
	public static final String RADIO_BUTTON	= "RadioButton";
	public static final String BUTTON	= "Button";
	public static final String IMAGE = "Image";
	public static final String LABEL = "Label";
	public static final String TEXT_FIELD	= "TextField";
	public static final String CHECK_BOX = "CheckBox";	
	
	private String type;
	private String id;

	public ScreenComponent( String type ) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	abstract public Container get();
	
	abstract protected ScreenComponent create(SCProperties properties);
	
	public static ScreenComponent create(String componentType, SCProperties properties) {
		switch(componentType) {
			case BUTTON:
				return new SCButton().create( properties );

			case RADIO_BUTTON:
				return new SCRadioButton().create( properties );	
				
			case IMAGE:
				return new SCImage().create( properties );
				
			case LABEL:
				return new SCLabel().create( properties );
				
			case TEXT_FIELD:
				return new SCTextField().create( properties );
				
			case CHECK_BOX:
				return new SCCheckBox().create( properties );
		}
		
		throw new RuntimeException("Недопустимый компонент экрана!");
	}
}