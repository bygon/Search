/*
 * Copyright (C) 2008 Google Inc.
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

package com.android.demo.notepad3;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NoteEdit extends Activity {
	private NotesDbAdapter mDbHelper;
	private EditText mTitleText;
	private EditText mBodyText;
	private Long mRowId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDbHelper = new NotesDbAdapter(this);

		mDbHelper.open();
		setContentView(R.layout.note_edit);
		setTitle(R.string.edit_note);

		mTitleText = (EditText) findViewById(R.id.title);
		mBodyText = (EditText) findViewById(R.id.body);

		Button confirmButton = (Button) findViewById(R.id.confirm);

		mRowId = (savedInstanceState == null) ? null
				: (Long) savedInstanceState
						.getSerializable(NotesDbAdapter.KEY_ROWID);
		if (mRowId == null) {
			Bundle extras = getIntent().getExtras();
			mRowId = extras != null ? extras.getLong(NotesDbAdapter.KEY_ROWID)
					: null;
		}

		populateFields();
		
		confirmButton.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Bundle bundle = new Bundle();

				bundle.putString(NotesDbAdapter.KEY_TITLE, mTitleText.getText()
						.toString());
				bundle.putString(NotesDbAdapter.KEY_BODY, mBodyText.getText()
						.toString());
				if (mRowId != null) {
					bundle.putLong(NotesDbAdapter.KEY_ROWID, mRowId);
				}

				Intent mIntent = new Intent();
				mIntent.putExtras(bundle);
				setResult(RESULT_OK, mIntent);
				finish();
			}

		});
	}


	private void populateFields() {
	    if (mRowId != null) {
	        Cursor note = mDbHelper.fetchNote(mRowId);
	        startManagingCursor(note);
	        mTitleText.setText(note.getString(
	                    note.getColumnIndexOrThrow(NotesDbAdapter.KEY_TITLE)));
	        mBodyText.setText(note.getString(
	                note.getColumnIndexOrThrow(NotesDbAdapter.KEY_BODY)));
	    }
	}
	
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveState();
        outState.putSerializable(NotesDbAdapter.KEY_ROWID, mRowId);
    }


    private void saveState() {
        String title = mTitleText.getText().toString();
        String body = mBodyText.getText().toString();

        if (mRowId == null) {
            long id = mDbHelper.createNote(title, body);
            if (id > 0) {
                mRowId = id;
            }
        } else {
            mDbHelper.updateNote(mRowId, title, body);
        }
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        saveState();
    }
    protected void onResume() {
        super.onResume();
        populateFields();
    }
    
}
