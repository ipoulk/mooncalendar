import csv
import mysql.connector as mysql
from pathlib import Path
import os
from dotenv import load_dotenv
from database import SQL_INSERT_DATE_RULE_TYPE,SQL_INSERT_DOCUMENT_CATEGORY, SQL_INSERT_EVENT, SQL_INSERT_EVENT_DATE_RULE, SQL_INSERT_LANGUAGE, SQL_INSERT_TRANSLATION,SQL_INSERT_FASTING_TYPE ,SQL_INSERT_EVENT_CATEGORY


# Download and install mySQL dirver:
# 1. check which environment importer.py is using with command:which python3 OR
#    check bottom right hand status bar.
# 2. check whether the MySQL driver is installed in THAT environment with : python3 -m pip show mysql-connector-python
#     if not installed run: python3 -m pip install mysql-connector-python
# 3. Verify that it is installed with: python3 -m pip show mysql-connector-python
# Note: pip is Python's package installer

# validation helper


def validate_loads(csv_path):
    pass

# translation
def translation_load(csv_path, cur):
    csv_path = Path(csv_path)

    if not csv_path.exists() or not csv_path.is_file():
        raise ValueError("Does not exist or is not a file")

    try:
        with csv_path.open("r", encoding="utf-8-sig", newline="") as file:
            data_reader = csv.reader(file)
            next(data_reader)

            for row in data_reader:   
                cur.execute(SQL_INSERT_TRANSLATION, row)
    except mysql.errors.IntegrityError as e:
        print(str(e))

# date_rule_type
def date_rule_type_load(csv_path, cur):
    csv_path = Path(csv_path)

    if not csv_path.exists() or not csv_path.is_file():
        raise ValueError("Does not exist or is not a file")

    try:
        with csv_path.open("r", encoding="utf-8-sig", newline="") as file:
            data_reader = csv.reader(file)
            next(data_reader)

            for row in data_reader:
                if row[3] in ("", "NULL", "null"):
                    row[3] = None
                else:
                    row[3] = int(row[3])
                cur.execute(SQL_INSERT_DATE_RULE_TYPE, row)
    except mysql.errors.IntegrityError as e:
        print(str(e))

# fasting type
def fasting_type_load(csv_path, cur):
    csv_path = Path(csv_path)

    if not csv_path.exists() or not csv_path.is_file():
        raise ValueError("Does not exist or is not a file")

    try:
        with csv_path.open("r", encoding="utf-8-sig", newline="") as file:
            data_reader = csv.reader(file)
            next(data_reader)

            for row in data_reader:
                if row[3] in ("", "NULL", "null"):
                    row[3] = None
                else:
                    row[3] = int(row[3])

                cur.execute(SQL_INSERT_FASTING_TYPE, row)
    except mysql.errors.IntegrityError as e:
        print(str(e))

# event_category
def event_category_load(csv_path, cur):
    csv_path = Path(csv_path)

    if not csv_path.exists() or not csv_path.is_file():
        raise ValueError("Does not exist or is not a file")

    try:
        with csv_path.open("r", encoding="utf-8-sig", newline="") as file:
            data_reader = csv.reader(file)
            next(data_reader)

            for row in data_reader:
                if row[1] in ("", "NULL", "null"):
                    row[1] = None
                else:
                    row[1] = int(row[1])

                cur.execute(SQL_INSERT_EVENT_CATEGORY, row)
    except mysql.errors.IntegrityError as e:
        print(str(e))

# event
def event_load(csv_path, cur):
    csv_path = Path(csv_path)

    if not csv_path.exists() or not csv_path.is_file():
        raise ValueError("Does not exist or is not a file")

    try:
        with csv_path.open("r", encoding="utf-8-sig", newline="") as file:
            data_reader = csv.reader(file)
            next(data_reader)
            event_id = ""
            category_id = ""
            name_translation = ""
            for row in data_reader:
                if row[3] in ("", "NULL", "null"):
                    row[3] = None
                else:
                    row[3] = int(row[3])
                event_id = row[0]
                category_id = row[3]
                name_translation = row[5]
                cur.execute(SQL_INSERT_EVENT, row)
    except mysql.errors.IntegrityError as e:
        print(
            f"foreign key missing: event: {event_id} and category id:{category_id} and translation id:{name_translation}" + str(e))

# event_date_rule
def event_date_rule_load(csv_path, cur):
    csv_path = Path(csv_path)

    if not csv_path.exists() or not csv_path.is_file():
        raise ValueError("Does not exist or is not a file")

    
    with csv_path.open("r", encoding="utf-8-sig", newline="") as file:
            data_reader = csv.reader(file)
            next(data_reader)

            for row in data_reader:
                if row[3] in ("", "NULL", "null"):
                    row[3] = None
                else:
                    row[3] = int(row[3])

                if row[5] in ("", "NULL", "null"):
                    row[5] = None
                else:
                    row[5] = int(row[5])

                if row[6] in ("", "NULL", "null"):
                    row[6] = None
                else:
                    row[6] = int(row[6])

                if row[7] in ("", "NULL", "null"):
                    row[7] = None
                else:
                    row[7] = int(row[7])

                if row[8] in ("", "NULL", "null"):
                    row[8] = None
                else:
                    row[8] = int(row[8]) 

                if row[2] in ("", "NULL", "null"):
                    row[2] = None
                else:
                    row[2] = int(row[2])    

                if row[4] in ("", "NULL", "null"):
                    row[4] = None
                else:
                    row[4] = int(row[4])   

                if row[10] in ("", "NULL", "null"):
                    row[10] = None
                else:
                    row[10] = int(row[10])  

                if row[12] in ("", "NULL", "null"):
                    row[12] = None
                else:
                    row[12] = int(row[12])   
               
                try:
                    cur.execute(SQL_INSERT_EVENT_DATE_RULE, row)
                except mysql.Error as e:
                    print(f"Failed row id={row[0]}: {e}")
                    continue
    
      
        # optionally continue to the next row
           

# language
def language_load(csv_path, cur):
    csv_path = Path(csv_path)

    if not csv_path.exists() or not csv_path.is_file():
        raise ValueError("Does not exist or is not a file")

    try:
        with csv_path.open("r", encoding="utf-8-sig", newline="") as file:
            data_reader = csv.reader(file)
            next(data_reader)

            for row in data_reader:
                if row[3] in ("", "NULL", "null"):
                    row[3] = None
                else:
                    row[3] = int(row[3])

                cur.execute(SQL_INSERT_LANGUAGE, row)
    except mysql.errors.IntegrityError as e:
        print(str(e))

# document_category
def document_category_load(csv_path, cur):
    csv_path = Path(csv_path)

    if not csv_path.exists() or not csv_path.is_file():
        raise ValueError("Does not exist or is not a file")

    try:
        with csv_path.open("r", encoding="utf-8-sig", newline="") as file:
            data_reader = csv.reader(file)
            next(data_reader)

            for row in data_reader:
                if row[3] in ("", "NULL", "null"):
                    row[3] = None
                else:
                    row[3] = int(row[3])

                cur.execute(SQL_INSERT_DOCUMENT_CATEGORY, row)
    except mysql.errors.IntegrityError as e:
        print(str(e))        

# cur.execute("SELECT VERSION()")


def main():

    # To read env KEYS and VALUES need to install package python-dotenv
    # 1. activate python environment: source .venv/bin/activate
    # 2. install package: python -m pip install python-dotenv
    # 3. Verify: python -m pip show python-dotenv
    load_dotenv()

    csv_path = os.getenv("CSV_ROOT_PATH")

    if not csv_path:
        raise ValueError("CSV_ROOT_PATH not set in environment variables")

    if not csv_path.endswith('/'):
        csv_path += '/'


    # Check directory exists:

    dir_path = Path(csv_path)

    if not dir_path.exists() or not dir_path.is_dir():
        raise ValueError("Directory does not exist or is not a directory")

    # MySQL connection notes (local Mac)
    # Workbench checks:
    # - Workbench params: Home → connection "wrench" icon → Hostname / Port / Username
    # - Database name: after connecting → "Schemas" tab (MySQL calls databases "schemas")
    #
    # Terminal checks:
    # - Is MySQL server listening (port open)?
    #     sudo lsof -nP -iTCP:3306 -sTCP:LISTEN
    # - View saved Workbench connection details (often shows host/user/port):
    #     grep -nEi "localhost|127\.0\.0\.1|port|root|user|socket" \
    #       "$HOME/Library/Application Support/MySQL/Workbench/connections.xml"
    # - View database name:
    #     mysql -h localhost -p 3306 -u <username> -p -e "SHOW DATABASES;"
    # Note: -e in mysql means "execute this SQL and then exit"
    # Note: Workbench may show/store host as "localhost" while Python can use "127.0.0.1" (both are local).
    mydb = mysql.connect(
        host=os.getenv("DB_HOST"),
        port=int(os.getenv("DB_PORT")),
        user=os.getenv("DB_USER"),
        password=os.getenv("DB_PASSWORD"),
        database=os.getenv("DB_NAME"),
    )

    # cursor executes SQL and fetches results via the connection
    cur = mydb.cursor()


    load_translation = csv_path + os.getenv("TRANSLATION_CSV")
    load_date_rule_type = csv_path + os.getenv("DATE_RULE_TYPE_CSV")
    load_fasting_type = csv_path + os.getenv("FASTING_TYPE_CSV")
    load_event_category = csv_path + os.getenv("EVENT_CATEGORY_CSV")
    load_event = csv_path + os.getenv("EVENT_CSV")
    load_event_date_rule_type= csv_path + os.getenv("EVENT_DATE_RULE_CSV")
    load_language = csv_path + os.getenv("LANGUAGE_CSV")
    load_document_category = csv_path + os.getenv("DOCUMENT_CATEGORY_CSV")

    data_paths = {load_translation:translation_load,
                  load_date_rule_type:date_rule_type_load,
                  load_fasting_type:fasting_type_load,
                  load_event_category:event_category_load,
                  load_event:event_load,
                  load_event_date_rule_type:event_date_rule_load,
                  load_language:language_load,
                  load_document_category:document_category_load
                  }
    
    # Verify if each key for each csv exist or is not empty. Then call the def for that particular path.
    # if key does not exist, ask user to input the path.NOTE:User might add nonesense.
    
    for load_path, load_function in data_paths.items():
        if load_path:
            load_function(load_path, cur)
        else:
            print(f"Key for {load_function.__name__} does not exist")

            try:
                inputfile_path = input("Enter the file path for event.csv")
                inputfile_path = inputfile_path.strip()

                if not inputfile_path:
                    print("You have entered an empty String")
                else:
                    load_function(inputfile_path, cur)
            except ValueError as e:
                print("You have entered an invalid path. It should be in the following format"
                  "/Users/<name>/folder/....csv"+str(e))

    mydb.commit()

    # print("mysql VERSION:",cur.fetchone()[0])
    cur.close()
    mydb.close()


if __name__ == '__main__':
    main()
