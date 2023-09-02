package com.shishkin.config.jwt;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;


@AllArgsConstructor
@Slf4j
// Фильтр, пропускающий через себя каждый запрос
public class JwtTokenFilter extends OncePerRequestFilter {
    // Генерация JWT
    private JwtTokenProvider tokenProvider;

    // Метод, реализующий работу фильтра
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtTokenFilter : doFilterInternal");
        // Получение токена из заголовка
        String token = request.getHeader("Authorization");
        // Если токен не пустой, то выполняем авторизацию
        if (token != null) {
            try {
                // Получение составляющих используемых для генерации токена
                Claims claims = tokenProvider.getClaimsFromToken(token);
                // Проверка, что токен не просрочен
                if (!claims.getExpiration().before(new Date())) {
                    Authentication authentication = tokenProvider.getAuthentication(claims.getSubject());

                    if (authentication.isAuthenticated()) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            } catch (RuntimeException e) {
                // Обработка исключения, которое возникает, если токен не прошел проверку
                try {
                    // Очистка контекста и формирование ответа с ошибкой
                    SecurityContextHolder.clearContext();
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().println(new JSONObject().put("exception", "expired or invalid JWT token " + e.getMessage()));
                } catch (IOException | JSONException e1) {
                    e1.printStackTrace();
                }
                return;
            }
        } else {
            log.info("first time so creating token using UserResourceImpl - authenticate method");
        }
        filterChain.doFilter(request, response);
    }
}
