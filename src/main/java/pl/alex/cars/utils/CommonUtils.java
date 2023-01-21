package pl.alex.cars.utils;

public class CommonUtils {

	public static Integer convertFieldToInteger(String input) {
		if (input == null || input.trim().equals("-")) {
			return null;
		} else {
			String[] parts = input.split(" ");
			if(parts[0].isBlank()) {
				return null;
			}
			return Integer.valueOf(parts[0]);
		}
	}

	public static Double convertFieldToDouble(String input) {
		if (input == null || input.trim().equals("-")) {
			return null;
		} else {
			String[] parts = input.split(" ");
			
			return Double.valueOf(parts[0].replaceAll(",", "."));
		}
	}
}
