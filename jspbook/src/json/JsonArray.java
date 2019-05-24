package json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonArray {

	public static void main(String[] args) {
		System.out.println("----------------------------");
		System.out.println("JSON String 생성");
		
		JSONObject jObj = new JSONObject();
		
		JSONArray jArray = new JSONArray();
		
		JSONObject school = new JSONObject();
		school.put("subject", "math");
		jArray.add(school);
		
		school = new JSONObject();
		school.put("subject", "국어");
		jArray.add(school);
		
		jObj.put("school", jArray);
		
		System.out.println(jObj.toString());
		
		System.out.println("----------------------------");
		System.out.println("JSON String 파싱");
		try {
			JSONArray returnSchool = (JSONArray) jObj.get("school");
			System.out.println("Size= " +returnSchool.size());
			for(int i=0; i<returnSchool.size(); i++) {
				JSONObject returnSubject = (JSONObject) returnSchool.get(i);
				System.out.println("subject :  " + returnSubject.get("subject"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("----------------------------");

	}

}
