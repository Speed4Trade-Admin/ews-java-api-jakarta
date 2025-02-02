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

package microsoft.exchange.webservices.data.core.request;

import java.util.ArrayList;
import java.util.List;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.response.DelegateManagementResponse;
import microsoft.exchange.webservices.data.property.complex.UserId;

/**
 * Represents a RemoveDelete request.
 */
public class RemoveDelegateRequest extends
    DelegateManagementRequestBase<DelegateManagementResponse> {

  /**
   * The user ids.
   */
  private List<UserId> userIds = new ArrayList<UserId>();

  /**
   * Initializes a new instance of the class.
   *
   * @param service the service
   * @throws Exception the exception
   */
  public RemoveDelegateRequest(ExchangeService service)
      throws Exception {
    super(service);
  }

  /**
   * Asserts the valid.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParamCollection(this.getUserIds().iterator(), "UserIds");
  }

  /**
   * Asserts the valid.
   *
   * @param writer the writer
   * @throws Exception the exception
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer)
      throws Exception {
    super.writeElementsToXml(writer);
    writer
        .writeStartElement(XmlNamespace.Messages,
            XmlElementNames.UserIds);

    for (UserId userId : this.getUserIds()) {
      userId.writeToXml(writer, XmlElementNames.UserId);
    }

    writer.writeEndElement(); // UserIds
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return XML element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.RemoveDelegateResponse;
  }

  /**
   * Gets the name of the XML element.
   *
   * @return XML element name
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.RemoveDelegate;
  }

  /**
   * Creates the response.
   *
   * @return Service response
   */
  @Override
  protected DelegateManagementResponse createResponse() {
    return new DelegateManagementResponse(false, null);
  }

  /**
   * Gets the request version.
   *
   * @return Earliest Exchange version in which this request is supported.
   */
  @Override
  protected ExchangeVersion getMinimumRequiredServerVersion() {
    return ExchangeVersion.Exchange2007_SP1;
  }

  /**
   * Gets the user ids.
   *
   * @return the user ids
   */
  public List<UserId> getUserIds() {
    return this.userIds;
  }
}
