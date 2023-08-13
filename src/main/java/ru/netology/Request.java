package ru.netology;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.nio.charset.StandardCharsets;
import java.util.*;


class Request {
    private final String method;
    private final String path;
    private final Map<String, List<String>> queryParams;

    public Request(String method, String path, String queryString) {
        this.method = method;
        this.path = path;
        this.queryParams = parseQueryString(queryString);
    }

    private Map<String, List<String>> parseQueryString(String queryString) {
        List<NameValuePair> params = URLEncodedUtils.parse(queryString, StandardCharsets.UTF_8);
        Map<String, List<String>> queryParams = new HashMap<>();
        for (NameValuePair param : params) {
            queryParams.computeIfAbsent(param.getName(), k -> new ArrayList<>()).add(param.getValue());
        }
        return queryParams;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public List<String> getQueryParam(String name) {
        return queryParams.getOrDefault(name, Collections.emptyList());
    }

    public Map<String, List<String>> getQueryParams() {
        return queryParams;
    }
}
