
drop table if exists sub_process;
drop table if exists yangshi_process;
drop table if exists yangshi_prescription;
drop table if exists address;
drop table if exists general_prescription;
drop table if exists patient;


/*==============================================================*/
/* Table : patient                                       */
/*==============================================================*/
CREATE TABLE if NOT EXISTS patient
(
   id                             bigint                         NOT NULL auto_increment,
   first_name                     varchar(60)                    NOT NULL,
   last_name                      varchar(60)                    NOT NULL,
   gender                         varchar(10)                    NOT NULL,
   age                            int                            NOT NULL,
   email                          varchar(50),
   phone                          varchar(50),                  
   main_problem                   varchar(300),                   
   current_sick_history           varchar(300),                   
   family_sick_history            varchar(300),                    
   diagnose                       varchar(300),                     
   therapy                        varchar(300),                     
   inspect                        varchar(300),  
   createddate                    datetime                      NOT NULL,
   PRIMARY KEY (id)
)AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table : address                                             */
/*==============================================================*/
CREATE TABLE if NOT EXISTS address
(
   id                             bigint NOT NULL auto_increment,
   street_address                 varchar(100),
   city                           varchar(40),
   province                       varchar(40),
   county                         varchar(80),
   postal_code                    varchar(10),
   patient_id                      bigint,
   PRIMARY KEY (id),
   CONSTRAINT FK_ADDRESS_REFERENCE_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
) AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8;


/*==============================================================*/
/* Table : general_prescription                                  */
/*==============================================================*/
CREATE TABLE if NOT EXISTS general_prescription
(
   id                             bigint                         NOT NULL auto_increment,
   prescription_text              varchar(300)                   NOT NULL,
   created_date                   datetime                       NOT NULL,
   patient_id                      bigint                         NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT FK_GENERAL_PRESCRIPTION_REFERENCE_patient FOREIGN KEY (patient_id) REFERENCES patient(id)
) AUTO_INCREMENT=200000 DEFAULT CHARSET=utf8;



/*==============================================================*/
/* Table : yangshi_prescription                                  */
/*==============================================================*/
CREATE TABLE if NOT EXISTS yangshi_prescription
(
   id                             bigint                         NOT NULL auto_increment,
   prescription_text              varchar(300),
   created_date                   datetime                       NOT NULL,
   prescription_name              varchar(80)                    NOT NULL,
   PRIMARY KEY (id)
) AUTO_INCREMENT=300000 DEFAULT CHARSET=utf8;



/*==============================================================*/
/* Table : yangshi_Process                                       */
/*==============================================================*/
CREATE TABLE if NOT EXISTS yangshi_process
(
   id                             bigint                         NOT NULL auto_increment,
   order_num                      int                            NOT NULL,
   finished                      TINYINT(1)                      NOT NULL,
   patient_id                      bigint                         NOT NULL,
   yangshi_prescription_id        bigint,
   PRIMARY KEY (id),
   CONSTRAINT FK_YANGSHI_PROCESS_REFERENCE_patient FOREIGN KEY (patient_id) REFERENCES patient(id),
   CONSTRAINT FK_YANGSHI_PROCESS_REFERENCE_YANGSHI_PRESCRIPTION FOREIGN KEY (yangshi_prescription_id ) REFERENCES yangshi_prescription(id)
)  AUTO_INCREMENT=400000 DEFAULT CHARSET=utf8;

/*==============================================================*/
/* Table : sub_process                                           */
/*==============================================================*/
CREATE TABLE if NOT EXISTS sub_process
(
   id                             bigint                         NOT NULL auto_increment,
   order_num                      int                            NOT NULL,
   process_value                  varchar(10),
   created_date                   datetime                       NOT NULL,
   yangshi_process_id             bigint                         NOT NULL,
   PRIMARY KEY (id),
   CONSTRAINT FK_SUB_PROCESS_REFERENCE_YANGSHI_PROCESS FOREIGN KEY (yangshi_process_id) REFERENCES yangshi_process(id)
)  AUTO_INCREMENT=500000 DEFAULT CHARSET=utf8;


INSERT INTO patient (first_name,  last_name, gender, age,  email, phone, main_problem,current_sick_history, family_sick_history, diagnose,  therapy,inspect ,createddate   )
      VALUES ("tom", "liu", "male", "20","tom@gmail.com", "12345678", "main problem", "sick history", "family sick history", "diagonse" ,"therapy", "inspect", now());
INSERT INTO address (street_address, city, county, province, postal_code, patient_id)
      VALUES ("123 main street", "tom city", "some county", "some province", "123456",  LAST_INSERT_ID());
      
INSERT INTO patient (first_name,  last_name, gender, age,  email, phone, main_problem,current_sick_history, family_sick_history, diagnose,  therapy,inspect,createddate    )
      VALUES ("alex", "wang", "female", "2e","alex@gmail.com", "3323232", "main problem", "sick history", "family sick history", "diagonse" ,"therapy", "inspect", now());
INSERT INTO address (street_address, city, county, province, postal_code, patient_id)
      VALUES ("155 main street", "alex city", "alex county", "alex province", "343434", LAST_INSERT_ID());


