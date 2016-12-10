package nikolay.morshchagin.Xml;

import javax.swing.*;
import nikolay.morshchagin.ScreenComponents.*;
import java.util.Vector;

//Загрузить экраны с компонентами программы
public class XmlScreensParser extends XmlParser {
	private final String GUI = "GUI";
	private final String SCREEN	= "Screen";
	private final String BUTTON_GROUP	= "ButtonGroup";

	private int	groupIndex = 0;
	private Vector<Screen> screens = new Vector<Screen>();

	private XmlScreensParser( String fileName ) {
		super( fileName );
	}
	
	@Override
	public void makeParsing() {
		for ( int screenTagCount = 0; screenTagCount < getChildCount( GUI, 0, SCREEN ); screenTagCount++ ) {
			Screen screen = new Screen( StringToInt( getChildAttribute( GUI, 0, SCREEN, screenTagCount, "number" ) ) );
			
			addScreenComponent( screen, ScreenComponent.BUTTON, screenTagCount );
			addScreenComponent( screen, ScreenComponent.IMAGE, screenTagCount );
			addScreenComponent( screen, ScreenComponent.LABEL, screenTagCount );
			addScreenComponent( screen, BUTTON_GROUP, screenTagCount );
			addScreenComponent( screen, ScreenComponent.TEXT_FIELD, screenTagCount );
			addScreenComponent( screen, ScreenComponent.CHECK_BOX, screenTagCount );
			
			screens.addElement( screen );
		}
	}

	private Screen addScreenComponent( Screen screen, String componentTag, int screenTagIndex ) {

		for ( int componentTagCount = 0; componentTagCount < getChildCount( SCREEN, screenTagIndex, componentTag ); componentTagCount++ ) {	
			
			if ( isComponentTagValueFull(screenTagIndex, componentTag, componentTagCount) ) {				
				SCProperties properties = buildComponentProperties(screenTagIndex, componentTag, componentTagCount);
				
				if( isComplexComponent(componentTag) ) {
					screen = buildButtonGroup(screen, properties);
				}
				else {
					screen = buildSimpleComponent(screen, componentTag, properties);
				}
			}
		}
		return screen;		
	}

	private SCProperties buildComponentProperties(int screenTagIndex, String componentTag, int componentTagIndex) {
		SCProperties properties = new SCProperties();
		
		properties.setId( getChildAttribute( SCREEN, screenTagIndex, componentTag, componentTagIndex, "id" ) );
		properties.setValue( getChildValue( SCREEN, screenTagIndex, componentTag, componentTagIndex ) );
		properties.setCommand( getChildAttribute( SCREEN, screenTagIndex, componentTag, componentTagIndex, "command" ) );		
		properties.setPosition( 
			StringToInt( getChildAttribute( SCREEN, screenTagIndex, componentTag, componentTagIndex, "posX" ) ),
			StringToInt( getChildAttribute( SCREEN, screenTagIndex, componentTag, componentTagIndex, "posY" ) ), 
			StringToInt( getChildAttribute( SCREEN, screenTagIndex, componentTag, componentTagIndex, "width" ) ),
			StringToInt( getChildAttribute( SCREEN, screenTagIndex, componentTag, componentTagIndex, "height" ) ) 
	  );
		
		properties.setLength( StringToInt( getChildAttribute( SCREEN, screenTagIndex, componentTag, componentTagIndex, "length" ) ) );
		
		return properties;
	}
	
	private boolean isComplexComponent( String componentTag) {
		if(componentTag.equals( BUTTON_GROUP )) {
			return true;
		}
		
		return false;
	}
	
	private Screen buildButtonGroup(Screen screen, SCProperties properties ) {
		ButtonGroup buttonGroup = new ButtonGroup();
		
		for ( int buttonTagCount = 0; buttonTagCount < getChildCount( BUTTON_GROUP, groupIndex, ScreenComponent.RADIO_BUTTON ); buttonTagCount++ ) {
			
			properties.setValue( getChildValue( BUTTON_GROUP, groupIndex, ScreenComponent.RADIO_BUTTON, buttonTagCount ) );
			properties.setCommand( getChildAttribute( BUTTON_GROUP, groupIndex, ScreenComponent.RADIO_BUTTON, buttonTagCount, "command" ) );
			screen.addComponent( ScreenComponent.create( ScreenComponent.RADIO_BUTTON, properties ));							
			
			properties.setPosY( properties.getPosY() + properties.getHeight() );
			
			for ( int buttonCount = 0; buttonCount < screen.getComponentsCount(); buttonCount++ ) {
				if ( screen.getComponent( buttonCount ).getType() == ScreenComponent.RADIO_BUTTON ) {
					JRadioButton jRadioButton = (JRadioButton) screen.getComponent( buttonCount ).get();
					buttonGroup.add( jRadioButton );
				}
			}
		}
		groupIndex++;		
		
		return screen;
	}
	
	private Screen buildSimpleComponent(Screen screen, String componentTag, SCProperties properties) {
		screen.addComponent( ScreenComponent.create( componentTag, properties ) );
		return screen;
	}
	
	private boolean isComponentTagValueFull(int screenTagIndex, String componentTag, int componentTagIndex) {
		if ( getChildValue( SCREEN, screenTagIndex, componentTag, componentTagIndex ) != null ) {
			return true;
		}
		return false;
	}	

	public static Vector<Screen> generateScreensFrom( String fileName ) {
		XmlScreensParser xml = new XmlScreensParser( fileName );
		xml.prepareDocument();
		xml.makeParsing();
		return xml.screens;
	}
}