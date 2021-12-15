package com.esc.mall;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT 工具类
 *
 * @author jiaorun
 * @date 2021/11/3 15:55
 **/
@Component
public class JWTTokenUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTTokenUtils.class);

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成JWT的token
     *
     * @param claims
     * @return java.lang.String
     * @author jiaorun
     * @data 2021/11/3 16:43
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 从token中获取JWT的负载
     *
     * @param token
     * @return io.jsonwebtoken.Claims
     * @author jiaorun
     * @data 2021/11/3 16:47
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("JWT格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * 从token中获取登录用户名
     *
     * @param token
     * @return java.lang.String
     * @author jiaorun
     * @data 2021/11/3 16:49
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 验证token是否有效
     *
     * @param token
     * @param userDetails
     * @return boolean
     * @author jiaorun
     * @data 2021/11/3 17:00
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equals(userDetails.getUsername()) && isTokenExpired(token);
    }

    /**
     * 判断token是否已失效
     *
     * @param token
     * @return boolean
     * @author jiaorun
     * @data 2021/11/3 16:56
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFromToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * 根据用户信息生成token
     *
     * @param userDetails
     * @return java.lang.String
     * @author jiaorun
     * @data 2021/11/3 17:04
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(2);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 从token中获取过期时间
     *
     * @param token
     * @return java.util.Date
     * @author jiaorun
     * @data 2021/11/3 16:57
     */
    private Date getExpiredDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 生成token的过期时间
     *
     * @return java.util.Date
     * @author jiaorun
     * @data 2021/11/3 16:13
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 判断token是否可以刷新
     *
     * @param token
     * @return boolean
     * @author jiaorun
     * @data 2021/11/3 17:06
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     *
     * @param token
     * @return java.lang.String
     * @author jiaorun
     * @data 2021/11/3 17:51
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
