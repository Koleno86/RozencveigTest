package nikolay.morshchagin.ProfileTable;

import java.util.Comparator;

public class FirstExemplarComparator implements Comparator<ProfilePos> {
	private ProfileTable profileTable;
	
	FirstExemplarComparator(ProfileTable profileTable) {
		this.profileTable = profileTable;
	}
	
	@Override
	public int compare( ProfilePos row1, ProfilePos row2 ) {		
		float row1Value = profileTable.getCellValue( row1, ProfilePos.summaPos() );
		float row2Value = profileTable.getCellValue( row2, ProfilePos.summaPos() );
		
		if(row1Value >= row2Value) {
			return -1;
		}
		else if(row1Value < row2Value) {
			return 1;
		}
		else
		  return 0;
	}
}
