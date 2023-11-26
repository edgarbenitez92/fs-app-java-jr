package com.fullstack.springboot.fsappjavajr.jwt;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fullstack.springboot.fsappjavajr.user.User;
import com.fullstack.springboot.fsappjavajr.user.UserResource;

@RestController
public class JwtAuthenticationController {

	private final JwtTokenService tokenService;
	private final AuthenticationManager authenticationManager;
	private final UserResource userResource;

	public JwtAuthenticationController(JwtTokenService tokenService, AuthenticationManager authenticationManager,
			UserResource userResource) {
		this.tokenService = tokenService;
		this.authenticationManager = authenticationManager;
		this.userResource = userResource;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<JwtTokenResponse> generateToken(@RequestBody JwtTokenRequest jwtTokenRequest) {

		List<User> users = userResource.getUsers();

		Optional<User> userBd = users.stream()
		        .filter(user -> 
		        	user.getUser().contentEquals(jwtTokenRequest.username())
		            && user.getPassword().contentEquals(jwtTokenRequest.password()))
		        .findFirst();
		
		if(userBd.isPresent()) {
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					jwtTokenRequest.username(), jwtTokenRequest.password());
			
			var authentication = authenticationManager.authenticate(authenticationToken);
			var token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new JwtTokenResponse(token));		
		} else  {
			throw new JwtNotFoundException(
					String.format("Usuario no existe en la BD. Por favor lea las instrucciones."));
		}
	}
}
