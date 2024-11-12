package com.techgatha.gateway.GatewayService.filter;

import com.techgatha.gateway.GatewayService.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RouteValidator validator;

    public AuthenticationFilter(){
        super(Config.class);
    }

    public static class Config{

    }
    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("Auth filter");
        return  ((exchange, chain) -> {
            ServerHttpRequest request = null;
            if(validator.isSecured.test(exchange.getRequest())){
                // check if it contains token or not
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Missing auth header");
                }
                String authHeaderValue = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                System.out.println(authHeaderValue);
                String jwtToken = null;
                if (authHeaderValue != null && authHeaderValue.startsWith("Bearer")){
                    jwtToken = authHeaderValue.substring(7);
                    System.out.println(jwtToken);
                }
                try{
                    jwtUtil.validateToken(jwtToken);
                    request = exchange.getRequest().mutate()
                           // .header("username",jwtUtil.extractUsername(jwtToken))
                            .header("token",jwtToken)
                            .build();
                    System.out.println(jwtUtil.extractUsername(jwtToken));
                }
                catch (Exception e){
                    throw new RuntimeException("Invalid Access");
                }
            }
            return chain.filter(exchange.mutate().request(request).build());
        });

    }
}
