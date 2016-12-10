package nikolay.morshchagin.ProfileTable;

import java.util.Vector;
import nikolay.morshchagin.Data.Reaction;
import nikolay.morshchagin.Data.ReactionType;
import nikolay.morshchagin.Data.ReactionDirection;

public abstract class ProfilePos {
	private static Vector<ProfilePos> positions;

	public abstract int value();
	public abstract ProfilePosType type();
	public abstract String getName();
  
	private static void InitializePositions() {
		if(positions == null) {
			positions = new Vector<ProfilePos>();
			
			positions.add( new ExtrapunitiveRow() );
			positions.add( new IntropunitiveRow() );
			positions.add( new ImpunitiveRow() );
			
			positions.add( new DominantColumn() );
			positions.add( new DefenseColumn() );
			positions.add( new NeededColumn() );
			
			positions.add( new SummaPos() );
			positions.add( new PercentagePos() );		
		}
	}
	
	private static Vector<ProfilePos> getPositions() {
		InitializePositions();
		
		return positions;
	}

//--------------------------------------------------------------------------------------------------------		
	
	public static ProfilePos getRow(int position) {
		for(ProfilePos row : getPositions()) {
			boolean isRowFound = (row.value() == position && 
			  ( row.type().equals( ProfilePosType.ROW ) || row.type().equals( ProfilePosType.ROW_COLUMN )));
			
			if(isRowFound) {
				return row;
			}
		}
		
		throw new RuntimeException("Строка не определена!");
	}

	public static ProfilePos getColumn(int position) {	
		for(ProfilePos column : getPositions()) {
			boolean isColumnFound = (column.value() == position && 
				( column.type().equals( ProfilePosType.COLUMN ) || column.type().equals( ProfilePosType.ROW_COLUMN )));
			
			if(isColumnFound) {
				return column;
			}
		}
		
		throw new RuntimeException("Колонка не определена!");
	}

//--------------------------------------------------------------------------------------------------------		
	
	public static ProfilePos getRow(Reaction reaction) { 	
		for(ReactionDirection reactionDirection : ReactionDirection.values() ) {
			boolean isFoundByReactionDirection = reaction.direction().equals( reactionDirection );
			
			if(isFoundByReactionDirection) {
				return getRow(reactionDirection.ordinal());
			}
		}
		
		throw new RuntimeException("Строка не определена!");
	}
	
	public static ProfilePos getColumn(Reaction reaction) {
		for(ReactionType reactionType : ReactionType.values() ) {
			boolean isFoundByReactionType = reaction.type().equals( reactionType );
			
			if(isFoundByReactionType) {
				return getColumn(reactionType.ordinal()); 
			}
		}
		
		throw new RuntimeException("Колонка не определена!");
	}

//--------------------------------------------------------------------------------------------------------		
	
	public static ProfilePos extrapunitiveRow() {
		return getRow(0);		
	}	
	
	public static ProfilePos intropunitiveRow() {
		return getRow(1);
	}
	
	public static ProfilePos impunitiveRow() {
		return getRow(2);
	}	
	
	public static ProfilePos dominantColumn() {
		return getColumn(0);
	}	
	
	public static ProfilePos defenseColumn() {
		return getColumn(1);
	}		
	
	public static ProfilePos neededColumn() {
		return getColumn(2);
	}
	
	public static ProfilePos summaPos() {
		return getColumn(3);   
	}
	
	public static ProfilePos percentagePos() {
		return getColumn(4);		
	}	
		
}
