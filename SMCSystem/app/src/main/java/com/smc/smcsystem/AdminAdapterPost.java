package com.smc.smcsystem;
import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminAdapterPost extends FirebaseRecyclerAdapter<ModelPost,AdminAdapterPost.myviewholder> {
    private static final int CAMERA_REQUEST = 100;
    private static final int STORAGE_REQUEST = 200;
    String cameraPermission[];
    String storagePermission[];
    private static final int IMAGEPICK_GALLERY_REQUEST = 300;
    private static final int IMAGE_PICKCAMERA_REQUEST = 400;
    public AdminAdapterPost(@NonNull FirebaseRecyclerOptions<ModelPost> options) {
        super(options);
    }

    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView") int position, @NonNull ModelPost model)
    {
        holder.title.setText(model.getTitle());
        holder.des.setText(model.getDescription());
        holder.comment.setText(model.getPcomments());
        holder.status.setText("Status: "+model.getStatus());
        Glide.with(holder.img.getContext()).load(model.getUimage()).into(holder.img);
        holder.mascom.setEnabled(true);
       if (model.getStatus().contains("Completed"))
        {
            holder.mascom.setEnabled(false);
        }
       if(model.getStatus().contains("Completed"))
       {
           holder.status.setBackgroundResource(R.color.colorGreen);
       }
       else
       {
           holder.status.setBackgroundResource(R.color.colorRed);
       }

       holder.mascom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                        .setContentHolder(new ViewHolder(R.layout.completedmac))
                        .setExpanded(true,600)
                        .create();
                dialogPlus.show();
                View myview=dialogPlus.getHolderView();
                final EditText replymsg=myview.findViewById(R.id.edreviemsg);
               // ImageView revieimg=myview.findViewById(R.id.imgrevies);
                Button mac=myview.findViewById(R.id.btnsub);
                mac.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View view) {
                        Map<String,Object> map=new HashMap<>();
                        String selectedText=model.getStatus();
//                        int selectedId =holder.rg1.getCheckedRadioButtonId();
//                        if(selectedId!=-1)
//                        {
//                           // RadioButton rdd=view.findViewById(selectedId);
//                           // selectedText=rdd.getText().toString();
//                            selectedText="Completed";
//                        }
//                        else
//                        {
//                          //  Toast.makeText(myview.getContext(), )
//                        }
                        String de=String.valueOf(System.currentTimeMillis());
                        //String d=de.concat("Completed");
                        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
                        calendar.setTimeInMillis(Long.parseLong(de));
                        String timedate = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
                        map.put("reply",replymsg.getText().toString());
                        map.put("status","Completed on "+timedate);

                        FirebaseDatabase.getInstance().getReference().child("Posts").child(model.getPtime())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });





//                revieimg.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        String options[] = {"Camera", "Gallery"};
//                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
//                        builder.setTitle("Pick Image From");
//                        builder.setItems(options, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // check for the camera and storage permission if
//                                // not given the request for permission
//                                if (which == 0) {
//                                    boolean result = ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.CAMERA) == (PackageManager.PERMISSION_GRANTED);
//                                    boolean result1 = ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == (PackageManager.PERMISSION_GRANTED);
//
//                                    if (!result && result1) {
//
//                                    } else {
//                                        pickFromCamera();
//                                    }
//                                } else if (which == 1) {
//                                    if (!checkStoragePermission()) {
//                                        requestStoragePermission();
//                                    } else {
//                                        pickFromGallery();
//                                    }
//                                }
//                            }
//                        });
//                        builder.create().show();
//                    }
//
//                });



//                FirebaseDatabase.getInstance().getReference("Posts").child(model.getPtime()).child("status").setValue("Completed").addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        holder.status.setText("Status: Completed");
//                    }
//                });


            }

            private void requestCameraPermission() {
                //ActivityCompat.Api23Impl.requestPermissions(cameraPermission, CAMERA_REQUEST);
            }


        });
       holder.more.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final DialogPlus dialogPlus=DialogPlus.newDialog(view.getContext())
                       .setContentHolder(new ViewHolder(R.layout.complaintsdetailscard))
                       .setExpanded(true,1040)
                       .create();
               View myview=dialogPlus.getHolderView();
               final TextView uname=myview.findViewById(R.id.unameco);
               final TextView udatetime=myview.findViewById(R.id.utimeco);
               final TextView utitle=myview.findViewById(R.id.ptitleco);
               final TextView udes=myview.findViewById(R.id.descriptco);
               final TextView uadd=myview.findViewById(R.id.addofcom);
               final TextView ustatus=myview.findViewById(R.id.sta);
               final TextView utype=myview.findViewById(R.id.comptype);
               ImageView dp=myview.findViewById(R.id.pictureco);
               ImageView image=myview.findViewById(R.id.pimagetvco);
               Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
               calendar.setTimeInMillis(Long.parseLong(model.getPtime()));
               String timedate = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();
               //holder.name.setText(nameh);
               udatetime.setText(timedate);
               utitle.setText("Title:"+model.getTitle());
               uname.setText(model.getUname());
               udes.setText("Description:"+model.getDescription());
               uadd.setText(model.getPcomments());
               ustatus.setText("Status:"+model.getStatus());
               utype.setText("Type:"+model.getType());
               try {
                   Glide.with(view.getContext()).load(model.getUdp()).into(dp);
               } catch (Exception e) {
               }
               image.setVisibility(View.VISIBLE);
               try {
                   Glide.with(view.getContext()).load(model.getUimage()).into(image);
               } catch (Exception e) {
               }
               dialogPlus.show();
           }
       });

//       FirebaseDatabase fd=FirebaseDatabase.getInstance();
//       DatabaseReference dr =fd.getReference("Department");
//       List<String> data =new ArrayList<>();
//       dr.addValueEventListener(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//               for(DataSnapshot snapshot: dataSnapshot.getChildren())
//               {
//                String value=snapshot.getValue(String.class);
//                   data.add(value);
//               }
//           ArrayAdapter adapter=new ArrayAdapter(data);//<>(this.getContext(),R.layout.support_simple_spinner_dropdown_item,data);
//
//               adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
//               holder.sp.setAdapter(adapter);
//           }

//           @Override
//           public void onCancelled(@NonNull DatabaseError databaseError) {
//
//           }
//       })

    }
//

    private void requestCameraPermission() {
        //ActivityCompat.Api23Impl.requestPermissions(cameraPermission, CAMERA_REQUEST);
    }
    @NonNull
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adminpostrow,parent,false);
        return new myviewholder(view);
    }
    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView title,des,comment,status;
        Button mascom;
        Spinner sp;
        ImageButton more;
        CardView card;
        RadioButton rb1;
        RadioButton rb2;
        RadioGroup rg1;
        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            img= (ImageView) itemView.findViewById(R.id.apicturetv);
            title=(TextView)itemView.findViewById(R.id.atitletv);
            des=(TextView)itemView.findViewById(R.id.adestv);
            comment=(TextView)itemView.findViewById(R.id.addresstv);
            status=(TextView)itemView.findViewById(R.id.status);
            mascom=(Button)itemView.findViewById(R.id.btnmarkcom);
            more=(ImageButton)itemView.findViewById(R.id. morebtn);


        }
    }
}

