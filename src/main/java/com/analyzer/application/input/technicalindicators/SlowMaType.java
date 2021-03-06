package com.analyzer.application.input.technicalindicators;

import com.analyzer.application.input.ApiParameter;
public enum SlowMaType implements ApiParameter {
  SMA(0),
  EMA(1),
  WMA(2),
  DEMA(3),
  TEMA(4),
  TRIMA(5),
  T3(6),
  KAMA(7),
  MAMA(8);

  private int type;

  SlowMaType(int type) {
    this.type = type;
  }

  @Override
  public String getKey() {
    return "slowmatype";
  }

  @Override
  public String getValue() {
    return String.valueOf(type);
  }
}
