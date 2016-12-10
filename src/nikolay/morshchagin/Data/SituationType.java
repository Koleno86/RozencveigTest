package nikolay.morshchagin.Data;

public enum SituationType {
	ALL(""),
	ACCUSATION("accusation"),
	BARRIER("barrier");
	
	private String type;
	
	SituationType(String type) {
		this.type = type;
	}
	
	public String value() {
		return type;
	}
	
	public static SituationType getByValue(String value) {
		for(SituationType situationType : SituationType.values()) {
			final boolean isSituationTypeFound = situationType.value().equals( value );
			
			if(isSituationTypeFound) {
				return situationType;
			}
		}
		
		throw new RuntimeException("Тип ситуации не определен!");
	}
}
