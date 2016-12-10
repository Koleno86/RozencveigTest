package nikolay.morshchagin.Data;

import nikolay.morshchagin.ProfileTable.ProfileTable;
import nikolay.morshchagin.Xml.*;

public class Patient {
	private String name;
	private int	age;
	private String group;
	private GCR	standartGCR;
	private GCR	patientGCR;
	private ProfileTable profileTable;

	public Patient(String name, int age) {
		this.name = name;
		this.age = age;
		
		initializeStandartGCR();
		initializeGroup();
	}

	private void initializeStandartGCR() {
		if(standartGCR == null) {
		  standartGCR = XmlFactory.loadStandartGCR( age );
		}
	}
	
	private void initializeGroup() {
		assert(6 <= age);
		
		if(6 <= age && age <= 7) {
			group = "6-7 лет";
		}
		if(8 <= age && age <= 9) {
			group = "8-9 лет";
		}
		if(10 <= age && age <= 11) {
			group = "10-11 лет";
		}
		if(12 <= age && age <= 13) {
			group = "12-13 лет";
		}		
		if(age >= 14) {
			group = "Взрослые";
		}			
	}	
	
	public void addAnswer(int answer, int situationNumber) {
		Situations situations = Situations.getInstance(); 		
		situations.addPatientAnswer( answer, situationNumber );
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public String getGroup() {
		return group;
	}
	
	public void setPatientGCR() {
		if(patientGCR == null) {
		  patientGCR = XmlFactory.loadPatientGCR();
		}
	}	
	
	public GCR getPatientGCR() {
		return patientGCR;
	}
  
	public GCR getStandartGCR() {
		return standartGCR;
	}

	public ProfileTable getProfileTable() {
		return profileTable;
	}
		
	public float getGCRPercantageRating() {
		return patientGCR.ratingInPercent( getGCRRating(), getMaxStandartReactions() );
	}

	public float getGCRRating() {
		return patientGCR.calculateRatingByTwoGCR( standartGCR );
	}
	
	public int getMaxStandartReactions() {
		return standartGCR.getMaxReactions();
	}
	
	public void buildProfileTable() {
		profileTable = new ProfileTable( patientGCR, SituationType.ALL );		
	}
}