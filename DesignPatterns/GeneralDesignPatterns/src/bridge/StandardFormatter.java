package bridge;

public class StandardFormatter implements Formattable {

	@Override
	public String format(String key, String value) {
		
		return String.format("%s: %s", key, value);
	}

}
