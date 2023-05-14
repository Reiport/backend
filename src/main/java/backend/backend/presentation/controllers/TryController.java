package backend.backend.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TryController {

    @GetMapping("/try")
    public String trys() {
        return "invoiceTemplate";
    }

}
