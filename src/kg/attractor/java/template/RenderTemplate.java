package kg.attractor.java.template;

import com.sun.net.httpserver.HttpExchange;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Map;

public class RenderTemplate {
    private static final Configuration config = new Configuration(Configuration.VERSION_2_3_29);

    static {
        try {
            Path templatesDirectory = Path.of("data/template");
            config.setDirectoryForTemplateLoading(templatesDirectory.toFile());
            config.setDefaultEncoding("UTF-8");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка настройки FreeMarker", e);
        }
    }

    public static void renderTemplate(HttpExchange exchange, String templateName, Map<String, Object> data) throws IOException {
        try {
            Template template = config.getTemplate(templateName);
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, 0);

            try (OutputStreamWriter writer = new OutputStreamWriter(exchange.getResponseBody(), StandardCharsets.UTF_8)) {
                template.process(data, writer);
            }
        } catch (TemplateException e) {
            e.printStackTrace();
            exchange.sendResponseHeaders(500, -1);
        }
    }
}
