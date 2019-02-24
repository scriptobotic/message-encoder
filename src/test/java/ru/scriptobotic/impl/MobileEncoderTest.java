package ru.scriptobotic.impl;

import org.junit.Assert;
import org.junit.Test;

public class MobileEncoderTest {

  @Test
  public void encodeSuccess() {
    String raw = "A ABCD";
    String result = "202223";
    MobileEncoder encoder = new MobileEncoder();
    Assert.assertEquals(result, encoder.encode(raw));

    raw = "AA BB ZZ";
    result = "2 202 209 9";
    Assert.assertEquals(result, encoder.encode(raw));

    raw = "Aa  ByB";
    result = "2 20 0292";
    Assert.assertEquals(result, encoder.encode(raw));
  }

  @Test(expected = NullPointerException.class)
  public void encodeFail() {
    MobileEncoder encoder = new MobileEncoder();
    encoder.encode(null);
  }
}