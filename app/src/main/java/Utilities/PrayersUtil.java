package Utilities;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

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

    public void fetchData() {
        String url = "https://www.sabah.com.tr/sakarya-namaz-vakitleri";

        try {
            // date = soup.find('div', {'class','date'})
            // prayers = soup.find('div', {'class','vakitler boxShadowSet'}).findAll('li')
            Document document = Jsoup.connect(url).get();
            Elements prayers = document.getElementsByClass("vakitler boxShadowSet");
            System.out.println(prayers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
