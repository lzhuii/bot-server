package bot.service;

import bot.dto.Verify;
import bot.dto.request.VerifyRequest;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.crypto.params.Ed25519PrivateKeyParameters;
import org.bouncycastle.crypto.signers.Ed25519Signer;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author hui
 * @since 2025-01-15
 */
@Slf4j
@Service
public class VerifyService {
    @Value("${bot.app-secret}")
    private String appSecret;

    public Verify verify(VerifyRequest request) {
        // 获取必要的参数
        String plainToken = request.plainToken();
        Ed25519Signer signer = getEd25519Signer(request, plainToken);
        // 生成签名并转换为十六进制格式的字符串
        byte[] signatureBytes = signer.generateSignature();
        String signature = Hex.toHexString(signatureBytes);
        log.info("生成签名: {}", signature);
        // 构建并返回验证响应
        Verify verify = new Verify(plainToken, signature);
        log.info("返回响应: {}", verify);
        return verify;
    }

    private Ed25519Signer getEd25519Signer(VerifyRequest verifyRequest, String plainToken) {
        String eventTs = verifyRequest.eventTs();
        // 构建用于生成种子字节的字符串
        byte[] seedBytes = getSeedBytes();
        // 初始化私钥
        Ed25519PrivateKeyParameters privateKey = new Ed25519PrivateKeyParameters(seedBytes, 0);
        // 构建消息字符串并转换为字节数组
        String message = eventTs + plainToken;
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        // 初始化签名器并更新消息
        Ed25519Signer signer = new Ed25519Signer();
        signer.init(true, privateKey);
        signer.update(messageBytes, 0, messageBytes.length);
        return signer;
    }

    private byte[] getSeedBytes() {
        StringBuilder seedBuilder = new StringBuilder(appSecret);
        while (seedBuilder.length() < 32) {
            seedBuilder.append(seedBuilder);
        }
        return seedBuilder.substring(0, 32).getBytes(StandardCharsets.UTF_8);
    }
}
