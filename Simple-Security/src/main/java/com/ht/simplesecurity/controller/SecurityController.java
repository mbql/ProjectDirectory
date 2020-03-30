package com.ht.simplesecurity.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mbql
 * @date 2020/3/30 15:31
 */
@RestController
public class SecurityController {

  @GetMapping("hello")
  public String hello(){
      System.out.println( SecurityContextHolder.getContext().getAuthentication().getCredentials());
      return "hello mbql !!!";
  }
}
