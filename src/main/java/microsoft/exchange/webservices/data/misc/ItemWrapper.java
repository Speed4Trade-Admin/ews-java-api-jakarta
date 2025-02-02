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

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.exception.service.local.ServiceLocalException;
import microsoft.exchange.webservices.data.core.service.item.Item;

/**
 * Represents an item Id provided by a ItemBase object.
 */
class ItemWrapper extends AbstractItemIdWrapper {

  /**
   * The ItemBase object providing the Id.
   */
  private Item item;

  /**
   * Initializes a new instance of ItemWrapper.
   *
   * @param item the item
   * @throws ServiceLocalException the service local exception
   */
  protected ItemWrapper(final Item item) throws ServiceLocalException {
    EwsUtilities.ewsAssert(item != null, "ItemWrapper.ctor", "item is null");
    EwsUtilities.ewsAssert(!item.isNew(), "ItemWrapper.ctor", "item does not have an Id");
    this.item = item;
  }

  /**
   * Obtains the ItemBase object associated with the wrapper.
   *
   * @return The ItemBase object associated with the wrapper
   */
  public Item getItem() {
    return this.item;
  }

  /**
   * Writes the Id encapsulated in the wrapper to XML.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeToXml(EwsServiceXmlWriter writer) throws Exception {
    this.item.getId().writeToXml(writer);

  }
}
