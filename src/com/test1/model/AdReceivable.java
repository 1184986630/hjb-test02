package com.test1.model;

public class AdReceivable {

	private int ad_owner_id;
	private String name;
	private double receivable;
	private double invoice_money;
	private double account_moeny;
	private double gap_money;
	private double receivable_gap;
	private double affirm_money;
	private double no_affirm_money;
	private String finder;
	
	public int getAd_owner_id() {
		return ad_owner_id;
	}
	public void setAd_owner_id(int ad_owner_id) {
		this.ad_owner_id = ad_owner_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getReceivable() {
		return receivable;
	}
	public void setReceivable(double receivable) {
		this.receivable = receivable;
	}
	public double getInvoice_money() {
		return invoice_money;
	}
	public void setInvoice_money(double invoice_money) {
		this.invoice_money = invoice_money;
	}
	public double getAccount_moeny() {
		return account_moeny;
	}
	public void setAccount_moeny(double account_moeny) {
		this.account_moeny = account_moeny;
	}
	public double getGap_money() {
		return gap_money;
	}
	public void setGap_money(double gap_money) {
		this.gap_money = gap_money;
	}
	public double getReceivable_gap() {
		return receivable_gap;
	}
	public void setReceivable_gap(double receivable_gap) {
		this.receivable_gap = receivable_gap;
	}
	public String getFinder() {
		return finder;
	}
	public void setFinder(String finder) {
		this.finder = finder;
	}
	public double getAffirm_money() {
		return affirm_money;
	}
	public void setAffirm_money(double affirm_money) {
		this.affirm_money = affirm_money;
	}
	public double getNo_affirm_money() {
		return no_affirm_money;
	}
	public void setNo_affirm_money(double no_affirm_money) {
		this.no_affirm_money = no_affirm_money;
	}
}