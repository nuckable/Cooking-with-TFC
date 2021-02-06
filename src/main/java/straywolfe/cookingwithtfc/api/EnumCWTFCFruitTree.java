package straywolfe.cookingwithtfc.api;

import com.dunk.tfc.api.Enums.EnumFruitTree;

public enum EnumCWTFCFruitTree {
	ALMOND ("ALMOND",       /*minRain*/100, /*maxRain*/3000,  /*minTemp*/15, /*maxTemp*/30, /*minEVT*/0.5f, /*maxEVT*/8f),
	CASHEW ("CASHEW",       /*minRain*/600, /*maxRain*/8000,  /*minTemp*/15, /*maxTemp*/40, /*minEVT*/0.5f, /*maxEVT*/8f),
	COCONUT ("COCONUT",     /*minRain*/750, /*maxRain*/8000,  /*minTemp*/7,  /*maxTemp*/35, /*minEVT*/0.5f, /*maxEVT*/8f),
	HAZELNUT ("HAZELNUT",   /*minRain*/350, /*maxRain*/3000,  /*minTemp*/8,  /*maxTemp*/36, /*minEVT*/0.5f, /*maxEVT*/8f),
	MACADAMIA ("MACADAMIA", /*minRain*/100, /*maxRain*/9000,  /*minTemp*/10, /*maxTemp*/32, /*minEVT*/0.5f, /*maxEVT*/8f),
	PISTACHIO ("PISTACHIO", /*minRain*/125, /*maxRain*/1500,  /*minTemp*/10, /*maxTemp*/35, /*minEVT*/0.5f, /*maxEVT*/8f);
	
	public final float minRain;
	public final float maxRain;
	public final float minTemp;
	public final float maxTemp;
	public final float minEVT;
	public final float maxEVT;
	
	public static final EnumCWTFCFruitTree[] AMERICAS = new EnumCWTFCFruitTree[]{CASHEW};
	public static final EnumCWTFCFruitTree[] EUROPE = new EnumCWTFCFruitTree[]{HAZELNUT};
	public static final EnumCWTFCFruitTree[] ASIA = new EnumCWTFCFruitTree[]{ALMOND,COCONUT,MACADAMIA,PISTACHIO};
	public static final EnumCWTFCFruitTree[] AFRICA = new EnumCWTFCFruitTree[]{ALMOND,PISTACHIO};
	
	public static final EnumCWTFCFruitTree[][]REGIONS = new EnumCWTFCFruitTree[][]{AMERICAS,EUROPE,AFRICA,ASIA};
	
	
	private EnumCWTFCFruitTree(String s, float i, float j, float mintemp, float maxtemp, float minevt, float maxevt)
	{
		minRain = i;
		maxRain = j;
		minTemp = mintemp;
		maxTemp = maxtemp;
		minEVT = minevt;
		maxEVT = maxevt;
	}
}
