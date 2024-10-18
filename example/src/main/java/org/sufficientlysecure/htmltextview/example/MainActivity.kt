/*
 * Copyright (C) 2013-2016 Dominik Schürmann <dominik@schuermann.eu>
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

import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.sufficientlysecure.htmltextview.ClickableTableSpan
import org.sufficientlysecure.htmltextview.DrawTableLinkSpan
import org.sufficientlysecure.htmltextview.HtmlResImageGetter
import org.sufficientlysecure.htmltextview.example.WebViewActivity.Companion.EXTRA_TABLE_HTML
import org.sufficientlysecure.htmltextview.example.databinding.ActivityMainBinding


class MainActivity: AppCompatActivity() {

        private lateinit var binding: ActivityMainBinding



        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                binding = ActivityMainBinding.inflate(layoutInflater)
                setContentView(binding.root)


                //text.setRemoveFromHtmlSpace(false); // default is true
                binding.htmlText.setClickableTableSpan(ClickableTableSpanImpl())
                val drawTableLinkSpan = DrawTableLinkSpan()
                drawTableLinkSpan.tableLinkText = "[tap for table]";
                binding.htmlText.setDrawTableLinkSpan(drawTableLinkSpan);


                // Best to use indentation that matches screen density.
                val metrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(metrics);
                binding.htmlText.setListIndentPx(metrics.density * 10);

                // a tag click listener
                binding.htmlText.setOnClickATagListener { widget, spannedText, href ->
                        Toast.makeText(this@MainActivity, href.toString(), Toast.LENGTH_SHORT).show()
                        false
                }
                binding.htmlText.blockQuoteBackgroundColor = getResources().getColor(R.color.whitish)
                binding.htmlText.blockQuoteStripColor = getResources().getColor(R.color.blue)

                binding.htmlText.setHtml(R.raw.example, HtmlResImageGetter(baseContext))
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
                menuInflater.inflate(R.menu.menu, menu)
                return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.action_view_data_binding) {
                        val intent = Intent(this@MainActivity, DataBindingExampleActivity::class.java)
                        startActivity(intent)
                        return true
                }
                return super.onOptionsItemSelected(item)
        }




        // The html table(s) are individually passed through to the ClickableTableSpan implementation
        // presumably for a WebView activity.
        inner class ClickableTableSpanImpl: ClickableTableSpan() {
                override fun newInstance(): ClickableTableSpan {
                        return ClickableTableSpanImpl()
                }

                override fun onClick(widget: View) {
                        val intent = Intent(this@MainActivity, WebViewActivity::class.java)
                        intent.putExtra(EXTRA_TABLE_HTML, getTableHtml())
                        startActivity(intent);
                }
        }
}
