
    create table categories (
       id  serial not null,
        description varchar(200) not null,
        name varchar(50) not null,
        parent_categories_id int4,
        status boolean not null,
        time_create timestamp,
        time_update timestamp,
        primary key (id)
    );

    create table categories_product_model (
       id  serial not null,
        status boolean not null,
        time_create timestamp,
        time_update timestamp,
        categories_id int4,
        product_model_id uuid,
        primary key (id)
    );

    create table contact (
       id  serial not null,
        address varchar(100) not null,
        brand_story varchar(350) not null,
        contact_content varchar(250) not null,
        email varchar(50),
        facebook varchar(150),
        hotline varchar(15) not null,
        time_close time,
        time_open time,
        time_update timestamp,
        view_page int8,
        primary key (id)
    );

    create table feedback (
       id  serial not null,
        content int4,
        status int2 not null,
        time_create timestamp,
        time_update timestamp,
        user_id uuid,
        primary key (id)
    );

    create table model_image (
       id  serial not null,
        url_image varchar(150) not null,
        product_model_id uuid,
        primary key (id)
    );

    create table "order" (
       id uuid not null,
        address varchar(100) not null,
        email varchar(50),
        first_name varchar(20) not null,
        last_name varchar(20) not null,
        note varchar(100) not null,
        phone_number varchar(15) not null,
        status int2 not null,
        time_create timestamp,
        time_update timestamp,
        primary key (id)
    );

    create table order_item (
       id  serial not null,
        quantity int4 not null,
        time_create timestamp,
        order_id uuid,
        product_id uuid,
        primary key (id)
    );

    create table product (
       id uuid not null,
        avatar varchar(50),
        name varchar(50) not null,
        price_sale Decimal(10,2) default 0 not null,
        quantity int4 not null,
        sale_type varchar(15) not null,
        sold_product_quantity int4 default 0 not null,
        status boolean not null,
        time_create timestamp,
        time_update timestamp,
        product_model_id uuid,
        size_id int4,
        primary key (id)
    );

    create table product_model (
       id uuid not null,
        description varchar(200) not null,
        name varchar(50) not null,
        price_root Decimal(10,2) not null,
        status boolean not null,
        time_create timestamp,
        time_update timestamp,
        primary key (id)
    );

    create table rating (
       id  serial not null,
        content int4,
        rating int4 not null,
        status int2 not null,
        time_create timestamp,
        time_update timestamp,
        product_model_id uuid,
        user_id uuid,
        primary key (id)
    );

    create table "role" (
       id uuid not null,
        description varchar(200),
        level int2 not null,
        name varchar(50) not null,
        status int2 not null,
        primary key (id)
    );

    create table "size" (
       id  serial not null,
        name varchar(50) not null,
        status boolean not null,
        time_create timestamp,
        time_update timestamp,
        primary key (id)
    );

    create table user_info (
       id uuid not null,
        avatar varchar(35),
        email varchar(50) not null,
        first_name varchar(20) not null,
        gender boolean not null,
        last_name varchar(20) not null,
        password varchar(30) not null,
        phone_number varchar(15),
        status boolean not null,
        time_create timestamp,
        time_update timestamp,
        primary key (id)
    );

    create table user_role (
       id  serial not null,
        role_id uuid,
        user_id uuid,
        primary key (id)
    );

    create table wishlist (
       id  serial not null,
        status boolean not null,
        time_create timestamp,
        time_update timestamp,
        product_modelid uuid,
        user_id uuid,
        primary key (id)
    );

    alter table categories_product_model 
       add constraint FKkchw5m5319iymybgkyq9f591q 
       foreign key (categories_id) 
       references categories;

    alter table categories_product_model 
       add constraint FK85lp7oi93624jmhqt0w5pakcy 
       foreign key (product_model_id) 
       references product_model;

    alter table feedback 
       add constraint FKaxo7xjwxovdx3iv82efe0p4xr 
       foreign key (user_id) 
       references user_info;

    alter table model_image 
       add constraint FKiu0s3cpt2svvlmoqwhi9cv2vc 
       foreign key (product_model_id) 
       references product_model;

    alter table order_item 
       add constraint FKt6wv8m7eshksp5kp8w4b2d1dm 
       foreign key (order_id) 
       references order;

    alter table order_item 
       add constraint FK551losx9j75ss5d6bfsqvijna 
       foreign key (product_id) 
       references product;

    alter table product 
       add constraint FK3ac4n7gvn3ej9hr6h8d8pkex7 
       foreign key (product_model_id) 
       references product_model;

    alter table product 
       add constraint FKsccbu8jiglqc6t5tjsp04amv7 
       foreign key (size_id) 
       references "size";

    alter table rating 
       add constraint FKaeyym7u4he52usn16cn1fy1qe 
       foreign key (product_model_id) 
       references product_model;

    alter table rating 
       add constraint FKe6hcuecfdtxu1f7i332ahiu6y 
       foreign key (user_id) 
       references user_info;

    alter table user_role 
       add constraint FKa68196081fvovjhkek5m97n3y 
       foreign key (role_id) 
       references "role";

    alter table user_role 
       add constraint FKm90yi1ak9h9tbct25k3km0hia 
       foreign key (user_id) 
       references user_info;

    alter table wishlist 
       add constraint FKqhoc8o333aqqqp2u6rrw2ikba 
       foreign key (product_modelid) 
       references product_model;

    alter table wishlist 
       add constraint FKbrbunxhtojin0ona9ui421o0r 
       foreign key (user_id) 
       references user_info;
