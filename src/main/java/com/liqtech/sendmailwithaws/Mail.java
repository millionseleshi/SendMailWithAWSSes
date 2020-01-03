package com.liqtech.sendmailwithaws;

import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Mail {

  private String from;
  private String to;
  private String subject;
  private Map<String, Object> model;
}
