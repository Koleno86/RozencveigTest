package nikolay.morshchagin;

import nikolay.morshchagin.GUI.*;

class Main {
	public static void main( String[] args ) {
		final int mainScreen = 1001;
		Gui gui = new RozencveigTestGUI();
		
		gui.callScreen( mainScreen );
	}
}