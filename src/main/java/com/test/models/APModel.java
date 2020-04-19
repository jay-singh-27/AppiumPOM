package com.test.models;

public class APModel {

	private String model;
	private String serial;
	private String usage;
	private String nofClients;
	
	public APModel(){
	
	}
	
	
 public APModel(String model, String serial, String usage, String nofClients){
		this.model= model;
		this.serial= serial;
		this.usage= usage;
		this.nofClients= nofClients;
		
		
	}
 
 public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getNofClients() {
		return nofClients;
	}

	public void setNofClients(String nofClients) {
		this.nofClients = nofClients;
	}

	
	@Override
	public String toString() {
		return "APModel [model=" + model + ", serial=" + serial + ", usage=" + usage + ", nofClients="
				+ nofClients + "]";
	}

 
 
 
	
	
	
	
}
