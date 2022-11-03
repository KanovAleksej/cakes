package com.example.frontend.controller;

import com.example.frontend.dto.CakeDto;
import com.example.frontend.service.CakeClientRestService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class CakeController {

    private final CakeClientRestService cakeRestService;

    public CakeController(CakeClientRestService cakeRestService) {
        this.cakeRestService = cakeRestService;
    }

    @GetMapping
    public String main(Model model){
        List<CakeDto> cakeDtos = cakeRestService.getAllCakes();
        CakeDto cakeDto = new CakeDto();
        model.addAttribute("cakes", cakeDtos);
        model.addAttribute("cakeDto", cakeDto);
        return "index";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("cakeDto") CakeDto cakeDto){
        cakeRestService.addNewCake(cakeDto);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete (@PathVariable Long id){
        cakeRestService.deleteCake(id);
        return "redirect:/";
    }
    @PostMapping("/update/{id}")
    public String edit(@PathVariable Long id, @ModelAttribute("cakeDto") CakeDto cakeDto){
        System.out.println(cakeDto);
        cakeRestService.updateCake(cakeDto, id);
        return "redirect:/";
    }
}

