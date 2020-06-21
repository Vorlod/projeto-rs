package br.com.validator.infra.exception;

import br.com.validator.infra.dto.SimpleMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class AcessDeniedException extends RuntimeException implements AccessDeniedHandler {
    private final Logger log = LoggerFactory.getLogger(AcessDeniedException.class);
    private String code;
    private Object[] params;

    public AcessDeniedException() {
    }

    public String getCode() {
        return this.code;
    }

    public Object[] getParams() {
        return this.params;
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String msg = "Acesso negado.";
        String urlRequisicao = request.getRequestURI();
        if (Objects.nonNull(auth)) {
            this.log.info("=============== ACESSO NEGADO NA REQUISICAO ===============");
            this.log.info("     Usuario : {} tentou acessar a URL {}", auth.getName(), urlRequisicao);
            this.log.info("===========================================================");
        } else {
            this.log.info("=============== ACESSO NEGADO NA REQUISICAO ===============");
            this.log.info("     Requisicao sem autenticacao na URL: {}", urlRequisicao);
            this.log.info("===========================================================");
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(401);
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(new SimpleMessage("ERRO", msg));
        out.print(jsonString);
        out.flush();
    }
}
