package nikolay.morshchagin.ProfileTable;

import nikolay.morshchagin.Data.ReactionType;

public class DefenseColumn extends ProfilePos {
	@Override
	public String getName() {
		return ReactionType.DEFENSE.value();
	}

	@Override
	public int value() {
		return 1;
	}

	@Override
	public ProfilePosType type() {
		return ProfilePosType.COLUMN;
	}
}
