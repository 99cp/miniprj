package com.bloom.pium.service.impl;


import com.bloom.pium.data.dto.MatchingDto;
import com.bloom.pium.data.dto.MatchingResponseDto;
import com.bloom.pium.data.entity.Board;
import com.bloom.pium.data.entity.Matching;
import com.bloom.pium.data.entity.UserInfo;
import com.bloom.pium.data.repository.MatchingRepository;
import com.bloom.pium.service.MatchingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MatchingServiceImpl implements MatchingService {
    private final Logger LOGGER = LoggerFactory.getLogger(MatchingServiceImpl.class);

    private MatchingRepository matchingRepository;

    @Autowired
    public MatchingServiceImpl(MatchingRepository matchingRepository){
        this.matchingRepository = matchingRepository;
    }


    @Override
    public MatchingResponseDto saveMatching(MatchingDto matchingDto) {
        Matching matching = new Matching();

        matching.setTitle(matchingDto.getTitle());
        matching.setComment(matchingDto.getComment());

        Matching savedMatching = matchingRepository.save(matching);

        MatchingResponseDto matchingResponseDto = new MatchingResponseDto();
        matchingResponseDto.setMatchingId(savedMatching.getMatchingId());
        matchingResponseDto.setTitle(savedMatching.getTitle());
        matchingResponseDto.setComment(savedMatching.getComment());

        matchingResponseDto.setUserId(savedMatching.getUserId());
        matchingResponseDto.setBoardId(savedMatching.getBoardId());

        return matchingResponseDto;
    }

    @Override
    public List<Matching> getMatchingList() {
        return matchingRepository.findAll();
    }

    @Override
    public void updateParticipation(long matchingId, boolean participate) {
        // MatchingId에 해당하는 엔티티의 참가 상태 업데이트
        Matching matching = matchingRepository.findById(matchingId)
                .orElseThrow(() -> new RuntimeException("Matching not found with id: " + matchingId));
        matching.setParticipate(participate);
        matchingRepository.save(matching);
    }


}
