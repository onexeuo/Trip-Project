package tot.admin.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/key")
public class AdminKeyController {

	private static final String OPENAI_API_URL = "https://api.openai.com/v1/chat/completions"; // OpenAI API URL
	private static final String OPENAI_API_KEY = "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"; // OpenAI
																																															// API

	@GetMapping
	public String checkOpenAiApiKeyStatus(Model model) {
		String apiStatus = checkOpenAiApiKey();
		model.addAttribute("apiStatus", apiStatus);
		return "adminKey";
	}

	private String checkOpenAiApiKey() {
		try {
			URL url = new URL(OPENAI_API_URL);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Authorization", "Bearer " + OPENAI_API_KEY);
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setDoOutput(true);
			String jsonBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"Hello\"}]}";
			connection.getOutputStream().write(jsonBody.getBytes("UTF-8"));

			int responseCode = connection.getResponseCode();
			if (responseCode == 200) {
				return "OpenAI 키 상태: 정상";
			} else {
				return "OpenAI 키 상태: 오류";
			}
		} catch (IOException e) {
			return "OpenAI 키 상태: 오류";
		}
	}
}
