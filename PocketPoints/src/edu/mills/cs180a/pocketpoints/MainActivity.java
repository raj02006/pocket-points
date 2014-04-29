package edu.mills.cs180a.pocketpoints;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Activity that decides which fragment to display.
 *
 * @author chingmyu@gmail.com (Ching Yu)
 */
public class MainActivity extends Activity
	implements ClassListFragment.OnStudentSelectedListener,
	EditClassListFragment.OnEditStudentSelectedListener {
    private static final String TAG = "MainActivity";
    
    private FragmentManager mFragmentManager;
    private Fragment mEditStudentFragment;
    private Fragment mClassListFragment;
    private Fragment mEditClasslistFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 // Get references to fragment manager and fragments.
        mFragmentManager = getFragmentManager();
        mEditStudentFragment = mFragmentManager.findFragmentById(R.id.editStudentFragment);
        mClassListFragment = mFragmentManager.findFragmentById(R.id.fragment_classlist);
        mEditClasslistFragment = mFragmentManager.findFragmentById(R.id.editClasslistFragment);
        
        mFragmentManager.beginTransaction()
		.hide(mEditStudentFragment)
		.hide(mEditClasslistFragment)
		.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.classlist_options, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_edit_students:
			mFragmentManager.beginTransaction()
			.hide(mClassListFragment)
			.show(mEditClasslistFragment)
			.commit();
			return true;
		case R.id.menu_item_add_student:
			// Display the EditStudentActivity.
			// TODO: Not yet implemented.
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onStudentSelected(Student student) {
		// Display the StudentStickerFragment.
		// TODO: Not yet implemented.
	}

	@Override
	public void onEditStudentSelected(Student student) {
		// Display the EditStudentFragment.
		mFragmentManager.beginTransaction()
		.hide(mClassListFragment)
		.show(mEditStudentFragment)
		.commit();
		
		// Show the current person.
        ((EditStudentFragment) mEditStudentFragment).setStudent(student.getId());
	}
}
