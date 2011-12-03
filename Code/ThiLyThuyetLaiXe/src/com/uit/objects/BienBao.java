package com.uit.objects;

public class BienBao {
	public int id;
	public String link_anh = "";
	public String tenbb = "";
	public String noidung = "";
	public String phanloai = "";

	public BienBao() {
	}

	public BienBao(int id, String link_anh, String tenbb, String noidung,
			String phanloai) {
		super();
		this.id = id;
		this.link_anh = link_anh;
		this.tenbb = tenbb;
		this.noidung = noidung;
		this.phanloai = phanloai;
	}
}