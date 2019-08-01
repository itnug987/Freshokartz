package com.freshokartz;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.freshokartz.Product.Result;
import com.github.aakira.expandablelayout.ExpandableRelativeLayout;

import java.util.List;

public class ProAdapter extends RecyclerView.Adapter<ProAdapter.ProAdapterViewHolder> {

    String[] data;
    private Context context;
    private List<Result> items;

    public ProAdapter(Context context, List<Result> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ProAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_layout, viewGroup, false);
        return new ProAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProAdapterViewHolder proAdapterViewHolder, final int i) {
         Result result = items.get(i);
        proAdapterViewHolder.textView.setText(result.getProductName());
        proAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("incre","sdssd");
                String skId = items.get(i).getSku();
                Intent gintent = new Intent(context, ProductsPrime.class);
                gintent.putExtra("skId",skId);
                context.startActivity(gintent);
            }
        });
        proAdapterViewHolder.price.setText(String.valueOf("Rs. " + result.getPrice()));
        String url = "http://10.0.2.2:8000" + (String) result.getProductImage();
        Glide.with(context)
                .load(url)
                //.apply(new RequestOptions().override(140, 140))
                .into(proAdapterViewHolder.imageView);
        Log.i("pred","csdfsf");


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ProAdapterViewHolder extends RecyclerView.ViewHolder {
        ExpandableRelativeLayout relativeLayout;
        boolean invalidQuantity=false;
        boolean checkOfUnknown;
        TextView textView, price;
        double finalValue;
        ImageView imageView, plus, minus, dropd;
        EditText incre;
        Button buyNow;

        Spinner spinner;

        public ProAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            relativeLayout = itemView.findViewById(R.id.out);
            buyNow = itemView.findViewById(R.id.buynow);
            dropd = itemView.findViewById(R.id.dropd);
            buyNow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!relativeLayout.isExpanded()) {
                        dropd.setImageResource(R.drawable.upmain);
                        relativeLayout.expand();
                    } else if (relativeLayout.isExpanded()) {
                        dropd.setImageResource(R.drawable.maindown);
                        relativeLayout.collapse();
                    }
                }
            });
            textView = itemView.findViewById(R.id.text);
            incre = itemView.findViewById(R.id.incre);

            incre.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        double gtr = Double.parseDouble(s.toString());
                        if (gtr > 10) {
                            s.replace(0, s.length(), "10");
                        }
                    } catch (NumberFormatException e) {
                    }

                    try {
                        double krt = Double.parseDouble(s.toString());
                        String numberD = String.valueOf(krt);
                        numberD = numberD.substring(numberD.indexOf("."));
                        System.out.println(numberD);

                        Log.i("numbers", numberD);
                        if (!numberD.equals(".5") && !numberD.equals(".25") && !numberD.equals(".75") && !numberD.equals(".0") ) {
                            Log.i("nbeers", numberD);
                            incre.setError("Write quantity like 0.25,1, 1.5, 1.75 ",null);
                            buyNow.setEnabled(false);
                        }
                        else {
                            buyNow.setEnabled(true);
                            //invalidQuantity=false;
                            incre.setError(null);
                        }
                    } catch (Exception e) {
                    }

                }
            });

            plus = itemView.findViewById(R.id.plus);
            minus = itemView.findViewById(R.id.minus);
            price = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.img_product);
            spinner = itemView.findViewById(R.id.spin);
            String[] items = new String[]{"Kg"};
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(itemView.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
            spinner.setAdapter(adapter);



            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = incre.getText().toString();
                    try {
                        finalValue = Double.parseDouble(value);
                        double gra = (int) finalValue;
                        if (!checkOfUnknown) {
                            finalValue = gra;
                        }
                        finalValue = finalValue + 0.250;
                        incre.setText(String.valueOf(finalValue));
                        checkOfUnknown = true;
                    } catch (Exception e) {
                    }
                }
            });

            minus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String value = incre.getText().toString();
                    try {
                        finalValue = Double.parseDouble(value);
                        double gra = (int) finalValue;
                        if (!checkOfUnknown) {
                            finalValue = gra;
                            finalValue = finalValue + 0.250;
                        }
                        finalValue = finalValue - 0.250;
                        if (finalValue > 0) {
                            incre.setText(String.valueOf(finalValue));
                        }
                        checkOfUnknown = true;
                    } catch (Exception e) {
                    }
                }
            });


//            plus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String value = incre.getText().toString();
//                    finalValue = Integer.parseInt(value);
//                    finalValue++;
//                    incre.setText(String.valueOf(finalValue));
//                }
//            });
//
//            minus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    String value = incre.getText().toString();
//                    finalValue = Integer.parseInt(value);
//                    finalValue--;
//                    if (finalValue > 0)
//                        incre.setText(String.valueOf(finalValue));
//                }
//            });
        }

    }


}
