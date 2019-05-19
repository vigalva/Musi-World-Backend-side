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
public class AdminValidationAdvice {
	Log log = LogFactory.getLog(AdminValidationAdvice.class);
	
	@Before("@annotation(smartspace.aop.AdminValidation)")
	public void sampleTime(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		String className = jp.getTarget().getClass().getName();
		
	}
	
	
	@Around("@annotation(smartspace.aop.AdminValidation) && args(key,..)")
	public Object sampleTime(ProceedingJoinPoint pjp, String key) throws Throwable{
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().getName();
		
		
		try {
			Object rv = pjp.proceed();
			return rv;
		} catch (Throwable e) {
			throw e;
		}finally {
			
		}
	}

}