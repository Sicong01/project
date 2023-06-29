package com.ascending3.learnrestapi3.filter;

import com.ascending3.learnrestapi3.dto.UserDto;
import com.ascending3.learnrestapi3.service.Impl.JwtServiceImpl;
import com.ascending3.learnrestapi3.service.JWTService;
import com.ascending3.learnrestapi3.service.UserService;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "jwtFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class JwtFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private JWTService jwtService;

//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserService userService;

    private String AUTH_URI = "/auth";

    private String AUTH_URI_EXTERNAL = "/project2020/auth";


    /*
     * When the app is deployed to external Tomcat, spring DI unable to be fulfilled,
     * therefore, we need to override the following method which inherited from GenericFilterBean
     * to initialize the DI components manually.
     */

    @Override
    public void initFilterBean() throws ServletException{
//        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//        //reference to bean from app context
//        jwtService = webApplicationContext.getBean(JwtServiceImpl.class);
//        userService = webApplicationContext.getBean(UserService.class);

        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, getServletContext());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        int statusCode = authorization(request);
        if (statusCode == HttpServletResponse.SC_ACCEPTED){
            filterChain.doFilter(request,response);
        } else {
            response.sendError(statusCode);
        }

    }

    private int authorization(HttpServletRequest request) {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;

        String incomingUri = request.getRequestURI();
        logger.info("=====, within authorization(...), incomingUri={}", incomingUri);
        if (incomingUriRequestForAuth(incomingUri)) {
            return HttpServletResponse.SC_ACCEPTED;
        }

        try {
            String wholeTokenString = request.getHeader("Authorization");
            if (retrievedWholeTokenStringExist(wholeTokenString)) {
                String token = wholeTokenString.split(" ")[1].trim();
                logger.info("=====, within authorization(...), token={}", token);
                if(token == null || token.trim().isEmpty()){
                    return HttpServletResponse.SC_UNAUTHORIZED;
                }

                //validate token
//                boolean isTokenValid = jwtService.validateAccessToken(token);
//                if (isTokenValid){
//                    statusCode = HttpServletResponse.SC_ACCEPTED;
//                }



/////////////
                /*
                 *  1. parse the token and get Claims
                 *  2. verify user by ID through userService => unnecessary?
                 *  3. retrieve http method value from input HttpServletRequest (GET, POST, UPDATE,
                 *       PATCH, DELETE?)
                 *  4. based on http method value, retrieve the corresponding concatenated URI string
                 *  5. check to see if the allowed URI string does include the incomingUri
                 *      a. If yes, return HttpServletResponse.SC_ACCEPTED to allow the incoming request
                 *         to continue
                 *      b. If no, return HttpServletResponse.SC_UNAUTHORIZED to block the incoming request
                 */
                //parse the token and verify user info
                Claims claims = jwtService.decryptJwtToken(token);
                if(!isClaimsValid(claims))
                    return HttpServletResponse.SC_UNAUTHORIZED;
                logger.info("=== within authorization(...), parsed claims = {}", claims);

                //May not be necessary
                if(isUserRetrievedByUserIdInvalid(claims.getId()))
                    return HttpServletResponse.SC_UNAUTHORIZED;

                String httpMethodValue = request.getMethod();
                boolean isRequestedUriAllowedToBeAccessed = checkRequestUriAuthorization(claims, httpMethodValue, incomingUri);
                if(isRequestedUriAllowedToBeAccessed){
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                }

            }

        } catch (Exception e) {
            logger.error("Exception is thrown when parsing JWT token, error = {}", e.getMessage());
        }
        return statusCode;
    }

    private boolean checkRequestUriAuthorization(Claims claims, String httpMethodValue, String requestUri) {
        boolean isAuthorized = false;
        String allowedResources = findAllowedResourcesUsingHttpMethodValueWithclaims(claims,httpMethodValue);
        String[] allowedResourcesArray = allowedResources.split(",");
        for (String eachAllowedResource : allowedResourcesArray){
            logger.info("===, requestUri={}, eachAllowedResource={}", requestUri, eachAllowedResource);
            if (requestUri.trim().toLowerCase().startsWith(eachAllowedResource.trim().toLowerCase())){
                isAuthorized = true;
                break;
            }
        }
        return isAuthorized;
    }

    private String findAllowedResourcesUsingHttpMethodValueWithclaims(Claims claims, String httpMethodValue) {
        String allowedResources = "";
        switch (httpMethodValue){
            case "GET":
                allowedResources = (String) claims.get("allowedReadResources");
                break;
            case "POST":
                allowedResources = (String) claims.get("allowedCreateResources");
                break;
            case "PUT":
                allowedResources = (String) claims.get("allowedUpdateResources");
                break;
            case "PATCH":
                allowedResources = (String) claims.get("allowedUpdateResources");
                break;
            case "DELET":
                allowedResources = (String) claims.get("allowedDeleteResources");
                break;
        }
        return allowedResources;
    }


    private boolean isUserRetrievedByUserIdInvalid(String id) {
        boolean isValid = false;
        if(id != null){
            UserDto userDto = userService.getUserById(Long.valueOf(id));
            if (userDto != null){
                isValid = true;
                logger.info("===, Now using userId={}, retrieved UserDto={}", id, userDto);
            }
        }
        return  !isValid;
    }

    private boolean isClaimsValid(Claims claims) {
        boolean isClaimsValid = true;
        if (claims == null || claims.isEmpty())
            isClaimsValid = false;
        return isClaimsValid;
    }


    private boolean incomingUriRequestForAuth(String uri){
        boolean isUriRequestForAuth = false;
        if (uri.equalsIgnoreCase(AUTH_URI) || uri.equalsIgnoreCase(AUTH_URI_EXTERNAL)){
            isUriRequestForAuth = true;
        }
        return  isUriRequestForAuth;
    }


    private boolean retrievedWholeTokenStringExist(String wholeTokenString) {
        boolean isStringExist = false;
        if(wholeTokenString != null && !wholeTokenString.trim().isEmpty()){
            isStringExist = true;
        }
        return isStringExist;
    }
}
