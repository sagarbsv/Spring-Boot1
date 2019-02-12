package com.otm.tools.controller;

import java.util.List;

import org.apache.maven.shared.invoker.InvokerLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.otm.tools.model.MavenPomProps;
import com.otm.tools.service.MavenCommandInvokerService;

@RestController
public class MavenInvokerController {
	
	@Autowired
	MavenCommandInvokerService mISrvc;
	
	 @RequestMapping(value = "/mavenbuilds", method = RequestMethod.POST)
	    public ResponseEntity<Object> CreateModels(@RequestBody List<MavenPomProps> mavenPomProps) throws Exception {
	    		//return this.hospitalService.saveToRepo(hospital);
		 List<List<String>> results=mISrvc.executeMavenInstall(mavenPomProps);
	    	 return new ResponseEntity<Object>(results,HttpStatus.OK);

	        }

}
