package tot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tot.util.MemberUtil;

@RestController
@RequestMapping("/member")
public class MemberController {

	@GetMapping("/checkLogin")
	public ResponseEntity<Map<String, Boolean>> checkLoginStatus() {
		boolean loggedIn = MemberUtil.isMemberLoggedIn();

		Map<String, Boolean> response = new HashMap<>();
		response.put("loggedIn", loggedIn);

		return ResponseEntity.ok(response);
	}

}
