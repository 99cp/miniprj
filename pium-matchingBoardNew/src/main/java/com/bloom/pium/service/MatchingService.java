package com.bloom.pium.service;


import com.bloom.pium.data.dto.MatchingDto;
import com.bloom.pium.data.dto.MatchingResponseDto;
import com.bloom.pium.data.entity.Matching;

import java.util.List;

public interface MatchingService {

    // 매칭 게시글 작성
    MatchingResponseDto saveMatching(MatchingDto matchingDto);

    List<Matching> getMatchingList();

    void updateParticipation(long matchingId, boolean participate);

//    // 매칭 게시글 삭제
//    void deleteMatching (Long matchingId) throws Exception;

}
