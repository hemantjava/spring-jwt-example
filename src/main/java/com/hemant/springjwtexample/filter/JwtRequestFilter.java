package com.hemant.springjwtexample.filter;

import com.hemant.springjwtexample.service.MyUserDetailsService;
import com.hemant.springjwtexample.utils.JwtUtil;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Log4j2
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

  @Autowired
  private MyUserDetailsService myUserDetailsService;

  @Autowired
  private JwtUtil jwtUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse, FilterChain filterChain)
      throws ServletException, IOException {
    final String authorization = httpServletRequest.getHeader("Authorization");
    log.info(authorization);
    String userName = null;
    String jwt = null;
    if (authorization != null && authorization.startsWith("Bearer ")) {
      jwt = authorization.substring(7);
      userName = jwtUtil.extractUsername(jwt);
      log.info(jwt + "  " + userName);
    }
    if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      final UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
      if (jwtUtil.validateToken(jwt, userDetails)) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        log.info(usernamePasswordAuthenticationToken);
        usernamePasswordAuthenticationToken
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
