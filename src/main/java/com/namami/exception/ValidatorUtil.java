
package com.namami.exception;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 /** Utility class : methods will be used throughout the project to decrease code redundancy
 * 
 * @author Anand Jha
 * @version 1.0
 *
 * 
 */
public final class ValidatorUtil {

	/** SLFLOGGER. */
	private static final Logger SLFLOGGER = LoggerFactory.getLogger( ValidatorUtil.class );

	/**
	 * Accepts only alphabetic characters a to z/ A to Z , hyphens and apostrophes, but not spaces, with a minimal 2 character input.
	 */
	private static final Pattern ALPHA_REGEX = Pattern.compile( "^[a-zA-Z-']{2,40}$" );

	/** To check ALPHANUMERIC in expression. */
	private static final Pattern ALPHANUMERIC_REGEX = Pattern.compile( "^[a-zA-Z0-9]*$" );

	/** To check ALPHANUMERIC and some special in expression. */
	private static final Pattern ALPHANUMERICSPECIAL_REGEX = Pattern.compile( "^[a-zA-Z-'0-9]*$" );

	/** To check EMAIL_PATTERN in expression. */
	private static final Pattern EMAIL_PATTERN = Pattern.compile( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" );

	/** To allow only numbers in DOB. */
	private static final Pattern NUMERIC_REGEXP_DOB = Pattern.compile( "^([0-9]{8})$" );

	/** To check integer in expression. */
	private static final Pattern NUMERIC_REGEXP = Pattern.compile( "[0-9]*" );

	/** To allow only numbers in MOBILE_REGEXP with prefix 07. */
	private static final Pattern MOBILE_REGEXP = Pattern.compile( "^(07[\\d]{9})$" );

	private static final Pattern TIME24HOURS_PATTERN = Pattern.compile( "([01]?[0-9]|2[0-3]):[0-5][0-9]" );

	/** To give the regular expression for TIME Stamp. */
	private static final Pattern HeaderTimeStamp_PATTERN = Pattern
			.compile( "(([0-2]\\d)|(3[0-1]))/((0\\d)|(1[0-2]))/((1\\d{3})|(20\\d{2})) (([0-1]\\d)|(2[0-3])):(([0-5]\\d)|([0-9])):(([0-5]\\d)|([0-9])):(([0-5]\\d)|([0-9]))" );

	/** To avoid special character in SPECIALCHAR_REGEXP. */
	/*
	 * private static final String SPECIALCHAR_REGEXP = "[!\"#$%&'()*+-./:;<=>?@\\^_{|}~`,.\\[\\]]*";
	 */
	/** To give the regular expression for DOB. */
	private static final Pattern DOB_PATTERN = Pattern.compile( "^(([0-2]\\d)|(3[0-1]))((0\\d)|(1[0-2]))([0-9]{4})$" );

	/** To allow only numbers in last 4 digits of Mobile No. */
	private static final Pattern NUMERIC_REGEXP_LAST_FOUR_DIGIT = Pattern.compile( "^([0-9]{4})$" );

	private static final Pattern DECIMAL_WITH_TWO_VALUE = Pattern.compile( "^\\d+\\.\\d{1,2}$" );

	/** Allow Alpha character, allow spaces, hyphen, and apostrophes, but not consecutive (2 or more) space/hyphens/apostrophes. */
	private static final Pattern NAME_VALIDATION_CHECK = Pattern.compile( "[a-zA-z]+([ '-][a-zA-Z]+)*" );

	/** Reg Ex to allow min-2 & max-15 Alphanumeric char with hyphen and space **/
	private static final Pattern DEVICE_NICK_NAME_VAL = Pattern.compile( "^([a-zA-Z- 0-9]{2,15})$" );

	/** Reg Ex to allow min-2 & max-15 Alphanumeric char with hyphen and space **/
	private static final Pattern SVA_NICK_NAME_VAL = Pattern.compile( "^([a-zA-Z- 0-9]{2,15})$" );

	/** Numeric constants for 0. */
	private static final int ZERO = 0;

	/** Numeric constants for 1. */
	private static final int ONE = 1;

	/** Numeric constants for 2. */
	private static final int TWO = 2;

	/** Numeric constants for 3. */
	private static final int THREE = 3;

	/** Numeric constants for 4. */
	private static final int FOUR = 4;

	/** Numeric constants for 6. */
	private static final int SIX = 6;

	/** Numeric constants for number of month 12. */
	private static final int NO_OF_MONTH = 12;

	/** Numeric constants for 35 */
	private static final int THIRTY_FIRST = 35;

	/** verification code length (otp) */
	private static final int VERIFICATION_CODE_LENGHT = 9;

	/** PostCode regular expression */
	private static final Pattern POSTCODE_REGEX = Pattern
			.compile( "^(([gG][iI][rR] {0,}0[aA]{2})|((([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y]?[0-9][0-9]?)|(([a-pr-uwyzA-PR-UWYZ][0-9][a-hjkstuwA-HJKSTUW])|([a-pr-uwyzA-PR-UWYZ][a-hk-yA-HK-Y][0-9][abehmnprv-yABEHMNPRV-Y]))) {0,}[0-9][abd-hjlnp-uw-zABD-HJLNP-UW-Z]{2}))$" );

	/** private Constructor. */
	private ValidatorUtil() {

	}

	/**
	 * To check string contains null or empty.
	 * 
	 * @param values
	 *            - input variable values
	 * @return boolean
	 */
	public static boolean isEmpty( final String values ) {

		SLFLOGGER.debug( "isEmpty Check" );
		String whiteSpace = "\\s+";
		String noValue = "";

		if ( values != null ) {
			String tempValues = values.replaceAll( whiteSpace, noValue );
			return tempValues.isEmpty();
		}
		return true;
	}

	
	
	/**
	 * To check the String is of Alphanumeric characters only.
	 * 
	 * @param value
	 *            - input String value
	 * @return true or false values.
	 */
	public static boolean isAlphaNumeric( final String value ) {

		if ( ALPHANUMERIC_REGEX.matcher( value ).matches() ) {
			return true;
		}
		return false;
	}

	/**
	 * To check the String is of Alphabetic characters only.
	 * 
	 * @param value
	 *            input String value.
	 * @return true or false values.
	 */
	public static boolean isAlpha( final String value ) {

		if ( ALPHA_REGEX.matcher( value ).matches() ) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param cardNickName
	 * @return
	 */
	public static boolean isAlphaNumericSpecial( String value ) {

		if ( ALPHANUMERICSPECIAL_REGEX.matcher( value ).matches() ) {
			return true;
		}
		return false;
	}

	/**
	 * To check the String is of Numeric characters only.
	 * 
	 * @param value
	 *            input String
	 * @return true or false values.
	 */
	public static boolean isNumeric( final String value ) {

		if ( NUMERIC_REGEXP.matcher( value ).matches() ) {
			return true;
		}
		return false;
	}

	/**
	 * To check the String of 8 characters is of Numeric characters only.
	 * 
	 * @param value
	 *            input String
	 * @return true or false values.
	 */
	public static boolean isDobNumeric( final String value ) {

		if ( NUMERIC_REGEXP_DOB.matcher( value ).matches() ) {
			return true;
		}
		return false;
	}

	/**
	 * Validation to check if the email address is in correct format.
	 * 
	 * @param emailAddress
	 *            - input variable emailAddress
	 * @return boolean true or false
	 */
	public static boolean isValidEmailAddress( final String emailAddress ) {

		if ( EMAIL_PATTERN.matcher( emailAddress ).matches() ) {
			return true;
		}
		return false;
	}

	/**
	 * Validation to check if the valid UK mobile number.
	 * 
	 * @param mobileNumber
	 *            - input variables mobileNumber.
	 * @return boolean true or false
	 */
	public static boolean isValidUKMobile( final String mobileNumber ) {

		if ( MOBILE_REGEXP.matcher( mobileNumber ).matches() ) {
			return true;
		}
		return false;
	}

	
	

	
	/**
	 * To check whether a map is null or empty.
	 * 
	 * @param myMap
	 *            the myMap
	 * @return boolean
	 */
	public static boolean isMapNullOrEmpty( final Map < ?, ? > myMap ) {

		return myMap == null || myMap.isEmpty();
	}

	/**
	 * To check whether a collection is null or empty.
	 * 
	 * @param myCollection
	 *            the myCollection
	 * @return boolean
	 */
	public static boolean isCollectionNullOrEmpty( final Collection < ? > myCollection ) {

		return myCollection == null || myCollection.isEmpty();
	}

	/**
	 * To check an integer format.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isInteger( String input ) {

		try {
			Integer.parseInt( input );
			return true;
		} catch ( Exception e ) {
			return false;
		}
	}

	/**
	 * validateDate.
	 * 
	 * @param Date
	 *            input variable inputDate
	 * @return boolean *
	 */
	public static boolean validateDate( final String inputDate ) {

		String noVal = "";
		String format = "ddMMyyyy";
		boolean result = false;
		try {
			if ( inputDate != null && ! inputDate.equals( noVal ) && isDobNumeric( inputDate ) ) {
				String day = inputDate.substring( SIX, inputDate.length() );
				String month = inputDate.substring( FOUR, SIX );
				String year = inputDate.substring( ZERO, FOUR );
				int intDay = Integer.parseInt( day );
				int intMonth = Integer.parseInt( month );
				// int intYear = Integer.parseInt(year);
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat( format );
				Date date = simpleDateFormat.parse( inputDate );
				if ( date != null ) {
					if ( day.length() == TWO && month.length() == TWO && year.length() == FOUR ) {

						if ( intDay <= THIRTY_FIRST && intMonth <= NO_OF_MONTH ) {
							result = true;
						}
					} else {
						result = false;
					}
				} else {
					result = false;
				}
			} else {
				result = false;
			}
		} catch ( ParseException e ) {
			result = false;
			SLFLOGGER.error( " Exception occured during validating Date" );
		}
		return result;
	}

	
	/**
	 * Validation to check if the dob is in correct format.
	 * 
	 * @param dob
	 *            - input variable dob
	 * @return boolean true or false
	 */
	public static boolean isValidDobFormat( final String dob ) {

		if ( DOB_PATTERN.matcher( dob ).matches() ) {
			return true;
		}
		return false;
	}

	

	
}
