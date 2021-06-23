package com.rzschool.infosys.permission.subject;

import com.usthe.sureness.subject.Subject;
import com.usthe.sureness.subject.SubjectCreate;
import com.usthe.sureness.subject.support.JwtSubject;
import com.usthe.sureness.util.JsonWebTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * custom token creator, get token from http request header - {"Token" : "tokenValue"}
 * tokenValue is : admin--issueTime--refreshPeriodTime--uuid
 * @author tomsun28
 * @date 2020-12-03 22:08
 */
public class CustomTokenSubjectCreator implements SubjectCreate {

    private static final Logger logger = LoggerFactory.getLogger(CustomTokenSubjectCreator.class);

    public static final String HEADER_TOKEN = "Access-Token";

    @Override
    public boolean canSupportSubject(Object context) {
        if (context instanceof HttpServletRequest) {
            String authorization = ((HttpServletRequest)context).getHeader(HEADER_TOKEN);
            if (authorization != null) {
                return !JsonWebTokenUtil.isNotJsonWebToken(authorization);
            }
        }
        return false;
    }

    @Override
    public Subject createSubject(Object context) {
        String authorization = ((HttpServletRequest)context).getHeader(HEADER_TOKEN);
        if (authorization != null) {
            // jwt token
            if (JsonWebTokenUtil.isNotJsonWebToken(authorization)) {
                if (logger.isInfoEnabled()) {
                    logger.info("can not create JwtSubject by this request message, is not jwt");
                }
                return null;
            }
            String remoteHost = ((HttpServletRequest) context).getRemoteHost();
            String requestUri = ((HttpServletRequest) context).getRequestURI();
            String requestType = ((HttpServletRequest) context).getMethod();
            String targetUri = requestUri.concat("===").concat(requestType.toLowerCase());
            return JwtSubject.builder(authorization)
                    .setRemoteHost(remoteHost)
                    .setTargetResource(targetUri)
                    .build();
        }
        return null;
    }
}
