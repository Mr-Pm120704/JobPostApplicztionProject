package com.ZidioIntern.Service;

import java.io.IOException;
import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service

public class FileUploadService {
	
//	@Autowired
//	private Cloudinary cloudinary;
    private final Cloudinary cloudinary;

	
	public FileUploadService( 
		@Value("${cloudinary.cloud_name}") String cloudName,
		@Value("${cloudinary.api_key}") String apiKey,
		@Value("${cloudinary.api_secret}") String apiSecret) {
		this.cloudinary = new Cloudinary(ObjectUtils.asMap(
				"cloud_name", cloudName,
				"api_key", apiKey,
				"api_secret", apiSecret));
	}

	public String uploadFile(MultipartFile file,String folder) throws IOException{
		Map<? ,? > uploadResult = cloudinary.uploader().upload(file.getBytes(),ObjectUtils.asMap("folder",folder));
		return (String) uploadResult.get("secure_url");
	}
	
//	public String uploadFile(MultipartFile file,String folder) throws IOException {
//	    Map<, > uploadResult = cloudinary.uploader().upload(file.getInputStream(),ObjectUtils.asMap("folder", folder));
//	    return (String) uploadResult.get("secure_url");
//	}

}
