package nikolay.morshchagin.ProfileTable;

public class ProfileCell {
	private float value;
	private ProfilePos row;
	private ProfilePos column;
	
	public ProfileCell(ProfilePos row, ProfilePos column) {
		this.row = row;
		this.column = column;
	}
	
	public void setValue(float value) {
		this.value = value;
	}
	
	public float value() {
		return value;
	}
	
	public ProfilePos getRow() {
	  return row;
	}
	
	public ProfilePos getColumn() {
		return column;
	}
}
