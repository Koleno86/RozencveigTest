package nikolay.morshchagin.ProfileTable;

import nikolay.morshchagin.Data.ReactionDirection;

public class ExtrapunitiveRow extends ProfilePos {
	@Override
	public String getName() {
		return ReactionDirection.EXTRAPUNITIVE.value();
	}

	@Override
	public int value() {
		return 0;
	}

	@Override
	public ProfilePosType type() {
		return ProfilePosType.ROW;
	}
}
