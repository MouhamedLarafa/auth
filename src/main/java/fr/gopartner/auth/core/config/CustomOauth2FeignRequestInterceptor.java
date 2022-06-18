package fr.gopartner.auth.core.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class CustomOauth2FeignRequestInterceptor implements RequestInterceptor {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    @Override
    // hedha il intercepte les requette feign (men aand les micro services lokhrin) : injecte l token fl requettes li kharjin
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletRequest request = requestAttributes.getRequest();
        if (request == null) {
            return;
        }
        String authorization = request.getHeader(AUTHORIZATION_HEADER);
        if (authorization == null) {
            return;
        }
        requestTemplate.header(AUTHORIZATION_HEADER, authorization);
    }
}
