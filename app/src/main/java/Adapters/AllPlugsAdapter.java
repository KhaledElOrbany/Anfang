package Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.el3orb.anfang.R;
import com.el3orb.anfang.SinglePlugActivity;

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
            int nodeType = nodesDetails.getJSONObject(position).getInt("type");
            int plugId = nodesDetails.getJSONObject(position).getInt("id");
            String plugName = nodesDetails.getJSONObject(position).getString("nodeName");
            holder.nodeId.setText(String.valueOf(plugId));
            holder.nodeName.setText(plugName);
            holder.nodeStateSwitch.setChecked(nodesDetails.getJSONObject(position).getBoolean("nodeState"));
            holder.nodeStateSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
                PlugsUtil plugsUtil = new PlugsUtil();
                plugsUtil.setPlugsState(this.context, nodeType, plugId, isChecked);

            });
            holder.nodeCardLayout.setOnClickListener(v -> {
                Intent plugDetails = new Intent(this.context, SinglePlugActivity.class);
                plugDetails.putExtra("nodeType", nodeType);
                plugDetails.putExtra("plugId", plugId);
                plugDetails.putExtra("plugName", plugName);
                context.startActivity(plugDetails);
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
        Switch nodeStateSwitch;
        RelativeLayout nodeCardLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nodeId = itemView.findViewById(R.id.nodeId);
            nodeName = itemView.findViewById(R.id.nodeName);
            nodeStateSwitch = itemView.findViewById(R.id.nodeStateSwitch);
            nodeCardLayout = itemView.findViewById(R.id.nodeCardLayout);
        }
    }
}
