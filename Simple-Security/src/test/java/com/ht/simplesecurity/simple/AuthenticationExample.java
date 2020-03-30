package com.ht.simplesecurity.simple;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author mbql
 * @date 2020/3/30 16:10
 */
public class AuthenticationExample {

    /**
     * 1、获取用户名和密码，并将其组合到的一个实例UsernamePasswordAuthenticationToken（Authentication我们在前面看到的接口的实例）中。
     * 2、令牌会传递到的实例AuthenticationManager进行验证。
     * 3、成功进行身份验证后， 将AuthenticationManager返回完全填充的Authentication实例。
     * 4、通过调用SecurityContextHolder.getContext().setAuthentication(…​)并传入返回的身份验证对象来建立安全上下文。
     * 5、在这里，我们编写了一个小程序，要求用户输入用户名和密码并执行上述顺序。
     *    在AuthenticationManager我们已经在这里实现将验证其用户名和密码是相同的任何用户。它为每个用户分配一个角色。
     */
    private static AuthenticationManager am = new SampleAuthenticationManager();
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("Please enter your username:");
            String name = in.readLine();
            System.out.println("Please enter your password:");
            String password = in.readLine();
            try {
                Authentication request = new UsernamePasswordAuthenticationToken(name, password);
                Authentication result = am.authenticate(request);
                SecurityContextHolder.getContext().setAuthentication(result);
                break;
            } catch(AuthenticationException e) {
                System.out.println("Authentication failed: " + e.getMessage());
            }
        }
        System.out.println("Successfully authenticated. Security context contains: " +
                SecurityContextHolder.getContext().getAuthentication());
    }
}
