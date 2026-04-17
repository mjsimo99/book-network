package com.satoru.book.service;

import com.satoru.book.model.dto.TokenDTO;
import com.satoru.book.model.dto.responseDto.TokenRespDTO;

import java.util.List;

public interface TokenService {
    List<TokenRespDTO> getAllTokens();
    TokenRespDTO getTokenById(Long tokenId);
    TokenDTO createToken(TokenDTO tokenDTO);
    TokenDTO updateToken(Long tokenId, TokenDTO tokenDTO);
    void deleteToken(Long tokenId);
}