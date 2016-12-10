package nikolay.morshchagin.GUI;

import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;
import nikolay.morshchagin.ScreenComponents.Screen;
import nikolay.morshchagin.ScreenComponents.ScreenComponent;
import java.util.Vector;

public abstract class Gui extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -6441417656201924958L;
	private Vector<Screen>		screens;
	private int								currentScreen;

	public Gui( String title ) {
		super( title );
		setLayout( null );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

	protected int getScreenNumber( int screenIndex ) {
		return screens.elementAt( screenIndex ).getNumber();
	}

	protected Container getComponent( int screenIndex, int componentIndex ) {
		return screens.elementAt( screenIndex ).getComponent( componentIndex ).get();
	}

	protected String getComponentType( int screenIndex, int componentIndex ) {
		return screens.elementAt( screenIndex ).getComponent( componentIndex ).getType();
	}

	protected String getComponentId( int screenIndex, int componentIndex ) {
		return screens.elementAt( screenIndex ).getComponent( componentIndex ).getId();
	}

	protected int getComponentsCount( int screenIndex ) {
		return screens.elementAt( screenIndex ).getComponentsCount();
	}

	protected int getScreensCount() {
		return screens.size();
	}

	public void callScreen( int number ) {
		final int firstScreen = 0;
		int screenIndex = getScreenIndex(number);		
		
		if ( getCurrentScreenNumber() != firstScreen ) {
			clearCurrentScreen();
		}
	  
		for ( int componentCount = 0; componentCount < getComponentsCount( screenIndex ); componentCount++ ) {
			addComponentToScreen( screenIndex, componentCount );
		}
		setCurrentScreenNumber( number );
		modifyScreen( getCurrentScreenNumber() );				
	}	

	protected int getScreenIndex(int number) {
		for ( int screenCount = 0; screenCount < getScreensCount(); screenCount++ ) {
			if ( getScreenNumber( screenCount ) == number ) {
				return screenCount;
			}
		}
		
		throw new RuntimeException("Не удалось найти экран!");
	}		
	
	public int getCurrentScreenNumber() {
		return currentScreen;
	}	

	private void clearCurrentScreen() {
		int currentScreenIndex = getCurrentScreenIndex();
		for ( int componentCount = 0; componentCount < getComponentsCount( currentScreenIndex ); componentCount++ ) {
			remove( getComponent( currentScreenIndex, componentCount ) );
		}
		repaint();		
	}	

	protected int getCurrentScreenIndex() {
		return getScreenIndex(currentScreen);
	}	
	
	private void addComponentToScreen(int screenIndex, int componentIndex ) {
		String componentType = getComponentType( screenIndex, componentIndex );		
		final boolean isButton = ( componentType == ScreenComponent.BUTTON );
		final boolean isRadioButton = ( componentType == ScreenComponent.RADIO_BUTTON );		
		final boolean isStaticComponent = ( !isButton && !isRadioButton );
		
		if(isButton) {
		  addButton(screenIndex, componentIndex);
		}
		else if(isRadioButton) {
			addRadioButton(screenIndex, componentIndex);
		}
		else if(isStaticComponent) {
		  add( getComponent( screenIndex, componentIndex ) );
		}
	}
	
	private void addButton(int screenIndex, int componentIndex ) {
		JButton button = (JButton) getComponent( screenIndex, componentIndex );
		add( button );
		button.addActionListener( this );	
	}
	
	private void addRadioButton(int screenIndex, int componentIndex) {	
		JRadioButton radioButton = (JRadioButton) getComponent( screenIndex, componentIndex );

		radioButton.setSelected( true );
		add( radioButton );

		radioButton.addActionListener( this );
	}
	
	abstract public void modifyScreen( int number );

	public void setCurrentScreenNumber( int number ) {
		this.currentScreen = number;
	}
	
	public Screen getCurrentScreen() {
		for ( int screenCount = 0; screenCount < getScreensCount(); screenCount++ ) {
			if ( getScreenNumber( screenCount ) == currentScreen ) {
				return screens.elementAt( screenCount );
			}
		}
		
		throw new RuntimeException("Не удалось найти экран!");
	}

	public void setScreens( Vector<Screen> screens ) {
		this.screens = screens;
	}

	public void actionPerformed( ActionEvent e ) {
		userCommand( e.getActionCommand() );
	}

	abstract public void userCommand( String syUcomm );
}