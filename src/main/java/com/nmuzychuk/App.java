package com.nmuzychuk;

import spark.ModelAndView;
import spark.template.thymeleaf.ThymeleafTemplateEngine;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(getPort());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("title", "Web Info");
            model.put("heading", "Web Info");
            model.put("property", "Property");
            model.put("value", "Value");

            Map<String, String> info = new LinkedHashMap<>();
            info.put("Host", req.host());
            info.put("IP", req.ip());
            info.put("Protocol", req.protocol());
            info.put("Accept-Language", req.headers("Accept-Language"));
            info.put("User-Agent", req.userAgent());

            model.put("info", info);

            return render(model, "index");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new ThymeleafTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static int getPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        String port = processBuilder.environment().get("PORT");

        if (port != null) {
            return Integer.parseInt(port);
        } else {
            return 4567;
        }
    }
}
