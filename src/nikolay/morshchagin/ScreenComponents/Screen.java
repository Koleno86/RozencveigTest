package nikolay.morshchagin.ScreenComponents;

import java.util.Vector;

public class Screen {
	private int	number;
	private Vector<ScreenComponent>	components	= new Vector<ScreenComponent>();

	public Screen( int number ) {
		this.number = number;
	}

	public void setNumber( int number ) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void addComponent( ScreenComponent component ) {
		components.add( component );
	}

	public ScreenComponent getComponent( int item ) {
		return components.elementAt( item );
	}

	public int getComponentsCount() {
		return components.size();
	}
}