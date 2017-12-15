package com.namami.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.namami.aspect.SecureCustomer;
import com.namami.bo.AgreementDto;
import com.namami.bo.CreateAgreementReq;
import com.namami.bo.CreateTrackerRequest;
import com.namami.bo.MiscellaneousTermsReq;
import com.namami.common.RestURIConstants;
import com.namami.entity.Agreement;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.ValidationError;
import com.namami.exception.ValidationException;
import com.namami.service.AgreementService;
import com.namami.service.PersonService;
import com.namami.service.UserService;

/**
 * @author Anand Jha
 * 
 */
@RestController
public class AgreementController {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	AgreementService agreementService; 

	@Autowired
	PersonService personService; 

	@Autowired
	UserService userService; 
	
	@Value("${pdf-file-path}")
	private String pdfFilePath;

	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.CREATE_BLANK_AGREEMENT, method = RequestMethod.GET)
	@SecureCustomer
	public Integer createBlankAgreement() {
		
		Agreement agreement = agreementService.createBlankAgreement();
		
		return agreement.getAgreementId();
	}


	@CrossOrigin(origins = "*")
	//@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.CREATE_AGREEMENT, method = RequestMethod.POST)
	@SecureCustomer
	public Integer createAgreement(@RequestBody final CreateAgreementReq createAgreementReq) {
		
		return agreementService.createAgreement(createAgreementReq);
		
	}
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.UPDTATE_MISCELLANEOUS, method = RequestMethod.POST)
	@SecureCustomer
	public void updateMiscellaneous(@RequestBody final MiscellaneousTermsReq miscellaneousTermsReq) {
		
		agreementService.updateMiscellaneous(miscellaneousTermsReq);
		
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.GET_USER_AGREEMENTS, method = RequestMethod.GET)
	@SecureCustomer
	public List<AgreementDto> retriveUserAgreements() {
		
		
		return  agreementService.retriveUserAgreements();
		
	}
	
	
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.GET_AGREEMENT_DETAILS, method = RequestMethod.GET)
	@SecureCustomer
	public AgreementDto retriveAgreementDetails(@PathVariable final String agreementId) {
		
		return agreementService.retriveAgreementDetails(agreementId);
		
	}
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.CONFIRM_AGR_DATA, method = RequestMethod.POST)
	@SecureCustomer
	public void confirmAgreementData(@RequestBody final CreateTrackerRequest createTrackerRequest) {
		
		agreementService.confirmAgreementData(createTrackerRequest);
		
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value= RestURIConstants.GET_AGREEMENT_DETAIL_PDF, method=RequestMethod.GET/*,  produces = "application/pdf"*/)
	@SecureCustomer
	public /*ResponseEntity<byte[]>*/ void getPDF(HttpServletResponse response, @PathVariable final String agreementId) throws IOException{
		
		List<ValidationError> errors = new ArrayList<ValidationError>();
		if(null == agreementId ){
			ValidationError error = new ValidationError("getPDF()", GlobalErrorConstants.MISSING_MANDATORY_FIELD.getValue());
			errors.add(error);
			throw new ValidationException(errors, "agreementId", "Please verify your inputs");
		}

		/*String text = "Agreement Id: "+agreementId+"Any String you want";
		
	    byte[] byteStr =  text.getBytes();
	   
	    byte[] encoded = Base64.encode(byteStr);
	    byte[] contents = Base64.decode(encoded);
	    
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.parseMediaType("application/pdf"));
	    headers.set("charset", "utf-8");
	    String filename = "output.pdf";
	    headers.setContentDispositionFormData("attachment", filename);
	    headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
	    ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
	    
	    return response;*/
		
		File file = null;
        
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        //file = new File(classloader.getResource(agreementId+".pdf").getFile());
        file = new File(pdfFilePath+agreementId+".pdf");
         
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            SLF4JLOGGER.info(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
         
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            SLF4JLOGGER.info("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
         
        SLF4JLOGGER.info("mimetype : "+mimeType);
         
        response.setContentType(mimeType);
         
        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
 
         
        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
         
        response.setContentLength((int)file.length());
 
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
 
        //Copy bytes from source to destination(outputstream in this example), closes both streams.
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
	
	
}
