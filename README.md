# SQlite Database
 Easy to create and manage SQLiteDatabase 
====

````
 private static final String DATABASE_NAME = "data.db";
 
 //Create main folder
 File appDirectory = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/MainFoldername/");

            if (!appDirectory.exists()) {
                appDirectory.mkdir();
            }

//create database folder
            File dbDirectory = new File(
                    Environment.getExternalStorageDirectory().getAbsolutePath() + "/MainFoldername/DatabaseFolder/");
            if (!dbDirectory.exists()) {
                dbDirectory.mkdirs();
            }
            //open database
            DataBase db = new DataBase(getApplicationContext());
            db.open();
            
     // Insert Query       
            ContentValues values = new ContentValues();
            values.put(DataBaseField.TYPE_ID, 1);
            values.put(DataBaseField.TYPE_NAME, "name");
            db.insert(DataBase.tbl_type, values);
            
            //Update Query
            values.put(DataBaseField.TYPE_NAME, "bank");
                                    db.update(DataBase.tbl_type, values, "TYPE_ID='" + 1 + "'");
                                    db.close();
                                    
            //Delete Query
            db.delete(DataBase.tbl_type, "TYPE_ID='" + 1 + "'", null); 
                              
            //fetch Query
                              
             Cursor allcategory = db.fetch(DataBase.tbl_type, "TYPE_NAME = '" + "name" + "'");
                    if (allcategory != null) {
                        if (allcategory.moveToFirst()) {
                            do {
                                String catid = allcategory.getString(0);
                               int pos = Integer.parseInt(catid);
                            } while (allcategory.moveToNext());
                        }
                    }
           
           //select data query with order
             String sortOrder = DataBaseField.BANK_NAME + " COLLATE NOCASE ASC";
             Cursor catcursor = db.select(DataBase.tbl_type, new String[]{"TYPE_NAME"}, DataBaseField.BANK_ID + " != 0", null, null, sortOrder);
                    
             //Fetch All data query
              Cursor catcursor = db.fetchAll(DataBase.tbl_bank);
                    
            db.close();
            
            

