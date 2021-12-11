package Globals;

import com.google.firebase.database.DataSnapshot;

public interface Callbacks {
    void setPrayersTimes(Iterable<DataSnapshot> children);
}
