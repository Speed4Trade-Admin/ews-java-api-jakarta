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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import microsoft.exchange.webservices.data.core.EwsServiceXmlWriter;
import microsoft.exchange.webservices.data.core.EwsUtilities;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.XmlElementNames;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.misc.XmlNamespace;
import microsoft.exchange.webservices.data.core.enumeration.service.error.ServiceErrorHandling;
import microsoft.exchange.webservices.data.core.response.ServiceResponse;
import microsoft.exchange.webservices.data.misc.FolderIdWrapperList;

/**
 * Represents a DeleteFolder request.
 */
public final class DeleteFolderRequest extends DeleteRequest<ServiceResponse> {
  private static final Log LOG = LogFactory.getLog(DeleteFolderRequest.class);
  /**
   * The folder ids.
   */
  private FolderIdWrapperList folderIds = new FolderIdWrapperList();

  /**
   * Initializes a new instance of the DeleteFolderRequest class.
   *
   * @param service           the service
   * @param errorHandlingMode the error handling mode
   * @throws Exception the exception
   */
  public DeleteFolderRequest(ExchangeService service, ServiceErrorHandling errorHandlingMode)
      throws Exception {
    super(service, errorHandlingMode);
  }

  /**
   * Validate request.
   *
   * @throws Exception the exception
   */
  @Override
  protected void validate() throws Exception {
    super.validate();
    EwsUtilities.validateParam(this.getFolderIds(), "FolderIds");
    this.getFolderIds().validate(
        this.getService().getRequestedServerVersion());
  }

  /**
   * Gets the expected response message count.
   *
   * @return Number of expected response messages.
   */
  @Override
  protected int getExpectedResponseMessageCount() {
    return this.getFolderIds().getCount();
  }

  /**
   * Creates the service response.
   *
   * @param service       The service.
   * @param responseIndex Index of the response.
   * @return Service object.
   */
  @Override
  protected ServiceResponse createServiceResponse(ExchangeService service,
      int responseIndex) {
    return new ServiceResponse();
  }

  /**
   * Gets the name of the XML element.
   *
   * @return Xml element name
   */
  @Override public String getXmlElementName() {
    return XmlElementNames.DeleteFolder;
  }

  /**
   * Gets the name of the response XML element.
   *
   * @return Xml element name
   */
  @Override
  protected String getResponseXmlElementName() {
    return XmlElementNames.DeleteFolderResponse;
  }

  /**
   * Gets the name of the response message XML element.
   *
   * @return Xml element name
   */
  @Override
  protected String getResponseMessageXmlElementName() {
    return XmlElementNames.DeleteFolderResponseMessage;
  }

  /**
   * Writes XML elements.
   *
   * @param writer The writer
   */
  @Override
  protected void writeElementsToXml(EwsServiceXmlWriter writer) {
    try {
      this.getFolderIds().writeToXml(writer, XmlNamespace.Messages,
          XmlElementNames.FolderIds);
    } catch (Exception e) {
      LOG.error(e);
    }
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
   * Gets the folder ids.
   *
   * @return The folder ids.
   */
  public FolderIdWrapperList getFolderIds() {
    return this.folderIds;
  }

}
