package com.roller.doc.api.service.auth;

import com.roller.doc.db.entity.Role;
import com.roller.doc.db.entity.User;
import com.roller.doc.db.repository.RedisRepository;
import com.roller.doc.db.repository.UserRepository;
import com.roller.doc.util.CookieUtil;
import com.roller.doc.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final TokenService tokenservice;
    private final UserRepository userRepository;
    private final RedisUtil redisUtil;
    private final CookieUtil cookieUtil;
    private final RedisRepository redisRepository;
    @Value("${redirect.url}")
    private String redirectUrl;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        log.info("Principal에서 꺼낸 OAuth2User = {} ", oAuth2User);
        log.info("DB 등록 확인");

        User member = userRepository.findByUserEmail((String) oAuth2User.getAttribute("email"));
        if (oAuth2User != null && member == null) {
            User memberData = createMember(oAuth2User);
            if (memberData == null) {
                log.info("회원 가입 실패");
            } else {
                log.info("유저를 찾을 수 없습니다. 유저 정보를 등록합니다.");
            }
        }

        String nickname = userRepository.findByUserEmail((String) oAuth2User.getAttribute("email")).getUserName();
        log.debug("nickname = {}", nickname);


        if (redisUtil.getData((String) oAuth2User.getAttribute("email")) != null) {
            log.info("refresh token exists.Remove refresh token");
            redisRepository.deleteById((String) oAuth2User.getAttribute("email"));
        }


        String refreshtoken = tokenservice.generateToken((String) oAuth2User.getAttribute("email"), Role.MEMBER.toString(), nickname, "REFRESH");
        redisUtil.setDataExpire((String) oAuth2User.getAttribute("email"), refreshtoken, TokenService.refreshPeriod);


        ResponseCookie cookie = cookieUtil.getCookie(refreshtoken, TokenService.refreshPeriod);
        String accesstoken = tokenservice.generateToken(oAuth2User.getAttribute("email"), Role.MEMBER.toString(), nickname, "ACCESS");

        log.info("accecss_Token = {}", accesstoken);
        log.info("refresh_Token = {}", refreshtoken);
        response.setContentType("application/json;charset=UTF-8");
        response.setHeader("Set-Cookie", cookie.toString());

        log.debug("메인으로");
        getRedirectStrategy().sendRedirect(request, response, UriComponentsBuilder.fromUriString(redirectUrl + "/redirect")
                .queryParam("accesstoken", accesstoken)
                .build().toUriString());

    }

    public User createMember(OAuth2User oAuth2User) {
        return userRepository.saveAndFlush(User.builder()
                .userEmail((String) oAuth2User.getAttribute("email"))
                .userDeleted(false)
                .userName((String) oAuth2User.getAttribute("nickname"))
                .userRole(Role.MEMBER)
                .build());
    }
}
