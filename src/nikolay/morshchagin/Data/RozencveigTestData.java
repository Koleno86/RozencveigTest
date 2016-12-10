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
			File directory = new File( ".\\����������\\" );
			File file = new File( directory, patient.getName() + ".txt" );
			if ( !directory.exists() ) {
				directory.mkdir();
			}
			
			FileWriter writer = new FileWriter( file, false );		
			PatientDataWriter patientDataWriter = new PatientDataWriter(patient, writer);
			
			writer.write( "�������: " + patient.getName() );
			writer.append( newLine );
			
			writer.write( "�������: " + patient.getAge() + " ���" );
			writer.append( skipLine );
			writer.append( uLine );
			writer.append( skipLine );
			
			writer.write( "����������� ������� ������������ ��� ���������� ������ " + patient.getGroup() + ", GCR:" );
			writer.append( skipLine );			
			
			patientDataWriter.writeStandartGCR();
			writer.append( skipLine );

			writer.write( "����� �������� ��� ���������: " + String.valueOf( patient.getMaxStandartReactions() ) );
			writer.append( skipLine );			
			writer.append( uLine );
			writer.append( skipLine );
			
			writer.write( "������� ������������ ��������, GCR:" );
			writer.append( skipLine );			
	    patientDataWriter.writePatientGCR();
			
			writer.append( skipLine );
      
			writer.write( "����������� ������������ �������� � ��������� �� ����������: " );
	    patientDataWriter.writeCoeff();
			
			writer.append( skipLine );	
			writer.append( uLine );
			writer.append( skipLine );

			writer.write( "������� �������� ��������:" );
			writer.append( skipLine );			
			patientDataWriter.writeProfileTable();

			writer.append( skipLine );	
			writer.append( uLine );
			writer.append( skipLine );			
			
			writer.write( "������ �������: " );
			patientDataWriter.writeFirstExemplar();
			writer.append( skipLine );

			writer.write( "������ �������: " );
			patientDataWriter.writeSecondExemplar();			
			writer.append( skipLine );
			
			writer.write( "������ �������: " );
			patientDataWriter.writeThirdExemplar();					

			writer.append( skipLine );	
			writer.append( uLine );
			writer.append( skipLine );					
			
			writer.write( "��������� �������: " + skipLine );
			writer.write( "������������� ������� � ��������� ��������� � �����������: " );
			writer.append( skipLine );
			patientDataWriter.writeFourExemplar();					
			writer.append( skipLine );			
			
			writer.flush();
			writer.close();
		}
		catch ( IOException ex ) {
			JOptionPane.showMessageDialog( null, "������ �������� ����� �����������", "������ ��������", JOptionPane.ERROR_MESSAGE );
		}
	}
}