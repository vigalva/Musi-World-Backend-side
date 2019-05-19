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
public class ManegerValidationAdvice {
	Log log = LogFactory.getLog(ManegerValidationAdvice.class);
	
	@Before("@annotation(smartspace.aop.ManegerValidation)")
	public void sampleTime(JoinPoint jp) {

	}
	

}