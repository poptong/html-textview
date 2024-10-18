/*
 * Copyright (C) 2016 Andhie Wong <andhiewong@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sufficientlysecure.htmltextview.example

import android.app.Activity
import android.os.Bundle
import org.sufficientlysecure.htmltextview.DrawTableLinkSpan
import org.sufficientlysecure.htmltextview.example.databinding.ActivityDataBindingExampleBinding

class DataBindingExampleActivity: Activity() {

        private lateinit var binding: ActivityDataBindingExampleBinding

        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityDataBindingExampleBinding.inflate(layoutInflater)
                setContentView(binding.root)


                // in XML we declared a variable newsItem, data binding generated the set method
                // once set, all fields/values/views are updated accordingly
                binding.htmlText.setHtml("<p>Interdum et malesuada <b>some bold text in here</b> fames ac ante ipsum primis in faucibus.</p>")

                // if you have set an android:id in XML, data binding do the 'findViewById()'
                val drawTableLinkSpan = DrawTableLinkSpan()
                drawTableLinkSpan.tableLinkText = "[tap for table]"
                binding.htmlText.setDrawTableLinkSpan(drawTableLinkSpan)
        }
}
