import csv
import mysql.connector as mysql
from pathlib import Path
import os
from dotenv import load_dotenv

# Download and install mySQL dirver:
# 1. check which environment importer.py is using with command:which python OR
#    check bottom right hand status bar.
# 2. check whether the MySQL droiver is install in THAT environment with : python -m pip show mysql-connector-python
#     if not installed run: python -m pip install mysql-connector-python
# 3. Verify that it is installed with: python -m pip show mysql-connector-python
# Note: pip is Python's package installer

# validation helper
def validate_loads(csv_path):
  pass

# translation
def translation_load(csv_path,cur):
  csv_path=Path(csv_path)

  if not csv_path.exists() or not csv_path.is_file():
    raise ValueError("Does not exist or is not a file")
  
  try:
    with csv_path.open("r",encoding="utf-8-sig",newline="") as file:
      data_reader = csv.reader(file)
      next(data_reader)

      for row in data_reader:
          cur.execute("INSERT INTO translation(id, en, de, description, comment) VALUES (%s,%s,%s,%s,%s)", row)
  except mysql.errors.IntegrityError as e:
    print(str(e))

# event_category
def event_category_load(csv_path,cur):
  csv_path=Path(csv_path)

  if not csv_path.exists() or not csv_path.is_file():
    raise ValueError("Does not exist or is not a file")
  
  try:
    with csv_path.open("r",encoding="utf-8-sig",newline="") as file:
      data_reader = csv.reader(file)
      next(data_reader)

      for row in data_reader:
          cur.execute("INSERT INTO event_category(id, parent_category_id, code, name, name_translation_id,description,description_translation_id,comment,comment_translation_id) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s)", row)
  except mysql.errors.IntegrityError as e:
    print(str(e))    
  
# event  
def event_load(csv_path,cur):
  csv_path=Path(csv_path)
  
  if not csv_path.exists() or not csv_path.is_file():
    raise ValueError("Does not exist or is not a file")
  
  try:
    with csv_path.open("r",encoding="utf-8-sig",newline="") as file:
      data_reader = csv.reader(file)
      next(data_reader)
      event_id=""
      category_id=""
      name_translation=""
      for row in data_reader:
         event_id=row[0]
         category_id=row[3]
         name_translation=row[5]
         cur.execute("INSERT INTO event(id, code, is_season, event_category_id, name, name_translation_id, description, description_translation_id, comment, comment_translation_id) VALUES (%s,%s,%s,%s,%s,%s,%s,%s,%s,%s)", row)
  except mysql.errors.IntegrityError as e:
    print(f"foreign key missing: event: {event_id} and category id:{category_id} and translation id:{name_translation}"+ str(e))

    
# cur.execute("SELECT VERSION()")


def main():

  # To read env KEYS and VALUES need to install package python-dotenv
  # 1. activate python environment: source .venv/bin/activate
  # 2. install package: python -m pip install python-dotenv
  # 3. Verify: python -m pip show python-dotenv
  load_dotenv()

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
   
  load_translation=os.getenv("TRANSLATION_CSV")
  load_event_category=os.getenv("EVENT_CATEGORY_CSV")
  load_event= os.getenv("EVENT_CSV")
  

  # Verify if each key for each csv exist or is not empty. Then call the def for that particular path.
  # if key does not exist, ask user to input the path.NOTE:User might add rubbish.
  if load_event:
    translation_load(load_translation,cur)
    event_category_load(load_event_category,cur)
    event_load(load_event,cur)
  else:
    print("Key does not exist")
    try:
      inputfile_path=input("Enter the file path for event.csv")
      inputfile_path=inputfile_path.strip()
      if not inputfile_path:
        print("You have entered an empty String")
      else:
        event_load(inputfile_path,cur)
    except ValueError as e:
      print("You have entered an invalid path. It should be in the following format" \
      "/Users/<name>/folder/....csv"+str(e))

  mydb.commit()

  #print("mysql VERSION:",cur.fetchone()[0])
  cur.close()
  mydb.close()
   


if __name__ == '__main__':
  main()



