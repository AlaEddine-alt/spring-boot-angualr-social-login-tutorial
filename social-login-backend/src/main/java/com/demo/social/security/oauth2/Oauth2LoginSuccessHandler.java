package com.demo.social.security.oauth2;


import com.demo.social.enums.Role;
import com.demo.social.security.JwtService;
import com.demo.social.user.User;
import com.demo.social.user.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

/**
 * Service to handle successful OAuth2 login.
 */
@RequiredArgsConstructor
@Slf4j
@Service
public class Oauth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    /**
     * Handles successful authentication by processing the authenticated user,
     * updating user information if necessary, and generating JWT tokens.
     *
     * @param request       the HttpServletRequest
     * @param response      the HttpServletResponse
     * @param authentication the Authentication object containing user details
     * @throws IOException      if an input or output error occurs
     * @throws ServletException if a servlet error occurs
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        log.info("Authentication: {}", authentication);

        // Get the user details from the authentication object
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        // Convert the user details to AppUser
        AppUser appUser = AppUser.fromGoogleUser(oidcUser);
        User user = userRepository.findByEmail(appUser.getEmail()).orElse(null);

        //  If the user is not found, create a new user
        if (user == null) {
            String providerId = appUser.getId(); // This is the 'sub' attribute from Google
            OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) authentication;
            String provider = authenticationToken.getAuthorizedClientRegistrationId();

            var userToSave = User.builder()
                    .id(UUID.randomUUID())
                    .providerId(providerId)
                    .provider(provider)
                    .firstName(appUser.getGivenName())
                    .lastName(appUser.getFamilyName())
                    .emailVerified(true)
                    .profilePictureUrl(appUser.getImageUrl())
                    .enabled(true)
                    .accountLocked(false)
                    .role(Role.USER)
                    .version(0)
                    .email(appUser.getEmail())
                    .build();
            user = userRepository.save(userToSave);  // Save the user and assign to userEntity
        }
        // Check if the user information has changed
        boolean updated = false;
        if (!user.getFirstName().equals(appUser.getGivenName())) {
            user.setFirstName(appUser.getGivenName());
            updated = true;
        }
        if (!user.getLastName().equals(appUser.getFamilyName())) {
            user.setLastName(appUser.getFamilyName());
            updated = true;
        }
        if (!user.getProfilePictureUrl().equals(appUser.getImageUrl())) {
            user.setProfilePictureUrl(appUser.getImageUrl());
            updated = true;
        }
        if (updated) {
            user = userRepository.save(user);
        }

        // Generate JWT token and refresh token
        var claims = new HashMap<String, Object>();
        claims.put("fullName", user.getFullName());
        claims.put("email", user.getEmail());
        String token = jwtService.generateToken(claims, user);

        // Set the authentication in the context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Redirect to the Angular app with the JWT tokens
        String redirectUrl = "http://localhost:4200/login/callback";
        redirectUrl += "?accessToken=" + token;
        response.sendRedirect(redirectUrl);
    }


}
