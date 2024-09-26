package tot.controller;

import static tot.common.Constants.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/trip")
public class TripController {

	// 여행 코스 추천 경로 화면 호출
	@GetMapping
	public String getTripMap() {
		return PAGE_TRIP;
	}

}
