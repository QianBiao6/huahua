package jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreatJWT {

    /**
     * 生成token （jwt）
     */
    public static void main(String[] args) {
        JwtBuilder huahuaCommunity = Jwts.builder().setId("123456789")
                .setSubject("1608V")//使用者
                .setIssuedAt(new Date())//jwt签发时间
                .signWith(SignatureAlgorithm.HS256, "huahuaCommunity");//加密方式 HS256
        //通过加盐的规则 huahuaCommunity.compact() 获取token令牌
        System.out.println(huahuaCommunity.compact());

    }
}
