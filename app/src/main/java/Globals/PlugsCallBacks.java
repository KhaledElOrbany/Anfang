package Globals;

import com.google.firebase.database.DataSnapshot;

public interface PlugsCallBacks {
    void setPlugs(Iterable<DataSnapshot> children);
}
