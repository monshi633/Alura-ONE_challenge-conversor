package enums;

public enum TemperatureUnit {
	CELCIUS("C"),
	FARENHEIT("F"),
	KELVIN("K");
	
	private String unitName;
	
	TemperatureUnit(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitName() {
		return unitName;
	}
}
