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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.Header;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;

public class ByteArrayOSRequestEntity extends BasicHttpEntity {

  private ByteArrayOutputStream os = null;

  /**
   * Constructor for ByteArrayOSRequestEntity.
   * @param os the stream
   */
  public ByteArrayOSRequestEntity(OutputStream os) {
    super();
    this.os = (ByteArrayOutputStream) os;
  }

  @Override
  public long getContentLength() {
    return os.size();
  }

  @Override
  public Header getContentType() {
    return new BasicHeader("Content-Type", "text/xml; charset=utf-8");
  }

  @Override
  public boolean isRepeatable() {
    return true;
  }

  @Override
  public void writeTo(OutputStream out) throws IOException {
    os.writeTo(out);
  }

  @Override
  public boolean isStreaming() {
    return false;
  }
}
