package bot.controller;

import bot.dto.Payload;
import bot.dto.request.VerifyRequest;
import bot.enums.OpCode;
import bot.service.DispatchService;
import bot.service.VerifyService;
import bot.util.JsonUtil;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * webhook 控制器
 *
 * @author hui
 * @since 1.0.0
 */
@Slf4j
@RestController
public class WebhookController {
    private final JsonUtil jsonUtil;
    private final VerifyService verifyService;
    private final DispatchService dispatchService;

    public WebhookController(
            JsonUtil jsonUtil,
            VerifyService verifyService,
            DispatchService dispatchService
    ) {
        this.jsonUtil = jsonUtil;
        this.verifyService = verifyService;
        this.dispatchService = dispatchService;
    }

    @PostMapping("/webhook")
    public Object webhook(@RequestBody Payload<JsonNode> payload) {
        log.info("收到Webhook请求 {}", payload);
        OpCode opCode = OpCode.of(payload.op());
        if (opCode == OpCode.VERIFY) {
            // 回调验证
            VerifyRequest request = jsonUtil.readValue(payload.d().toString(), VerifyRequest.class);
            return verifyService.verify(request);
        } else if (opCode == OpCode.DISPATCH) {
            // 消息分发
            dispatchService.dispatch(payload);
        }
        return Void.TYPE;
    }
}
