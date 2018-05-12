package com.sim.cspc.cspcsimmanagement.fragements;


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sim.cspc.cspcsimmanagement.R;
import com.sim.cspc.cspcsimmanagement.activitys.DashboardActivity;
import com.sim.cspc.cspcsimmanagement.activitys.SignUpActivity;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;
import com.sim.cspc.cspcsimmanagement.utilities.Utility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupGeneralInformationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupGeneralInformationFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SignupGeneralInformationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupGeneralInformationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupGeneralInformationFragment newInstance(String param1, String param2) {
        SignupGeneralInformationFragment fragment = new SignupGeneralInformationFragment();
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


    private Spinner rolspinner, countryspinner, Nationalityspinner, documentTypespinner;
    private TextInputLayout input_layout_name, input_layout_middle, input_layout_surname, input_layout_rsaid;
    private EditText enterName, entermiddlename, entersurname, enterrsaid;
    private Button uploadrsa, passwordcopybt, asylumcopybt, workperminbt, proofcompanybt;

    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    public final int REQUEST_CAMERA = 101;
    public final int SELECT_PHOTO = 102;
    private String userChoosenTask;
    private String base64Image;
    private Toolbar toolbar;
    LinearLayout rsaIdDetail, nonrsalayout, passpostLayoutdetail, asylumLayoutdetail, workpermitLayoutdetail;
    TextView passportexpDate, asylumexpDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        view = inflater.inflate(R.layout.fragment_signup_general_information, container, false);
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


        input_layout_name = (TextInputLayout) view.findViewById(R.id.input_layout_name);
        input_layout_middle = (TextInputLayout) view.findViewById(R.id.input_layout_middle);
        input_layout_surname = (TextInputLayout) view.findViewById(R.id.input_layout_surname);
        input_layout_rsaid = (TextInputLayout) view.findViewById(R.id.input_layout_rsaid);

        enterName = (EditText) view.findViewById(R.id.enterName);
        entermiddlename = (EditText) view.findViewById(R.id.entermiddlename);
        entersurname = (EditText) view.findViewById(R.id.entersurname);
        enterrsaid = (EditText) view.findViewById(R.id.enterrsaid);


        rolspinner = (Spinner) view.findViewById(R.id.rolspinner);
        countryspinner = (Spinner) view.findViewById(R.id.countryspinner);

        Nationalityspinner = (Spinner) view.findViewById(R.id.Nationalityspinner);
        documentTypespinner = (Spinner) view.findViewById(R.id.documentTypespinner);
        // Nationalityspinner.getBackground().setColorFilter(Color.parseColor("#4a4d4e"), PorterDuff.Mode.SRC_ATOP);
        uploadrsa = (Button) view.findViewById(R.id.uploadrsa);
        uploadrsa.setOnClickListener(this);

        passwordcopybt = (Button) view.findViewById(R.id.passwordcopybt);
        passwordcopybt.setOnClickListener(this);
        asylumcopybt = (Button) view.findViewById(R.id.asylumcopybt);
        asylumcopybt.setOnClickListener(this);
        workperminbt = (Button) view.findViewById(R.id.workperminbt);
        workperminbt.setOnClickListener(this);
        proofcompanybt = (Button) view.findViewById(R.id.proofcompanybt);
        proofcompanybt.setOnClickListener(this);


        rsaIdDetail = (LinearLayout) view.findViewById(R.id.rsaIdDetail);
        nonrsalayout = (LinearLayout) view.findViewById(R.id.nonrsalayout);
        passpostLayoutdetail = (LinearLayout) view.findViewById(R.id.passpostLayoutdetail);
        asylumLayoutdetail = (LinearLayout) view.findViewById(R.id.asylumLayoutdetail);
        workpermitLayoutdetail = (LinearLayout) view.findViewById(R.id.workpermitLayoutdetail);

        asylumexpDate = (TextView) view.findViewById(R.id.asylumexpDate);
        passportexpDate = (TextView) view.findViewById(R.id.passportexpDate);
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        final SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                passportexpDate.setText(sdf.format(myCalendar.getTime()));
            }
        };
        passportexpDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final DatePickerDialog.OnDateSetListener asylumdate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                asylumexpDate.setText(sdf.format(myCalendar.getTime()));
            }
        };
        asylumexpDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, asylumdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        setSpinerValue();
    }

    private void setSpinerValue() {
        String designation_array[] = {"Zoner", "Sub wholeseller", "Shop"};
        String nation_array[] = {"RSA", "Non RSA"};
        final String rsa_array[] = {"Johannesburg", "Capetown", "Durban"};
        final String nonrsa_array[] = {"Zimbabwe", "Nizeeria", "Umlanga"};
        String documenttype_array[] = {"Passport", "Asylum", "Workpermit"};


        ArrayAdapter<String> roladapter = new ArrayAdapter<String>(context, R.layout.spinner_row, designation_array);
        rolspinner.setAdapter(roladapter);
        //rolspinner.setOnItemSelectedListener(new SignUpActivity.MyOnItemSelectedListener());


        ArrayAdapter<String> nationalityspinneradapter = new ArrayAdapter<String>(context, R.layout.spinner_row, nation_array);
        Nationalityspinner.setAdapter(nationalityspinneradapter);
        //Nationalityspinner.setOnItemSelectedListener(new SignUpActivity.MyOnItemSelectedListener());

        ArrayAdapter<String> documenttypespinneradapter = new ArrayAdapter<String>(context, R.layout.spinner_row, documenttype_array);
        documentTypespinner.setAdapter(documenttypespinneradapter);
        documentTypespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = view.findViewById(R.id.cust_view);
                String documentType = textview.getText().toString();
                if (documentType.equals("Passport")) {
                    passpostLayoutdetail.setVisibility(View.VISIBLE);
                    asylumLayoutdetail.setVisibility(View.GONE);
                    workpermitLayoutdetail.setVisibility(View.GONE);
                } else if (documentType.equals("Asylum")) {
                    passpostLayoutdetail.setVisibility(View.GONE);
                    asylumLayoutdetail.setVisibility(View.VISIBLE);
                    workpermitLayoutdetail.setVisibility(View.GONE);
                } else if (documentType.equals("Workpermit")) {
                    passpostLayoutdetail.setVisibility(View.GONE);
                    asylumLayoutdetail.setVisibility(View.GONE);
                    workpermitLayoutdetail.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> rsaadapter = new ArrayAdapter<String>(context, R.layout.spinner_row, rsa_array);
        countryspinner.setAdapter(rsaadapter);
        Nationalityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = view.findViewById(R.id.cust_view);
                if (textview.getText().toString().equals("RSA")) {
                    ArrayAdapter<String> rsaadapter = new ArrayAdapter<String>(context, R.layout.spinner_row, rsa_array);
                    countryspinner.setAdapter(rsaadapter);
                } else {
                    ArrayAdapter<String> rsaadapter = new ArrayAdapter<String>(context, R.layout.spinner_row, nonrsa_array);
                    countryspinner.setAdapter(rsaadapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
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

            case R.id.uploadrsa:
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;
            case R.id.passwordcopybt:
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;
            case R.id.asylumcopybt:
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;
            case R.id.workperminbt:
                if (checkRuntimePermission()) {
                    selectImage();
                }
                break;
            case R.id.proofcompanybt:
                if (checkRuntimePermission()) {
                    selectImage();
                }

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
                // Bitmap bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());
                //  base64Image = Utility.encodeToBase64(bm, Bitmap.CompressFormat.JPEG, 100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (uri != null) {
            // profile_image.setImageURI(uri);
            //  Picasso.with(context).load(uri).into(image);
            // addUriAsFile(uri);
        }
    }

    private void onCaptureImageResult(Intent data) {

        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        Uri uri = Utility.getImageUri(context, thumbnail);
        base64Image = Utility.encodeToBase64(thumbnail, Bitmap.CompressFormat.JPEG, 100);
        if (uri != null) {
            // profile_image.setImageURI(uri);
            // Picasso.with(this).load(uri).into(image);
            // addUriAsFile(uri);
        }
     /*   ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        //thumbnail.compress(Bitmap.CompressFormat.JPEG, 100, bytes);

        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");

        FileOutputStream fo;
        try {
            destination.createNewFile();
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

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
