package smartspace.aop;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.assertj.core.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import smartspace.dao.AdvancedUserDao;
import smartspace.data.UserEntity;
import smartspace.data.UserRole;

@Component
@Aspect
public class RoleValidationAdvice {
	Log log = LogFactory.getLog(RoleValidationAdvice.class);
	
	
	private AdvancedUserDao<String> userDao;
	
	@Autowired
	public RoleValidationAdvice(AdvancedUserDao<String> userDao) {
		this.userDao = userDao;
	}
	
	@Around("@annotation(smartspace.aop.RoleValidation)&& args(smartspace,email,..)")
	public Object validateUserType(ProceedingJoinPoint pjp,String smartspace, String email) throws Throwable{
//		String methodName = pjp.getSignature().getName();
//		String className = pjp.getTarget().getClass().getName();
		
		
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		Method method = signature.getMethod();
		
		UserRole[] roles = method.getAnnotation(RoleValidation.class).permissions();
		try {
		roleValidate(smartspace, email, roles);
		
			Object rv = pjp.proceed();
			return rv;
		} catch (Throwable e) {
			throw e;
		}
	}
	
	private void roleValidate(String smartspace,String email,UserRole[] roles)
	{
		//System.err.println(roles);
		UserRole role = userDao.readById(smartspace + "!" + email)
				.orElseThrow(RuntimeException::new).getRole();
		if(!Arrays.asList(roles).contains(role))
		{
			
			throw new RuntimeException("Illegal action for role");
			
		}
	}

}