package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = inflater.inflate(R.layout.plug_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        try {
            int id = nodesDetails.getJSONObject(position).getInt("id");
            holder.nodeId.setText(String.valueOf(id));
            holder.nodeName.setText(nodesDetails.getJSONObject(position).getString("nodeName"));
            holder.nodeState.setChecked(nodesDetails.getJSONObject(position).getBoolean("nodeState"));
            holder.nodeState.setOnCheckedChangeListener((buttonView, isChecked) -> {
                PlugsUtil plugsUtil = new PlugsUtil();
                plugsUtil.setPlugsState(this.context, id, isChecked);

            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return count;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nodeName, nodeId;
        Switch nodeState;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nodeId = itemView.findViewById(R.id.plugId);
            nodeName = itemView.findViewById(R.id.plugName);
            nodeState = itemView.findViewById(R.id.switchPlug);
        }
    }
}
