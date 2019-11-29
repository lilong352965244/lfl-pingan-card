package com.lfl.utils;

import com.lfl.constant.CommonConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;


/**
 * @author: lifalong
 * @create: 2019-11-08 18:22
 * @description: 生成token的工具类
 **/

public class TokenUtil {
    /**
     * 签名秘钥(唯一秘钥，可以用密码做为秘钥)
     */
    public static final String SECRET="lfl2019";

    /**
     * 生成token
     * @param username
     * @return
     */
    public static String createJwtToken(String username,String id,Date expireTime){
        String issuer="pingancard2019";
        String subject="lfl";
        long ttlMillis= CommonConstant.JWT_EXPIRE_Millis;
        return createJwtToken(username,id,expireTime,issuer,subject,ttlMillis);
    }

    /**
     * 生成token
     * @param username 用户名
     * @param issuer 改JWT的签发者，是否使用可以选
     * @param subject 改JWT所面向的用户，是否使用可选
     * @param ttlMillis 签发时间（有效时间，过期会报错）
     * @return token string
     */
    public static String createJwtToken(String username,String id,Date expireTime,
                                        String issuer,String subject,long ttlMillis){
        //签名算法，将token进行签名
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;
        //生成签发时间
        long nowMills=System.currentTimeMillis();
        Date now=new Date(nowMills);
        //通过秘钥签名JWT
        byte[] apiKeySecretBytes= DatatypeConverter.parseBase64Binary(SECRET);
        Key signingKey=new SecretKeySpec(apiKeySecretBytes,signatureAlgorithm.getJcaName());
        //创建token
        JwtBuilder builder=Jwts.builder().setId(username)
                .claim("id",id)  // 用户的Id
                .claim("expireTime",expireTime) // 过期时间
                .setIssuedAt(now)
                .signWith(signatureAlgorithm,signingKey);
        //添加过期时间
        if(ttlMillis>=0){
            long expMillis=nowMills+ttlMillis;
            Date exp=new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    //验证和读取JWT的示例方法
    public static Claims parseJWT(String jwt){
        Claims claims=Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET))
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    public static void main(String[] args){

//      String  token =      TokenUtil.createJwtToken("李四2", Long.toString(1L));
//       System.out.println(token);
        String   token =  "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJsaXNpIiwiaWQiOiI2IiwiZXhwaXJlVGltZSI6MTYwNTE0NDg1MTAwMCwiaWF0IjoxNTc0ODMzNzMyLCJleHAiOjE1NzUwOTI5MzJ9.iIUvalC4jfEo5_Ze_q9-WShZZHtFLbdpLr2VzjB3e3o";

        Claims claims = parseJWT(token);
        String claimsId = claims.getId();
        Date issuedAt = claims.getIssuedAt();
        String issuer = claims.getIssuer();
        String id = (String) claims.get("id");


        System.out.println(claimsId);
        System.out.println(issuedAt);
        System.out.println(issuer);
        System.out.println(Long.valueOf(id));

    }

}
