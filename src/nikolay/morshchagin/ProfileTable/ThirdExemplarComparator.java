package nikolay.morshchagin.ProfileTable;

import java.util.Comparator;

public class ThirdExemplarComparator implements Comparator<ProfileCell> {

	@Override
	public int compare( ProfileCell cell1, ProfileCell cell2 ) {
		float cell1Value = cell1.value();
		float cell2Value = cell2.value();
		
		if(cell1Value >= cell2Value) {
			return -1;
		}
		else if(cell1Value < cell2Value) {
			return 1;
		}
		else
		  return 0;
	}
}
