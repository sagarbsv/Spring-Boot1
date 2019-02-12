package com.otm.tools.service;

import java.io.File;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.apache.maven.shared.invoker.PrintStreamHandler;
import org.springframework.stereotype.Service;

import com.otm.tools.controller.CollectingLogOutputStream;
import com.otm.tools.model.MavenPomProps;

@Service
public class MavenCommandInvokerService {
	public List<List<String>> executeMavenInstall(List<MavenPomProps> mpp) {
		// mpp.parallelStream().forEach(m->{processData(m);});
		List<List<String>> results = mpp.parallelStream().map(m -> processData(m)).collect(Collectors.toList());
		return results;

	}
	private List<String>  processData(MavenPomProps mpp) {
		List<String> output = null;
		String commandMaven = mpp.getMavenCommand();
		String path = mpp.getModelProjectPath();
		Invoker invoker = new DefaultInvoker();
		InvocationRequest request = new DefaultInvocationRequest();	
		final CollectingLogOutputStream logOutput = new CollectingLogOutputStream(false);
		invoker.setOutputHandler(new PrintStreamHandler(new PrintStream(logOutput), true));
		request.setPomFile(new File(path));
		request.setGoals(Collections.singletonList(commandMaven));	
		try {
			invoker.execute(request);
			output = logOutput.getLines();
		
		} catch (MavenInvocationException e) {
			e.printStackTrace();
		}
	
		return output;
	}
	
	
	 
}
