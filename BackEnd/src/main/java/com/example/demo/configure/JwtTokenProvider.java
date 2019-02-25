package com.example.demo.configure;

import java.security.Key;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;



import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SigningKeyResolverAdapter;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {


	@Value("${jwt.expiration}")
	private long validityInMilliseconds;

	@Autowired
	private ApplicationContext contex;

	@Autowired
	private UserDetailsService userDetailsService;

	private Key key;

	private Key keys;

	@Autowired
	private ReserveKey reserveKey;

	@PostConstruct
	protected void init() {

		keys = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}

	public String createToken(String username, String roles) {
		boolean result = reserveKey.addKey(username, keys);
		if (result) {
			Claims claims = Jwts.claims().setSubject(username);
			claims.put("role", roles);

			Date now = new Date();
			Date validity = new Date(now.getTime() + validityInMilliseconds);

			return Jwts.builder().setClaims(claims).setHeaderParam(JwsHeader.KEY_ID, key).setIssuedAt(now)
					.setExpiration(validity).signWith(keys).compact();
		}
		return "User already login";
	}

	public Authentication getAuthentication(String token) {
		System.out.println("token "+token);
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
		System.out.println("result "+new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities()));
		return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
	}

	public String getUsername(String token) {
		Claims c = getKey(token);
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody().getSubject();
	}

	public String resolveToken(HttpServletRequest req) {
		String bearerToken = req.getHeader("Authorization");

		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7,bearerToken.length());
			}
		return null;
	}

	public boolean validateToken(String token) {

			Claims cla = getKey(token);
			Jws<Claims> claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;

	}
	
	public boolean logOut(String token) {
		Claims c=getKey(token);
		String username = c.getSubject();
		ReserveKey reserveKey = contex.getBean(ReserveKey.class);
		boolean remove=reserveKey.removeKey(username);
		if(remove) {
			return true;
		}
		return false;
	}

	private Claims getKey(String token) {
		return Jwts.parser().setSigningKeyResolver(new SigningKeyResolverAdapter() {
			@Override
			public Key resolveSigningKey(JwsHeader header, Claims claims) {
				String subject = claims.getSubject();
				ReserveKey reserveKey = contex.getBean(ReserveKey.class);
				key = reserveKey.getKey(subject);
				return key;
			}
		}).parseClaimsJws(token).getBody();
	}
}
