package nikolay.morshchagin.ProfileTable;

import nikolay.morshchagin.Data.ReactionDirection;

public class IntropunitiveRow extends ProfilePos {
	@Override
	public String getName() {
		return ReactionDirection.INTROPUNITIVE.value();
	}

	@Override
	public int value() {
		return 1;
	}

	@Override
	public ProfilePosType type() {
		return ProfilePosType.ROW;
	}
}
