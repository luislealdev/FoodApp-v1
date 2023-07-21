package luisrrleal.com.foodapp_v1;

import androidx.annotation.Nullable;
import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.rxjava3.RxDataStore;

import java.util.Map;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Single;

public class UserDataStore {
    RxDataStore<Preferences> datastore;
    private static final UserDataStore ourInstance = new UserDataStore();

    public static UserDataStore getInstance() {
        return ourInstance;
    }
    private UserDataStore() { }
    public void setDataStore(RxDataStore<Preferences> datastore) {
        this.datastore = datastore;
    }
    public RxDataStore<Preferences> getDataStore() {
        return datastore;
    }

    public boolean putStringValue(String Key, String value){
        boolean returnvalue;
        Preferences.Key<String> DATA_KEY = PreferencesKeys.stringKey(Key);

        @NonNull Preferences pref_error = new Preferences() {
            @Override
            public <T> boolean contains(@androidx.annotation.NonNull Preferences.Key<T> key) {
                return false;
            }

            @Nullable
            @Override
            public <T> T get(@androidx.annotation.NonNull Preferences.Key<T> key) {
                return null;
            }

            @androidx.annotation.NonNull
            @Override
            public Map<Preferences.Key<?>, Object> asMap() {
                return null;
            }
        };

        Single<Preferences> updateResult =  datastore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.set(DATA_KEY, value);
            return Single.just(mutablePreferences);
        }).onErrorReturnItem(pref_error);
        returnvalue = updateResult.blockingGet() != pref_error;
        return returnvalue;
    }

    public String getStringValue(String Key) {
        Preferences.Key<String> DATA_KEY = PreferencesKeys.stringKey(Key);
        Single<String> value = datastore.data().firstOrError().map(prefs -> prefs.get(DATA_KEY)).onErrorReturnItem("null");
        return value.blockingGet();
    }
}
