package com.example.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import cogent.demo.models.Product;



@Aspect  
@Component  
@EnableAspectJAutoProxy
public class EmployeeServiceAspect   
{  
	private Logger logger = Logger.getLogger("EmployeeServiceAspect");

	//	@After(value = "com.example.demo.aspect.CommonJoinPointConfig.businessLayerExecution()")

	@Before(value = "execution(* cogent.demo.controllers.ProductC.findProfuct())")  
	//execution(* com.example.demo.services.*.*(..))"
	public void beforeAdvice() {  
//		System.out.println("Before method:" + joinPoint.getSignature());  
		System.out.println(" Before Creating a customer ");  
		logger.info(" Before Creating a customer " );

	}  
	
	@After(value = "execution(* cogent.demo.controllers.ProductC.*(..)) and args(cust)")  
	public void afterAdvice(JoinPoint joinPoint, Product cust) {  
	System.out.println("After method:" + joinPoint.getSignature());  
	System.out.println("After Creating Employee  " + cust );
	logger.info(" After Creating a customer " + cust);
	} 
	
	@After(value = "cogent.demo.security.services.ProductService.findbyId() and args(cust)")  
	public void afterAdvice1(JoinPoint joinPoint, Product cust) {  
	System.out.println("After method:" + joinPoint.getSignature());  
	System.out.println("After Creating Employee  " + cust );
	logger.info(" After Creating a customer " + cust);
	} 
	
	
}  
