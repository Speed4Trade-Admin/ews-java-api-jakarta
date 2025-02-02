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

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import microsoft.exchange.webservices.data.property.complex.time.OlsonTimeZoneDefinition;
import microsoft.exchange.webservices.data.util.TimeZoneUtils;

public class OlsonTimeZoneTest {

  @Test
  public void testOlsonTimeZoneConversion() {
    final Map<String, String> olsonTimeZoneToMsMap = TimeZoneUtils.createOlsonTimeZoneToMsMap();
    final String[] timeZoneIds = TimeZone.getAvailableIDs();
    final List<String> blacklist = Arrays.asList("Europe/Astrakhan", "America/Nuuk", "America/Punta_Arenas", 
            "Europe/Kirov", "Europe/Saratov", "Europe/Ulyanovsk");

    for (final String timeZoneId : timeZoneIds) {
      final boolean america = timeZoneId.startsWith("America");
      final boolean europe = timeZoneId.startsWith("Europe");
      final boolean africa = timeZoneId.startsWith("Africa");

      if (!blacklist.contains(timeZoneId) && (america || europe || africa)) {
        // There are a few timezones that are out of date or don't have direct microsoft mappings
        // according to the Unicode source we use so we will only test Americas, Europe and Africa.
        final TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
        final OlsonTimeZoneDefinition olsonTimeZone = new OlsonTimeZoneDefinition(timeZone);
        final String olsonTimeZoneId = olsonTimeZone.getId();

        Assertions.assertFalse(olsonTimeZoneId == null || olsonTimeZoneId.isEmpty(), "olsonTimeZoneId for " + timeZoneId + " is blank");
        Assertions.assertEquals(olsonTimeZoneToMsMap.get(timeZoneId), olsonTimeZoneId);
      }
    }
  }

}
