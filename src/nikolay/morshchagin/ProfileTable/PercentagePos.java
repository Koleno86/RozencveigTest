package nikolay.morshchagin.ProfileTable;

public class PercentagePos extends ProfilePos {
	@Override
	public String getName() {
		return "%";
	}

	@Override
	public int value() {
		return 4;
	}

	@Override
	public ProfilePosType type() {
		return ProfilePosType.ROW_COLUMN;
	}
}
