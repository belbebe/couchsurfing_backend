create type room_type_enum as enum ('COUCH', 'ROOM', 'APARTMENT');

create type payment_method_type as enum ('CASH', 'CARD');

create type currency_enum as enum ('HUF', 'EUR', 'USD');

create table couchsurfing_user(
                                  user_id serial primary key,
                                  full_name text not null,
                                  user_name text not null,
                                  birth_date date not null,
                                  email_address text not null,
                                  phone_number text not null,
                                  user_password text not null
);

create table leader_board(
                             lb_id serial primary key,
                             user_id int not null,
                             tenant_score int,
                             host_score int,
                             foreign key (user_id) references couchsurfing_user(user_id)
);

create table room(
                     room_id serial primary key,
                     owner_id int not null,
                     address text not null,
                     longitude float not null,
                     latitude float not null,
                     room_type room_type_enum not null,
                     price float not null,
                     price_with_chores float,
                     currency currency_enum not null,
                     paying_with_chores_possible boolean not null,
                     paying_with_card_possible boolean not null,
                     paying_with_cash_possible boolean not null,
                     max_num_of_guests int not null,
                     non_smoking boolean not null,
                     pet_friendly boolean not null,
                     air_conditioner boolean not null,
                     parking boolean not null,
                     bicycle_storage boolean not null,
                     additional_info text,
                     foreign key (owner_id) references couchsurfing_user(user_id)
);

create table booking(
                        booking_id serial primary key,
                        tenant_id int not null,
                        room_id int not null,
                        start_date date not null,
                        end_date date not null,
                        payment_method payment_method_type not null,
                        num_of_guests int not null,
                        additional_notes text,
                        pay_with_chores boolean not null,
                        total_price float not null,
                        currency currency_enum not null,
                        foreign key (tenant_id) references couchsurfing_user(user_id),
                        foreign key (room_id) references room(room_id)
);

create table chat(
                     chat_id serial primary key,
                     user_id_from int not null,
                     user_id_to int not null ,
                     message_time timestamp not null,
                     message text not null,
                     foreign key (user_id_from) references couchsurfing_user(user_id),
                     foreign key (user_id_to) references couchsurfing_user(user_id)
);

/*
create table payment_method(
                                payment_method payment_method_type primary key
);

insert into payment_method values ('cash');
insert into payment_method values ('card');
 */