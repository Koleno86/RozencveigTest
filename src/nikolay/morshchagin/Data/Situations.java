package nikolay.morshchagin.Data;

import java.util.Vector;

public class Situations {
	private Vector<SituationType> types = new Vector<SituationType>();
  private Vector<Integer> patientAnswers = new Vector<Integer>();
  
  private static Situations instance;
  private Situations() {};
  
  public static Situations getInstance() {
  	if(instance == null) {
  		instance = new Situations();
  	}
  	return instance;
  }
  
  public void addPatientAnswer(int answer, int situationNumber) {
  	patientAnswers.insertElementAt( answer, situationNumber );
  }
  
  public int getPatientAnswer(int situationNumber) {
  	return patientAnswers.elementAt( situationNumber );
  }
  
  public void addType(SituationType type, int situationNumber) {
  	types.insertElementAt( type, situationNumber );
  }
  
  public SituationType getType(int situationNumber) {
  	return types.elementAt( situationNumber );
  }
 
  public int size() {
  	return patientAnswers.size();
  }
}
