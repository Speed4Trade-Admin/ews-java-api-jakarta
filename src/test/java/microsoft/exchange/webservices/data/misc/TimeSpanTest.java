/*
 * The MIT License
 * Copyright (c) 2012 Microsoft Corporation
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package microsoft.exchange.webservices.data.misc;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import microsoft.exchange.webservices.base.BaseTest;

/**
 * The Class TimeSpanTest.
 */
public class TimeSpanTest extends BaseTest {

  /**
   * testTimeSpanToXSDuration
   */
  @Test
  public void testTimeSpanToXSDuration() {
    Calendar calendar = new GregorianCalendar(2008, Calendar.OCTOBER, 10);
    timeSpanToXSDuration(calendar);
  }

  /**
   * Time span to xs duration.
   *
   * @param timeSpan the time span
   * @return the string
   */
  public String timeSpanToXSDuration(Calendar timeSpan) {
    String offsetStr = (timeSpan.get(Calendar.SECOND) < 0) ? "-" : "";
    String obj = String.format("%s %s %s %s %s ", offsetStr, 
            Math.abs(timeSpan.get(Calendar.DAY_OF_MONTH)), 
            Math.abs(timeSpan.get(Calendar.HOUR_OF_DAY)), 
            Math.abs(timeSpan.get(Calendar.MINUTE)), 
            Math.abs(timeSpan.get(Calendar.SECOND)) + "." +
            Math.abs(timeSpan.get(Calendar.MILLISECOND)));

    return obj;
  }
}
