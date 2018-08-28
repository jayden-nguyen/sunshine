import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.example.android.sunshine.R;

public class SettingFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.sunshine_pref);
    }
}
