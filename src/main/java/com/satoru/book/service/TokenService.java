package com.satoru.book.service;

import com.satoru.book.model.dto.TokenDTO;

import java.util.List;

public interface TokenService {
    List<TokenDTO> getAllTokens();
    TokenDTO getTokenById(Long tokenId);
    TokenDTO createToken(TokenDTO tokenDTO);
    TokenDTO updateToken(Long tokenId, TokenDTO tokenDTO);
    void deleteToken(Long tokenId);
}