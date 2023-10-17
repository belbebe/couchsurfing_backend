create table couchsurfing_user(
                                  user_id int not null primary key auto_increment,
                                  full_name varchar(255) not null,
                                  user_name varchar(255) not null,
                                  birth_date date not null,
                                  email_address varchar(255) not null,
                                  phone_number varchar(255) not null,
                                  user_password varchar(255) not null
);

create table leader_board(
                             lb_id int not null primary key auto_increment,
                             user_id int not null,
                             tenant_score int,
                             host_score int,
                             foreign key (user_id) references couchsurfing_user(user_id)
);

create table room(
                     room_id int not null primary key auto_increment,
                     user_id int not null,
                     address varchar(255) not null,
                     longitude float not null,
                     latitude float not null,
                     type varchar(255) not null,
                     price float not null,
                     currency varchar(255) not null,
                     max_num_of_guests int not null,
                     non_smoking boolean not null,
                     pet_friendly boolean not null,
                     air_conditioner boolean not null,
                     parking boolean not null,
                     bicylce_storage boolean not null,
                     additional_info varchar(255),
                     foreign key (user_id) references couchsurfing_user(user_id)
);

create table booking(
                        booking_id int not null primary key auto_increment,
                        user_id int not null,
                        room_id int not null,
                        start_date date not null,
                        end_date date not null,
                        payment_method varchar(255) not null,
                        approved boolean not null,
                        num_of_guests int not null,
                        additional_notes varchar(255),
                        pay_with_chores boolean not null,
                        total_price float not null ,
                        foreign key (user_id) references couchsurfing_user(user_id),
                        foreign key (room_id) references room(room_id)
);

create table chat(
                     chat_id int not null primary key auto_increment,
                     user_id_from int not null,
                     user_id_to int not null ,
                     message_time timestamp not null,
                     message varchar(1000) not null,
                     foreign key (user_id_from) references couchsurfing_user(user_id),
                     foreign key (user_id_to) references couchsurfing_user(user_id)
);