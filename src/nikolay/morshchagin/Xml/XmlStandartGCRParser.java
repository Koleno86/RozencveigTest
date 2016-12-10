package nikolay.morshchagin.Xml;

import javax.swing.JOptionPane;
import nikolay.morshchagin.Data.GCR;
import nikolay.morshchagin.Data.Reaction;

//Загрузить стандартную таблицу конформности по возрасту
public class XmlStandartGCRParser extends XmlParser {
	private final String standartGCRTag = "StandartGCR";
	private final String patientTag = "Patient";
	private final String situationTag = "Situation";
	private final String ageMinAttribute = "age_min";
	private final String ageMaxAttribute = "age_max";
	private final String addAttribute = "add";
	
	private int	age;
	private GCR	standartGCR	= new GCR();

	public XmlStandartGCRParser( String fileName ) {
		super( fileName );
	}
		
	@Override
	public void makeParsing() {

    addFullReaction( ageGroupIndex() );
	
	}

	private void addFullReaction(int ageGroupIndex) {
		for ( int situationCount = 0; situationCount < getChildCount( patientTag, ageGroupIndex, situationTag ); situationCount++ ) {
			Reaction basicReaction = Reaction.getByValue( getChildValue( patientTag, ageGroupIndex, situationTag, situationCount ) );
			Reaction additionReaction = Reaction.getByValue( getChildAttribute( patientTag, ageGroupIndex, situationTag, situationCount, addAttribute ) );
			
			standartGCR.addFullReaction( basicReaction, additionReaction, situationCount );
		}	
	}	

	private int ageGroupIndex() {
		for ( int ageGroupCount = 0; ageGroupCount < getChildCount( standartGCRTag, 0, patientTag ); ageGroupCount++ ) { 
			try {
			  int minAge = Integer.valueOf( getChildAttribute( standartGCRTag, 0, patientTag, ageGroupCount, ageMinAttribute ) );
			  int maxAge = Integer.valueOf( getChildAttribute( standartGCRTag, 0, patientTag, ageGroupCount, ageMaxAttribute ) );
			  
				if ( minAge <= age && age <= maxAge ) {
					return ageGroupCount;
				}
			}
			catch ( NumberFormatException ex ) {
				JOptionPane.showMessageDialog( null, 
					"Ошибка в XML-файле standartGCR: аттр. age_min или age_max не число", "NaN", JOptionPane.ERROR_MESSAGE );
			}	
		}
		
		throw new RuntimeException("Ошибка в XML-файле standartGCR: Не найдена возрастная группа!");
	}	
	
	public static GCR generateStandart( int age ) {
		XmlStandartGCRParser xml = new XmlStandartGCRParser( ".\\ProgramData\\StandartGCR.xml" );
		xml.prepareDocument();
		xml.age = age;
		xml.makeParsing();
		return xml.standartGCR;
	}
}
