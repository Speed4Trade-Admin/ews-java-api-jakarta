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

package microsoft.exchange.webservices.data.core.service.items;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsInstanceOf;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import microsoft.exchange.webservices.base.BaseTest;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceObjectPropertyException;
import microsoft.exchange.webservices.data.core.service.item.Task;

/**
 * Testclass for methods of Task
 */
public class TaskTest extends BaseTest {

  /**
   * Mock for the Task
   */
  private Task taskMock;

  /**
   * Setup Mocks
   *
   * @throws Exception the exception
   */
  @BeforeEach
  public void setup() throws Exception {
    this.taskMock = new Task(TaskTest.exchangeServiceMock);
  }

  /**
   * Test for reading the value before it is assigned
   *
   * @throws Exception the exception
   */
  @Test
  public void testInitialValuePercent() throws Exception {
      Assertions.assertThrows(ServiceObjectPropertyException.class, () -> {
          taskMock.getPercentComplete();
      });
  }

  /**
   * Test for adding 0.0 as percentCompleted
   *
   * @throws Exception the exception
   */
  @Test
  public void testAddZeroPercent() throws Exception {
    final Double targetValue = 0.0;
    taskMock.setPercentComplete(targetValue);

    assertThat(taskMock.getPercentComplete(), IsNot.not(IsNull.nullValue()));
    assertThat(taskMock.getPercentComplete(), IsInstanceOf.instanceOf(Double.class));
    assertThat(taskMock.getPercentComplete(), IsEqual.equalTo(targetValue));
  }

  /**
   * Test for adding 100.0 as percentCompleted
   *
   * @throws Exception the exception
   */
  @Test
  public void testAdd100Percent() throws Exception {
    final Double targetValue = 100.0;
    taskMock.setPercentComplete(targetValue);

    assertThat(taskMock.getPercentComplete(), IsNot.not(IsNull.nullValue()));
    assertThat(taskMock.getPercentComplete(), IsInstanceOf.instanceOf(Double.class));
    assertThat(taskMock.getPercentComplete(), IsEqual.equalTo(targetValue));
  }

  /**
   * Test for adding Double.MAX_VALUE as percentCompleted
   *
   * @throws Exception the exception
   */
  @Test
  public void testAddMaxDoublePercent() throws Exception {
      Assertions.assertThrows(IllegalArgumentException.class, () -> {
          final Double targetValue = Double.MAX_VALUE;
          taskMock.setPercentComplete(targetValue);
      });
  }

  /**
   * Test for adding -0.1 as percentCompleted
   *
   * @throws Exception the exception
   */
  @Test
  public void testAddInvalidPercent() throws Exception {
      Assertions.assertThrows(IllegalArgumentException.class, () -> {
          final Double targetValue = -0.1;
          taskMock.setPercentComplete(targetValue);
      });
  }

  /**
   * Test for adding +100.1 as percentCompleted
   *
   * @throws Exception the exception
   */
  @Test
  public void testAddInvalidPercent2() throws Exception {
      Assertions.assertThrows(IllegalArgumentException.class, () -> {
          final Double targetValue = +100.1;
          taskMock.setPercentComplete(targetValue);
      });
  }

  /**
   * Test for adding Double.NaN as percentCompleted
   *
   * @throws Exception the exception
   */
  @Test
  public void testAddNanDoublePercent() throws Exception {
      Assertions.assertThrows(IllegalArgumentException.class, () -> {
          final Double targetValue = Double.NaN; // closest Value to Zero
          taskMock.setPercentComplete(targetValue);
      });
  }

  /**
   * Test for checking if the value changes in case of a thrown exception
   *
   * @throws Exception the exception
   */
  @Test
  public void testDontChangeValueOnException() throws Exception {
    // set valid value
    final Double targetValue = 50.5;
    taskMock.setPercentComplete(targetValue);

    assertThat(taskMock.getPercentComplete(), IsNot.not(IsNull.nullValue()));
    assertThat(taskMock.getPercentComplete(), IsInstanceOf.instanceOf(Double.class));
    assertThat(taskMock.getPercentComplete(), IsEqual.equalTo(targetValue));

    final Double invalidValue = -0.1;
    try {
      taskMock.setPercentComplete(invalidValue);
    } catch (IllegalArgumentException ex) {
      // ignored
    }

    assertThat(taskMock.getPercentComplete(), IsNot.not(IsNull.nullValue()));
    assertThat(taskMock.getPercentComplete(), IsInstanceOf.instanceOf(Double.class));
    assertThat(taskMock.getPercentComplete(), IsEqual.equalTo(targetValue));
  }
}
