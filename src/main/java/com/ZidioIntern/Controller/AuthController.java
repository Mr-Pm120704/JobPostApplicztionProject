package com.ZidioIntern.Controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ZidioIntern.DTO.AuthResponseDTO;
import com.ZidioIntern.DTO.LoginRequestDTO;
import com.ZidioIntern.DTO.UserDTO;
import com.ZidioIntern.Service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor

public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	public ResponseEntity<AuthResponseDTO>register(@RequestBody UserDTO dto){
		return ResponseEntity.ok(authService.register(dto));
	}
	
//	@PostMapping("/login")
//	public ResponseEntity<String> login(@RequestBody LoginRequestDTO dto){
//		return ResponseEntity.ok(authService.login(dto));
//	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO dto) {
	    String token = authService.login(dto);
	    return ResponseEntity.ok(Collections.singletonMap("token", token));
	}

	
	
	
	@GetMapping("/me")
    public ResponseEntity<UserDTO> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
       
        UserDTO user = authService.getUserFromToken(token);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}
