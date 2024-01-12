package com.example.springdemo.util;

import com.example.springdemo.inter.RedisKey;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Author jalin
 * @Description 验证码
 * @Date 2020/6/11 19:54
 */
@Component
public class CodeUtil {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    DefaultKaptcha captchaProducer;

    /**
     * 获取会议码
     *
     * @param
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void getCode(String codeId, HttpServletResponse response) {
        // 清除浏览器的缓存
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpg");
        // 浏览器记忆功能-----当前过浏览器和服务器交互成功以后下载的图片和资源会进行缓存一次。下次刷新的时候就不会在到服务器去下载。
        // 获取KAPTCHA验证的随机文本
        String capText = captchaProducer.createText();
        // 将生成好的图片放入缓存
        redisUtil.set(RedisKey.AUTHORIZATION_CODE_KEY + codeId, capText, 120L);
        // create the image with the text
        BufferedImage bi = captchaProducer.createImage(capText);
        // 将图像输出到Servlet输出流中。
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            // write the data out
            ImageIO.write(bi, "jpg", out);
            out.flush();
            out.close();//关闭
        } catch (Exception e) {

        }
    }

    /**
     * 校验验证码
     *
     * @param codeId
     * @param code
     * @return
     */
/*    public BaseResult authorityCode(String codeId, String code) {
        String sessionCode = (String) redisUtil.get(RedisKey.AUTHORIZATION_CODE_KEY + codeId);
        if (sessionCode == null) {
            //验证码失效
            return BaseResult.failed(ResponseCodeEnum.AUTHORIZATION_CODE_NO_EXIST);
        }
        if (StringUtils.isNotBlank(code) && code.equalsIgnoreCase(sessionCode)) {
            return null;
        } else {
            //验证码不正确
            return BaseResult.failed(ResponseCodeEnum.AUTHORIZATION_CODE_ERROR);
        }
    }*/
}
