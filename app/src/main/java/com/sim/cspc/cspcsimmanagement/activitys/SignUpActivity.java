package com.sim.cspc.cspcsimmanagement.activitys;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MotionEvent;
import android.view.View;
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
import com.sim.cspc.cspcsimmanagement.utilities.CompatibilityUtility;
import com.sim.cspc.cspcsimmanagement.utilities.FontManager;
import com.sim.cspc.cspcsimmanagement.utilities.Utility;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner rolspinner, countryspinner, postalspinner, Nationalityspinner, documentTypespinner;
    private TextInputLayout input_layout_name, input_layout_middle, input_layout_surname, input_layout_rsaid, input_layout_phone, input_layout_email, input_layout_physicaladdress, input_layout_postaladdress;
    private EditText enterName, entermiddlename, entersurname, enterrsaid, enterphone, enteremail, enterphysicaladdress, enterpostaladdress;
    private Button uploadrsa, send, passwordcopybt, asylumcopybt, workperminbt, proofcompanybt;

    final private int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
    public final int REQUEST_CAMERA = 101;
    public final int SELECT_PHOTO = 102;
    private String userChoosenTask;
    private String base64Image;
    private Toolbar toolbar;
    LinearLayout rsaIdDetail, nonrsalayout, passpostLayoutdetail, asylumLayoutdetail, workpermitLayoutdetail;
    TextView passportexpDate, asylumexpDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        chechPortaitAndLandSacpe();//chech Portait And LandSacpe Orientation
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    //chech Portait And LandSacpe Orientation
    public void chechPortaitAndLandSacpe() {
        if (CompatibilityUtility.isTablet(SignUpActivity.this)) {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    private void initView() {
        Typeface materialdesignicons_font = FontManager.getFontTypefaceMaterialDesignIcons(this, "fonts/materialdesignicons-webfont.otf");
        TextView back = (TextView) toolbar.findViewById(R.id.back);
        back.setTypeface(materialdesignicons_font);
        back.setText(Html.fromHtml("&#xf30d;"));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        input_layout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        input_layout_middle = (TextInputLayout) findViewById(R.id.input_layout_middle);
        input_layout_surname = (TextInputLayout) findViewById(R.id.input_layout_surname);
        input_layout_rsaid = (TextInputLayout) findViewById(R.id.input_layout_rsaid);
        input_layout_phone = (TextInputLayout) findViewById(R.id.input_layout_phone);
        input_layout_email = (TextInputLayout) findViewById(R.id.input_layout_email);
        input_layout_physicaladdress = (TextInputLayout) findViewById(R.id.input_layout_physicaladdress);
        input_layout_postaladdress = (TextInputLayout) findViewById(R.id.input_layout_postaladdress);
        input_layout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        input_layout_name = (TextInputLayout) findViewById(R.id.input_layout_name);
        enterName = (EditText) findViewById(R.id.enterName);
        entermiddlename = (EditText) findViewById(R.id.entermiddlename);
        entersurname = (EditText) findViewById(R.id.entersurname);
        enterrsaid = (EditText) findViewById(R.id.enterrsaid);
        enterphone = (EditText) findViewById(R.id.enterphone);
        enteremail = (EditText) findViewById(R.id.enteremail);
        enterphysicaladdress = (EditText) findViewById(R.id.enterphysicaladdress);
        enterpostaladdress = (EditText) findViewById(R.id.enterpostaladdress);

        rolspinner = (Spinner) findViewById(R.id.rolspinner);
        countryspinner = (Spinner) findViewById(R.id.countryspinner);
        postalspinner = (Spinner) findViewById(R.id.postalspinner);
        Nationalityspinner = (Spinner) findViewById(R.id.Nationalityspinner);
        documentTypespinner = (Spinner) findViewById(R.id.documentTypespinner);
        // Nationalityspinner.getBackground().setColorFilter(Color.parseColor("#4a4d4e"), PorterDuff.Mode.SRC_ATOP);
        uploadrsa = (Button) findViewById(R.id.uploadrsa);
        send = (Button) findViewById(R.id.send);
        uploadrsa.setOnClickListener(this);
        send.setOnClickListener(this);

        passwordcopybt = (Button) findViewById(R.id.passwordcopybt);
        passwordcopybt.setOnClickListener(this);
        asylumcopybt = (Button) findViewById(R.id.asylumcopybt);
        asylumcopybt.setOnClickListener(this);
        workperminbt = (Button) findViewById(R.id.workperminbt);
        workperminbt.setOnClickListener(this);
        proofcompanybt = (Button) findViewById(R.id.proofcompanybt);
        proofcompanybt.setOnClickListener(this);


        rsaIdDetail = (LinearLayout) findViewById(R.id.rsaIdDetail);
        nonrsalayout = (LinearLayout) findViewById(R.id.nonrsalayout);
        passpostLayoutdetail = (LinearLayout) findViewById(R.id.passpostLayoutdetail);
        asylumLayoutdetail = (LinearLayout) findViewById(R.id.asylumLayoutdetail);
        workpermitLayoutdetail = (LinearLayout) findViewById(R.id.workpermitLayoutdetail);

        asylumexpDate = (TextView) findViewById(R.id.asylumexpDate);
        passportexpDate = (TextView) findViewById(R.id.passportexpDate);
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
                new DatePickerDialog(SignUpActivity.this, date, myCalendar
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
                new DatePickerDialog(SignUpActivity.this, asylumdate, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        setSpinerValue();
    }

    private void setSpinerValue() {
        String postal_address_array[] = {"Yes", "No"};
        String designation_array[] = {"Zoner", "Sub wholeseller", "Shop"};
        String nation_array[] = {"RSA", "Non RSA"};
        final String rsa_array[] = {"Johannesburg", "Capetown", "Durban"};
        final String nonrsa_array[] = {"Zimbabwe", "Nizeeria", "Umlanga"};
        String documenttype_array[] = {"Passport", "Asylum", "Workpermit"};


        ArrayAdapter<String> postalAdapter = new ArrayAdapter<String>(this, R.layout.spinner_row, postal_address_array);
        postalspinner.setAdapter(postalAdapter);
        postalspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> roladapter = new ArrayAdapter<String>(this, R.layout.spinner_row, designation_array);
        rolspinner.setAdapter(roladapter);
        rolspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());


        ArrayAdapter<String> nationalityspinneradapter = new ArrayAdapter<String>(this, R.layout.spinner_row, nation_array);
        Nationalityspinner.setAdapter(nationalityspinneradapter);
        Nationalityspinner.setOnItemSelectedListener(new MyOnItemSelectedListener());

        ArrayAdapter<String> documenttypespinneradapter = new ArrayAdapter<String>(this, R.layout.spinner_row, documenttype_array);
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

        ArrayAdapter<String> rsaadapter = new ArrayAdapter<String>(this, R.layout.spinner_row, rsa_array);
        countryspinner.setAdapter(rsaadapter);
        Nationalityspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView textview = view.findViewById(R.id.cust_view);
                if (textview.getText().toString().equals("RSA")) {
                    ArrayAdapter<String> rsaadapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_row, rsa_array);
                    countryspinner.setAdapter(rsaadapter);
                } else {
                    ArrayAdapter<String> rsaadapter = new ArrayAdapter<String>(SignUpActivity.this, R.layout.spinner_row, nonrsa_array);
                    countryspinner.setAdapter(rsaadapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent,
                                   View view, int pos, long id) {
            // Toast.makeText(parent.getContext(), "The planet is " +parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }

        public void onNothingSelected(AdapterView parent) {
            // Do nothing.
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.send:
                Intent intent = new Intent(SignUpActivity.this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }

    private void selectImage() {
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
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

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
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
        Uri uri = Utility.getImageUri(SignUpActivity.this, thumbnail);
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
                                ActivityCompat.requestPermissions(SignUpActivity.this, permissionsList.toArray(new String[permissionsList.size()]),
                                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
                            }
                        });
                return false;
            }
            ActivityCompat.requestPermissions(SignUpActivity.this, permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    //add run time permission
    private boolean addPermission(List<String> permissionsList, String permission) {
        if (ContextCompat.checkSelfPermission(SignUpActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission);
            // Check for Rationale Option
            if (!ActivityCompat.shouldShowRequestPermissionRationale(SignUpActivity.this, permission))
                return false;
        }
        return true;
    }

    //show permission alert
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(SignUpActivity.this)
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
                    Toast.makeText(SignUpActivity.this, "Permission is Denied", Toast.LENGTH_SHORT)
                            .show();
                }
            }
            break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //for hid keyboard when tab outside edittext box
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int) event.getRawX(), (int) event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }
}
