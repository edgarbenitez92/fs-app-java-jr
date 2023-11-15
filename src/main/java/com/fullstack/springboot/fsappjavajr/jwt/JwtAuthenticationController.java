package com.fullstack.springboot.fsappjavajr.jwt;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {

	private final JwtTokenService tokenService;

	private final AuthenticationManager authenticationManager;

	public JwtAuthenticationController(JwtTokenService tokenService, AuthenticationManager authenticationManager) {
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody JwtTokenRequest jwtTokenRequest) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
				jwtTokenRequest.username(), jwtTokenRequest.password());

		System.out.println("auth: " + authenticationToken);

		if (!authenticationToken.isAuthenticated()) {
			throw new JwtNotFoundException(
					String.format("Usuario no existe en la BD. Por favor lea las instrucciones."));
		} else {
			var authentication = authenticationManager.authenticate(authenticationToken);
			var token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new JwtTokenResponse(token));
		}
	}
}
