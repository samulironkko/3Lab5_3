package com.example.a3lab5_3;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Timer;

import static java.math.BigDecimal.*;

public class CalculatePi extends Thread {

  private static final BigDecimal TWO = new BigDecimal(2);
  private static final BigDecimal FOUR = new BigDecimal(4);
  final int SCALE = 50000;


  public interface MyInterface {
    void returnResult(BigDecimal result);
  }

  public CalculatePi(MyInterface MyInterface) {
    callBackInterface = MyInterface;
  }

  MyInterface callBackInterface = null;

  public void run() {
    long start = System.currentTimeMillis();
    long current = 0;
    BigDecimal a = ONE;
    BigDecimal b = ONE.divide(sqrt(TWO, SCALE, start), SCALE, ROUND_HALF_UP);
    BigDecimal t = new BigDecimal(0.25);
    BigDecimal x = ONE;
    BigDecimal y;

    while (!a.equals(b) && (current - start) < 10000) {
      y = a;
      a = a.add(b).divide(TWO, SCALE, ROUND_HALF_UP);
      b = sqrt(b.multiply(y), SCALE, start);
      t = t.subtract(x.multiply(y.subtract(a).multiply(y.subtract(a))));
      x = x.multiply(TWO);
      current = System.currentTimeMillis();
    }

    callBackInterface.returnResult(a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), SCALE, ROUND_HALF_UP));
   // return a.add(b).multiply(a.add(b)).divide(t.multiply(FOUR), SCALE, ROUND_HALF_UP);

  }

  public static BigDecimal sqrt(BigDecimal A, final int SCALE, long start) {
    BigDecimal x0 = new BigDecimal("0");
    BigDecimal x1 = new BigDecimal(Math.sqrt(A.doubleValue()));
    long current = System.currentTimeMillis();

    while (!x0.equals(x1) && (current - start) < 10000) {
      x0 = x1;
      x1 = A.divide(x0, SCALE, ROUND_HALF_UP);
      x1 = x1.add(x0);
      x1 = x1.divide(TWO, SCALE, ROUND_HALF_UP);
      current = System.currentTimeMillis();
    }

    return x1;
  }

}
