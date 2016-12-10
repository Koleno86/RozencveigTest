package nikolay.morshchagin.ScreenComponents;

public class SCProperties {
	private String	id;
	private String	value;
	private String	command;
	private int			posX;
	private int			posY;
	private int			width;
	private int			height;
	private int			length;

	public SCProperties() {
	}

	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue( String value ) {
		this.value = value;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand( String command ) {
		this.command = command;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX( int posX ) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY( int posY ) {
		this.posY = posY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth( int width ) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight( int height ) {
		this.height = height;
	}

	public void setPosition( int posX, int posY, int width, int height ) {
		this.posX = posX;
		this.posY = posY;
		this.width = width;
		this.height = height;
	}

	public int getLength() {
		return length;
	}

	public void setLength( int length ) {
		this.length = length;
	}
}