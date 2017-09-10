package fragmentlab.ian.fragmentlab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;

import static android.R.attr.data;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OrdersEntryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OrdersEntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersEntryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /** MY VARIABLES **/
    ArrayList<Order> CandyOrders = new ArrayList<>();
    private EditText firstName;
    private EditText lastName;
    private Spinner chocolateType;
    private EditText numberOfBars;
    private RadioGroup shipping;
    private RadioButton normalShipping;
    private RadioButton expeditedShipping;
    private Button completeOrderBtn;
    private TextView orderStatusDisplay;
    private View view;

    private OnFragmentInteractionListener mListener;

    public OrdersEntryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersEntryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersEntryFragment newInstance(String param1, String param2) {
        OrdersEntryFragment fragment = new OrdersEntryFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_blank, container, false);
        // set var to findViewById's of each el
        firstName = view.findViewById(R.id.editTextFName);
        lastName = view.findViewById(R.id.editTextLName);
        chocolateType =  view.findViewById(R.id.spinnerChocolateType);
        numberOfBars =  view.findViewById(R.id.editTextNumOfBars);
        shipping =  view.findViewById(R.id.radioGroupShipping);
        normalShipping =  view.findViewById(R.id.radioButtonNormShipping);
        expeditedShipping =  view.findViewById(R.id.radioButtonExpShipping);
        completeOrderBtn =  view.findViewById(R.id.buttonSave);



        completeOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = firstName.getText().toString();
                String lName = lastName.getText().toString();
                String chocType = chocolateType.getSelectedItem().toString();
                int numOfBars = Integer.valueOf(numberOfBars.getText().toString());
                boolean shippingType;

                if (normalShipping.isChecked()) {
                    shippingType = true;
                } else {
                    shippingType = false;
                }


                Order newOrder = new Order();
                newOrder.setFirstName(fName);
                newOrder.setLastName(lName);
                newOrder.setChocolateType(chocType);
                newOrder.setNumOfBarsPurchased(numOfBars);
                newOrder.setShippingType(shippingType);

                MainActivity.CandyOrders.add(newOrder);


                onButtonPressed(Uri.parse("There are now " + MainActivity.CandyOrders.size() + " orders"));

                firstName.setText("");
                lastName.setText("");
                chocolateType.setSelection(0);
                numberOfBars.setText("");
                normalShipping.setChecked(true);



            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void LoadFragmentData(Order order){
        String fname = order.getFirstName();
        String lname = order.getLastName();
        String chocolatetype = order.getChocolateType();
        String numOfBars = String.valueOf(order.getNumOfBarsPurchased());
        boolean shippingType = order.getShippingType();

        firstName.setText(fname);
        lastName.setText(lname);

        numberOfBars.setText(numOfBars);

        if(chocolatetype.equals("Milk Chocolate")) {
            int pos = 0;
            chocolateType.setSelection(pos);
        } else if (chocolatetype.equals("Dark Chocolate")) {
            int pos = 1;
            chocolateType.setSelection(pos);
        } else {
            int pos = 2;
            chocolateType.setSelection(pos);
        }

        if (shippingType == true) {
            normalShipping.setChecked(true);
        }
        else {
            expeditedShipping.setChecked(true);
        }



    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
