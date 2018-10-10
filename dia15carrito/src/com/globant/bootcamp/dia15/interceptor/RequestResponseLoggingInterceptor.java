package com.globant.bootcamp.dia15.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import java.util.GregorianCalendar;

@Component
public class RequestResponseLoggingInterceptor implements HandlerInterceptor {

    private Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
            throws Exception {
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        log.info("\n Completed "+
                "\n\t Time taken: "+ (endTime-startTime)+" ms"+
                "\n\t Time ended: "+ new GregorianCalendar());

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
            throws Exception {
        ContentCachingResponseWrapper responseCacheWrapperObject = new ContentCachingResponseWrapper(response);

        log.info("\nResponse: "+
                "\n\tStatus: "+responseCacheWrapperObject.getStatus()+
                "\n\tContent-Type: "+responseCacheWrapperObject.getContentType()/*+
                "\n\tBody: "+IOUtils.toString(responseCacheWrapperObject.getContentInputStream(), responseCacheWrapperObject.getCharacterEncoding())*/);

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
        ContentCachingRequestWrapper requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
        requestCacheWrapperObject.getParameterMap();
        long startTime = System.currentTimeMillis();
        log.info("\nRequest:"+
                "\n\tURI: "+requestCacheWrapperObject.getRequestURI()+
                "\n\tHttp.Method: "+requestCacheWrapperObject.getMethod()+
                "\n\tContent-Type:  "+requestCacheWrapperObject.getContentType()/*+
                "\n\tBody:\n"+ IOUtils.toString(requestCacheWrapperObject.getInputStream(), requestCacheWrapperObject.getCharacterEncoding())*/);
        request.setAttribute("startTime", startTime);
        return true;
    }

}