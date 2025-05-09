package ng.victoriaejeh.javaappawscloud.security;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private static final String NAME_ATTRIBUTE = "login";
    private static final String EMAIL_KEY = "email";

    private final GitHubEmailFetcher emailFetcher;
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();

    public CustomOAuth2UserService(GitHubEmailFetcher emailFetcher) {
        this.emailFetcher = emailFetcher;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        if (!"github".equals(userRequest.getClientRegistration().getRegistrationId())) {
            return oAuth2User;
        }

        String primaryEmail = extractPrimaryEmailAddress(oAuth2User, userRequest.getAccessToken().getTokenValue());

        if (primaryEmail == null) {
            return oAuth2User;
        }

        Map<String, Object> updatedAttributes = new HashMap<>(oAuth2User.getAttributes());
        updatedAttributes.put(EMAIL_KEY, primaryEmail);

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                updatedAttributes,
                NAME_ATTRIBUTE
        );
    }

    private String extractPrimaryEmailAddress(OAuth2User oAuth2User, String token) {
        Object emailObj = oAuth2User.getAttributes().get(EMAIL_KEY);
        String primaryEmail = emailObj != null ? emailObj.toString() : null;

        if (primaryEmail != null && !primaryEmail.isBlank()) {
            return primaryEmail;
        }

        return emailFetcher.fetchPrimaryEmailAddress(token);
    }
}
