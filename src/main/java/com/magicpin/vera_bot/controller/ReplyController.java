package com.magicpin.vera_bot.controller;

import com.magicpin.vera_bot.dto.ReplyRequest;
import com.magicpin.vera_bot.dto.ReplyResponse;
import com.magicpin.vera_bot.services.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class ReplyController {

    private final ReplyService replyService;

    public ReplyController(ReplyService replyService){
        this.replyService=replyService;
    }

    @PostMapping("/reply")
    public ReplyResponse reply(@RequestBody ReplyRequest request) {
        return replyService.reply(request);
    }
}