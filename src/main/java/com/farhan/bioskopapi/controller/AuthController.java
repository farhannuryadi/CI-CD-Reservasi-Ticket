package com.farhan.bioskopapi.controller;


import com.farhan.bioskopapi.dto.request.LoginRequest;
import com.farhan.bioskopapi.dto.request.SignupRequest;
import com.farhan.bioskopapi.dto.response.JwtResponse;
import com.farhan.bioskopapi.dto.response.MessageResponse;
import com.farhan.bioskopapi.dto.response.ResponseData;
import com.farhan.bioskopapi.entity.ERole;
import com.farhan.bioskopapi.entity.FilmEntity;
import com.farhan.bioskopapi.entity.RoleEntity;
import com.farhan.bioskopapi.entity.UserEntity;
import com.farhan.bioskopapi.helper.utility.ErrorParsingUtility;
import com.farhan.bioskopapi.helper.utility.StatusCode;
import com.farhan.bioskopapi.repository.RoleRepository;
import com.farhan.bioskopapi.repository.UserRepository;
import com.farhan.bioskopapi.security.jwt.JwtUtils;
import com.farhan.bioskopapi.security.services.UserDetailsImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Operation signin and signup")
public class AuthController {

    public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, Errors errors) {

        ResponseData<FilmEntity> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("request invalid :{}", ErrorParsingUtility.parse(errors).toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        logger.info("sukses login user : {}", loginRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, Errors errors) {
        ResponseData<FilmEntity> responseData = new ResponseData<>();
        if (errors.hasErrors()) {
            responseData.setStatusCode(StatusCode.BAD_REQUEST);
            responseData.setStatus(false);
            responseData.setMessages(ErrorParsingUtility.parse(errors));
            logger.warn("request invalid :{}", ErrorParsingUtility.parse(errors).toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            logger.warn("Error: Username is already taken!");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            logger.warn("Error: Email is already in use!");
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        UserEntity user = new UserEntity(signUpRequest.getUsername(),
                                        signUpRequest.getFullName(),
                                        signUpRequest.getAddress(),
                                        signUpRequest.getEmail(),
                                        encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<RoleEntity> roles = new HashSet<>();

        if (strRoles == null) {
            RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> {
                        logger.warn("Error: Role is not found.");
                        return new RuntimeException("Error: Role is not found.");
                    });
            roles.add(userRole);
            strRoles.forEach(System.out::println);
            System.out.println("ke panggil");
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> {
                                    logger.warn("Error: Role is not found.");
                                    return new RuntimeException("Error: Role is not found.");
                                });
                        roles.add(adminRole);

                        break;
                    case "mod":
                        RoleEntity modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> {
                                    logger.warn("Error: Role is not found.");
                                    return new RuntimeException("Error: Role is not found.");
                                });
                        roles.add(modRole);

                        break;
                    default:
                        RoleEntity userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> {
                                    logger.warn("Error: Role is not found.");
                                    return new RuntimeException("Error: Role is not found.");
                                });
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        logger.info("new signup sukses user : {}", signUpRequest.getUsername());
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
