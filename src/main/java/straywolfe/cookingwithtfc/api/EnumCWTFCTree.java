package straywolfe.cookingwithtfc.api;

import com.dunk.tfc.api.Enums.EnumTree;

public enum EnumCWTFCTree {
	WALNUT("WALNUT", /*minRain*/500f, /*maxRain*/1200f, /*minTemp*/5, /*maxTemp*/25, /*minEVT*/0.5f, /*maxEVT*/2, false,0.8f);
	
	public final float minRain;
	public final float maxRain;
	public final float minTemp;
	public final float maxTemp;
	public final float minEVT;
	public final float maxEVT;
	public final boolean isEvergreen;
	public final float rarity;
	
	public static final EnumCWTFCTree[] AMERICAS = new EnumCWTFCTree[]{WALNUT};
	public static final EnumCWTFCTree[] EUROPE = new EnumCWTFCTree[]{WALNUT};
	public static final EnumCWTFCTree[] ASIA = new EnumCWTFCTree[]{WALNUT};
	public static final EnumCWTFCTree[] AFRICA = new EnumCWTFCTree[]{WALNUT};

	public static final EnumCWTFCTree[][]REGIONS = new EnumCWTFCTree[][]{AMERICAS,EUROPE,ASIA};

	private static final EnumCWTFCTree MATERIALS[] = new EnumCWTFCTree[] {WALNUT};

	private EnumCWTFCTree(String s, float i, float j, float mintemp, float maxtemp, float minevt, float maxevt, boolean e, float r)
	{
		minRain = i;
		maxRain = j;
		minTemp = mintemp;
		maxTemp = maxtemp;
		minEVT = minevt;
		maxEVT = maxevt;
		isEvergreen = e;
		rarity = Math.max(r, 0);
	}
}
