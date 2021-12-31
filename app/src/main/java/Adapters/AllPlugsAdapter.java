package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.el3orb.anfang.AllPlugsActivity;
import com.el3orb.anfang.R;

import org.json.JSONArray;
import org.json.JSONException;

import Utilities.PlugsUtil;

public class AllPlugsAdapter extends RecyclerView.Adapter<AllPlugsAdapter.MyViewHolder> {
    int count;
    Context context;
    JSONArray nodesDetails;

    public AllPlugsAdapter(Context context, JSONArray nodesDetails) {
        this.context = context;
        this.nodesDetails = nodesDetails;
        this.count = nodesDetails.length();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.plug_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            holder.nodeName.setText(nodesDetails.getJSONObject(position).getString("nodeName"));
            holder.nodeState.setChecked(nodesDetails.getJSONObject(position).getBoolean("nodeState"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nodeName;
        Switch nodeState;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nodeName = itemView.findViewById(R.id.plugName);
            nodeState = itemView.findViewById(R.id.switchPlug);
            nodeState.setOnCheckedChangeListener((buttonView, isChecked) -> {
//                PlugsUtil plugsUtil = new PlugsUtil();
//                plugsUtil.setPlugsState(AllPlugsActivity.this, plugId, isChecked);
            });
        }
    }
}
