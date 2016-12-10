package nikolay.morshchagin.Xml;

import java.util.Vector;
import nikolay.morshchagin.Data.GCR;
import nikolay.morshchagin.ScreenComponents.Screen;

public class XmlFactory {
	public static GCR loadPatientGCR() {
		return XmlAllGCRParser.generatePatientGCR();
	}
	
  public static GCR loadStandartGCR( int age ) {
  	return XmlStandartGCRParser.generateStandart( age );	
  }
  
  public static Vector<Screen> loadScreens() {
  	return XmlScreensParser.generateScreensFrom( ".\\ProgramData\\GUI.xml" );
  }
}
