package nirmalya.aathithya.webmodule.common.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors (InterceptorRegistry registry) {
		registry.addInterceptor(new RequestInterceptor())
			.excludePathPatterns("/index-assets/**","/index-get-activity-list",
					"/index-get-function-list-resp","/register-getStateList",
			        "/assets/**","/extend/**", "/css/**", "/datatables/**", 
					"/FileUpload/**","/download/**", "/js/**", "/login",
					"/logout", "/register","/" ,"/document/**","/access-denied",
					"/order-status",					
					"/error",
					"/get-profile-swap","/login-swap",
					"/login-with-otp","/login-send-otp"
				
					,"/login-clear-otp-column","/welcomex",
					"/thanku","/user/create-order-web","/user/save-payment-status-web","/user/create-order-webview"
					,"/user/view-order-patient","/admin/view-patient-documentation-report","/admin/view-patient-document-details"

);;
	}
	
}
