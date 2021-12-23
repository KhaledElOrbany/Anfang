package Utilities;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Globals.PlugsCallBacks;

public class PlugsUtil {

    FirebaseDatabase rootRef = FirebaseDatabase.getInstance();
    DatabaseReference prayerRef = rootRef.getReference();
    private final DatabaseReference plugs = prayerRef.child("/hm01/nodes/plugs");
    //TODO: Search about queries

    public void getPlugs(PlugsCallBacks callbacks) {
        plugs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                callbacks.setPlugs(dataSnapshot.getChildren());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void setPlugsState(String plugId, boolean state) {
        if (state) {
            plugs.child(plugId).child("state").setValue(1);
        } else {
            plugs.child(plugId).child("state").setValue(0);
        }
    }
}
