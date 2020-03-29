package com.covid19tracker.Model;

public class LocationData {

	private String state;
	private String country;
	private int latestStats;
	private int diffFromPreviousDay;
	
	public int getDiffFromPreviousDay() {
		return diffFromPreviousDay;
	}
	public void setDiffFromPreviousDay(int diffFromPreviousDay) {
		this.diffFromPreviousDay = diffFromPreviousDay;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getLatestStats() {
		return latestStats;
	}
	public void setLatestStats(int latestStats) {
		this.latestStats = latestStats;
	}
	@Override
	public String toString() {
		return "LocationData [state=" + state + ", country=" + country + ", latestStats=" + latestStats + "]";
	}
	
	
}
