package backend.backend.presentation.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TryController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/try")
    public ResponseEntity<?> trys() {

        Map<String, String> map = new HashMap<>();
        map.put("olivia.hobberts@reiport-entreprise.trl", passwordEncoder.encode("YSJ]uu%%Ni[4p=5w"));
        map.put("david.shot@reiport-entreprise.trl", passwordEncoder.encode("C&H/+u]w,Pre6mt!"));
        map.put("steven.cooper@reiport-entreprise.trl", passwordEncoder.encode("$59rh3zz4TW7=w1["));
        map.put("cavas.callahan@reiport-entreprise.trl", passwordEncoder.encode("5ZNwai,EZE68pv&m"));
        map.put("karen.deadshot@reiport-entreprise.trl", passwordEncoder.encode("firekingforever"));

        return ResponseEntity.ok().body(map);
    }

}
