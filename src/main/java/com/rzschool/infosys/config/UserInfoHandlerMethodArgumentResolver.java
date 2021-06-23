package com.rzschool.infosys.config;

import com.rzschool.infosys.db.entity.RzUser;
import com.rzschool.infosys.permission.subject.CustomTokenSubjectCreator;
import com.usthe.sureness.util.JsonWebTokenUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Configuration
public class UserInfoHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        if(methodParameter.getParameterType().equals(RzUser.class)){
            return true;
        }
        return false;
    }

    @Override
    public RzUser resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        String token = nativeWebRequest.getHeader(CustomTokenSubjectCreator.HEADER_TOKEN);
        if(StringUtils.isBlank(token)){
            return null;
        }
        RzUser user = new RzUser();
        Claims claims = JsonWebTokenUtil.parseJwt(token);
        user.setAccount(claims.get("sub").toString());
        user.setId(Integer.valueOf(claims.get("userId").toString()));
        return user;
    }
}
