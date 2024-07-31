--회원관리 (탈퇴해도 사라지지 않고 평생 보관)
create table zmember(
 mem_num number not null,
 id varchar2(12) unique not null,
 auth number(1) default 2 not null, --회원 등급:0탈퇴회원,1정지회원,2일반회원,9관리자
 constraint zmember_pk primary key (mem_num)
);
--회원상세정보 (탈퇴하면 지워버리려고 한다.)
create table zmember_detail(
 mem_num number not null,
 name varchar2(30) not null,
 passwd varchar2(12) not null,
 phone varchar2(15) not null,
 email varchar2(50) not null,
 zipcode varchar2(5) not null,
 address1 varchar2(90) not null,
 address2 varchar2(90) not null,
 photo varchar2(400),
 reg_date date default sysdate not null,
 modify_date date, --수정 날짜
 constraint zmember_detail_pk primary key (mem_num),
 constraint zmember_detail_fk foreign key (mem_num) references zmember (mem_num)
);
create sequence zmember_seq;

--게시판
create table zboard(
 board_num number not null,
 title varchar2(150) not null,
 content clob not null,
 hit number(9) default 0 not null,
 reg_date date default sysdate not null,
 modify_date date,
 filename varchar2(400),
 ip varchar2(40) not null,
 mem_num number not null,
 constraint zboard_pk primary key (board_num),
 constraint zboard_fk foreign key (mem_num) references zmember (mem_num)
);
create sequence zboard_seq;

--좋아요
create table zboard_fav(
 board_num number not null,
 mem_num number not null,
 constraint zboard_fav_fk1 foreign key (board_num) references zboard (board_num),
 constraint zobard_fav_fk2 foreign key (mem_num) references zmember (mem_num)
);

--댓글
create table zboard_reply(
 re_num number not null,
 re_content varchar2(900) not null,
 re_date date default sysdate not null,
 re_modifydate date,
 re_ip varchar2(40) not null,
 board_num number not null,
 mem_num number not null,
 constraint zreply_pk primary key (re_num),
 constraint zreply_fk1 foreign key (board_num) references zboard (board_num),
 constraint zreply_fk2 foreign key (mem_num) references zmember (mem_num)
);
create sequence zreply_seq;

--상품
create table zitem(
 item_num number not null,
 name varchar2(30) not null,
 price number(9) not null,
 quantity number(7) not null,
 photo1 varchar2(400) not null,
 photo2 varchar2(400) not null,
 detail clob not null,
 reg_date date default sysdate not null,
 modify_date date,
 status number(1) not null, --표시 여부(판매 가능 여부) 1:미표시, 2:표시
 constraint zitem_pk primary key (item_num)
);
create sequence zitem_seq;

--장바구니
create table zcart(
 cart_num number,
 item_num number not null,
 order_quantity number(7) not null,
 reg_date date default sysdate not null,
 mem_num number not null, -- 상품 구매자 회원번호
 constraint zcart_pk primary key (cart_num),
 constraint zcart_fk1 foreign key (item_num) references zitem (item_num),
 constraint zcart_fk2 foreign key (mem_num) references zmember (mem_num)
);
create sequence zcart_seq;

--주문
create table zorder(
 order_num number not null,
 order_total number(9) not null,
 payment number(1) not null, --결제방식
 status number(1) default 1 not null, --배송 상태
 receive_name varchar2(30) not null,
 receive_post varchar2(5) not null,
 receive_address1 varchar2(90) not null,
 receive_address2 varchar2(90) not null,
 receive_phone varchar2(15) not null,
 notice varchar2(4000),
 reg_date date default sysdate not null,
 modify_date date,
 mem_num number not null, --구매자
 constraint zorder_pk primary key (order_num),
 constraint zorder_fk foreign key (mem_num) references zmember(mem_num)
);
create sequence zorder_seq;

create table zorder_detail(
 detail_num number not null,
 item_num number not null,
 item_name varchar2(30) not null,
 item_price number(9) not null,
 item_total number(9) not null,
 order_quantity number(7) not null,
 order_num number not null,
 constraint zorder_detail_pk primary key (detail_num),
 constraint zorder_detail_fk foreign key (order_num) references zorder (order_num)
);
create sequence zorder_detail_seq;