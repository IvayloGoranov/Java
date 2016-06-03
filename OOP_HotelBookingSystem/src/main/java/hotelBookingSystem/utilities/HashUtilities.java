package hotelBookingSystem.utilities;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtilities {

	public static String getSha256Hash(String text) {
        
		String sha256hex = DigestUtils.sha256Hex(text); 
       
        return sha256hex;
    }
}
