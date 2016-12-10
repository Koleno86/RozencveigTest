package nikolay.morshchagin.Data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.TreeSet;
import nikolay.morshchagin.ProfileTable.ProfileTable;
import nikolay.morshchagin.ProfileTable.ProfileCell;
import nikolay.morshchagin.ProfileTable.ProfilePos;

//Вывод в файл рассчетных данных пациента
public class PatientDataWriter {
	private Patient patient;
	private FileWriter writer;
	
	PatientDataWriter(Patient patient, FileWriter writer) {
		this.patient = patient;
		this.writer = writer;
	}

//----------------------------------------------------------------------------------------------------------------------------		
// Коэффициент конформности	
	public void writeCoeff() throws IOException {
		Formatter gcrCoeff = new Formatter();
		gcrCoeff.format( "%-2.1f%s (%-2.1f %s)", patient.getGCRPercantageRating(), "%", patient.getGCRRating(), "баллов" );
		writer.write( gcrCoeff.toString() );
		gcrCoeff.close();		
	}

//----------------------------------------------------------------------------------------------------------------------------	
// Таблицы конформности	
	public void writeGCR(boolean isPatientGCR) throws IOException {
		final String newLine = "\r\n";

		final int maxSituations = Situations.getInstance().size();
		final GCR gcr; 
		
		if(isPatientGCR) {
		  gcr = patient.getPatientGCR();
		}
		else {
			gcr = patient.getStandartGCR();
		}		
		
		for(int situationCount = 0; situationCount < maxSituations; situationCount++ ) {
			String reaction = Reaction.ABSENT.value();
			boolean isReactionUnique = gcr.isSimpleReaction(situationCount);
			
			if(isReactionUnique) {
				reaction = gcr.getBasicReaction(situationCount).value();
			}
			else {
				reaction = gcr.getBasicReaction(situationCount).value() + "/" + gcr.getAdditionReaction(situationCount).value();
			}
			
			Formatter situationLine = new Formatter();			
			situationLine.format( "%-6s%-10s", String.valueOf( situationCount + 1 ), reaction );
			writer.write( situationLine.toString() );
			situationLine.close();
			
			if(situationCount != maxSituations ) {
			  writer.append( newLine );
			}
		}
	}
	
	public void writePatientGCR() throws IOException {
		final boolean patient = true;
		
		writeGCR( patient );
	}
	
	public void writeStandartGCR() throws IOException {
		final boolean standart = false;
		
		writeGCR( standart );
	}
	
//----------------------------------------------------------------------------------------------------------------------------	
// Таблица профилей
	public void writeProfileTable() throws IOException {
	  final String newLine = "\r\n";
	  final int maxRows = 5;
	  final int maxColumns = 5;
	  final ProfileTable profileTable = patient.getProfileTable();
	  
	  Formatter headerLine = new Formatter();
	  
	  headerLine.format( "%-10s", "" );
	  
	  for(int columnCount = 0; columnCount < maxColumns; columnCount++) {	
	  	headerLine.format( "%-10s", ProfilePos.getColumn( columnCount ).getName() );
	  }
	  headerLine.format( "%n%n" );
	  
	  writer.write( headerLine.toString() );
	  headerLine.close();
	  
	  for(int rowCount = 0; rowCount < maxRows; rowCount++) {
	  	ProfilePos row = ProfilePos.getRow( rowCount ); 
	  	
	  	Formatter tableLine = new Formatter();	  	
	  	tableLine.format( "%-10s", row.getName() );
	  		  	
	  	for(int columnCount = 0; columnCount < maxColumns; columnCount++) { 
	  		ProfilePos column = ProfilePos.getColumn( columnCount );
	  		
	  		if( !isCrossSummOrPercentage( row, column ) ) {
	  			tableLine.format( "%-10.1f", profileTable.getCellValue( row, column ) );
	  		}
	  	}
      
	  	tableLine.format( "%n" );
	  	writer.write( tableLine.toString() );
	  	tableLine.close();
	  	
	  	if(rowCount != maxRows ) {
	  	  writer.append( newLine );
	  	}
	  }		
	}	

  private boolean isCrossSummOrPercentage(ProfilePos row, ProfilePos column) {
  	final boolean cross = true;
  	final boolean notCross = false;
  	final ProfilePos summaPos = ProfilePos.summaPos();
  	final ProfilePos percentagePos = ProfilePos.percentagePos();
  	
  	final boolean sumRow = (row == summaPos );
  	final boolean percentRow = (row == percentagePos);
  	final boolean sumOrPercentColumn = ( column == summaPos || column == percentagePos );
  	
		if( sumRow && sumOrPercentColumn ) {
		  return cross;
		}
		
		if ( percentRow && sumOrPercentColumn ) {
			return cross;
		}
		
  	return notCross;
  }	
//----------------------------------------------------------------------------------------------------------------------------		
//Вывод образцов таблицы профилей
	public void writeFirstExemplar() throws IOException {
    ProfileTable profileTable = patient.getProfileTable();
		TreeSet<ProfilePos> rows = profileTable.getFirstExemplar();
    
	  Formatter firstExemplar = new Formatter();	  	  
	  
	  for(ProfilePos row : rows) {	  	
	  	boolean isNotLast = (!row.equals( rows.last()));
	  	
	  	firstExemplar.format( "%s", row.getName() );
	  	
	  	if(isNotLast) {
	  		firstExemplar.format( " > ");
	  	}
	  }
	  
	  writer.write( firstExemplar.toString() );
    firstExemplar.close();
	}

	public void writeSecondExemplar() throws IOException {
    ProfileTable profileTable = patient.getProfileTable();
		TreeSet<ProfilePos> columns = profileTable.getSecondExemplar();
    
	  Formatter secondExemplar = new Formatter();	  	  
	  
	  for(ProfilePos column : columns) {
	  	boolean isNotLast = (!column.equals( columns.last()));	
	  	
	  	secondExemplar.format( "%s", column.getName() );
	  	
	  	if(isNotLast) {
	  		secondExemplar.format( " > ");
	  	}	  	
	  }
	  
	  writer.write( secondExemplar.toString() );
    secondExemplar.close();
	}	
	
	public void writeThirdExemplar() throws IOException {
		ProfileTable profileTable = patient.getProfileTable();
		TreeSet<ProfileCell> cells = profileTable.getThirdExemplar();
		
		Formatter thirdExemplar = new Formatter();
		
		for(ProfileCell cell : cells ) {	
			if(isNotTotals(cell)) {
				boolean isNotLast = (!cell.equals( cells.last()));
				Reaction reaction = Reaction.getByParams( cell.getRow().getName(), cell.getColumn().getName() );

			  thirdExemplar.format( "%s", reaction.value() );
				
				if(isNotLast) {
					thirdExemplar.format( " > " );
				}
			}
		}
		
		writer.write( thirdExemplar.toString() );
		thirdExemplar.close();
	}

	private boolean isNotTotals(ProfileCell cell) {
		return ( !cell.getRow().equals( ProfilePos.summaPos() ) && !cell.getRow().equals( ProfilePos.percentagePos() ) ) &&
			( !cell.getColumn().equals( ProfilePos.summaPos() ) && !cell.getColumn().equals( ProfilePos.percentagePos() ) );
	}	
	
	public void writeFourExemplar() throws IOException {
		ProfileTable accusationProfile = new ProfileTable(patient.getPatientGCR(), SituationType.ACCUSATION);
		ProfileTable barrierProfile = new ProfileTable(patient.getPatientGCR(), SituationType.BARRIER);
		
		float extrapunitiveAccusation = accusationProfile.getCellValue( ProfilePos.extrapunitiveRow(), ProfilePos.percentagePos() );	
		float intropunitiveAccusation = accusationProfile.getCellValue( ProfilePos.intropunitiveRow(), ProfilePos.percentagePos() );		
		float extrapunitiveBarrier = barrierProfile.getCellValue( ProfilePos.extrapunitiveRow(), ProfilePos.percentagePos() );	
		float intropunitiveBarrier = barrierProfile.getCellValue( ProfilePos.intropunitiveRow(), ProfilePos.percentagePos() );	
		
		Formatter fourExemplar = new Formatter();
		
		fourExemplar.format( "%-1s %-7s %-15s %-15s%n%n", "", "%", "Припятствие", "Обвинение" );
		fourExemplar.format( "%-1s %-7s %-15.1f %-15.1f%n%n", "", ReactionDirection.EXTRAPUNITIVE.value(), extrapunitiveBarrier, extrapunitiveAccusation );
		fourExemplar.format( "%-1s %-7s %-15.1f %-15.1f%n%n", "", ReactionDirection.INTROPUNITIVE.value(), intropunitiveBarrier, intropunitiveAccusation );
		
		writer.write( fourExemplar.toString() );
		fourExemplar.close();
	}
}
