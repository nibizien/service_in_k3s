package fr.biz;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.*;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}

@RestController
class AppRestController {
	@RequestMapping("/getinfos/{name}")
	public String getInfos(@PathVariable(value = "name") String name) {

		
			String response = "Get infos .......";
		
	
			return response;


	}
	
	
		@RequestMapping("/getpod")
	public String getPod() {
		
		
		      try{
			InetAddress my_address = InetAddress.getLocalHost();
			String response = "";
			String myPod = "--- Hello I am POD : " + my_address.getHostName();
			String myIp = "--- My POD IP is : " + my_address.getHostAddress();
			String myDate = "--- My POD replyed at : " + new Date();
			//String response = "--- Hello I am POD : " + my_address.getHostName() + " with IP : " + my_address.getHostAddress() + " at : "+ new Date();
			System.out.println(myPod + "\n" + myIp + "\n" + myDate);
			return response;
		      }
		      catch (UnknownHostException e){
			 System.out.println( "Couldn't find the local address.");
		      }
      
      		return null;

	}
}
