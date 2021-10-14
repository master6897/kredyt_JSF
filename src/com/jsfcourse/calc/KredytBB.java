package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredytBB {
	private Double kwota;
	private Double lat;
	private Double oprocentowanie;
	private Double rata;
	@Inject
	FacesContext ctx;
	
	public Double getKwota() {
		return kwota;
	}

	public void setKwota(Double kwota) {
		this.kwota = kwota;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(Double oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public void setRata(Double rata) {
		this.rata = rata;
	}
	
	public Double getRata() {
		return rata;
	}

	public boolean doTheMath() {
		try {
			rata = (kwota + (kwota*(oprocentowanie/100)))/(lat*12);

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Operacja wykonana poprawnie", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd podczas przetwarzania parametrów", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showrata";
		}
		return null;
	}

	// Put result in messages on AJAX call
	public String calc_AJAX() {
		if (doTheMath()) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wynik: " + rata, null));
		}
		return null;
	}

	public String info() {
		return "info";
	}
}
