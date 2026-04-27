package com.satoru.book.controller;

import com.satoru.book.model.dto.TokenDTO;
import com.satoru.book.model.dto.responseDto.TokenRespDTO;
import com.satoru.book.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tokens")
@CrossOrigin
public class TokenController {

    private final TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<List<TokenRespDTO>> getAllTokens() {
        List<TokenRespDTO> tokens = tokenService.getAllTokens();
        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TokenRespDTO> getTokenById(@PathVariable("id") Long tokenId) {
        TokenRespDTO tokenDTO = tokenService.getTokenById(tokenId);
        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TokenDTO> createToken(@Valid @RequestBody TokenDTO tokenDTO) {
        TokenDTO createdTokenDTO = tokenService.createToken(tokenDTO);
        return new ResponseEntity<>(createdTokenDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TokenDTO> updateToken(@PathVariable("id") Long tokenId, @Valid @RequestBody TokenDTO tokenDTO) {
        TokenDTO updatedTokenDTO = tokenService.updateToken(tokenId, tokenDTO);
        return new ResponseEntity<>(updatedTokenDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToken(@PathVariable("id") Long tokenId) {
        tokenService.deleteToken(tokenId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

