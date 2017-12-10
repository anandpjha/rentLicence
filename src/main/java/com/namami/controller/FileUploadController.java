package com.namami.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.namami.aspect.SecureAdminAssociate;
import com.namami.common.RestURIConstants;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.ValidationError;
import com.namami.exception.ValidationException;

/**
 * @author Anand Jha
 * 
 */
@RestController
public class FileUploadController {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(FileUploadController.class);
	
	@Value("${pdf-file-path}")
	private String pdfFilePath;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.UPLOAD, method = RequestMethod.POST, consumes = {"multipart/form-data", "multipart/mixed"})
	@SecureAdminAssociate
    public String handleFileUpload( @RequestPart("file") MultipartFile file, @RequestPart("agreementId") final String agreementId) throws Exception{
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(null == agreementId ){
			ValidationError error = new ValidationError("handleFileUpload()", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			throw new ValidationException(errors, "agreementId", "Please verify your inputs");
		}

		
			StringBuilder name = new StringBuilder();
            name.append(pdfFilePath);
            name.append(agreementId.replace("\"", ""));
            name.append(".pdf");
            SLF4JLOGGER.info("pdfFilePath: "+pdfFilePath);
            SLF4JLOGGER.info("File name: "+name);
            
        if (!file.isEmpty()) {
            	
            	byte[]	data = file.getBytes();
            	
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name.toString())));
                stream.write(data);
                stream.close();
                SLF4JLOGGER.info("You successfully uploaded file ");
                return "You successfully uploaded file for agreement id "+agreementId.replace("\"", "");
            
        } else {
        	SLF4JLOGGER.info("Error uploaded file ");
            return "fail to uploaded file for agreement id "+agreementId.replace("\"", "")+" because the file was empty";
            
        }
    }

}
