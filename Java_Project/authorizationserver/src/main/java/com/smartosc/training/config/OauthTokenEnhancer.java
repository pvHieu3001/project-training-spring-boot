package com.smartosc.training.config;

import java.util.HashMap;
import java.util.Map;

import com.smartosc.training.dto.UserDTO;
import com.smartosc.training.services.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;


@Component
public class OauthTokenEnhancer implements TokenEnhancer {

  @Autowired
  private UserService userService;
  
  @SneakyThrows
  @Override
  public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,
      OAuth2Authentication authentication) {
    String userName = authentication.getName();
    UserDTO user = userService.findUserByUserName(userName);
    final Map<String, Object> additionalInfo = new HashMap<>();
    additionalInfo.put("name", user.getUsername());
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
    return accessToken;
  }

}
