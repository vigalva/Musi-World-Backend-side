package smartspace.aop;

import javax.management.RuntimeErrorException;

import org.apache.catalina.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

import smartspace.data.UserEntity;
import smartspace.data.UserRole;

@Component
@Aspect
public class AdminValidationAdvice {
	Log log = LogFactory.getLog(AdminValidationAdvice.class);
	
	/*@Before("@annotation(smartspace.aop.AdminValidation)")
	public void sampleTime(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		UserEntity user =  (UserEntity) jp.getThis();
		if((user == null)||(user.getRole() != UserRole.ADMIN)) {
			throw new RuntimeException(methodName +"can not be executed by user type " + user.getRole().name()) ;
		}
	}*/
	
	@Around("@annotation(smartspace.aop.AdminValidation)")
	public Object validateUserType(ProceedingJoinPoint pjp) throws Throwable{
		String methodName = pjp.getSignature().getName();
		UserEntity user =  (UserEntity) pjp.getThis();
		
		try {
			if((user == null)||(user.getRole() != UserRole.ADMIN)) {
				throw new RuntimeException(methodName +"can not be executed by user type " + user.getRole().name()) ;
			}
			Object[] args = pjp.getArgs();
			for(Object arg :args) {
				if((arg instanceof String)&&(((String)arg).equals(""))) {
					throw new RuntimeException(methodName +"can not be executed with an empty String as an argument") ;
				}
			}
			Object rv = pjp.proceed();
			return rv;
		} catch (Throwable e) {
			throw e;
		}finally {
			
		}
	}

}