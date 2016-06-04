package issueTrackingSystem.utilities;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtilities {

	public static String hashPassword(String password) {
        
		String sha256hex = DigestUtils.sha256Hex(password); 
	       
        return sha256hex;
    }
}
