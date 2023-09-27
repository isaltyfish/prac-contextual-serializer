package net.verytools.prac.praccontextualserializer;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/userInfo")
    public User userInfo() {
        User user = new User();
        user.setMobile("18656153972");
        user.setMobile2("18656153972");
        return user;
    }

}
