package me.smorodin.exam.security;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security.jwt")
public class JwtProperties {
    private String secret = "SFJAKLJakxlfjkljSakjAKLDJASKLDsadkmasdklasdjSDJSKLJSDALJsxm,adnsml,";
    private Long expiration = 86400000L;
    private boolean enabled = true;

    public String getSecret() { return secret; }
    public void setSecret(String secret) { this.secret = secret; }

    public Long getExpiration() { return expiration; }
    public void setExpiration(Long expiration) { this.expiration = expiration; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}