package com.example.movieReviewsApp.configs;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.movieReviewsApp.models.User;
import com.example.movieReviewsApp.utils.JwtAuthenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Request filter. This class controlls that all routes are authenticated with a valid jwt token.
 */
@Component
public class RequestFilter extends OncePerRequestFilter{

    @Autowired JwtAuthenticator jwtAuth;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    
                String token;
                User user = null;


                if (header == null || !header.startsWith("Bearer ")) {
        
                    response.setStatus(403);
                    return;

                }

                token = header.split(" ")[1].trim(); 

               try {

                user = jwtAuth.authenticateUser(token);

               } catch (Exception e) {
                   // If any errors happend during the authentication of the token, return a 403 status and end the call.
                   response.setStatus(403);
                   return;
               }
       
               // Since I removed spring securities own authentication protocol and implemented my own protocol, all incoming calls will be presented as "anonymousUser" and will 
               // Cause an error when checking for getPrincipals, this is why I check for "anonymousUser" instead of "NULL" value.
                if (user != null && SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {

                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        user, user.getUsername()
                        );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

                filterChain.doFilter(request, response);
    }
    
}
