package com.yourDocuSmart.yourDocuSmart.web.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

//    ------------------------------------------------------------------------------------------------------------------
//  key para el token (debe tener mas seguridad a la hora de poner una key)
    private static final String KEY = "W.,Q9yrZ6M~f$^l";
//    ------------------------------------------------------------------------------------------------------------------

//    ------------------------------------------------------------------------------------------------------------------
//    1.metodo para generar un token con tiempo de expiracion
//    2.algoritmo de firma HS256 recomendado (estudiar los demas)

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder().setSubject(userDetails.getUsername()).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, KEY).compact();
    }
//    ------------------------------------------------------------------------------------------------------------------


//    ------------------------------------------------------------------------------------------------------------------
//    validacion si el token es correcto (recibe como parametro el Token y los UserDetails
//    ademas de preguntar si el token no ha expirado
    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token))&& !isTokenExpired(token);
    }

    //    ------------------------------------------------------------------------------------------------------------------
//    extraemos el username mediante los el Claim expuesto en el metodo getClaims
    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    //    ------------------------------------------------------------------------------------------------------------------
//    Metodo para verificar si el token ha expirado
    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }
    //    ------------------------------------------------------------------------------------------------------------------
//    Metodo auxiliar Claims que retorna los objetos dentro de los jwt
//    para este caso los objetos pedidos en el jwt por la llave firmada es username: y el password:
    private Claims getClaims(String token){

        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
//    ------------------------------------------------------------------------------------------------------------------

}
