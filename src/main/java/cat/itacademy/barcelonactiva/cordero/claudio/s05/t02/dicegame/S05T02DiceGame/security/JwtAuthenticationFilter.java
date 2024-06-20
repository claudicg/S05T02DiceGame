package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.security;

import java.io.IOException;
 
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.JwtService;
import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.services.mysql.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private static Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserService userService;
 
	@Override
	protected void doFilterInternal(@NonNull HttpServletRequest request,
									@NonNull HttpServletResponse response,
									@NonNull FilterChain filterChain) throws ServletException, IOException {
 
		final String authorizationHeader = request.getHeader("Authorization");
		final String jwt;
		final String email;
		
		if(StringUtils.isEmpty(authorizationHeader)
				|| !StringUtils.startsWith(authorizationHeader, "Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}
		
		jwt = authorizationHeader.substring(7); //Ignore "Bearer "
		logger.info("JWT -> {}", jwt.toString());
		
		email = jwtService.extractUserName(jwt);
		logger.info("username -> {}", email);
		
		if(StringUtils.isNotEmpty(email)
				&& SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = userService.userDetailsService().loadUserByUsername(email);
			
			if(jwtService.isValidToken(jwt, userDetails)) {
				logger.debug("user -> {}", userDetails);
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				UsernamePasswordAuthenticationToken authorizationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authorizationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				securityContext.setAuthentication(authorizationToken);
				SecurityContextHolder.setContext(securityContext);
			}
			
			filterChain.doFilter(request, response);
			
		}
		
	}
 
}
 
