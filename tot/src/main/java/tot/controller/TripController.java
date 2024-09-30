package tot.controller;

import static tot.common.Constants.PAGE_TRIP;
import static tot.common.Constants.PAGE_TRIPLIST;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tot.domain.MemberVO;
import tot.domain.TripDTO;
import tot.service.TripService;
import tot.util.MemberUtil;

@Controller
@RequestMapping("/trip")
public class TripController {

	private TripService tripService;

	public TripController(TripService tripService) {
		this.tripService = tripService;
	}

	// 여행 목록 화면 호출
	@GetMapping("/list")
	public String getTripList(Model model) {
		MemberVO member = MemberUtil.isAuthenticatedMember();

		List<TripDTO> trips = tripService.getTripsByMemId(member.getMemId());

		model.addAttribute("trips", trips);
		return PAGE_TRIPLIST;
	}

	@GetMapping
	public String getTripMap(@RequestParam(value = "tripId") String tripId, Model model) {
		MemberVO member = MemberUtil.isAuthenticatedMember();

		model.addAttribute("tripId", tripId);
		model.addAttribute("member", member);
		return PAGE_TRIP;
	}

}
