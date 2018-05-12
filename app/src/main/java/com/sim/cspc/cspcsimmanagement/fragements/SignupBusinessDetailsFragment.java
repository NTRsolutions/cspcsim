package com.sim.cspc.cspcsimmanagement.fragements;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;
import com.sim.cspc.cspcsimmanagement.utilities.Utility;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupBusinessDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupBusinessDetailsFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SignupBusinessDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupBusinessDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupBusinessDetailsFragment newInstance(String param1, String param2) {
        SignupBusinessDetailsFragment fragment = new SignupBusinessDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Context context;
    View view;
    LinearLayout nextLayout;
    private TextInputLayout input_layout_physicaladdress, input_layout_tradingname, input_layout_bname, input_layout_physicalsuburb, input_layout_physicalcity, input_layout_physicalpostalcodel;
    private EditText entertradingnamne, enterbName, enterphysicaladdress, enterphysicalsuburb, enterphysicalcity, enterphysicalpostalcode;
    private ImageView idcompanymemberImage, idphotocompanyImage, idproofaddcompanyImage, idcompanydocImage;
    private Button uploadcompanymember, uploadphotocompany, uploadaddcompny, uploadcompanydoc;

    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    public final int REQUEST_CAMERA = 101;
    public final int SELECT_PHOTO = 102;
    private String userChoosenTask;
    private String base64Image;
    private boolean compnayDocFlag = false;
    private boolean compnayAddFlag = false;
    private boolean compnayPhotoFlag = false;
    private boolean compnayMemberFlag = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_signup_business_details, container, false);
        init();
        return view;
    }

    private void init() {
        Typeface fontawesome_font = FontManager.getFontTypefaceMaterialDesignIcons(getActivity(), "fonts/fontawesome-webfont.ttf");
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(getActivity(), "fonts/materialdesignicons-webfont.otf");
        TextView previous = (TextView) view.findViewById(R.id.previous);
        previous.setTypeface(materialdesignicons_font);
        previous.setText(Html.fromHtml("&#xf141;"));
        previous.setOnClickListener(this);
        nextLayout = (LinearLayout) view.findViewById(R.id.nextLayout);
        nextLayout.setOnClickListener(this);
        TextView nextIcon = (TextView) view.findViewById(R.id.nextIcon);
        nextIcon.setTypeface(materialdesignicons_font);
        nextIcon.setText(Html.fromHtml("&#xf142;"));

        input_layout_physicaladdress = (TextInputLayout) view.findViewById(R.id.input_layout_physicaladdress);
        input_layout_physicalsuburb = (TextInputLayout) view.findViewById(R.id.input_layout_physicalsuburb);
        input_layout_physicalcity = (TextInputLayout) view.findViewById(R.id.input_layout_physicalcity);
        input_layout_physicalpostalcodel = (TextInputLayout) view.findViewById(R.id.input_layout_physicalpostalcode);
        enterphysicaladdress = (EditText) view.findViewById(R.id.enterphysicaladdress);
        enterphysicalsuburb = (EditText) view.findViewById(R.id.enterphysicalsuburb);
        enterphysicalcity = (EditText) view.findViewById(R.id.enterphysicalcity);
        enterphysicalpostalcode = (EditText) view.findViewById(R.id.enterphysicalpostalcode);
        idcompanymemberImage = (ImageView) view.findViewById(R.id.idcompanymemberImage);
        idphotocompanyImage = (ImageView) view.findViewById(R.id.idphotocompanyImage);
        idproofaddcompanyImage = (ImageView) view.findViewById(R.id.idproofaddcompanyImage);
        uploadcompanymember = (Button) view.findViewById(R.id.uploadcompanymember);
        uploadphotocompany = (Button) view.findViewById(R.id.uploadphotocompany);
        uploadaddcompny = (Button) view.findViewById(R.id.uploadaddcompny);
        uploadaddcompny.setOnClickListener(this);
        uploadcompanymember.setOnClickListener(this);
        uploadphotocompany.setOnClickListener(this);
        uploadcompanydoc = (Button) view.findViewById(R.id.uploadcompanydoc);
        uploadcompanydoc.setOnClickListener(this);
        idcompanydocImage = (ImageView) view.findViewById(R.id.idcompanydocImage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nextLayout:
                saveScreenData(true, false);
                break;
            case R.id.previous:
                saveScreenData(false, false);
                break;
            case R.id.uploadaddcompny:
                compnayDocFlag = false;
                compnayAddFlag = true;
                compnayPhotoFlag = false;
                compnayMemberFlag = false;
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;
            case R.id.uploadphotocompany:
                compnayDocFlag = false;
                compnayAddFlag = false;
                compnayPhotoFlag = true;
                compnayMemberFlag = false;
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;
            case R.id.uploadcompanymember:
                compnayDocFlag = false;
                compnayAddFlag = false;
                compnayPhotoFlag = false;
                compnayMemberFlag = true;
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;
            case R.id.uploadcompanydoc:
                compnayDocFlag = true;
                compnayAddFlag = false;
                compnayPhotoFlag = false;
                compnayMemberFlag = false;
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;

        }
    }


    private void saveScreenData(boolean NextPreviousFlag, boolean DoneFlag) {
        Intent intent = new Intent("ViewPageChange");
        intent.putExtra("NextPreviousFlag", NextPreviousFlag);
        intent.putExtra("DoneFlag", DoneFlag);
        getActivity().sendBroadcast(intent);
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Select File!");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    //open camera
    private void cameraIntent() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    //select image from android.widget.Gallery
    private void galleryIntent() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select Photo"), SELECT_PHOTO);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_PHOTO) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            }
        }
    }

    private void onSelectFromGalleryResult(Intent data) {
        Uri uri = null;
        if (data != null) {
            try {
                uri = data.getData();
                /*Bitmap bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
                final InputStream imageStream = context.getContentResolver().openInputStream(uri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);*/

                if (uri != null) {
                    if (compnayDocFlag) {
                        // idProofImage = Utility.encodeToBase64(selectedImage, Bitmap.CompressFormat.PNG, 100);
                        Picasso.with(context).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idcompanydocImage);
                    } else if (compnayAddFlag) {
                        // profileImage = Utility.encodeToBase64(selectedImage, Bitmap.CompressFormat.PNG, 100);
                        Picasso.with(context).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idproofaddcompanyImage);
                    } else if (compnayPhotoFlag) {
                        Picasso.with(context).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idphotocompanyImage);
                    } else if (compnayMemberFlag) {
                        Picasso.with(context).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idcompanymemberImage);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        Uri uri = Utility.getImageUri(context, thumbnail);
        //base64Image = Utility.encodeToBase64(thumbnail, Bitmap.CompressFormat.JPEG, 100);
        if (uri != null) {
            if (compnayDocFlag) {
                // idProofImage = Utility.encodeToBase64(selectedImage, Bitmap.CompressFormat.PNG, 100);
                Picasso.with(context).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idcompanydocImage);
            } else if (compnayAddFlag) {
                // profileImage = Utility.encodeToBase64(selectedImage, Bitmap.CompressFormat.PNG, 100);
                Picasso.with(context).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idproofaddcompanyImage);
            } else if (compnayPhotoFlag) {
                Picasso.with(context).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idphotocompanyImage);
            } else if (compnayMemberFlag) {
                Picasso.with(context).load(uri).resize(100, 80).placeholder(R.drawable.imageplaceholder).into(idcompanymemberImage);
            }
        }

    }

    //check storage and camera run time permission
    private Boolean checkRuntimePermission() {
        List<String> permissionsNeeded = new ArrayList<String>();

        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.READ_EXTERNAL_STORAGE))
            permissionsNeeded.add("Storage");
        if (!addPermission(permissionsList, Manifest.permission.CAMERA))
            permissionsNeeded.add("Camera");
       /* if (!addPermission(permissionsList, Manifest.permission.WRITE_CONTACTS))
            permissionsNeeded.add("Write Contacts");*/

        if (permissionsList.size() > 0) {
            if (permissionsNeeded.size() > 0) {
                // Need Rationale
                String message = "You need to grant access to " + permissionsNeeded.get(0);
                for (int i = 1; i < permissionsNeeded.size(); i++)
                    message = message + ", " + permissionsNeeded.get(i);
                showMessageOKCancel(message,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return false;
            }
            requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    //add run time permission
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!shouldShowRequestPermissionRationale(permission))
                return false;
        }
        return true;
    }

    //show permission alert
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .setCancelable(false)
                .create()
                .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS: {
                Map<String, Integer> perms = new HashMap<String, Integer>();
                // Initial
                perms.put(Manifest.permission.READ_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                // Fill with results
                for (int i = 0; i < permissions.length; i++)
                    perms.put(permissions[i], grantResults[i]);
                if (perms.get(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        && perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    // All Permissions Granted
                    selectImage();
                } else {
                    // Permission Denied
                    Toast.makeText(context, "Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
