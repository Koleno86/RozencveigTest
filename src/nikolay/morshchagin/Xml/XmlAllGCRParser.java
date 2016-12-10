package nikolay.morshchagin.Xml;

import nikolay.morshchagin.Data.GCR;
import nikolay.morshchagin.Data.Reaction;
import nikolay.morshchagin.Data.SituationType;
import nikolay.morshchagin.Data.Situations;

//Загрузить все варианты ответов с типами реакций пациента
public class XmlAllGCRParser extends XmlParser {
	private final String allSituationsGCRTag = "AllSituationsGCR";
	private final String situationTag = "Situation";
	private final String answerTag = "Answer";
	private final String numberAttribute = "number";
	private final String addAttribute = "add";

	private GCR	patientGCR = new GCR();

	public XmlAllGCRParser( String fileName ) {
		super( fileName );
	}	

	@Override
	public void makeParsing() {
		
		for ( int situationCount = 0; situationCount < getChildCount( allSituationsGCRTag, 0, situationTag ); situationCount++ ) {
			for ( int answerCount = 0; answerCount < getChildCount( situationTag, situationCount, answerTag ); answerCount++ ) {
				if(addReaction(situationCount, answerCount)) {
					break;
				}
			}
		}
	}	
	
	private boolean addReaction(int situationNumber, int answerNumber) {
		Situations situations = Situations.getInstance();		
		SituationType situationType = SituationType.getByValue( getChildAttribute(allSituationsGCRTag, 0, situationTag, situationNumber, "type" ) );
				
		int attributeAnswer = StringToInt( getChildAttribute( situationTag, situationNumber, answerTag, answerNumber, numberAttribute ) );
		int patientAnswer = situations.getPatientAnswer( situationNumber );

		Reaction basicReaction = Reaction.getByValue( getChildValue( situationTag, situationNumber, answerTag, answerNumber ) );
		Reaction additionReaction = Reaction.getByValue( getChildAttribute( situationTag, situationNumber, answerTag, answerNumber, addAttribute ) );

		final boolean additionComplete = true;
		final boolean additionFailed = false;
    final boolean isAttributeAnswerFound = ( attributeAnswer == patientAnswer ); 
		
    
		if ( isAttributeAnswerFound ) {
			patientGCR.addFullReaction( basicReaction, additionReaction, situationNumber );					
			situations.addType( situationType, situationNumber );
			
			return additionComplete;
		}
		
		return additionFailed;
	}

	public static GCR generatePatientGCR() {
		XmlAllGCRParser xml = new XmlAllGCRParser( ".\\programData\\AllSituationsGCR.xml" );
		xml.prepareDocument();
		xml.makeParsing();
		return xml.patientGCR;
	}	
}
