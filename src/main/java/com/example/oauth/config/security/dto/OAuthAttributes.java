package com.example.oauth.config.security.dto;

import com.example.oauth.domain.user.Role;
import com.example.oauth.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OAuthAttributes {

    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String email;
    private String picture;

    public static OAuthAttributes initialize(Map<String, Object> attributes, String nameAttributeKey, String name, String email, String picture) {
        var instance = new OAuthAttributes();
        instance.attributes = attributes;
        instance.nameAttributeKey = nameAttributeKey;
        instance.name = name;
        instance.email = email;
        instance.picture = picture;

        return instance;
    }

    public static OAuthAttributes of(String registrationId, String userNameAttributeName, Map<String, Object> attributes) {
        if("naver".equals(registrationId)) {
            return ofNaver("id", attributes);
        }
        return ofGoogle(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
        Map<String, Object> response = (Map<String, Object>)attributes.get("response");

        return OAuthAttributes.initialize(response, userNameAttributeName, (String) response.get("name"),
                (String) response.get("email"), (String) response.get("profile_image"));
    }

    private static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.initialize(attributes, userNameAttributeName, (String) attributes.get("name"),
                (String) attributes.get("email"), (String) attributes.get("picture"));
    }

    public User toEntity() {
        return User.of(name, email, picture, Role.USER);
    }

}
