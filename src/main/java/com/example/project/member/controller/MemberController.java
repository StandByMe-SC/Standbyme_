package com.example.project.member.controller;

import com.example.project.auth.application.MemberDetailService;
import com.example.project.member.domain.Member;
import com.example.project.member.dto.UserInfo;
import com.example.project.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
@Tag(name = "유저(맴버) API", description = "Swagger 유저(맴버) API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {
    @Autowired
    private MemberDetailService memberDetailService;
    // 되는 거 확인했다.
    // 프론트에서 로그인 된 유저 ID or Email을 넘겨줄 수 있나?
    // 이거만 되면 ㄹㅇ 할 수 있을 것 같은데
    @GetMapping("/user")
    public String userName(@AuthenticationPrincipal Member member){ // @AuthenticationalPrincipal = 객체를 인자로 받을 수 있게 해주는 어노테이션
        String userName = member.getEmail();
        return userName;
    }

    @Operation(summary = "로그인 성공", description = "로그인 시에 불러올 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/details")
    public UserInfo getUserInfo(@AuthenticationPrincipal Member member){
        Long userId = member.getId();
        return memberDetailService.loadUserByUserId(userId);
    }

    @Operation(summary = "로그인 성공", description = "로그인 시에 불러올 api")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @PostMapping("/info")
    public UserInfo postUserInfo(@AuthenticationPrincipal Member member){
        Long userId = member.getId();
        return memberDetailService.loadUserByUserId(userId);
    }
}