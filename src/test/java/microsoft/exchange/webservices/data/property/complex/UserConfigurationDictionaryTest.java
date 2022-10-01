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

package microsoft.exchange.webservices.data.property.complex;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import microsoft.exchange.webservices.base.BaseTest;
import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;

/**
 * Testclass for methods of UserConfigurationDictionary
 */

@ExtendWith(MockitoExtension.class)
public class UserConfigurationDictionaryTest extends BaseTest {

  /**
   * Mock for the UserConfigurationDictionary
   */
  protected UserConfigurationDictionary userConfigurationDictionary;

  @BeforeEach
  public void setup() throws Exception {
    // Initialise a UserConfigurationDictionary Testobject
    this.userConfigurationDictionary = new UserConfigurationDictionary();
  }

  /**
   * Adding a Double Value to the Dictionary witch is not allowed
   *
   * @throws Exception the exception
   */
  @Test
  public void testAddUnsupportedElementsToDictionary() throws Exception {
      Assertions.assertThrows(ServiceLocalException.class, () -> {
          this.userConfigurationDictionary.addElement("someDouble", 1.0);
      });
  }

  /**
   * testAddSupportedElementsToDictionary
   *
   * @throws Exception the exception
   */
  @Test
  public void testAddSupportedElementsToDictionary() throws Exception {
    fillDictionaryWithValidEntries();
  }

  /**
   * Fills the Dictionary with
   *
   * @throws Exception the exception
   */
  private void fillDictionaryWithValidEntries() throws Exception {
    // Adding Test Values to the Object
    final int testInt = 1;
    final long testLong = 1l;
    final String testString = "someVal";
    final String[] testStringArray = new String[] {"test1", "test2", "test3"};
    final Date testDate = new Date();
    final boolean testBoolean = true;
    final byte testByte = Byte.decode("0x10");
    final byte[] testByteArray = testString.getBytes();
    final Byte[] testByteArray2 = new Byte[testByteArray.length];
    for (int currentIndex = 0; currentIndex < testByteArray.length; currentIndex++) {
      testByteArray2[currentIndex] = testByteArray[currentIndex];
    }

    Assertions.assertNotNull(this.userConfigurationDictionary);

    this.userConfigurationDictionary.addElement("someString", testString);
    Assertions.assertTrue(this.userConfigurationDictionary.containsKey("someString"));
    Assertions.assertEquals(testString, this.userConfigurationDictionary.getElements("someString"));
    Assertions.assertTrue(this.userConfigurationDictionary.getElements("someString") instanceof String);

    this.userConfigurationDictionary.addElement("someLong", testLong);
    Assertions.assertTrue(this.userConfigurationDictionary.containsKey("someLong"));
    Assertions.assertEquals(testLong, this.userConfigurationDictionary.getElements("someLong"));
    Assertions.assertTrue(this.userConfigurationDictionary.getElements("someLong") instanceof Long);

    this.userConfigurationDictionary.addElement("someInteger", testInt);
    Assertions.assertTrue(this.userConfigurationDictionary.containsKey("someInteger"));
    Assertions.assertEquals(testInt, this.userConfigurationDictionary.getElements("someInteger"));
    Assertions.assertTrue(this.userConfigurationDictionary.getElements("someInteger") instanceof Integer);

    this.userConfigurationDictionary.addElement("someString[]", testStringArray);
    Assertions.assertTrue(this.userConfigurationDictionary.containsKey("someString[]"));
    Assertions.assertEquals(testStringArray, this.userConfigurationDictionary.getElements("someString[]"));
    Assertions.assertTrue(this.userConfigurationDictionary.getElements("someString[]") instanceof String[]);

    this.userConfigurationDictionary.addElement("someDate", testDate);
    Assertions.assertTrue(this.userConfigurationDictionary.containsKey("someDate"));
    Assertions.assertEquals(testDate, this.userConfigurationDictionary.getElements("someDate"));
    Assertions.assertTrue(this.userConfigurationDictionary.getElements("someDate") instanceof Date);

    this.userConfigurationDictionary.addElement("someBoolean", testBoolean);
    Assertions.assertTrue(this.userConfigurationDictionary.containsKey("someBoolean"));
    Assertions.assertEquals(testBoolean, this.userConfigurationDictionary.getElements("someBoolean"));
    Assertions.assertTrue(this.userConfigurationDictionary.getElements("someBoolean") instanceof Boolean);

    this.userConfigurationDictionary.addElement("someByte", testByte);
    Assertions.assertTrue(this.userConfigurationDictionary.containsKey("someByte"));
    Assertions.assertEquals(testByte, this.userConfigurationDictionary.getElements("someByte"));
    Assertions.assertTrue(this.userConfigurationDictionary.getElements("someByte") instanceof Byte);

    this.userConfigurationDictionary.addElement("someByte[]", testByteArray);
    Assertions.assertTrue(this.userConfigurationDictionary.containsKey("someByte[]"));
    Assertions.assertEquals(testByteArray, this.userConfigurationDictionary.getElements("someByte[]"));
    Assertions.assertTrue(this.userConfigurationDictionary.getElements("someByte[]") instanceof byte[]);

    this.userConfigurationDictionary.addElement("someByte2[]", testByteArray2);
    Assertions.assertTrue(this.userConfigurationDictionary.containsKey("someByte2[]"));
    Assertions.assertEquals(testByteArray2, this.userConfigurationDictionary.getElements("someByte2[]"));
    Assertions.assertTrue(this.userConfigurationDictionary.getElements("someByte2[]") instanceof Byte[]);
  }

  /**
   * Tests the Method writeElementsToXml(...)
   * with all valid Elements
   */
  @Test
  public void testWriteElementsToXml() throws Exception {
    // Mock up needed Classes
    OutputStream output = new ByteArrayOutputStream();
    EwsServiceXmlWriter testWriter = new EwsServiceXmlWriter(exchangeServiceBaseMock, output);

    // Adding Test Values to the Object
    fillDictionaryWithValidEntries();

    // Write the Elements
    this.userConfigurationDictionary.writeElementsToXml(testWriter);
  }
}
