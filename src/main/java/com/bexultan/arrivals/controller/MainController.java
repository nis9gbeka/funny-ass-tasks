package com.bexultan.arrivals.controller;

import com.bexultan.arrivals.domain.User;
import com.bexultan.arrivals.repository.ArrivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final ArrivalRepository arrivalRepository;

    @Autowired
    public MainController(ArrivalRepository arrivalRepository) {
        this.arrivalRepository = arrivalRepository;
    }


    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){

        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", user);
        data.put("arrivals", arrivalRepository.findAll());

        model.addAttribute("frontendData", data);
        return "index";
    }
}
