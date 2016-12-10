package nikolay.morshchagin.Data;

public enum ReactionType {
	DOMINANT("OD"),
	DEFENSE("ED"),
	NEEDED("NP"),
	ABSENT("");	
	
	private String type;
	
	ReactionType(String type) {
		this.type = type;
	}
	
	public String value() {
		return type;
	}
}