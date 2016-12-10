package nikolay.morshchagin.Data;

import java.util.Vector;

// Вычисление групповой конформности(Мера индивидуальной адаптации субъекта к социальному окружению)
public class GCR {
	private Vector<Reaction> basicReactions = new Vector<Reaction>();
	private Vector<Reaction> additionReactions = new Vector<Reaction>();

	public float calculateRatingByTwoGCR( GCR gcr ) {
		float rate = 0;
		
		for ( int situationCount = 0; situationCount < getMaxReactions(); situationCount++ ) {
			rate += getSituationRate( gcr, situationCount );
		}
		return rate;
	}		
	
	private float getSituationRate( GCR gcr, int situationNumber ) {
		float rate = 0;
		boolean basicReaction = true;
		boolean additionReaction = false;
		
		if ( isReactionsEquals( basicReaction, gcr, situationNumber ) ) {
			rate += getFullOrHalfRate( situationNumber );
		}
		if ( isReactionsEquals( additionReaction, gcr, situationNumber ) ) {
			rate += getFullOrHalfRate( situationNumber );
		}
		
		assert (0 < rate && rate <= 1);
		return rate;
	}	

	private boolean isReactionsEquals( boolean basicReaction, GCR gcr, int situationNumber ) {
		if ( basicReaction ) {
			boolean basicAndBasicEquals = getBasicReaction( situationNumber ).equals( gcr.getBasicReaction( situationNumber ) );
			boolean basicAndAdditionEquals = getBasicReaction( situationNumber ).equals( gcr.getAdditionReaction( situationNumber ) );
			
			if ( isBasicReactionExist( situationNumber ) && ( basicAndBasicEquals || basicAndAdditionEquals ) ) {
				return true;
			}
		}
		
		final boolean additionReaction = !basicReaction;
		if ( additionReaction ) {
			boolean additionAndBasicEquals = getAdditionReaction( situationNumber ).equals( gcr.getBasicReaction( situationNumber ) );
			boolean additionAndAdditionEquals = getAdditionReaction( situationNumber ).equals( gcr.getAdditionReaction( situationNumber ) );
			
			if ( isAdditionReactionExist( situationNumber ) && ( additionAndBasicEquals || additionAndAdditionEquals ) ) {
				return true;
			}
		}	
		return false;
	}	
	
	public float getFullOrHalfRate( int situationNumber ) {
		final float fullRate = 1;		
		final float halfRate = 0.5f;
		
		if ( isSimpleReaction( situationNumber ) ) {
			return fullRate;
		}
		return halfRate;
	}	
	
	public boolean isSimpleReaction( int situationNumber ) {
		final boolean simpleReaction = true;
		final boolean complexReaction = false;
		
		if ( isAdditionReactionExist( situationNumber ) ) {
			return complexReaction;
		}
		return simpleReaction;
	}

	public float ratingInPercent( float rate, int maxReactions ) {
		final float halfRate = 0.5f;
		final int maxPercent = 100;
		final float maxRate = getMaxReactions();
		
		assert ( 0 < maxReactions  && ( halfRate <= rate && rate <= maxRate ) );
		return ( rate * maxPercent ) / maxReactions;
	}	
	
	public int getMaxReactions() throws RuntimeException {
		Situations situations = Situations.getInstance();	
		int maxSituations = 0;
		
		for ( int situationCount = 0; situationCount < situations.size(); situationCount++ ) {
			if ( isBasicReactionExist( situationCount ) ) {
				maxSituations++;
			}
		}
		
		assert 0 < maxSituations;
		return maxSituations;
	}
	
	public void addFullReaction( Reaction basicReaction, Reaction additionReaction, int situationNumber ) {
		basicReactions.insertElementAt( basicReaction, situationNumber );
		additionReactions.insertElementAt( additionReaction, situationNumber );
	}
  
	public Reaction getBasicReaction( int situationNumber ) {
		return basicReactions.elementAt( situationNumber );
	}

	public Reaction getAdditionReaction( int situationNumber ) {
		return additionReactions.elementAt( situationNumber );
	}	
	
	public boolean isBasicReactionExist( int situationNumber ) {
		if ( !getBasicReaction( situationNumber ).isEmpty() ) {
			return true;
		}	
		return false;
	}
	
	public boolean isAdditionReactionExist( int situationNumber ) {
		if ( !getAdditionReaction( situationNumber ).isEmpty() ) {
			return true;
		}	
		return false;
	}
}
