package nikolay.morshchagin.Data;

public enum ReactionDirection {
	EXTRAPUNITIVE("E"),
	INTROPUNITIVE("I"),
	IMPUNITIVE("M"),
	ABSENT("");	
	
	private String direction;
	
	ReactionDirection(String direction) {
		this.direction = direction;
	}
	
	public String value() {
		return direction;
	}
}
