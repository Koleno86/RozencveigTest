package nikolay.morshchagin.ProfileTable;

import nikolay.morshchagin.Data.ReactionDirection;

public class ImpunitiveRow extends ProfilePos {
	@Override
	public String getName() {
		return ReactionDirection.IMPUNITIVE.value();
	}

	@Override
	public int value() {
		return 2;
	}

	@Override
	public ProfilePosType type() {
		return ProfilePosType.ROW;
	}
}
