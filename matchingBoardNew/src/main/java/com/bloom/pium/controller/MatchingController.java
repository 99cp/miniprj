package com.bloom.pium.controller;

import com.bloom.pium.data.dto.MatchingDto;
import com.bloom.pium.data.dto.MatchingResponseDto;
import com.bloom.pium.data.entity.Matching;
import com.bloom.pium.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/matching")
public class MatchingController {
    private final MatchingService matchingService;

    @Autowired
    public MatchingController(MatchingService matchingService){
        this.matchingService = matchingService;
    }

    @GetMapping("/form")
    public String showMatchingForm(Model model) {
        model.addAttribute("matchingDto", new MatchingDto());
        return "MatchingForm";
    }
    @PostMapping("/write")
    public String createMatching(@ModelAttribute("matchingDto") MatchingDto matchingDto, Model model){
        MatchingResponseDto matchingResponseDto = matchingService.saveMatching(matchingDto);

        model.addAttribute("matchingResponseDto", matchingResponseDto);
        return "redirect:/matching/list";
    }
    @GetMapping("/list")
    public String listMatching(Model model) {
        List<Matching> matchingList = matchingService.getMatchingList();
        model.addAttribute("matchingList", matchingList);
        return "matching-list";
    }
    // "참가" 버튼 클릭 시 호출되는 엔드포인트
    @PostMapping("/participate/{matchingId}")
    public String participateEvent(@PathVariable Long matchingId) {
        matchingService.toggleParticipation(matchingId);
        return "redirect:/matching/list";
    }
    @PostMapping("/delete/{matchingId}")
    public String deleteMatching(@PathVariable Long matchingId) {
        matchingService.deleteMatching(matchingId);
        return "redirect:/matching/list";
    }

    @PostMapping("/matching/toggle-deadline/{matchingId}")
    public String toggleDeadline(@PathVariable Long matchingId) {
        matchingService.toggleDeadline(matchingId);
        return "redirect:/matching-list"; // 변경 후 리다이렉트할 URL을 지정합니다.
    }

}