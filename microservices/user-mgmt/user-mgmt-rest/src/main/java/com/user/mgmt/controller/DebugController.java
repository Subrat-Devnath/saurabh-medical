// ...existing code...
package com.user.mgmt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/debug", produces = MediaType.APPLICATION_JSON_VALUE)
public class DebugController {

    @PostMapping("/headers")
    public Map<String, Object> echoHeadersAndBody(HttpServletRequest request) throws IOException {
        Map<String, Object> result = new HashMap<>();

        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                headers.put(name, request.getHeader(name));
            }
        }

        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line).append('\n');
            }
        }

        result.put("headers", headers);
        result.put("body", body.length() > 0 ? body.toString().trim() : "");
        return Collections.unmodifiableMap(result);
    }
}
// ...existing code...
