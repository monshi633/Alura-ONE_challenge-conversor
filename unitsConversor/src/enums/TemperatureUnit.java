package enums;

public enum TemperatureUnit {
	CELCIUS("Celcius"),
	FARENHEIT("Farenheit"),
	KELVIN("Kelvin");
	
	private String unitName;
	
	TemperatureUnit(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
}
