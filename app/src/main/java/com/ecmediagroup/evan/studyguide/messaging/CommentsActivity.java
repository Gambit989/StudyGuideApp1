/*
 * Copyright 2016 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ecmediagroup.evan.studyguide.messaging;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;

import com.ecmediagroup.evan.studyguide.R;

public class CommentsActivity extends AppCompatActivity {
    public static final String POST_KEY_EXTRA = "post_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        String postKey = getIntent().getStringExtra(POST_KEY_EXTRA);
        if (postKey == null) {
            finish();
        }

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.comments_fragment, CommentsFragment.newInstance(postKey))
                .commit();
    }

}