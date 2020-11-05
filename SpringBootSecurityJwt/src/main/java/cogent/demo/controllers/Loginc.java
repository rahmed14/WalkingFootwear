package cogent.demo.controllers;

import java.security.Principal;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cogent.demo.models.User;


@RestController
@CrossOrigin
public class Loginc {
	@RequestMapping("/login")
    public boolean login(@RequestBody User user) {
        return user.getUsername().equals("user") && user.getPassword().equals("password");
    }
	
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
          .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
          .decode(authToken)).split(":")[0];
    }

}
