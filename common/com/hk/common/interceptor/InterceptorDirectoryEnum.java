package com.hk.common.interceptor;

public enum InterceptorDirectoryEnum {
	

	MASTER_USER("/master/user"),
	MASTER_MODULE("/master/module"),
	MASTER_ACCESSUSER("/master/accessuser"),
	MASTER_PROSPEK("/master/prospek"),
	MASTER_GUDANG_GRUP("/master/gudangGrup"),
	MASTER_SALES("/master/sales"),
	MASTER_GUDANG("/master/gudang"),
	MASTER_GEDUNG("/master/gedung"),
	MASTER_MATA_UANG("/master/mataUang"),
	MASTER_KAS_BANK("/master/kasBank"),
	MASTER_BARANG_MERK("/master/barangMerk"),
	MASTER_BARANG_GRUP("/master/barangGrup"),
	MASTER_BARANG_DIVISI("/master/barangDivisi"),
	MASTER_UNIT("/master/unit"),
	MASTER_AKUN("/master/akun"),
	MASTER_WARNA_KAIN("/master/warnaKain"),
	MASTER_WARNA_KAIN_LUAR("/master/warnaKainLuar"),
	MASTER_SUPPLIER_WARNA("/master/supplierWarna"),
	MASTER_WARNA_HARGA("/master/warnaHargaHdr"),
	MASTER_WARNA_HARGA_LUAR("/master/warnaHargaLuarHdr"),
	MASTER_KAIN_MOTIF("/master/kainMotif"),
	MASTER_JENIS_KAIN("/master/jenisKain"),
	MASTER_MESIN("/master/mesin"),
	MASTER_JASA("/master/jasa"),
	MASTER_JASA_GRUP("/master/jasaGrup"),
	MASTER_SUPPLIER("/master/supplier"),
	MASTER_CUSTOMER("/master/customer"),
	MASTER_ROLE("/master/role"),
	MASTER_RAK("/master/rak"),
	MASTER_JENIS_KAIN_TAMBAHAN("/master/jenisKainTambahan"),
	MASTER_BENANG("/master/benang"),
	MASTER_WARNA_KAIN_GRUP("/master/warnaKainGrup"),
	MASTER_BENANG_GRUP("/master/benangGrup"),
	MASTER_BENANG_TIPE("/master/benangTipe"),
	MASTER_REQUEST_COMPLAIN("/master/requestComplain"),
	MASTER_AGEN("/master/agen"),
	
	SETTING_OTORISASI("/setting/otorisasi"),
	SETTING_KODE_TRANSAKSI("/setting/kodeTransaksi"),
	
	INISIAL_PACKING("/setting/inisialPacking"),

	REQUEST_ACCESS_MENU("/setting/requestAccessMenu"),

	VERSION_CONTROL("/setting/versionControl"),
	
	BARANG_LAIN_AWAL_STOK("/barangLain/awalStok"),
	
	PIUTANG_AWAL_PIUTANG("/piutang/awalPiutang"),
	
	HUTANG_AWAL_HUTANG("/hutang/awalHutang"),
	
	DO_PROSES_GROUP("/master/doProsesGroup"),
	DO_PROSES("/proses/doProsesHdr"),
	BBM_PROSES_GREIGE("/proses/bbmProsesGreigeHdr"),
	FAKTUR_PROSES_GREIGE("/proses/fakturProsesGreigeHdr"),
	BBM_PROSES_WARNA("/proses/bbmProsesWarnaHdr"),
	FAKTUR_PROSES_WARNA("/proses/fakturProsesWarnaHdr"),
	BBM_PROSES_MOTIF("/proses/bbmProsesMotifHdr"),
	FAKTUR_PROSES_MOTIF("/proses/fakturProsesMotifHdr"),
	PINDAH_GUDANG("/proses/pindahGudangHdr"),
	PINDAH_KAIN_GRADE("/proses/pindahKainGradeHdr"),
	PECAH_KAIN("/proses/pecahKainHdr"),
	GABUNG_KAIN("/proses/gabungKainHdr"),
	GABUNG_KAIN_RFP("/proses/gabungKainRfpHdr"),
	
	
	INVENTORY_SALDO_AWAL_KAIN_GREIGE_HDR("/inventory/saldoAwalKainGreigeHdr"),
	
	INVENTORY_SALDO_AWAL_KAIN_WARNA_HDR("/inventory/saldoAwalKainWarnaHdr"),
	
	INVENTORY_SALDO_AWAL_KAIN_MOTIF_HDR("/inventory/saldoAwalKainMotifHdr"),
	
	STOCK_OPNAME_HDR("/inventory/stockOpname"),
	
	STOCK_OPNAME_CANCELED_HDR("/inventory/stockOpnameCanceled"),

	PINDAH_RAK("/inventory/pindahRakHdr"),

	
	STOCK_REAL_GREIGE("/stock/saldoAkhirStockRealGreige"),
	
	STOCK_REAL_WARNA("/stock/saldoAkhirStockRealWarna"),
	
	STOCK_REAL_MOTIF("/stock/saldoAkhirStockRealMotif"),
	
	STOCK_PEMUTIHAN("/stock/stockPemutihan"),

	KARTU_STOCK_REAL("/stock/kartuStockReal"),

	
	KAIN_GRADE("/master/kainGrade"),
	
	PROSES_BBM_MAKLOON_GREIGE_HDR("/proses/bbmMakloonGreigeHdr"),
	
	PROSES_BBM_MAKLOON_WARNA_HDR("/proses/bbmMakloonWarnaHdr"),
	
	PROSES_BBM_MAKLOON_MOTIF_HDR("/proses/bbmMakloonMotifHdr"),
	
	PROSES_DELIVERY_ORDER_HDR("/proses/deliveryOrderHdr"),
	
	PROSES_SURAT_JALAN_HDR("/proses/suratJalanHdr"),
	
	PROSES_SURAT_JALAN_PERBAIKAN_HDR("/proses/suratJalanPerbaikanHdr"),
	
	PROSES_FAKTUR_JUAL_HDR("/proses/fakturJualHdr"),
	
	PROSES_FAKTUR_JUAL_JASA_HDR("/proses/fakturJualJasaHdr"),
	
	PROSES_FAKTUR_JUAL_PERBAIKAN_HDR("/proses/fakturJualPerbaikanHdr"),
	
	PROSES_RETUR_JUAL_HDR("/proses/returJualHdr"),
	
	PROSES_NOTA_RETUR_HDR("/proses/notaReturHdr"),
	
	PEMBELIAN_ORDER_BELI_KAIN_HDR("/pembelian/orderBeliKainHdr"),
	
	PEMBELIAN_ORDER_BELI_KAIN_SINGLE_HDR("/pembelian/orderBeliKainSingleHdr"),
	
	PEMBELIAN_BBM_BELI_KAIN_GREIGE_HDR("/pembelian/bbmBeliKainGreigeHdr"),
	
	PEMBELIAN_BBM_BELI_KAIN_WARNA_HDR("/pembelian/bbmBeliKainWarnaHdr"),
	
	PEMBELIAN_BBM_BELI_KAIN_MOTIF_HDR("/pembelian/bbmBeliKainMotifHdr"),
	
	PENJUALAN_ORDER_JUAL_KAIN_HDR("/penjualan/orderJualKainHdr"),
	
	PENJUALAN_ORDER_JUAL_KAIN_JASA("/penjualan/orderJualKainJasa"),
	
	PENJUALAN_REALISASI_ORDER_JUAL_KAIN_HDR("/penjualan/realisasiOrderJualKainHdr"),

	PROSES_FAKTUR_BELI_GREIGE_HDR("/proses/fakturBeliGreigeHdr"),
	
	PROSES_FAKTUR_BELI_WARNA_HDR("/proses/fakturBeliWarnaHdr"),
	
	PROSES_FAKTUR_BELI_MOTIF_HDR("/proses/fakturBeliMotifHdr"),
	
	PROSES_DO_CELUP_HDR("/proses/doCelupHdr"),
	
	PROSES_JO_CELUP_HDR("/proses/joCelupHdr"),
	
	PROSES_JO_CELUP_KP_PROSES("/proses/joCelupKpProses"),
	
	PROSES_JO_PROSES_HDR("/proses/joProsesHdr"),
	
	PROSES_DO_PRINTING_HDR("/proses/doPrintingHdr"),
	
	PROSES_BBM_CELUP_HDR("/proses/bbmCelupHdr"),
	
	PROSES_RETUR_DO_CELUP_HDR("/proses/returDoCelupHdr"),
	
	PROSES_BBM_PRINTING_HDR("/proses/bbmPrintingHdr"),
	
	PROSES_FAKTUR_CELUP_HDR("/proses/fakturCelupHdr"),

	PROSES_FAKTUR_GABUNG_KAIN_RFP_HDR("/proses/fakturGabungKainRfpHdr"),

	PROSES_FAKTUR_PRINTING_HDR("/proses/fakturPrintingHdr"),
	
	PROSES_SERAH_TERIMA_PRODUKSI("/proses/serahTerimaProduksi"),
	
	PROSES_SERAH_TERIMA_PRODUKSI_PROSES_GREIGE("/proses/serahTerimaProduksiProsesGreige"),
	
	PROSES_SERAH_TERIMA_PRODUKSI_PROSES_WARNA("/proses/serahTerimaProduksiProsesWarna"),

	PROSES_SERAH_TERIMA_PRODUKSI_PROSES_MOTIF("/proses/serahTerimaProduksiProsesMotif"),
	
	PROSES_SERAH_TERIMA_PRODUKSI_GAGAL("/proses/serahTerimaProduksiGagal"),
	
	PROSES_SERAH_TERIMA_PRODUKSI_PROSES_GREIGE_GAGAL("/proses/serahTerimaProduksiProsesGreigeGagal"),
	
	PROSES_SERAH_TERIMA_PRODUKSI_PROSES_WARNA_GAGAL("/proses/serahTerimaProduksiProsesWarnaGagal"),

	PROSES_SERAH_TERIMA_PRODUKSI_PROSES_MOTIF_GAGAL("/proses/serahTerimaProduksiProsesMotifGagal"),

	PROSES_SERAH_TERIMA_PRODUKSI_KP("/proses/serahTerimaProduksiKp"),
	
	PROSES_HASIL_PACKING("/proses/hasilPacking"),
	
	PROSES_HASIL_PACKING_KP("/proses/hasilPackingKp"),
	
	PROSES_HASIL_PACKING_KP_JOIN("/proses/hasilPackingKpJoin"),
	
	PROSES_HASIL_REKAP_KP("/proses/rekapKp"),
	
	PROSES_HASIL_REKAP_KP_PROSES("/proses/rekapKpProses"),
	
	PEMARTAIAN_KP("/proses/pemartaianKp"),
	
	PEMARTAIAN_PROSES_KP("/proses/pemartaianProsesKp"),
	
	MUTASI_SAFETY_STOCK("/proses/mutasiSafetyStock"),
	
	SPK_HDR("/proses/spkHdr"),
	
	PACKING_LIST("/proses/packingList"),
	
	SURAT_JALAN_PACKING_LIST("/proses/suratJalanPackingList"),
	
	RETUR_JUAL_TANPA_SJ("/proses/returJualTanpaSuratJalanHdr"),
	
	DATA_KUALITAS_KAIN("/proses/dataKualitasKain"),
	
	HASIL_KERJA_PEMARTAIAN("/proses/hasilKerjaPemartaian"),
	
	CONFIRM_PROSES_JOB_ORDER("/proses/confirmProsesJobOrder"),
	
	KONTROL_PROGRES_PRODUKSI("/proses/kontrolProgresProduksi"),
	
	OUTSTANDING_HASIL_PACKING("/proses/outstandingHasilPacking"),
	
	
	
	
	RETUR_BELI_GREIGE("/pembelian/returBeliGreige"),
	
	RETUR_BELI_WARNA("/pembelian/returBeliWarna"),
	
	RETUR_BELI_MOTIF("/pembelian/returBeliMotif"),
	
	PEMBELIAN_NOTA_RETUR_BELI("/pembelian/notaReturBeli");
	
	private String val;

	InterceptorDirectoryEnum(String val) {
		this.val = val;
	}

	public String getVal() {
		return val;
	}
}
