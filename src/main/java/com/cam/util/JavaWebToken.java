package com.cam.util;

import java.security.Key;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * 
 * 作者：ChenJianrong
 *
 * 创建时间：2018年8月9日 下午9:28:00
 *
 * 功能描述： 基于JWT的token认证机制
 * 
 * 版本： V1.0
 */
public class JavaWebToken {
	private static Logger log = LoggerFactory.getLogger(JavaWebToken.class);

	// 该方法使用HS256算法和Secret:bankgl生成signKey
	private static Key getKeyInstance() {
		// We will sign our JavaWebToken with our ApiKey secret
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("bankgl");
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
		return signingKey;
	}

	// 使用HS256签名算法和生成的signingKey最终的Token,claims中是有效载荷
	public static String createJavaWebToken(Map<String, Object> claims) {
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS256, getKeyInstance()).compact();
	}

	// 解析Token，同时也能验证Token，当验证失败返回null
	public static Map<String, Object> parserJavaWebToken(String jwt) {
		try {
			Map<String, Object> jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
			return jwtClaims;
		} catch (Exception e) {
			log.error("json web token verify failed");
			return null;
		}
	}
}