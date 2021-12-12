package Utilities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Globals.PrayersCallbacks;

public class PrayersUtil {
    FirebaseDatabase rootRef = FirebaseDatabase.getInstance();
    DatabaseReference prayerRef = rootRef.getReference();
    private final DatabaseReference prayersDate = prayerRef.child("/hm01/prayers/date");
    private final DatabaseReference prayersTimes = prayerRef.child("/hm01/prayers/times");

    public void getPrayersDetails(PrayersCallbacks callbacks) {
        prayersDate.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                callbacks.setPrayersDate(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        prayersTimes.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                callbacks.setPrayersTimes(dataSnapshot.getChildren());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}
