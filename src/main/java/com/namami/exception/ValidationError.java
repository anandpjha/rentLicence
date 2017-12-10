/**
 * ValidationError class will be used to throw the exception.
 */
package com.namami.exception;

/**
 * @author Anand Jha
 *
 */
public class ValidationError {

    /** Name of the field for which exception occurred. */
    private String fieldName;
    /** Variable Error description code. */
    private String errorCode;


    public ValidationError() {
    	super();
    	
    }
    
    /**
     * Constructor dealing with exception code.
     * @param code - Error description code.
     */
    public ValidationError(final String code) {
    	super();
        errorCode = code;
    }

   /**
    * Constructor dealing with the field and code.
    * @param field - Name of the field for which exception occurred.
    * @param code - Error description code.
    */
    public ValidationError(final String field, final String code) {
    	super();
        fieldName = field;
        errorCode = code;
    }

    /**
     * @return the fieldName
     */
    public final String getFieldName() {
        return fieldName;
    }

    /**
     * @return the errorCode
     */
    public final String getErrorCode() {
        return errorCode;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidationError [");
		if (fieldName != null) {
			builder.append("fieldName=");
			builder.append(fieldName);
			builder.append(", ");
		}
		if (errorCode != null) {
			builder.append("errorCode=");
			builder.append(errorCode);
		}
		builder.append("]");
		return builder.toString();
	}
}