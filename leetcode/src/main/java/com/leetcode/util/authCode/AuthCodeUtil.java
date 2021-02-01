package com.leetcode.util.authCode;


import com.leetcode.util.mail.MailUtilImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author Jarvan
 * @version 1.0
 * @create 2020/12/2 17:27
 */
@Service
@Slf4j
public class AuthCodeUtil {
    @Autowired
    MailUtilImpl mailUtil;


    @Async
    public void  sendAuthCode(String toUser,String authCode){

        //这里我们要获得验证码还要储存到redis.
        boolean b = mailUtil.sendAttachmentsMail(toUser, subject, contentLeft + authCode + contentRight, null);
        if (b){
            log.info("=======mail发送成功=======");
        }else {
            log.error("=====发送失败"+toUser+"======");
        }
    }
    private String subject = "[LeetCode]账号身份验证";
    private String contentLeft = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>方塘云</title>\n" +
            "</head>\n" +
            "\n" +
            "<body>\n" +
            "    <div id=\"mailContentContainer\" class=\"qmbox qm_con_body_content qqmail_webmail_only\" style=\"\">\n" +
            "        <div style=\"background:#f5f5f5;padding:48px 0;\">\n" +
            "            <div style=\"width:665px;margin:0 auto;border:1px solid #dcdcdc;background:#ffffff\">\n" +
            "                <h2\n" +
            "                    style=\"height:56px;line-height:56px;margin:0;color:#ffffff;font-size:20px;background:#40caba;padding-left:30px;font-weight:normal\">\n" +
            "                    帐号</h2>\n" +
            "                <div style=\"padding:50px 0;margin:0 30px;font-size:13px;border-bottom:1px solid #ebebeb;\">\n" +
            "                    <h3 style=\"color:#000000;font-size:15px;margin:0;margin-bottom:4px;\">亲爱的用户：</h3>您正在验证身份，验证码是：<b\n" +
            "                        style=\"font-size:26px;color:#40caba;margin-bottom:20px;display:block;margin-top:10px;\">";
    private String contentRight = "</b>,5分钟内有效，为了您的帐号安全，请勿泄露给他人。\n" +
            "                </div>\n" +
            "                <div style=\"color:#898989;font-size:10px;background:#fcfcfc;padding:18px 30px;\">本邮件由系统自动发出，请勿直接回复。 谢谢！\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "        <style type=\"text/css\">\n" +
            "            .qmbox style,\n" +
            "            .qmbox script,\n" +
            "            .qmbox head,\n" +
            "            .qmbox link,\n" +
            "            .qmbox meta {\n" +
            "                display: none !important;\n" +
            "            }\n" +
            "        </style>\n" +
            "    </div>\n" +
            "</body>\n" +
            "\n" +
            "</html>\n";

}
