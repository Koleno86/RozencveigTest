package nikolay.morshchagin.ProfileTable;

import nikolay.morshchagin.Data.ReactionType;

public class NeededColumn extends ProfilePos {
	@Override
	public String getName() {
		return ReactionType.NEEDED.value();
	}

	@Override
	public int value() {
		return 2;
	}

@Override
public ProfilePosType type() {
	return ProfilePosType.COLUMN;
}
	
	
}
