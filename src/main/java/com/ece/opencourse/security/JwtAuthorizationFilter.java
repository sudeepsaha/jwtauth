package com.ece.opencourse.security;

import com.ece.opencourse.model.JwtToken;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    private static final String ROLES = "roles";

    private String secretKey = "secret";

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);
        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token != null) {
            try {
                String user = Jwts.parser()
                        .setSigningKey(secretKey.getBytes())
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody()
                        .getSubject();

                String[] roles = Jwts.parser()
                        .setSigningKey(secretKey.getBytes())
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody().get(ROLES).toString().split(",");

                JwtToken jwtToken = new JwtToken(user, roles);
                if (jwtToken.getUserName() != null && jwtToken.getRoles() != null) {
                    final Collection authorities
                            = Arrays.stream(jwtToken.getRoles())
                                    .map(SimpleGrantedAuthority::new)
                                    .collect(Collectors.toList());
                    return new UsernamePasswordAuthenticationToken(jwtToken.getUserName(), null, authorities);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        return null;
    }

}
