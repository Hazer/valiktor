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

package org.valiktor.sample

import org.valiktor.constraints.DecimalDigits
import org.valiktor.constraints.Email
import org.valiktor.constraints.Greater
import org.valiktor.constraints.Size
import org.valiktor.test.shouldFailValidation
import kotlin.test.Test

class SampleApplicationTest {

    @Test
    fun `should validate employee`() {
        shouldFailValidation<Employee> {
            Employee(
                id = -1,
                name = "aa",
                email = "aaa",
                salary = 9999.999
            )
        }.verify {
            expect(Employee::id, -1, Greater(0))
            expect(Employee::name, "aa", Size(min = 3, max = 30))
            expect(Employee::email, "aaa", Email)
            expect(Employee::salary, 9999.999, DecimalDigits(max = 2))
        }
    }
}
