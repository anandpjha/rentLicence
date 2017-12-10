/**
 * ValidationError class will be used to throw the exception.
 */
package com.namami.exception;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Anand Jha
 *
 */
@JsonInclude(Include.NON_NULL)
public class ValidationResponse {

    private String status;
	/** invokeURL variable used to display URL for UI. */
    private String invokeUrl;
	/** Name of the field for which exception occurred. */
    private List<ValidationError> validationErrors;
    
    private String customData;
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
     * @return the invokeURL
     */
    public final String getInvokeUrl() {
        return invokeUrl;
    }
    /**
     * @param invokeURL the invokeUrl to set
     */
    public final void setInvokeUrl(final String invokeURL) {
        invokeUrl = invokeURL;
    }

	
    /**
     * @return the validationErrorResp
     */
    public final List<ValidationError> getValidationErrors() {
        return validationErrors;
    }

    /**
     * @param validationErrorsList the validationErrorsList to set
     */
    public final void setValidationErrors(final List<ValidationError> validationErrorsList) {
        this.validationErrors = validationErrorsList;
    }

	/**
	 * @return the customData
	 */
	public String getCustomData() {
		return customData;
	}

	/**
	 * @param customData the customData to set
	 */
	public void setCustomData(String customData) {
		this.customData = customData;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		builder.append( "ValidationResponse [" );
		if ( status != null ) {
			builder.append( "status=" );
			builder.append( status );
			builder.append( ", " );
		}
		if ( invokeUrl != null ) {
			builder.append( "invokeUrl=" );
			builder.append( invokeUrl );
			builder.append( ", " );
		}
		if ( validationErrors != null ) {
			builder.append( "validationErrors=" );
			builder.append( validationErrors );
			builder.append( ", " );
		}
		if ( customData != null ) {
			builder.append( "customData=" );
			builder.append( customData );
		}
		builder.append( "]" );
		return builder.toString();
	}

	    	
}
