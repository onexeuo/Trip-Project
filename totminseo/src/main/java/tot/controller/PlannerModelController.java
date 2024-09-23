package tot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/planner")
public class PlannerModelController {

	
	
    @GetMapping
    public String showRecommendationForm() {
        return "planner"; // JSP 페이지 반환
    }

    @GetMapping("/data")
    @ResponseBody
    public Map<String, Object> getRecommendationData(HttpSession session) {
        return (Map<String, Object>) session.getAttribute("recommendationData");
    }
    
    @GetMapping("/chatdata")
    @ResponseBody
    public Map<String, Object> getRecommendationChatData(HttpSession session) {
        Object recommendationContent = session.getAttribute("recommendationContent");
        if (recommendationContent != null) {
            // 이미 JSON 객체로 저장된 경우, 그대로 반환
            return Map.of("content", recommendationContent);
        } else {
            return Map.of("content", "No data found");
        }
    }
    
    
}
