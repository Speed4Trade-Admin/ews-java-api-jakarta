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

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ComplexPropertyCollectionTest {

  @Test
  public void testComplexPropertyChangedPositive() {
    final ComplexPropertyCollection<ComplexProperty> collection = createFakeComplexPropertyCollection();

    final ComplexProperty property = createFakeComplexProperty();
    collection.complexPropertyChanged(property);

    final List<ComplexProperty> modifiedItems = collection.getModifiedItems();
    Assertions.assertTrue(collection.getAddedItems().isEmpty());
    Assertions.assertTrue(modifiedItems.contains(property));
    Assertions.assertEquals(1, modifiedItems.size());
  }

  @Test
  public void testComplexPropertyChangedNegative() {
      Assertions.assertThrows(RuntimeException.class, () -> {      
          final ComplexPropertyCollection<ComplexProperty> collection = createFakeComplexPropertyCollection();
          collection.complexPropertyChanged(null);
      });
  }


  private ComplexProperty createFakeComplexProperty() {
    return new ComplexProperty() {};
  }

  private ComplexPropertyCollection<ComplexProperty> createFakeComplexPropertyCollection() {
    return new ComplexPropertyCollection<ComplexProperty>() {
      @Override protected ComplexProperty createComplexProperty(final String xmlElementName) {
        return null;
      }
      @Override protected String getCollectionItemXmlElementName(final ComplexProperty complexProperty) {
        return null;
      }
    };
  }

}
