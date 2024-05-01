/**
 * This class is a filter that runs on every request to authorize the request
 * using a JWT token.
 * It is an important component of the project as it handles the authentication
 * and authorization process.
 * The filter validates the JWT token in the request and sets up the Spring
 * Security context if the user is authenticated and authorized.
 * It also filters out certain endpoints such as login, signup, forgot-password,
 * reset-password, and verify, from the filtering process.
 * If the user is not authenticated or authorized, it writes an access denied
 * response to the user.
 */

package com.sky.companion.security.jwt;

import com.sky.companion.authentication.common.dto.UserDTO;
import com.sky.companion.authentication.get_user.service.GetUserService;
import com.sky.companion.common.exceptions.UserNotFoundException;
import com.sky.companion.common.jackson.JacksonUtils;
import com.sky.companion.common.model.HttpErrorResponse;
import com.sky.companion.common.model.HttpSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final RequestMatcher loginMatcher = new AntPathRequestMatcher("/login", HttpMethod.POST.name());
    private final RequestMatcher signUpMatcher = new AntPathRequestMatcher("/signup", HttpMethod.POST.name());
    private final RequestMatcher forgotPasswordMatcher = new AntPathRequestMatcher("/forgot-password", HttpMethod.POST.name());
    private final RequestMatcher resetPasswordMatcher = new AntPathRequestMatcher("/reset-password", HttpMethod.POST.name());

    private final RequestMatcher verifyMatcher = new AntPathRequestMatcher("/verify", HttpMethod.GET.name());

    @Autowired
    private GetUserService getUserService;
    @Autowired
    private JWTUtils jwtUtils;

    /**
     * method to filter login, signup, forgot-password, reset-password endpoints from filtering
     *
     * @param request
     * @return
     */
    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        return loginMatcher.matches(request) || signUpMatcher.matches(request) || forgotPasswordMatcher.matches(request) || resetPasswordMatcher.matches(request) || verifyMatcher.matches(request);
    }

    /**
     * this method override default filter functionality to validate JWT token coming in request and setup spring security if user is authenticated and authorized
     *
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader(HttpHeaders.ORIGIN));
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            String authorizationHeader = request.getHeader(JWTConstants.HEADER);
            if (!authorizationHeader.startsWith(JWTConstants.PREFIX)) {
                writeForbiddenResponse(response);
                return;
            }
            Integer userid = jwtUtils.validateJWTTokenAndGetUserId(authorizationHeader);
            if (userid == null) {
                writeForbiddenResponse(response);
                return;
            }
            try {
                HttpSuccessResponse<UserDTO> user = getUserService.getUserById(userid);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getData(), null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                filterChain.doFilter(request, response);
            } catch (UserNotFoundException e) {
                writeForbiddenResponse(response);
            }
        }

    }

    /**
     * this method writes Access denied message to user when unauthenticated user try to access api
     *
     * @param response
     * @throws IOException
     */
    public void writeForbiddenResponse(HttpServletResponse response) throws IOException {
        HttpErrorResponse errorResponse = new HttpErrorResponse("Access denied");
        String responseMessage = JacksonUtils.toJson(errorResponse);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.getOutputStream().write(responseMessage.getBytes());
        SecurityContextHolder.clearContext();
    }
}
