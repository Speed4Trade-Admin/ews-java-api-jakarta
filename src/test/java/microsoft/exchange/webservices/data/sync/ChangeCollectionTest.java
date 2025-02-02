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

package microsoft.exchange.webservices.data.sync;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class) public class ChangeCollectionTest {

  private static final String STATE = "SOME_STATE";
  @Mock Change change0;
  @Mock Change change1;
  @Mock Change change2;

  ChangeCollection<Change> impl;
  @InjectMocks ChangeCollection<Change> spiedImpl;

  @Mock(name = "changes") List<Change> innerList;


  @BeforeEach public void setUp() throws Exception {

    impl = new ChangeCollection<Change>();
  }

  @Test public void testAdd() throws Exception {

    assertEquals(impl.getCount(), 0);
    impl.add(change0);
    assertEquals(1, impl.getCount());
    impl.add(change1);
    assertEquals(2, impl.getCount());

  }


  @Test public void testGetChangeAtIndex() throws Exception {
    assertEquals(impl.getCount(), 0);
    impl.add(change0);
    impl.add(change1);
    impl.add(change2);
    assertSame(change0, impl.getChangeAtIndex(0));
    assertSame(change1, impl.getChangeAtIndex(1));
    assertSame(change2, impl.getChangeAtIndex(2));

  }

  @Test
  public void testGetChangeAtIndexThrowsIndexOutOfBoundException() throws Exception {
      Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
        assertEquals(impl.getCount(), 0);
        impl.add(change0);
        impl.add(change1);
        impl.add(change2);
    
        impl.getChangeAtIndex(99);
      });
  }

  @Test public void testGetSyncState() throws Exception {

    impl.setSyncState(STATE);
    assertSame(STATE, impl.getSyncState());

  }


  @Test public void testGetMoreChangesAvailable() throws Exception {
    impl.setMoreChangesAvailable(true);
    assertTrue(impl.getMoreChangesAvailable());

    impl.setMoreChangesAvailable(false);
    assertFalse(impl.getMoreChangesAvailable());
  }

  @Test public void testIterator() throws Exception {
    spiedImpl.iterator();

    verify(innerList).iterator();
  }
}
