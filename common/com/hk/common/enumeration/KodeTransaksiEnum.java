package com.hk.common.enumeration;

public enum KodeTransaksiEnum {
	
	/*SUMBER GREIGE*/
	SawGry("SAW-GRY"),
	BbmBlg("BBM-BLG"),
	BbmMlg("BBM_MLG"),
	BbmPrg("BBM_PRG"),
	PngGry("PNG_GRY"),
	PckGry("PCK_GRY"),
	GbkGry("GBK_GRY"),
	BbmRtG("BBM_RTG"),
	RtrClp("RTR_CLP"),
	
	/*SUMBER WARNA*/
	SawWrn("SAW-WRN"),
	BbmBlw("BBM-BLW"),
	BbmMlw("BBM_MLW"),
	BbmPrw("BBM_PRW"),
	PngWrn("PNG_WRN"),
	PckWrn("PCK_WRN"),
	GbkWrn("GBK_WRN"),
	BbmRtW("BBM_RTW"),
	BbmClp("BBM_CLP"),
	
	/*SUMBER MOTIF*/
	SawMtf("SAW-MTF"),
	BbmBlm("BBM-BLM"),
	BbmMlm("BBM_MLM"),
	BbmPrm("BBM_PRM"),
	PngMtf("PNG_MTF"),
	PckMtf("PCK_MTF"),
	GbkMtf("GBK_MTF"),
	BbmRtM("BBM_RTM"),
	BbmPrt("BBM_PRT"),
	
	/*SUMBER CAMPUR*/
	StoOpn("STO-OPN"),
	
	
	/*KELUAR*/
	DoClp("DO_CLP"),
	DoPrs("DO_PRS"),
	DoPrt("DO_PRT"),
	SrtJln("SRT_JLN"),
	SrtJlnPbk("SRT_JLN_PBK"),
	RtrBlg("RTR_BLG"),
	RtrBlw("RTR_BLW"),
	RtrBlm("RTR_BLM"),
	RtrMlg("RTR_MLG"),
	RtrMlw("RTR_MLW"),
	RtrMlm("RTR_MLM"),
	PthSto("PTH-STO"),
	
	BCLNok("BCL_NOK"),
	BrgNok("BRG_NOK"),
	
	PoPerSo("PO_PER_SO"),
	PoTanpaSo("PO_TANPA_SO");
	
	private String val;

	KodeTransaksiEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
