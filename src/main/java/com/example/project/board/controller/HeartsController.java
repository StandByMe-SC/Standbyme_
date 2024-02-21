package com.example.project.board.controller;

import com.example.project.board.dto.HeartsResponseDto;
import com.example.project.board.service.HeartsService;
import com.example.project.member.domain.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "좋아요 API", description = "Swagger 좋아요 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/hearts")
public class HeartsController {
    private final HeartsService heartsService;


    @Operation(summary = "좋아요 등록", description = "좋아요 등록 합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "등록 성공", content = @Content(schema = @Schema(implementation = HeartsResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    }) // 좋아요 스웨거 연동 - 김예은
    // CREATE : 좋아요 누르기
    @PostMapping("/{boardId}")
    public ResponseEntity<String> addHearts(@AuthenticationPrincipal Member member, @PathVariable Long boardId){
        boolean result = false;

        if(member != null){
            result = heartsService.addHearts(member, boardId);
        }
        return result ?
                new ResponseEntity<>("좋아요 추가", HttpStatus.OK)
                :new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }


    @Operation(summary = "좋아요 삭제", description = "좋아요 삭제 합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "삭제 성공", content = @Content(schema = @Schema(implementation = HeartsResponseDto.class))),
            @ApiResponse(responseCode = "401", description = "인증실패", content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN"))),
    })
    // DELETE : 좋아요 삭제하기
    @DeleteMapping("/{boardId}")
    public ResponseEntity<String> deleteHearts(@AuthenticationPrincipal Member member, @PathVariable Long boardId){
        boolean result = false;

        if(member != null){
            result = heartsService.deleteHearts(member, boardId);
        }
        return result ?
                new ResponseEntity<>("좋아요 삭제", HttpStatus.OK)
                :new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }
}
