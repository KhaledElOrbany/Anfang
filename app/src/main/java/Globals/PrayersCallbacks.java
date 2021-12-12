package Globals;

import com.google.firebase.database.DataSnapshot;

public interface PrayersCallbacks {
    void setPrayersDate(DataSnapshot child);
    void setPrayersTimes(Iterable<DataSnapshot> children);
}
