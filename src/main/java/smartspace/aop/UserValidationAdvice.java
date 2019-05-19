package smartspace.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserValidationAdvice {
	Log log = LogFactory.getLog(UserValidationAdvice.class);
	
	@Before("@annotation(smartspace.aop.UserValidation)")
	public void validateUserType(JoinPoint jp) {
		
	}

}