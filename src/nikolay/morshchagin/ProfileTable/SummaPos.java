package nikolay.morshchagin.ProfileTable;

public class SummaPos extends ProfilePos {

	@Override
	public String getName() {
		return "Сумма";
	}

	@Override
	public int value() {
		return 3;
	}

	@Override
	public ProfilePosType type() {
		return ProfilePosType.ROW_COLUMN;
	}
}
