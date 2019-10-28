/*
 * Copyright 2018-2019 https://www.valiktor.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.valiktor.i18n

import org.valiktor.i18n.formatters.LocalDateFormatter
import org.valiktor.i18n.formatters.LocalDateTimeFormatter
import org.valiktor.i18n.formatters.LocalTimeFormatter
import org.valiktor.i18n.formatters.OffsetDateTimeFormatter
import org.valiktor.i18n.formatters.OffsetTimeFormatter
import org.valiktor.i18n.formatters.ZonedDateTimeFormatter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.OffsetDateTime
import java.time.OffsetTime
import java.time.ZonedDateTime
import kotlin.test.Test
import kotlin.test.assertEquals

class JavaTimeFormatterSpiTest {

    @Test
    fun `should get JavaTimeFormatterSpi`() {
        assertEquals(Formatters[LocalDate::class], LocalDateFormatter)
        assertEquals(Formatters[LocalDateTime::class], LocalDateTimeFormatter)
        assertEquals(Formatters[LocalTime::class], LocalTimeFormatter)
        assertEquals(Formatters[OffsetDateTime::class], OffsetDateTimeFormatter)
        assertEquals(Formatters[OffsetTime::class], OffsetTimeFormatter)
        assertEquals(Formatters[ZonedDateTime::class], ZonedDateTimeFormatter)
    }
}
