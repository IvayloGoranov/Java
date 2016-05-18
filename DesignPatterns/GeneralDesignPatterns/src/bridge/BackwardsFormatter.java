package bridge;

public class BackwardsFormatter implements Formattable {

	@Override
	public String format(String key, String value) {
		
		String reversedValue = new StringBuilder(new String(value)).reverse().toString();
		
		return String.format("%s: %s", key, reversedValue);
	}

}
