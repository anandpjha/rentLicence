package com.namami.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

import org.springframework.stereotype.Component;
/**
 * @author Anand Jha
 * 
 * Common Utils class 
 */
@Component("commonUtils")
public class CommonUtils {
	
	public String getSecurePassword(String passwordToHash, String salt)
    {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
     
    //Add salt
    public String getSalt() throws NoSuchAlgorithmException, NoSuchProviderException
    {
        //Always use a SecureRandom generator
        //SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        //byte[] salt = new byte[16];
        //Get a random salt
       // sr.nextBytes(salt);
        //return salt
        return "ggjhgjg6757657ghfhg";//salt.toString();
    }
    
    /**
	 * A method to check if given string is null or spaces or "null"  string
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) 
	{
		boolean flag = true;
		
		if (str != null && ! "".equals(str.trim()) && ! "null".equalsIgnoreCase(str.trim())) 
		{
			flag = false;
		}

		return flag;
	}

}
