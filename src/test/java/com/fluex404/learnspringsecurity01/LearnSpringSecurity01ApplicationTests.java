package com.fluex404.learnspringsecurity01;

import com.fluex404.learnspringsecurity01.config.MyUserDetails;
import com.fluex404.learnspringsecurity01.config.MyUserDetailsService;
import com.fluex404.learnspringsecurity01.entity.UserData;
import com.fluex404.learnspringsecurity01.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Date;

@SpringBootTest
class LearnSpringSecurity01ApplicationTests {

	@Autowired
    private JwtUtil jwtUtil;
	@Autowired
	private MyUserDetailsService myUserDetailsService;

    @Test
    void ngetestJwtUtils() {
		System.out.println("TEST JWT");

		MyUserDetails user = new MyUserDetails("user1", "password1", true,
				Arrays.asList("ROLE_USER", "ROLE_ADMIN"));

		String jwtToken = jwtUtil.generateToken(user);
		System.out.println(jwtToken);
		System.out.println(jwtUtil.extractExpiration(jwtToken));
		System.out.println(jwtUtil.extractUsername(jwtToken));
		System.out.println(jwtUtil.validateToken(jwtToken, user));
	}

	@Test
	void ngeTestTokenJwt(){
		String jwtToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyMSIsImV4cCI6MTY2ODg2MTY4MCwiaWF0IjoxNjY4ODYxMzgwfQ.GsJLkReGbGyt4PacFyI0gSF1HBQwVJg6JBSgWziIH85x_1xejsV78AxGzWxyjhMK6GdCR0SKQR60eouIL20i_A";
		String username = jwtUtil.extractUsername(jwtToken);
		Date expiredDate = jwtUtil.extractExpiration(jwtToken);
		UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);

		System.out.println(username);
		System.out.println(expiredDate);
		System.out.println(jwtUtil.validateToken(jwtToken, userDetails));
	}

}
