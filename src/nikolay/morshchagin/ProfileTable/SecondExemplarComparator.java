package nikolay.morshchagin.ProfileTable;

import java.util.Comparator;

public class SecondExemplarComparator implements Comparator<ProfilePos> {
	private ProfileTable profileTable;
	
	SecondExemplarComparator(ProfileTable profileTable) {
		this.profileTable = profileTable;
	}
	
	@Override
	public int compare( ProfilePos column1, ProfilePos column2 ) {
		float column1Value = profileTable.getCellValue( ProfilePos.summaPos(), column1 );
		float column2Value = profileTable.getCellValue( ProfilePos.summaPos(), column2 );
		
		if(column1Value >= column2Value) {
			return -1;
		}
		else if(column1Value < column2Value) {
			return 1;
		}
		else
		  return 0;
	}
}
