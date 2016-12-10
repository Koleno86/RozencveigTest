package nikolay.morshchagin.Data;

import java.io.*;
import javax.swing.JOptionPane;

public class RozencveigTestData {
	private Patient patient;
  
	public void createPatient( Patient patient ) {
		this.patient = patient;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void addPatientAnswer( int answer, int situationNumber ) {
		patient.addAnswer( answer, situationNumber );
	}
  
	public void countResult() {
		patient.setPatientGCR();	
		patient.buildProfileTable();
	}
	
	public void printResult() {	
		final String newLine = "\r\n";
		final String skipLine = newLine + newLine;
		final String uLine = "------------------------------------------------------------------------------------------";
		
		try {
			File directory = new File( ".\\Результаты\\" );
			File file = new File( directory, patient.getName() + ".txt" );
			if ( !directory.exists() ) {
				directory.mkdir();
			}
			
			FileWriter writer = new FileWriter( file, false );		
			PatientDataWriter patientDataWriter = new PatientDataWriter(patient, writer);
			
			writer.write( "Пациент: " + patient.getName() );
			writer.append( newLine );
			
			writer.write( "Возраст: " + patient.getAge() + " лет" );
			writer.append( skipLine );
			writer.append( uLine );
			writer.append( skipLine );
			
			writer.write( "Стандартная таблица конформности для возрастной группы " + patient.getGroup() + ", GCR:" );
			writer.append( skipLine );			
			
			patientDataWriter.writeStandartGCR();
			writer.append( skipLine );

			writer.write( "Всего ситуаций для сравнения: " + String.valueOf( patient.getMaxStandartReactions() ) );
			writer.append( skipLine );			
			writer.append( uLine );
			writer.append( skipLine );
			
			writer.write( "Таблица конформности пациента, GCR:" );
			writer.append( skipLine );			
	    patientDataWriter.writePatientGCR();
			
			writer.append( skipLine );
      
			writer.write( "Коэффициент конформности пациента в сравнении со стандартом: " );
	    patientDataWriter.writeCoeff();
			
			writer.append( skipLine );	
			writer.append( uLine );
			writer.append( skipLine );

			writer.write( "Таблица профилей пациента:" );
			writer.append( skipLine );			
			patientDataWriter.writeProfileTable();

			writer.append( skipLine );	
			writer.append( uLine );
			writer.append( skipLine );			
			
			writer.write( "Первый образец: " );
			patientDataWriter.writeFirstExemplar();
			writer.append( skipLine );

			writer.write( "Второй образец: " );
			patientDataWriter.writeSecondExemplar();			
			writer.append( skipLine );
			
			writer.write( "Третий образец: " );
			patientDataWriter.writeThirdExemplar();					

			writer.append( skipLine );	
			writer.append( uLine );
			writer.append( skipLine );					
			
			writer.write( "Четвертый образец: " + skipLine );
			writer.write( "Сравнительная таблица в ситуациях обвинения и припятствия: " );
			writer.append( skipLine );
			patientDataWriter.writeFourExemplar();					
			writer.append( skipLine );			
			
			writer.flush();
			writer.close();
		}
		catch ( IOException ex ) {
			JOptionPane.showMessageDialog( null, "Ошибка создания файла результатов", "Плохой параметр", JOptionPane.ERROR_MESSAGE );
		}
	}
}