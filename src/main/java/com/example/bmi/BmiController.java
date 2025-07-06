package com.example.bmi;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BmiController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("bmiForm", new BmiForm());
        return "form";
    }

    @PostMapping("/result")
    public String calculateBmi(@ModelAttribute BmiForm bmiForm, Model model) {
        double heightInMeters = bmiForm.getHeight() / 100.0;
        double bmi = bmiForm.getWeight() / (heightInMeters * heightInMeters);

        String category;
        if (bmi < 18.5) category = "Niedowaga";
        else if (bmi < 25) category = "Waga prawidłowa";
        else if (bmi < 30) category = "Nadwaga";
        else category = "Otyłość";

        model.addAttribute("bmi", String.format("%.2f", bmi));
        model.addAttribute("category", category);
        return "result";
    }
}
