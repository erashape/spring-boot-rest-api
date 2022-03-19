package com.health.config.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Component
public class CustomServletWrappingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(new RequestWrapper(request), new ResponseWrapper(response), filterChain);
        }
    }

    protected void doFilterWrapped(RequestWrapper request, ContentCachingResponseWrapper response, FilterChain filterChain) throws ServletException, IOException {
        long start = System.currentTimeMillis();

        try {
            writeRequestLog(request);
            filterChain.doFilter(request, response);
        } finally {
            writeResponseLog(response, start);
            response.copyBodyToResponse();
        }
    }

    private static void writeRequestLog(RequestWrapper request) throws IOException {
        String query = request.getQueryString();
        String content = getContents(request.getInputStream());

        log.info("[Request]: Uri:[{}] - Method/ContentType:[{}/{}]",
                StringUtils.hasText(query) ? request.getRequestURI() + query : request.getRequestURI(),
                request.getMethod(),
                request.getContentType()
        );

        if (StringUtils.hasText(content)) {
            log.info("{}", content);
        }
    }

    private static void writeResponseLog(ContentCachingResponseWrapper response, long startTime) throws IOException {
        String content = getContents(response.getContentInputStream());

        if (StringUtils.hasText(content)) {
            log.info("[Response/EndTime:{}]: {}",
                    (System.currentTimeMillis() - startTime) / 1000.0,
                    content);
        }
    }

    private static String getContents(InputStream inputStream) throws IOException {
        byte[] content = StreamUtils.copyToByteArray(inputStream);

        if (content.length > 0) {
            return new String(content);
        }

        return "";
    }
}
