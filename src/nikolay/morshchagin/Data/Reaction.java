package nikolay.morshchagin.Data;

public enum Reaction {
	ABSENT("", ReactionDirection.ABSENT, ReactionType.ABSENT),
	EXTRAPUNITIVE_DOMINANT( "E'", ReactionDirection.EXTRAPUNITIVE, ReactionType.DOMINANT ),
	EXTRAPUNITIVE_DEFENSE( "E", ReactionDirection.EXTRAPUNITIVE, ReactionType.DEFENSE ),
	EXTRAPUNITIVE_NEEDED( "e", ReactionDirection.EXTRAPUNITIVE, ReactionType.NEEDED ),
	INTROPUNITIVE_DOMINANT( "I'", ReactionDirection.INTROPUNITIVE, ReactionType.DOMINANT ),
	INTROPUNITIVE_DEFENSE( "I", ReactionDirection.INTROPUNITIVE, ReactionType.DEFENSE ),
	INTROPUNITIVE_NEEDED( "i", ReactionDirection.INTROPUNITIVE, ReactionType.NEEDED ),
	IMPUNITIVE_DOMINANT( "M'", ReactionDirection.IMPUNITIVE, ReactionType.DOMINANT ),
	IMPUNITIVE_DEFENSE( "M", ReactionDirection.IMPUNITIVE,ReactionType.DEFENSE ),
	IMPUNITIVE_NEEDED( "m", ReactionDirection.IMPUNITIVE, ReactionType.NEEDED ),
	EXTRAPUNITIVE_NEGATIVE( "E_", ReactionDirection.EXTRAPUNITIVE, ReactionType.DEFENSE ),
	INTROPUNITIVE_NEGATIVE( "I_", ReactionDirection.INTROPUNITIVE, ReactionType.DEFENSE );
	
	private String value;
	private ReactionDirection direction;
	private ReactionType type;
	
	Reaction( String value, ReactionDirection direction, ReactionType type ) {
		this.value = value;
		this.direction = direction;
		this.type = type;
	}
	
	public String value() {
		return value;
	}
	
	public ReactionDirection direction() {
		return direction;
	}
	
	public ReactionType type() {
		return type;
	}
	
	public static Reaction getByValue(String value) { 
		for(Reaction reaction : Reaction.values() ) {
			final boolean isReactionFound = reaction.value().equals( value );
			
			if(isReactionFound) {
			  return reaction;
			}			
		}

		throw new RuntimeException("Не удалось определить рекцию по значению");
	}
	
	public static Reaction getByParams(String reactionDirection, String reactionType) {
		for(Reaction reaction : Reaction.values() ) {
			final boolean isReactionFound = 
				reaction.direction().value().equals( reactionDirection ) && 
				reaction.type().value().equals( reactionType );
			
			if(isReactionFound) {
				return reaction;
			}
		}
		
		throw new RuntimeException("Не удалось определить реакцию по параметрам");
	}
	
	public boolean isEmpty() {
		final boolean patientWithoutReaction = true;
		final boolean patientHasReaction = false;
		
		if(this.equals( ABSENT )) {
			return patientWithoutReaction;
		}
		
		return patientHasReaction;
	}
}

