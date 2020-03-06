package com.storicard.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Edge implements Serializable {

  private static final long serialVersionUID = 1L;
  private int x;
  private int h;
  private boolean isLeft;

}
