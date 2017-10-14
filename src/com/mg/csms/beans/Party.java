package com.mg.csms.beans;

public class Party {

	private String partyName;
	private PartyContact contact;
	private String partyId;
	private String gstNo;

	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public PartyContact getContact() {
		return contact;
	}

	public void setContact(PartyContact contact) {
		this.contact = contact;
	}

	public String getPartyId() {
		return partyId;
	}

	public void setPartyId(String partyId) {
		this.partyId = partyId;
	}

	public String getGstNo() {
		return gstNo;
	}

	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

}
