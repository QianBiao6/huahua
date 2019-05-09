package jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryJWT {
    //token 令牌
    public static void main(String[] args) {
        String token= "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxMjM0NTY3ODkiLCJzdWIiOiIxNjA4ViIsImlhdCI6MTU1NjE5ODkzN30.CuwBgAk1EeaxXYWWtAHPbIrxIt5yjQxoVB4gw8WWno0";

        Claims body = Jwts.parser().setSigningKey("huahuaCommunity")
                .parseClaimsJws(token).getBody();
        System.out.println("用户的id:"+body.getId());
        System.out.println("用户的名称:"+body.getSubject());
        System.out.println("签发时间:"+body.getIssuedAt());
    }
}
