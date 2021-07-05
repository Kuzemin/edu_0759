package com.hfad.secondapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<User> userList = new ArrayList<>();  // элементы списка
    UserAdapter userAdapter;
    Button addUserBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));  // списковая компановка макета (не плиточная)
        addUserBtn = findViewById(R.id.addUserBtn);
        addUserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
                startActivity(intent);
            }
        });
    }

    private void recyclerViewInit(){
        Users users = new Users(MainActivity.this);
        userList = users.getUserList();
        userAdapter = new UserAdapter(userList);    // создание адаптера
        recyclerView.setAdapter(userAdapter);
        /*for (int i = 0; i < 100; i++) {
            User user = new User();
            user.setUserName("Пользователь №"+i);
            user.setUserLastName("Фамилия №"+i);
            users.add(user);
        }*/
    }

    @Override
    public void onResume(){
        super.onResume();
        recyclerViewInit();
    }

    private  class UserHolder extends RecyclerView.ViewHolder{      // UserHolder создаёт элемент списка
        TextView itemTextView;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {
            super(inflater.inflate(R.layout.single_item, viewGroup, false));
            // itemView - текущий layout single_item
            itemTextView = itemView.findViewById(R.id.itemTextView);
        }

        public void bind(String userString){
            itemTextView.setText(userString);
        }
    }
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{     // адаптер помещает элементы, созданные UserHolder, на экран
        ArrayList<User> users;
        public UserAdapter(ArrayList<User> users) {
            this.users = users;
        }
        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i) {  // RecyclerView вызывает этот метод, когда создаёт новый ViewHolder
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            return new UserHolder(inflater, viewGroup);     // возвращается элемент списка (пока пустой)
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) { // RecyclerView вызывает этот медот, чтобы наполнить ViewHolder данными
            User user = users.get(position);
            String userString = user.getUserName()+"\n"+user.getUserLastName();
            userHolder.bind(userString);

            TextView itemTextView = userHolder.itemTextView;
            itemTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, UserFormActivity.class);
                    intent.putExtra("ReadOnly", true);    //
                    intent.putExtra("UserName", user.getUserName());
                    intent.putExtra("UserLastName", user.getUserLastName());
                    intent.putExtra("Phone", user.getPhone());
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {         // возвращает количество элементов в списке
            return users.size();
        }
    }
}