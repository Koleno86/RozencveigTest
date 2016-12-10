package nikolay.morshchagin.ProfileTable;

import java.util.TreeSet;
import java.util.Vector;
import nikolay.morshchagin.Data.GCR;
import nikolay.morshchagin.Data.Reaction;
import nikolay.morshchagin.Data.SituationType;
import nikolay.morshchagin.Data.Situations;

//Вычисление таблицы профилей теста
public class ProfileTable {
	private final int maxRows = 5;
	private final int maxColumns = 5;
	public final int mainRows = 3;
	public final int mainColumns = 3;
	
	private SituationType situationType;
	private GCR	patientGCR;	
	private Vector<ProfileCell> cells = new Vector<ProfileCell>();
	
	public ProfileTable( GCR patientGCR, SituationType situationType ) {
		this.patientGCR = patientGCR;
    this.situationType = situationType;
		
		initializeCells();
		fillProfileTable();
	}
 	
	private void initializeCells() {
		ProfileCell cell;
		
		for(int rowCount = 0; rowCount < maxRows; rowCount++) {
			for(int columnCount = 0; columnCount < maxColumns; columnCount++) {
				cell = new ProfileCell(ProfilePos.getRow( rowCount ), ProfilePos.getColumn( columnCount ));
				cells.add( cell );
			}
		}
	}

	private void fillProfileTable() {
	  Situations situations = Situations.getInstance();
	  
	  for(int situationCount = 0; situationCount < patientGCR.getMaxReactions(); situationCount++) {
		  final boolean isCountBySituationType = 
		    ( situationType.equals( SituationType.ALL ) || situationType.equals( situations.getType( situationCount ) ));
		  
	    if(isCountBySituationType) {
		  	summaRatingsByReaction( patientGCR.getBasicReaction( situationCount ), situationCount );
		  	summaRatingsByReaction( patientGCR.getAdditionReaction( situationCount ), situationCount );	  	
	    }
	  }
    
    for(int rowCount = 0; rowCount < maxRows; rowCount++) {
	    summaAndPercentInRow( ProfilePos.getRow( rowCount ) );
    }
    
    for(int columnCount = 0; columnCount < maxColumns; columnCount++) {
	    summaAndPercentInColumn( ProfilePos.getColumn( columnCount ) );
    }    
	}	

	private void summaRatingsByReaction(Reaction reaction, int situation) {
    final boolean isPatientHasReaction = ( !reaction.isEmpty() );
		final ProfilePos row = ProfilePos.getRow( reaction );
		final ProfilePos column = ProfilePos.getColumn( reaction );  
    float rate = getCellValue( row, column );
    
    if(isPatientHasReaction) {
  		rate += patientGCR.getFullOrHalfRate( situation );
      setCellValue( row, column, rate );
    }
	}	

	public float getCellValue(ProfilePos row, ProfilePos column) {
		return getCell(row, column).value();
	}	
	
	public ProfileCell getCell(ProfilePos row, ProfilePos column) {
		for(ProfileCell cell : cells) {
			boolean isCellFound = (row.equals( cell.getRow()) && column.equals( cell.getColumn()));
			
			if(isCellFound) {
				return cell;
			}			
		}		
		
		throw new RuntimeException("Не удалось определить ячейку таблицы профилей");
	}
			
	private void summaAndPercentInRow(ProfilePos reactionDirection) {
		final ProfilePos summa = ProfilePos.summaPos();  
		final ProfilePos percentage = ProfilePos.percentagePos();
    float rate = 0;
		
		for(int columnCount = 0; columnCount < mainColumns; columnCount++) {
			rate += getCellValue(reactionDirection, ProfilePos.getColumn( columnCount ));
		}
		
		setCellValue(reactionDirection, summa, rate);
		setCellValue(reactionDirection, percentage, patientGCR.ratingInPercent( rate, patientGCR.getMaxReactions()));
	}

	private void summaAndPercentInColumn(ProfilePos reactionType) {
		final ProfilePos summa = ProfilePos.summaPos();
		final ProfilePos percentage = ProfilePos.percentagePos(); 
	  float rate = 0;
			
	  for(int rowCount = 0; rowCount < mainRows; rowCount++) {
	  	rate += getCellValue(ProfilePos.getRow( rowCount ), reactionType);
	  }
	  
	  setCellValue(summa, reactionType, rate);
	  setCellValue(percentage, reactionType, patientGCR.ratingInPercent( rate, patientGCR.getMaxReactions() ));
	}

	public void setCellValue(ProfilePos row, ProfilePos column, float value) {
		getCell(row, column).setValue( value );
	}	
	
	public TreeSet<ProfilePos> getFirstExemplar() {
		
		FirstExemplarComparator comp = new FirstExemplarComparator(this);
    
	  TreeSet<ProfilePos> rows = new TreeSet<ProfilePos>(comp);
	  rows.add( ProfilePos.extrapunitiveRow() );
	  rows.add( ProfilePos.intropunitiveRow() );
	  rows.add( ProfilePos.impunitiveRow() );
	  
		return rows;	
	}
	
	public TreeSet<ProfilePos> getSecondExemplar() {
		
		SecondExemplarComparator comp = new SecondExemplarComparator(this);
    
	  TreeSet<ProfilePos> columns = new TreeSet<ProfilePos>(comp);
	  columns.add( ProfilePos.dominantColumn() );
	  columns.add( ProfilePos.defenseColumn() );
	  columns.add( ProfilePos.neededColumn() );
	  
		return columns;	
	}
	
	public TreeSet<ProfileCell> getThirdExemplar() {
		ThirdExemplarComparator comp = new ThirdExemplarComparator();		
		TreeSet<ProfileCell> cells = new TreeSet<ProfileCell>(comp);
		cells.addAll( this.cells );
    return cells;		
	}	
}
