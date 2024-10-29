package app.codePreufer.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConfigLoader {
    public String loadConfig(String filePath) {
        StringBuilder config = new StringBuilder();
        try (InputStream inputStream = getClass().getResourceAsStream("/" + filePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("Конфигурационный файл не найден: " + filePath);
            }
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    config.append(line).append(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла конфигурации: " + e.getMessage());
        }
        return String.valueOf(config);
    }
}
