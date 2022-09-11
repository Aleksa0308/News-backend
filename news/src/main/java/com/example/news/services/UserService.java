package com.example.news.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.news.entities.User;
import com.example.news.repositories.user.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

public class UserService {

    @Inject
    UserRepository userRepository;

    public String login(String username, String password)
    {
        String hashedPassword = DigestUtils.sha256Hex(password);

        User user = this.userRepository.findUser(username);
        if (user == null || !user.getHashedPassword().equals(hashedPassword)) {
            return null;
        }

        Date issuedAt = new Date();
        Date expiresAt = new Date(issuedAt.getTime() + 24*60*60*1000); // One day

        Algorithm algorithm = Algorithm.HMAC256("secret");

        // JWT-om mozete bezbedono poslati informacije na FE
        // Tako sto sve sto zelite da posaljete zapakujete u claims mapu
        return JWT.create()
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .withSubject(username)
                .withClaim("username", user.getUsername())
                .withClaim("role", user.getRole())
                .withClaim("status", user.getStatus())
                .sign(algorithm);
    }

    public boolean isAuthorized(String token){
        Algorithm algorithm = Algorithm.HMAC256("secret");
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT jwt = verifier.verify(token);

        String username = jwt.getSubject();
        jwt.getClaim("username").asString();
        jwt.getClaim("role").asString();
        jwt.getClaim("status").asString();

        User user = this.userRepository.findUser(username);

        if (user == null){
            return false;
        }

        return true;
    }
    public List<User> allUsers() {
        return userRepository.allUsers();
    }
    public void deleteUser(Integer id){
        this.userRepository.deleteUser(id);
    }

    public User findUser(String str){
        return this.userRepository.findUser(str);
    }
    public User editUser(User user){
        return this.userRepository.editUser(user);
    }
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

}
