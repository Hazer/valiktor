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

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.joda.time.LocalTime
import org.valiktor.i18n.formatters.DateTimeFormatter
import org.valiktor.i18n.formatters.LocalDateFormatter
import org.valiktor.i18n.formatters.LocalDateTimeFormatter
import org.valiktor.i18n.formatters.LocalTimeFormatter

/**
 * Represents the implementation for service provider interface for Joda-Time formatters
 *
 * @property formatters specifies the [Set] of Joda-Time formatters
 *
 * @author Rodolpho S. Couto
 * @since 0.6.0
 */
class JodaTimeFormatterSpi : FormatterSpi {

    override val formatters = setOf(
        DateTime::class to DateTimeFormatter,
        LocalDate::class to LocalDateFormatter,
        LocalDateTime::class to LocalDateTimeFormatter,
        LocalTime::class to LocalTimeFormatter
    )
}
