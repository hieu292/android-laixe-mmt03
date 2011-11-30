package com.uit.objects;

public class BienBao {
	public String id;
	public String link_anh = null;
	public String noi_dung = null;
	public String phan_loai = null;
	public String ten_bien_bao = null;

	public BienBao() {
	}

	public BienBao(String id, String link_anh, String noi_dung,
			String phan_loai, String ten_bien_bao) {
		super();
		this.id = id;
		this.link_anh = link_anh;
		this.noi_dung = noi_dung;
		this.phan_loai = phan_loai;
		this.ten_bien_bao = ten_bien_bao;
	}
}