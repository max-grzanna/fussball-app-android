package de.eahjena.app.wi.fussball;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TableAdapter extends RecyclerView.Adapter<TableAdapter.TableHolder>{
    Context context;
    List<TableTeam> tableTeamList;

    public TableAdapter(Context context, List<TableTeam> tableTeamList) {
        this.context = context;
        this.tableTeamList = tableTeamList;
    }

    @NonNull
    @Override
    public TableHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater gets a single table row to parse data to -> links teamtable_row to view
        View view = LayoutInflater.from( parent.getContext() ).inflate(R.layout.teamtable_row_layout, parent, false);
        TableHolder tableHolder = new TableHolder(view);
        return tableHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TableHolder holder, int position) {
        holder.text_Place.setText((position+1)+""); // increase position because the starting position is 0
        // ivLogo set the image
        AssetManager assetManager = context.getAssets();
        try
        {
            InputStream in = assetManager.open(""+this.tableTeamList.get(position).teamIconUrl.toLowerCase());
            // load image as Drawable
            Drawable d = Drawable.createFromStream(in, null);
            // set image to ImageView
            holder.image_Logo.setImageDrawable(d);
            in.close();
        }
        catch(IOException ex)
        {
            return;
        }

        holder.text_Team.setText(""+this.tableTeamList.get(position).teamName.toString());
        holder.text_Goals.setText("+"+this.tableTeamList.get(position).goals);
        holder.text_Points.setText(""+this.tableTeamList.get(position).points);
        holder.text_Matches.setText(""+this.tableTeamList.get(position).matches);

    }

    @Override
    public int getItemCount() {
        return tableTeamList.size();
    }

    public static class TableHolder extends RecyclerView.ViewHolder {
        TextView text_Place;
        ImageView image_Logo;
        TextView text_Team;
        TextView text_Goals;
        TextView text_Points;
        TextView text_Matches;

        public TableHolder(@NonNull View itemView) {
            super(itemView);
            text_Place = itemView.findViewById(R.id.tv_place);
            image_Logo =  itemView.findViewById(R.id.iv_logo);
            text_Team = itemView.findViewById(R.id.tv_team);
            text_Goals = itemView.findViewById(R.id.tv_goals);
            text_Points = itemView.findViewById((R.id.tv_points));
            text_Matches = itemView.findViewById((R.id.tv_matches));
        }
    }

}


