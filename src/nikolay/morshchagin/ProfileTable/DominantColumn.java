package nikolay.morshchagin.ProfileTable;

import nikolay.morshchagin.Data.ReactionType;

public class DominantColumn extends ProfilePos {
	@Override
	public String getName() {
		return ReactionType.DOMINANT.value();
	}

	@Override
	public int value() {
		return 0;
	}

	@Override
	public ProfilePosType type() {
		return ProfilePosType.COLUMN;
	}
}
