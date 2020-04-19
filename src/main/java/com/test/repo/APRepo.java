package com.test.repo;



import java.io.IOException;
import java.io.InputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.test.models.APModel;

	public class APRepo {
		APModel ap= new APModel();
		public APRepo(String key) throws IOException{
			
			JSONObject dataset;
			InputStream datais = null;
			try {
				String dataFileName = "validation/data.json";
				datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
				JSONTokener tokener = new JSONTokener(datais);
				dataset = new JSONObject(tokener);
			} catch(Exception e) {
				e.printStackTrace();
				throw e;
			} finally {
				if(datais != null) {
					datais.close();
				}
			}
			
			ap.setModel(dataset.getJSONObject(key).getString("model"));
			ap.setSerial(dataset.getJSONObject(key).getString("serial"));
			ap.setUsage(dataset.getJSONObject(key).getString("usage"));
			ap.setNofClients(dataset.getJSONObject(key).getString("nofClients"));
		}
		
		public APModel getAP(){
			
			return ap;
		}
		
	}