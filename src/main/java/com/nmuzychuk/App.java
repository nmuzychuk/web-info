package com.nmuzychuk;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("greeting", "Web Info");
            model.put("ip", req.ip());
            model.put("userAgent", req.userAgent());

            return render(model, "index");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
