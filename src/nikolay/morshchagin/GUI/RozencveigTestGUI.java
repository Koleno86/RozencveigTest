package nikolay.morshchagin.GUI;

import java.awt.Container;
import javax.swing.*;
import nikolay.morshchagin.Data.Patient;
import nikolay.morshchagin.Data.RozencveigTestData;
import nikolay.morshchagin.ScreenComponents.ScreenComponent;
import nikolay.morshchagin.Xml.XmlFactory;

public class RozencveigTestGUI extends Gui {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= -4343988309758970385L;
	private RozencveigTestData rozencveigTestData	= new RozencveigTestData();

	public RozencveigTestGUI() {
		super( "Тест Розенцвейга" );
		setScreens( XmlFactory.loadScreens() );
		setBounds( 450, 200, 950, 580 );
		setResizable( false );
		setVisible( true );
	}

	public void userCommand( String syUcomm ) {
		switch ( syUcomm ) {
			case "startTest":
				patientPushStartOnMainScreen();
				break;
				
			case "select":
				patientChoosedAnswer();
				break;
				
			case "exitTest":
				System.exit( 0 );
				break;
		}
	}
  
	private void patientPushStartOnMainScreen() {
		final int firstSituationScreen = 2001;
		
		takePatientData();
		if ( isPatientDataOk() ) {
			callScreen( firstSituationScreen );
		}		
	}
	
	private void takePatientData() {
		String patientName = "";
		int patientAge = 0;
		JTextField textField;
		
		for ( int componentCount = 0; componentCount < getComponentsCount( getCurrentScreenIndex() ); componentCount++ ) {
			String componentType = getComponentType( getCurrentScreenIndex(), componentCount );
			boolean isTextField = ( componentType == ScreenComponent.TEXT_FIELD );
			
			if ( isTextField ) {
				Container component = getComponent( getCurrentScreenIndex(), componentCount );
				String componentId = getComponentId( getCurrentScreenIndex(), componentCount );
				if ( componentId.contains( "name" ) ) {
					textField = (JTextField) component;
					patientName = textField.getText();
				}
				if ( componentId.contains( "age" ) ) {
					textField = (JTextField) component;
					try {
					  patientAge = textField.getText().isEmpty() ? 0 : Integer.valueOf( textField.getText() );
					}
					catch(NumberFormatException e) {
						JOptionPane.showMessageDialog( this, "Введите действительное значение", "Неверный возраст", JOptionPane.ERROR_MESSAGE  );
					}
				}
			}
		}
		rozencveigTestData.createPatient( new Patient( patientName, patientAge ) );
	}

	private boolean isPatientDataOk() {
		boolean badName = false;
		boolean badAge = false;
		boolean checkOk = true;
		
		boolean isBadPatientName = ( rozencveigTestData.getPatient().getName().contains( "ФИО" ) || rozencveigTestData.getPatient().getName().isEmpty() );
		boolean isBadPatientAge = ( rozencveigTestData.getPatient().getAge() <= 5 );
				
		if ( isBadPatientName ) {
			JOptionPane.showMessageDialog( this, "Введите ФИО!", "Неверное ФИО", JOptionPane.ERROR_MESSAGE  );
			return badName;
		}
				
		if ( isBadPatientAge ) {
			JOptionPane.showMessageDialog( this, "Тест рассчитан на людей старше 5 лет!", "Неверный возраст", JOptionPane.ERROR_MESSAGE  );
			return badAge;
		}
		
		return checkOk;
	}	
	
	private void patientChoosedAnswer() {
		final int currentSituation = getCurrentScreenNumber() % 100;
		final int nextScreen = getCurrentScreenNumber() + 1;
		final int finalScreen = 3001;
		final int maxSituations = 24;
		
		addPatientAnswer();
		if( currentSituation != maxSituations ) {
			callScreen( nextScreen );
		}
		else {
			callScreen( finalScreen );
		}	
	}
	
	private void addPatientAnswer() {
		final int situationNumber = ((getCurrentScreenNumber() % 100) - 1 );
		int choosedAnswer = 0;
		for ( int componentCount = 0; componentCount < getComponentsCount( getCurrentScreenIndex() ); componentCount++ ) {
			String componentType = getComponentType( getCurrentScreenIndex(), componentCount );
			boolean isRadioButton = ( componentType == ScreenComponent.RADIO_BUTTON );
			
			if ( isRadioButton ) {
				Container component = getComponent( getCurrentScreenIndex(), componentCount );
				choosedAnswer++;
				JRadioButton radioButton = (JRadioButton) component;
				if ( radioButton.isSelected() ) {
					rozencveigTestData.addPatientAnswer( choosedAnswer, situationNumber );
					break;
				}
			}
		}
	}
	
	public void modifyScreen( int number ) {
		final int finalScreen = 3001;
		
		if ( number == finalScreen ) {
			rozencveigTestData.countResult();
			rozencveigTestData.printResult();
		}
	}	
}