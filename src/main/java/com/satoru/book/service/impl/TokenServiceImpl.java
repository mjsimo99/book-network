package com.satoru.book.service.impl;

import com.satoru.book.model.dto.TokenDTO;
import com.satoru.book.model.dto.responseDto.TokenRespDTO;
import com.satoru.book.model.entity.Token;
import com.satoru.book.repository.TokenRepository;
import com.satoru.book.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository, ModelMapper modelMapper) {
        this.tokenRepository = tokenRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TokenRespDTO> getAllTokens() {
        try {
            List<Token> tokens = tokenRepository.findAll();
            return tokens.stream()
                    .map(token -> modelMapper.map(token, TokenRespDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch all tokens: " + e.getMessage());
        }
    }

    @Override
    public TokenRespDTO getTokenById(Long tokenId) {
        try {
            Token token = tokenRepository.findById(tokenId)
                    .orElseThrow(() -> new NotFoundException("Token not found with ID: " + tokenId));
            return modelMapper.map(token, TokenRespDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch token with ID " + tokenId + ": " + e.getMessage());
        }
    }

    @Override
    public TokenDTO createToken(TokenDTO tokenDTO) {
        try {
            Token token = modelMapper.map(tokenDTO, Token.class);
            token = tokenRepository.save(token);
            return modelMapper.map(token, TokenDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create token: " + e.getMessage());
        }
    }

    @Override
    public TokenDTO updateToken(Long tokenId, TokenDTO tokenDTO) {
        try {
            Token existingToken = tokenRepository.findById(tokenId)
                    .orElseThrow(() -> new NotFoundException("Token not found with ID: " + tokenId));
            modelMapper.map(tokenDTO, existingToken);
            existingToken.setTokenId(tokenId);
            existingToken = tokenRepository.save(existingToken);
            return modelMapper.map(existingToken, TokenDTO.class);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to update token with ID " + tokenId + ": " + e.getMessage());
        }
    }

    @Override
    public void deleteToken(Long tokenId) {
        try {
            if (!tokenRepository.existsById(tokenId)) {
                throw new NotFoundException("Token not found with ID: " + tokenId);
            }
            tokenRepository.deleteById(tokenId);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete token with ID " + tokenId);
        }
    }
}