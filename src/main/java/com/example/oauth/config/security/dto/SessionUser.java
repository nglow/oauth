package com.example.oauth.config.security.dto;

import com.example.oauth.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public static SessionUser from(User user) {
        var instance = new SessionUser();
        instance.name = user.getName();
        instance.email = user.getEmail();
        instance.picture = user.getPicture();

        return instance;
    }
}
